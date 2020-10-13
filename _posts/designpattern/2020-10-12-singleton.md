---
layout: post
title: "单例模式"
date: 2020-10-12
categories: DesignPattern
author: Maxwell-L
---

* **意图:** 保证一个类只有一个实例，并提供一个访问它的全局访问点
* **好处:** 避免一个全局使用的类频繁地创建和销毁
* **应用:** **Spring** 中的 **bean** 默认都是单例模式

### **实现**

**饿汉型**
``` java
public class Singleton {
    private static Singleton singleton = new Singleton();
    
    private Singleton() {}
    
    public static Singleton getInstance() {
        return singleton;
    }
}
```

* **验证**

    ``` java
    public class SingletonPatternDemo implements Runnable {
        @Override
        public void run() {
            System.out.println("INFO::Singleton.hashcode -> " + Singleton.getInstance().hashCode());
        }

        public static void main(String[] args) {
            SingletonPatternDemo demo = new SingletonPatternDemo();
            Thread t1 = new Thread(demo);
            Thread t2 = new Thread(demo);
            Thread t3 = new Thread(demo);
            t1.start();
            t2.start();
            t3.start();
        }
    }
    ```

    ![图片加载失败](https://maxwell-blog.cn/image/singleton1.png)

* 饿汉型单例模式实现简单，但实例对象在被调用前就已经创建完毕了，容易产生垃圾对象造成内存浪费。

**懒汉型**
* **DCL(Double-Checked Locking)**

    ``` java
    public class Singleton {
        private volatile static Singleton singleton;

        private Singleton() {}

        public static Singleton getInstance() {
            if(singleton == null) {
                synchronized (Singleton.class) {
                    if(singleton == null) {
                        singleton = new Singleton();
                    }
                }
            }
            return singleton;
        }
    }
    ```

* **静态内部类**

    ``` java
    public class Singleton {

        private static class SingletonHandler {
            private static Singleton singleton = new Singleton();
        }

        private Singleton() {}

        public static Singleton getInstance() {
            return SingletonHandler.singleton;
        }
    }
    ```
* **验证**

    ![图片加载失败](https://maxwell-blog.cn/image/singleton2.png)

* 比较两种懒汉型的单例模式，采用静态内部类的单例模式没有锁，在高并发环境下性能更好；而 DCL 方法中采用了 *synchronized* 保证线程安全，又做了双重检查，所以性能远不如静态内部类创建的单例模式。
* **DCL** 下单例对象采用了 **volatile** 修饰，这是为了避免 **JVM** 进行指令的重排序。原本在新建一个对象时，要经过：①为对象分配内存空间；②对象初始化；③将引用指向对象的内存空间地址。但 **JVM** 可能将指令重排序为 ①③②，如果A线程按照这个顺序执行到③时，B线程尝试获取单例对象，此时栈中已有单例对象的引用，因此B线程可以获取到，但此时单例对象仍未进行初始化，可能导致程序出错。