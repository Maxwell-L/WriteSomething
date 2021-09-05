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





