## **Linux中的调度策略**
--------------------------

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
调度对象|调度策略|调度算法
:---|:---|:---
限期进程|`SCHED_DEADLINE`|最早截止时间优先(EDF)
实时进程|`SCHED_FIFO`|先进先出
<a>|`SCHED_RR`|轮流调度
普通进程|`SCHED_NORMAL`|完全公平调度算法
<a>|`SCHED_IDLE`|完全公平调度算法

