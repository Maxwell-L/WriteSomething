---
layout: post
title: "Abstract class 和 Interface 的区别"
date: 2020-10-09
categories: Java
---

### **abstract class - 抽象类**
* 抽象类不能实例化，其余部分与普通类一致，可以拥有成员变量、成员方法、构造方法等。
* 由于抽象类不能被实例化，故只有其被继承时才能使用。
* 如果一个类包含抽象方法，则这个类必定时抽象类。
* 继承抽象类的子类必须重写父类的抽象方法，除非该子类也是抽象类，最终必须要有子类重写抽象方法，否则从最初的父类到最终的子类都无法被实例化。
* 抽象方法只是声明，不包含方法体。
* 构造方法、static修饰的方法不能被abstract修饰（即不能声明为抽象方法）

### **interface - 接口**
* 接口包含类要实现的方法，一个实现接口的类，除非是抽象类，否则要实现接口中所有方法。
* 接口不能实例化，没有构造方法。
* 接口中的方法隐式的被public abstract修饰，也仅能被public abstract修饰。
* 接口中的变量隐式的被public static final修饰，也仅能是public static final修饰。
* JDK1.8及之后接口中可以有静态方法和方法体，还有被default修饰的方法，静态方法默认被public修饰，且必须有方法体；default修饰的方法也必须有方法体，只能被实例对象调用。
* 接口可以继承多个接口，即支持接口多继承。
* **标记接口**：没有任何方法和属性的接口，仅表示它的类属于一个特定的类型。


