---
layout: post
title: "逃逸分析"
date: 2020-10-09
categories: JVM
---


* **逃逸** 是指在某个方法/代码块中创建的对象，除了在方法体/代码块内被引用之外，还在方法体/代码块之外被引用到。带来的问题是方法/代码块在执行完毕后，在其中创建的对象无法被GC回收，即成为逃逸。JDK1.8默认开启逃逸分析。

* **栈上分配** 一般情况下，Java对象在堆空间中分配内存，但在经过**逃逸分析**后发现，一个对象并没有逃逸出方法/代码块的话，就有可能被优化成栈上分配。这样对象可以随着方法执行完毕栈帧弹出而被销毁，不需要进行垃圾回收。

### **示例**
* 进行逃逸分析
``` java
public class EscapeAnalysisDemo {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for(int i = 0; i < 10000000; i++) {
            createObject();
        }
        System.out.println(System.currentTimeMillis() - start + "ms");
    }

    public static void createObject() {
        Object object = new Object();
    }
}
```
**结果**    
![图片加载失败](https://maxwell-blog.cn/image/escapeanalysis1.png)  


* 关闭逃逸分析并开启垃圾回收打印
``` markdown
-XX:-DoEscapeAnalysis
-XX:+PrintGC
```
**结果**  
![图片加载失败](https://maxwell-blog.cn/image/escapeanalysis2.png)  

