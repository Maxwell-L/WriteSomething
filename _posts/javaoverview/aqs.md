## **AQS源码阅读**
-------

* **AbstractQueuedSynchronizer** 是**J.U.C**提供的一套锁机制，**AQS**将每一条请求共享资源的线程封装成一个Node节点，通过**CLH**队列、volatile修饰的state变量来实现锁的分配。
* **AQS**定义了两种资源共享方式，以不同方式共享资源的锁自定义各自的同步器:
    + Exclusive: 独占 -> **ReentrantLock**
    + Share: 共享 -> **Semaphore**、**CountDownLatch**、

``` java
// jdk1.8 AbstractQueuedSynchronizer.java
static final class Node {
    /** Marker to indicate a node is waiting in shared mode */
    static final Node SHARED = new Node();
    /** Marker to indicate a node is waiting in exclusive mode */
    static final Node EXCLUSIVE = null;
    ...
}
/**
 * The synchronization state.
 */
private volatile int state;
```

* 对于一个请求共享资源的线程，**AQS**将其封装成一个**AQS**中的静态内部类**Node**对象，存进**CLH**队列的队尾。  

``` java
/**
 * Creates and enqueues node for current thread and given mode.
 *
 * @param mode Node.EXCLUSIVE for exclusive, Node.SHARED for shared
 * @return the new node
 */
private Node addWaiter(Node mode) {
    // 将当前线程封装为Node对象, mode为共享或独占模式
    Node node = new Node(Thread.currentThread(), mode);
    // Try the fast path of enq; backup to full enq on failure
    Node pred = tail;
    if (pred != null) {
        node.prev = pred;
        // 通过CAS将node设置为队尾, 处理双向链表的指针, 返回node
        if (compareAndSetTail(pred, node)) {
            pred.next = node;
            return node;
        }
    }
    // 若队列为空, 执行enq(node)方法插入节点
    enq(node);
    return node;
}

/**
 * Inserts node into queue, initializing if necessary. See picture above.
 * @param node the node to insert
 * @return node's predecessor
 */
private Node enq(final Node node) {
    for (;;) {
        Node t = tail;
        // 若队列为空
        if (t == null) { // Must initialize
            // 新建头节点, 因为AQS中的head,tail采用了懒加载模式
            if (compareAndSetHead(new Node()))
                tail = head;
        } else {
            // 将节点从尾部插入, 设置成功后返回node
            node.prev = t;
            if (compareAndSetTail(t, node)) {
                t.next = node;
                return t;
            }
        }
    }
}
```

* **AQS** 通过 *acquire(int arg)* 方法尝试抢占资源

``` java
/**
 * Acquires in exclusive mode, ignoring interrupts.  Implemented
 * by invoking at least once {@link #tryAcquire},
 * returning on success.  Otherwise the thread is queued, possibly
 * repeatedly blocking and unblocking, invoking {@link
 * #tryAcquire} until success.  This method can be used
 * to implement method {@link Lock#lock}.
 *
 * @param arg the acquire argument.  This value is conveyed to
 *        {@link #tryAcquire} but is otherwise uninterpreted and
 *        can represent anything you like.
 */
public final void acquire(int arg) {
    // tryAcquire(arg)由同步器根据需要各自实现
    // 获取锁失败时将当前线程封装为独占模式的Node存入CLH队列并不断竞争锁, 若此过程该线程被中断过则返回true, 否则返回false, 竞争过程不处理中断
    // 如果在竞争锁过程中被中断过, 则在此中断
    if (!tryAcquire(arg) &&
        acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
        selfInterrupt();
}
```

### **ReentrantLock实现的同步器**

![图片加载失败](https://maxwell-blog.cn/image/aqs1.jpg)

* **ReentrantLock** 的共享资源模式为独占，其静态内部抽象类**Sync**继承于**AQS**，基于此的公平锁和非公平锁继承了**Sync**，实现了各自抢占资源的方法。

``` java
/**
 * Sync object for non-fair locks
 */
static final class NonfairSync extends Sync {
    private static final long serialVersionUID = 7316153563782823691L;

    /**
     * Performs lock.  Try immediate barge, backing up to normal
     * acquire on failure.
     */
    final void lock() {
        // 非公平锁先尝试抢占资源, 通过CAS设置state, 若成功即把当前线程设置为独占线程, 否则
        if (compareAndSetState(0, 1))
            setExclusiveOwnerThread(Thread.currentThread());
        else
            acquire(1);
    }

    protected final boolean tryAcquire(int acquires) {
        return nonfairTryAcquire(acquires);
    }
}

/**
 * Performs non-fair tryLock.  tryAcquire is implemented in
 * subclasses, but both need nonfair try for trylock method.
 */
final boolean nonfairTryAcquire(int acquires) {
    final Thread current = Thread.currentThread();
    int c = getState();
    // 若c(state)为0, 则当前锁没有被锁定, 获取锁并且将占有线程设置为当前线程
    if (c == 0) {
        if (compareAndSetState(0, acquires)) {
            setExclusiveOwnerThread(current);
            return true;
        }
    }
    // state不为0但占有线程是当前线程, 说明是重入, state增加即可
    else if (current == getExclusiveOwnerThread()) {
        int nextc = c + acquires;
        if (nextc < 0) // overflow
            throw new Error("Maximum lock count exceeded");
        setState(nextc);
        return true;
    }
    // 竞争锁失败返回false
    return false;
}
        
/**
 * Sync object for fair locks
 */
static final class FairSync extends Sync {
    private static final long serialVersionUID = -3000897897090466540L;

    final void lock() {
        acquire(1);
    }

    /**
     * Fair version of tryAcquire.  Don't grant access unless
     * recursive call or no waiters or is first.
     */
    protected final boolean tryAcquire(int acquires) {
        final Thread current = Thread.currentThread();
        int c = getState();
        // 当前锁没被占有, 尝试
        if (c == 0) {
            // 判断是否有前面的节点在抢占锁
            // 没有则可以尝试抢占锁(公平)
            if (!hasQueuedPredecessors() &&
                compareAndSetState(0, acquires)) {
                setExclusiveOwnerThread(current);
                return true;
            }
        }
        // 占有锁线程是当前线程, 重入
        else if (current == getExclusiveOwnerThread()) {
            int nextc = c + acquires;
            if (nextc < 0)
                throw new Error("Maximum lock count exceeded");
            setState(nextc);
            return true;
        }
        return false;
    }
}
```


### **ReentrantReadWriteLock实现的同步器**





[返回首页](http://maxwell-blog.cn)