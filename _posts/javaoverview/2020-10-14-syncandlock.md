---
layout: post
title: "synchronized 和 Lock 的区别"
date: 2020-10-14
categories: Java
author: Maxwell-L
---

* **synchronized** 是 **Java** 的关键字，是在 **JVM** 层面上实现的；而 **Lock** 是一个接口，有多种不同的实现类，使用更加灵活，功能更加强大。
* **synchronized** 是隐式锁，当同步代码块执行完毕或者出现异常时，**JVM** 会自动释放锁；而 **Lock** 是显示锁，开发者必须在 *finally* 块主动释放锁，否则可能出现死锁的情况。
* **synchronized** 中无法响应中断，只能等待锁的释放，而 **Lock** 可以通过 *interrupt* 来中断等待。
* **synchronized** 无法获取锁的状态，而 **Lock** 可以通过 *tryLock* 来得知是否获得锁。
* **synchronized** 使用 *Object* 类本身的 *wait*、 *notify*、 *notifyAll* 来进行线程调度；而 **Lock** 可以通过绑定多个 *Condition* 按照不同条件来调度线程。
* **synchronized** 是一种不公平锁，而 **Lock** 可以实现公平锁。

### **synchronized 原理与使用**
* **synchronized** 通过 *monitorenter*、*monitorexit* 来实现 *lock* 和 *unlock* 操作。每个对象都是一个 *monitor*，当 *monitor* 被占用时就会处于锁定状态，线程执行 *monitorenter* 指令时会尝试获取 *monitor* 的所有权：

