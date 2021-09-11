---
layout: post
title: "Java 并发编程"
date: 2021-09-04
author: linliangqi
categories: StudyNote
---

- [Java并发机制底层原理](#java并发机制底层原理)
  - [前置知识](#前置知识)
    - [CPU的术语定义](#cpu的术语定义)
    - [MESI（缓存一致性协议）](#mesi缓存一致性协议)
    - [存储器层次结构](#存储器层次结构)
  - [volatile可见性原理](#volatile可见性原理)
  - [Synchronized原理](#synchronized原理)
    - [Java对象头](#java对象头)
    - [锁的升级与对比](#锁的升级与对比)


## Java并发机制底层原理

> Java的并发机制依赖于 JVM 的实现和 CPU 的指令

### 前置知识

#### CPU的术语定义

术语|描述
:-:|:-
内存屏障（memory barriers）|是一组处理器指令，用于实现对内存操作的顺序限制
缓冲行（cache line）|CPU高速缓存中可以分配的最小存储单位，处理器填写缓存行时会加载整个缓存行，现代CPU需要执行几百次CPU指令
原子操作（atomic operations）|不可中断的一个或一系列操作
缓存行填充（cache line fill）| 当处理器识别到从内存读取到的操作数是可缓存的，处理器读取整个高速缓存行到适当的缓存（L1、L2、L3或所有）
缓存命中（cache hit）|如果进行高速缓存行填充操作的内存位置仍然是下次处理器访问的地址时，处理器从缓存中读取操作数，而不是从内存中读取
写命中（write hit）|当处理器将操作数写回到一个内存缓存的区域时，它首先会检查这个缓存的内存地址是否在缓存行中，如果存在一个有效的缓存行，则处理器将这个操作数写回到缓存，而不是写回到内存，这个操作被称为写命中
写缺失（write misses the cache）|一个有效的缓存行被写到不存在的内存区域

#### MESI（缓存一致性协议）
处理器有一套完整的协议来保证Cache一致性，比较经典的是MESI协议，在MESI协议中，每个缓存行有4个状态，可以用两个bit表示：

状态|描述
:-:|:-
M(Modified)|数据有效，但被修改，与内存中不一致，数据仅存在本缓存中
E(Exclusive)|数据有效，与内存中一致，数据仅存在本缓存中
S(Shared)|数据有效，与内存中一致，数据存在多个缓存中
I(Invalid)|数据无效

MESI协议状态迁移图如下：

![图片加载失败](https://maxwell-blog.cn/image/mesi.jpg)

#### 存储器层次结构

![图片加载失败](https://maxwell-blog.cn/image/cache-level.jpg)


### volatile可见性原理
volatile 在并发编程中保证了共享变量的可见性，当一个线程修改共享变量的值时，volatile可以保证另一个线程可以读到修改后的值，volatile比synchronized更轻量，因为volatile不会引起上下文切换。

volatile修饰的共享变量在修改时会进行两个操作：
1. 将当前处理器缓存行的数据写回系统内存
2. 写回内存的操作会使其它处理器缓存的该缓存变量失效
   
对照MESI协议，可以了解此过程中各处理器的状态变化：修改了共享变量并写入内存的CPU的状态变化为 `S -> M -> E`，其它CPU的状态变化为 `S -> I`；其它CPU从内存中读取该变量时又会使得状态变化为 `I -> S`，而刚刚修改了该变量的CPU的状态变化为 `E -> S`。

### Synchronized原理

#### Java对象头
Java对象在内存中的布局可以分为3个区域：对象头（Header）、实例数据（Instance Data）和对齐填充（Padding）。Synchronized用的锁存储在Header里，Header的结构如下图所示：

![图片加载失败](https://maxwell-blog.cn/image/JavaHeader.png)

其中Mark Word存储了锁相关的信息，其数据会随着锁标志位的变化而变化：

![图片加载失败](https://maxwell-blog.cn/image/MarkWord.png)

#### 锁的升级与对比

Java SE在1.6版本为了减少获得锁和释放锁带来的性能消耗，引入了 **偏向锁** 和 **轻量级锁**，故锁共有4种状态，级别从低到高分别是：无锁状态、偏向锁状态、轻量级锁状态和重量级锁状态。

大多数情况下，锁不仅不存在多线程竞争，而且总是由同一线程获得，为了令线程获得锁的代价更低，HotSpot作者引入了偏向锁。



