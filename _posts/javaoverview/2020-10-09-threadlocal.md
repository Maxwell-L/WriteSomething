---
layout: post
title: "ThreadLocal源码阅读"
date: 2020-10-09
categories: Java
---

* **ThreadLocal** 提供了线程独有的局部变量，每个线程都可以通过 `set` 和 `get` 来修改或获取且不会和其它线程的操作产生冲突，实现线程间的数据隔离。

### **ThreadLocal原理**
![图片加载失败](https://maxwell-blog.cn/image/threadlocal1.png)


* **ThreadLocalMap** 为 **ThreadLocal** 的静态内部类
* **ThreadLocalMap** 内部定义了一个 **Entry** 类作为存放局部变量的结构，其中`key`为 *ThreadLocal* 对象，`value` 为局部变量的值
* **ThreadLocalMap** 用 *Entry[] table* 存放多个 **ThreadLocal**，通过`key`的哈希值和数组长度计算存放位置；哈希冲突采用开放定址法
* **Entry** 类继承了 **WeakReference**，其`key`值为弱引用，可以避免内存泄漏问题。
* **Thread** 类内部维护了一个 *ThreadLocal.ThreadLocalMap* 成员变量，即每个线程都对应一个 *ThreadLocalMap*，故使用 **ThreadLocal** 可以保证变量为线程独有且可以有多个。

### **ThreadLocal 源码**

* **ThreadLocal 结构**

``` java
public class ThreadLocal<T> {
    // ...
    static class ThreadLocalMap {
        static class Entry extends WeakReference<ThreadLocal<?>> {
            /** The value associated with this ThreadLocal. */
            Object value;

            Entry(ThreadLocal<?> k, Object v) {
                // key值为弱引用
                super(k);
                value = v;
            }
        }
    }
    // ...
}
```

* **ThreadLocalMap 解决哈希冲突**

``` java
private void set(ThreadLocal<?> key, Object value) {
    // We don't use a fast path as with get() because it is at
    // least as common to use set() to create new entries as
    // it is to replace existing ones, in which case, a fast
    // path would fail more often than not.

    Entry[] tab = table;
    int len = tab.length;
    int i = key.threadLocalHashCode & (len-1);
    // 开放定址法解决哈希冲突
    for (Entry e = tab[i];
            e != null;
            e = tab[i = nextIndex(i, len)]) {
        ThreadLocal<?> k = e.get();

        if (k == key) {
            e.value = value;
            return;
        }

        if (k == null) {
            replaceStaleEntry(key, value, i);
            return;
        }
    }

    tab[i] = new Entry(key, value);
    int sz = ++size;
    if (!cleanSomeSlots(i, sz) && sz >= threshold)
        rehash();
}

/**
 * Increment i modulo len.
 */
private static int nextIndex(int i, int len) {
    return ((i + 1 < len) ? i + 1 : 0);
}
```

* **ThreadLocal 处理数据**

``` java 
public class Thread implements Runnable {
    // ...
    /* ThreadLocal values pertaining to this thread. This map is maintained
     * by the ThreadLocal class. */
    ThreadLocal.ThreadLocalMap threadLocals = null;

}
```
> **SET**

``` java
/**
 * Sets the current thread's copy of this thread-local variable
 * to the specified value.  Most subclasses will have no need to
 * override this method, relying solely on the {@link #initialValue}
 * method to set the values of thread-locals.
 *
 * @param value the value to be stored in the current thread's copy of
 *        this thread-local.
 */
public void set(T value) {
    // 获取当前线程
    Thread t = Thread.currentThread();
    // 获取当前线程内部的ThreadLocalMap
    ThreadLocalMap map = getMap(t);
    if (map != null)
        map.set(this, value);
    else
        createMap(t, value);
}

/**
 * Get the map associated with a ThreadLocal. Overridden in
 * InheritableThreadLocal.
 *
 * @param  t the current thread
 * @return the map
 */
ThreadLocalMap getMap(Thread t) {
    return t.threadLocals;
}
```
> **GET**

``` java
/**
 * Returns the value in the current thread's copy of this
 * thread-local variable.  If the variable has no value for the
 * current thread, it is first initialized to the value returned
 * by an invocation of the {@link #initialValue} method.
 *
 * @return the current thread's value of this thread-local
 */
public T get() {
    Thread t = Thread.currentThread();
    ThreadLocalMap map = getMap(t);
    if (map != null) {
        // Entry中key为ThreadLocal
        ThreadLocalMap.Entry e = map.getEntry(this);
        if (e != null) {
            @SuppressWarnings("unchecked")
            T result = (T)e.value;
            return result;
        }
    }
    return setInitialValue();
}
```

> **REMOVE**

``` java
/**
 * Removes the current thread's value for this thread-local
 * variable.  If this thread-local variable is subsequently
 * {@linkplain #get read} by the current thread, its value will be
 * reinitialized by invoking its {@link #initialValue} method,
 * unless its value is {@linkplain #set set} by the current thread
 * in the interim.  This may result in multiple invocations of the
 * {@code initialValue} method in the current thread.
 *
 * @since 1.5
 */
public void remove() {
    ThreadLocalMap m = getMap(Thread.currentThread());
    if (m != null)
        m.remove(this);
}

/**
 * Remove the entry for key.
 */
private void remove(ThreadLocal<?> key) {
    Entry[] tab = table;
    int len = tab.length;
    int i = key.threadLocalHashCode & (len-1);
    for (Entry e = tab[i];
            e != null;
            e = tab[i = nextIndex(i, len)]) {
        if (e.get() == key) {
            // 清除对ThreadLocal的弱引用
            e.clear();
            // 将key值为null的value引用设置为null, 方便GC
            expungeStaleEntry(i);
            return;
        }
    }
}
```

### **ThreadLocal 内存泄漏问题**
![图片加载失败](https://maxwell-blog.cn/image/threadlocal2.jpg)

* **内存泄漏** 发生的原因是 **ThreadLocal** 不再使用时，栈空间中的 **ThreadLocalReference** 不再指向 **ThreadLocal** 对象，由于`key`中 **ThreadLocal** 对象的引用为虚引用，在GC时会将 **ThreadLocal** 对象回收。但由于线程仍在运行，其指向的**ThreadLocalMap** 中 **Entry** 的 `value` 仍以强引用方式指向堆空间中的 **Value** 数据，使得这部分已经失效的数据在堆空间中无法被回收
* 为了避免出现**内存泄漏**的发生，应该在 **ThreadLocal** 不再使用时调用 *remove()* 方法清除数据，*remove()* 方法中会将`key`值的引用设置为`null`，并且将`key`值为`null`的 **Entry** 的`value`引用设置为`null`

