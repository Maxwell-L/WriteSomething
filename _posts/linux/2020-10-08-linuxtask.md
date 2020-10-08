---
layout: post
title: "Linux中的进程"
date: 2020-10-08
categories: Linux
---

### **进程状态**
* **进程的描述字段**
``` c
/* include/linux/sched.h */

    /* -1 unrunnable, 0 runnable, >0 stopped: */
	  volatile long			state;

      int				    exit_state;
```

* **进程状态的定义**  

  ``` c
  /* include/linux/sched.h */

  /* Used in tsk->state: */
  #define TASK_RUNNING			  0x0000
  #define TASK_INTERRUPTIBLE		  0x0001
  #define TASK_UNINTERRUPTIBLE	  0x0002
  #define __TASK_STOPPED			  0x0004
  #define __TASK_TRACED			  0x0008

  /* Used in tsk->exit_state: */
  #define EXIT_DEAD			0x0010
  #define EXIT_ZOMBIE			0x0020
  #define EXIT_TRACE			(EXIT_ZOMBIE | EXIT_DEAD)

  /* Used in tsk->state again: */
  #define TASK_PARKED			0x0040
  #define TASK_DEAD			0x0080
  #define TASK_WAKEKILL		0x0100
  #define TASK_WAKING			0x0200
  #define TASK_NOLOAD			0x0400
  #define TASK_NEW			0x0800
  #define TASK_STATE_MAX	    0x1000

  /* Convenience macros for the sake of set_current_state: */
  #define TASK_KILLABLE		(TASK_WAKEKILL | TASK_UNINTERRUPTIBLE)
  #define TASK_STOPPED		(TASK_WAKEKILL | __TASK_STOPPED)
  #define TASK_TRACED			(TASK_WAKEKILL | __TASK_TRACED)
  #define TASK_IDLE			(TASK_UNINTERRUPTIBLE | TASK_NOLOAD)
  ```

* **主要状态**
  + **就绪状态**：`state = TASK_RUNNING` Linux中没有区分就绪状态和运行状态，处于运行队列中等待CPU调度的就是就绪状态。
  + **运行状态**：`state = TASK_RUNNING` 正在CPU上运行的任务处于运行状态。
  + **轻度睡眠**：`state = TASK_INTERRUPTIBLE` 可以响应信号，即睡眠状态可以被信号打断。
  + **中度睡眠**：`state = TASK_KILLABLE` 睡眠状态只能被致命的信号打断。
  + **深度睡眠**：`state = TASK_UNINTERRUPTIBLE` 不能被打断的睡眠状态，出现问题只能重启电脑。
  + **僵尸状态**：`state = TASK_DEAD` `exit_state = EXIT_ZOMBIE` 父进程关心子进程退出事件，子进程退出时发送`SIGCHLD`信号通知父进程并进入僵尸状态。
  + **死亡状态**：`state = TASK_DEAD` `exit_state = EXIT_DEAD` 父进程不关心子进程退出事件，子进程退出后自动消亡。

![图片加载失败](https://maxwell-blog.cn/image/taskstate.png)
