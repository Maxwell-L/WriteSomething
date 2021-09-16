---
layout: post
title: "Java对象在内存中的存储"
date: 2021-09-11
author: linliangqi
categories: JVM
---

### 背景
首先，抛出问题：

``` java
public class Main {
    public static void main(String[] args) {
        byte[] array1 = new byte[1024 * 1024];
        Byte[] array2 = new Byte[1024 * 1024];
    }
}
```

1. 代码中的`array1`占用了多少内存空间？
2. 类似`array2`这样的Byte包装类如何计算？
3. 设置多少内存空间可以刚好存下这样大的数组？

> 以下讨论默认64位机

### Java对象的结构
在HotSpot虚拟机中，Java对象在内存中的布局可以分为3块区域：对象头（Header）、实例数据（Instance Data）和对齐填充（Padding）。

**对齐填充** 用于对象按照8byte对齐，如果数据刚好是8byte的倍数，则不需要对齐填充。

**对象头** 存储了对象自身运行时的数据，如HashCode、GC分代年龄、锁标志，线程持有的锁、偏向锁相关等等，也存储了对象指向它的类元数据的指针（通过这个指针来确定这个对象是哪个类的实例），其结构如下图所示：

![图片加载失败](https://maxwell-blog.cn/image/JavaHeader.png)

其中 Mark Word 的大小为 8byte，Class Metadata Address大小为 8byte，但目前在JDK 1.6版本之后默认开启了压缩指针，这块的大小默认是 4byte（可以通过 `-XX:-UseCompressedOops` 关闭压缩指针），Array Length 仅在对象为数组时有，大小为 4byte。由此可以计算出，默认情况下，对于非数组对象的对象头大小为 `8byte（Mark Word） + 4byte（Class Metadata Address） + 4byte（Padding）= 16byte`，数组对象的对象头的大小为 `8byte + 4byte + 4byte（Array Length） = 16byte`，如果关闭压缩指针，Class Metadata Address 大小为 8byte，则非数组对象和数组对象的对象头大小分别为 16byte 和 24byte。

**实例数据** 是对象真正存储的有效数据，这块的大小分配跟对象的类型有关，不同类型的数据大小如下表所示：

数据类型|在内存中的大小
:-:|:-:
byte/boolean|1byte
char/short|2byte
int/float|4byte
long/double|8byte
reference|4byte(关闭指针压缩则为8byte)

### 问题分析
对于问题1，`array1`为数组对象，默认开启了指针压缩，对象头大小为 16 byte，实例数据类型为 byte，大小为 `1024 * 1024 * 1 byte`，故占用内存空间为 `1024 * 1024 + 16 = 1,048,592 byte`。

对于问题2，`array2`为数组对象，默认开启了指针压缩，对象头大小为 16 byte，实例数据类型为 reference，大小为 `1024 * 1024 * 4 byte`，故占用空间为 `1024 * 1024 * 4 + 16 = 4,194,320 byte`。

对于问题3，是否分配刚好的内存就可以存下类似`array1`和`array2`的数组了呢？实验一下，设置JVM参数 `-Xmx64m`，堆空间大小为 `64 * 1024 * 1024 byte`。仅从空间大小分析理论上可以存下 1024 * 1024 * 64 - 16 这样长度的 byte[] 数组，但是写出这样的代码就会发现抛出 `OutOfMemoryError: Java heap space`异常：

![图片加载失败](https://maxwell-blog.cn/image/outofmemory.png)

### Java堆的结构

上述问题又涉及到Java堆的结构，堆空间可以分为 `YoungGen`、`OldGen`、`MetaSpace`三个部分，`YoungGen`又可以分为`Eden`、`From Surviror`、`To Survivor`三个部分，对于普通对象的创建会先在 `YoungGen`中的 `Eden` 分配空间，每次 Minor GC 会将 `Eden`和一个`Survivor`中存活的对象移入另一个 `Survivor`中，同时存活下来的对象的分代年龄会增长1岁，默认在15岁时会被移入 `OldGen`。

但对于问题中的大数组，JVM对于这样的大对象会直接将其存储在 `OldGen` 中，那么可以推测，byte[] 数组的大小不能超过 `OldGen` 的大小，否则就会直接抛出异常。

JVM默认的`YoungGen`和`OldGen`空间比例为 1:2，对于上文中设置的 -Xmx64m，加上参数 `-XX:+PrintGCDetails`并令程序空跑一次，得到堆的信息：

![图片加载失败](https://maxwell-blog.cn/image/oldgen.png)

可以看到 `OldGen` 的大小为 44032K，即为 `44032 * 1024 byte = 1024 * 1024 * 43 byte`。

### 实践证明

![图片加载失败](https://maxwell-blog.cn/image/bytearray1.png)

可以看到 `OldGen`的空间被100%使用了，这时候只需要将数组的长度加 1 即会抛出异常，同理，对于Byte[]及其它包装类的数组，可以分配的长度则为 `1024 * 1024 * 43 / 4 - 4`，最后减4而不是减16是因为包装类数组每多一个长度增加的大小为 4byte（reference的大小），故减去4个就可以留出 16byte 大小给对象头使用。

如果通过指令 `-XX:-UseCompressedOops` 关闭指针压缩，此时数组对象的对象头大小为24byte，再次执行上述代码就会抛出异常。对于byte[]数组长度需要减去24，留出24byte给对象头，而对于包装类的数组，不仅对象头变为24byte，而且实例数据中reference的大小变为8byte，因此可以存储的数组长度为 `1024 * 1024 * 43 / 8 - 3`，最后的减3同样是为了留出24byte给对象头。

### 结论

通过实践得出结论，基本数据类型和包装类的大数组内存占用情况不同，能存下多大的大数组跟 `OldGen`的大小有直接关系，同时是否使用压缩指针也会给对象的存储带来不一样的结果。

