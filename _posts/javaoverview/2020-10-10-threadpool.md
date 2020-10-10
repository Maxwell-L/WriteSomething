---
layout: post
title: "线程池的实现与使用"
date: 2020-10-10
categories: Java
author: Lin Liangqi
---

* 为了避免系统频繁地创建和销毁线程，可以让创建的线程进行复用。线程池中，总有几个活跃线程，当需要使用线程时，可以从池子里拿一个空闲线程；完成工作时，不立即关闭线程，而是将线程退回到池子中
* 为了更好的控制多线程，**JDK** 提供了一套 `Executor` 框架  

    ![图片加载失败](https://maxwell-blog.cn/image/threadpool1.png)  

    其中 `ThreadPoolExecutor` 表示一个线程池
* `Executors`类中实现了几种类型的线程池:

    ``` java
    public static ExecutorService newFixedThreadPool(int nThreads)
    public static ExecutorService newSingleThreadExecutor()
    public static ExecutorService newCachedThreadPool()
    //...
    ```
    + `newFixedThreadPool()` 返回一个固定线程数量的线程池。若新的任务提交时，线程池中没有空闲线程了，则会被暂存在一个任务队列中，待有线程空闲时再处理。
    + `newSingleThreadExecutor()` 返回一个只有一个线程的线程池，多余线程被存放在一个任务队列中，待线程空闲时按先入先出的顺序执行队列中的任务。
    + `newCachedThreadPool()` 返回一个可根据实际情况调整线程数量的线程池。线程池数量不确定。若有空闲线程时会优先复用，若没有空闲线程时，则会创建新的线程处理任务。

### **线程池的底层实现**

``` java
public static ExecutorService newFixedThreadPool(int nThreads, ThreadFactory threadFactory) {
    return new ThreadPoolExecutor(nThreads, nThreads,
                                    0L, TimeUnit.MILLISECONDS,
                                    new LinkedBlockingQueue<Runnable>(),
                                    threadFactory);
}

public static ExecutorService newSingleThreadExecutor() {
    return new FinalizableDelegatedExecutorService
        (new ThreadPoolExecutor(1, 1,
                                0L, TimeUnit.MILLISECONDS,
                                new LinkedBlockingQueue<Runnable>()));
}

public static ExecutorService newCachedThreadPool(ThreadFactory threadFactory) {
    return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                                    60L, TimeUnit.SECONDS,
                                    new SynchronousQueue<Runnable>(),
                                    threadFactory);
}
```
* 可以看到上文中不同类型的线程池内部均使用了 `ThreadPoolExecutor` 实现，`ThreadPoolExecutor` 中最关键的构造函数如下：

    ``` java
    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue,
                              ThreadFactory threadFactory,
                              RejectedExecutionHandler handler)
    ```
    + **corePoolSize** 指定线程池中的核心线程数
    + **maximumPoolSize** 指定线程池中的最大线程数
    + **keepAliveTime** 当线程池中的线程数量超过 **corePoolSize** 时，多余的空闲线程的存活时间
    + **TimeUnit** *keepAliveTime* 的单位
    + **workQueue** 任务队列，保存被提交但未执行的任务
    + **threadFactory** 线程工厂，用于创建线程，一般采用默认
    + **handler** 拒绝策略，当任务太多来不及处理时如何拒绝

### **BlockingQueue** 
* 线程池中使用 **BlockingQueue** 来存放未执行的任务。按照队列功能分类，在 **ThreadPoolExecutor** 的构造函数中可以使用以下几种 **BlockingQueue**
* **SynchronousQueue** 是一个没有容量的阻塞队列，每一个 `put` 操作都要等待一个 `take` 操作，反之每个 `take` 操作也要等待一个 `put` 操作。`newCachedThreadPool` 中采用了这种阻塞队列，提交的任务不会被真实的保存，而总是提交给线程执行，如果没有空闲线程则创建新线程。当线程数达到 *maximumPoolSize* 时执行拒绝策略。
* **ArrayBlockingQueue** 有界的阻塞队列，有新任务提交时，若当前线程数小于 *corePoolSize*，则创建新线程；若大于 *corePoolSize*，则将任务加入阻塞队列；若阻塞队列已满且线程数小于 *maximumPoolSize* 则创建新线程执行任务；若阻塞队列已满且线程数达到 *maximumPoolSize* 则执行拒绝策略。
* **LinkedBlockingQueue** 无界的阻塞队列，与有界的阻塞队列相比，除非系统资源耗尽，否则不会出现任务入队失败的情况，若线程数达到 *corePoolSize* 又没有空闲线程，则后续任务都会进入阻塞队列里等待。
* 总的调度逻辑如下：  

    ![图片加载失败](https://maxwell-blog.cn/image/threadpool2.jpg)

### **拒绝策略**