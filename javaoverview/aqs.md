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


### **ReentrantLock实现的同步器**
![图片加载失败](https://maxwell-l.github.io/WriteSomething/image/aqs1.jpg)









[返回首页](http://maxwell-l.github.io/WriteSomething)