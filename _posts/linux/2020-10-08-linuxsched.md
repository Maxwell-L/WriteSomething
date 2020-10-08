---
layout: post
title: "Linux中的调度策略"
date: 2020-10-08
categories: Linux
---

### **调度策略的定义**
``` c
/* include/uapi/linux/sched.h */

/*
 * Scheduling policies
 */
#define SCHED_NORMAL    0
#define SCHED_FIFO		1
#define SCHED_RR		2
#define SCHED_BATCH		3
/* SCHED_ISO: reserved but not implemented yet */
#define SCHED_IDLE		5
#define SCHED_DEADLINE  6
```

### **调度对象对应策略及算法**

调度类|调度对象|调度策略|调度算法
:---|:---|:---|:---
限期调度类|限期进程|`SCHED_DEADLINE`|最早截止时间优先(EDF)
实时调度类|实时进程|`SCHED_FIFO`|先进先出
<a>|<a>|`SCHED_RR`|轮流调度
公平调度类|普通进程|`SCHED_NORMAL`|完全公平调度算法(CFS)
<a>|<a>|`SCHED_IDLE`|完全公平调度算法(CFS)

* **限期调度类：** 
  + **最早截至时间算法** 使用红黑树把进程按照截止时间从小到大排序，每次调度时选择绝对截止时间最小的进程。当限期进程用完它的运行时间后，它将让出处理器，等下一个周期开始时重新进入运行队列。
* **实时调度类：** 为每个调度优先级维护一个队列，每次调度找到优先级最高的一个队列并调用队列中第一个进程。
  + **先进先出** 没有时间片，如果没有优先级更高的实时进程，那么它将一直霸占处理器直到运行完成。对于相同优先级的进程实行先进先出，但优先级高的进程可以抢占优先级低的进程。
  + **轮流调度** 有时间片，用完时间片后进程加入队列尾部。
* **公平调度类：** 
  + **完全公平调度算法** CFS引入虚拟运行时间`vruntime`的概念：`vruntime = delta_exec * NICE_0_LOAD / WEIGHT`，其中`delta_exec`为实际运行时间，`NICE_0_LOAD`为1024，`weight`是进程的权重。  
  CFS使用红黑树把进程按照虚拟运行时间从小到大排序，每次调度时选择虚拟运行时间最小的进程。由上式可知，实际运行时间一定时，权重越大，虚拟运行时间越小。
* **源码中的`NICE n`的权重**

    ``` c
    /* kernel/sched/core.c */
    /*
    * Nice levels are multiplicative, with a gentle 10% change for every
    * nice level changed. I.e. when a CPU-bound task goes from nice 0 to
    * nice 1, it will get ~10% less CPU time than another CPU-bound task
    * that remained on nice 0.
    *
    * The "10% effect" is relative and cumulative: from _any_ nice level,
    * if you go up 1 level, it's -10% CPU usage, if you go down 1 level
    * it's +10% CPU usage. (to achieve that we use a multiplier of 1.25.
    * If a task goes up by ~10% and another task goes down by ~10% then
    * the relative distance between them is ~25%.)
    */
    const int sched_prio_to_weight[40] = {
    /* -20 */     88761,     71755,     56483,     46273,     36291,
    /* -15 */     29154,     23254,     18705,     14949,     11916,
    /* -10 */      9548,      7620,      6100,      4904,      3906,
    /*  -5 */      3121,      2501,      1991,      1586,      1277,
    /*   0 */      1024,       820,       655,       526,       423,
    /*   5 */       335,       272,       215,       172,       137,
    /*  10 */       110,        87,        70,        56,        45,
    /*  15 */        36,        29,        23,        18,        15,
    };
    ```
  
[返回首页](https://maxwell-l.github.io/WriteSomething)