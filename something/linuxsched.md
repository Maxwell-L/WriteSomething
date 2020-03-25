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