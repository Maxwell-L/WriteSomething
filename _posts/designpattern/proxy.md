---
layout: post
title: "代理模式"
date: 2020-10-08
categories: DesignPattern
---

## **代理模式**
------

* **意图:** 为其它对象提供一种代理以控制这个对象的访问
* **应用:** `Spring AOP`

### **实现**
``` java
public interface Animal {
    void eat();
}
```
``` java
public class Dog implements Animal {

    private String name;

    public Dog(String name) {
        this.name = name;
    }

    @Override
    public void eat() {
        System.out.println(name + " is eating!");
    }
}
```
**静态代理**
``` java
public class AnimalStaticProxy implements Animal {

    private Dog dog;
    private String name;

    public AnimalStaticProxy(String name) {
        this.name = name;
        dog = new Dog(name);
    }

    @Override
    public void eat() {
        System.out.println("Dog eat before...");
        dog.eat();
        System.out.println("Dog eat after...");
    }
}
```

**动态代理**
``` java
public class AnimalDynamicProxy<T> implements InvocationHandler {

    private T target;

    public AnimalDynamicProxy(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Dog eat before...");
        Object result = method.invoke(target, args);
        System.out.println("Dog eat after...");
        return result;
    }
}
```

**调用**
``` java
public class ProxyPatternDemo {
    public static void main(String[] args) {
        // static proxy
        System.out.println("== STATIC PROXY==");
        Animal animal1 = new AnimalStaticProxy("gigi");
        animal1.eat();

        // dynamic proxy
        System.out.println("== DYNAMIC PROXY==");
        Animal animal2 = (Animal) Proxy.newProxyInstance(Animal.class.getClassLoader(), new Class<?>[]{Animal.class}, new AnimalDynamicProxy<Dog>(new Dog("kiki")));
        animal2.eat();
    }
}
```
**运行结果**  
![图片加载失败](https://maxwell-l.github.io/WriteSomething/image/proxy1.png)



[返回首页](https://maxwell-l.github.io/WriteSomething)