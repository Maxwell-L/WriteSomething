## **封装 / 继承 / 多态**
--------------------------

### **封装**

* 减少耦合，隐藏类的一些信息，防止该类的代码和数据被外部类访问，可以修改我们自己写的代码而不用修改调用我们代码的代码
* 可见性：private / protected / default / public
    + private：仅本类可以访问<br>
    + protected：仅本类和本包可以访问<br>
    + default：仅本类、本包和子类可以访问<br>
    + public：均可访问

不同包中protected修饰的属性不能被实例访问，但可以被子类继承；default此时与private相同，public任何时候都可以被实例对象访问。<br>
![图片加载失败](https://maxwell-l.github.io/WriteSomething/image/featureOne1.png)
![图片加载失败](https://maxwell-l.github.io/WriteSomething/image/featureOne2.png)

### **继承**

* Java类仅支持单继承，但可以多重继承；子类可以继承父类的属性和方法，具有父类的特性，也可以具有自身的特性；接口支持多继承。
    + 子类继承父类所有非private的属性和方法<br>
    + 子类仅可继承一个父类（extends），但可以实现多个接口（implements）<br>
    + 可以通过关键字super来访问父类属性方法<br>
    + 继承减少了冗余的代码<br>
    + 耦合性提高了<br>
    + final修饰的类不能被继承<br>
    + 类中用final修饰的属性不能被修改<br>
    + 用final修饰的方法不能被重写<br>
    + 被final修饰的类中的属性不是final

![图片加载失败](https://maxwell-l.github.io/WriteSomething/image/featureTwo1.png)

* 若父类没有构造函数（此时Java会自动构造一个无参构造函数）或者定义了无参的构造函数，子类的任何构造函数在没有显式调用父类构造函数时会自动调用父类的无参构造函数。
* 若父类仅有带参的构造函数，则Java不会自动构造无参构造函数，子类的构造函数必须显式调用父类构造函数，否则会报错。


### **多态**
* 指不同对象执行同一个动作时有不同的表现
* 三个必要元素：继承、重写、父类引用子对象
* 实现方式
    + 重写  
    + 接口  
    + 抽象类  

``` java   
class Animal {
    public void eat() {
        System.out.println("Animal Eat");
    }
}

class Dog extends Animal {
    @Override
    public void eat() {
        System.out.println("Dog Eat");
    }
}

class Cat extends Animal {
    @Override
    public void eat() {
        System.out.println("Cat Eat");
    }
}
```

``` java
public static void main(String[] args) {
    Animal dog = new Dog();
    Animal cat = new Cat();
    dog.eat();
    cat.eat();
}
```
输出：
```
Dog Eat
Cat Eat
```



[返回首页](https://maxwell-l.github.io/WriteSomething)
