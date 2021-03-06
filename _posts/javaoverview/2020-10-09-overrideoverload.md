---
layout: post
title: "Override 和 Overload 的区别"
date: 2020-10-09
categories: Java
---


### **Override - 重写**
* 指子类重写父类的方法，是父类和子类间一种多态的体现
    + 参数必须一致<br>
    + 返回类型必须为父类方法返回类型或其派生类<br>
    + 访问限制不能高于父类方法<br>
    + 抛出异常类型不能广于父类方法<br>
    + 声明为final、static方法不能被重写

### **Overload - 重载**
* 指同一个类中，方法名字相同，参数不同，返回类型（可能）不同；最常用的是构造方法的重载；重载是一个类多态性的表现。
    + 参数列表必须不同<br>
    + 可以改变访问修饰符<br>
    + 可以声明新的或更广的异常

![图片加载失败](http://maxwell-blog.cn/image/over1.png)





