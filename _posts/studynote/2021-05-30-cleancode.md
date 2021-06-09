---
layout: post
title: "《代码整洁之道》学习笔记"
date: 2021-05-30j
author: linliangqi
categories: StudyNote
---

- [一、整洁代码](#一整洁代码)
- [二、有意义的命名](#二有意义的命名)
- [三、函数](#三函数)


> 做一个更好的程序员


### 一、整洁代码
* 编程是一件多人合作的事情
* 随着混乱增加，团队生产力会急剧下降
* 简单代码规则：
  * 能通过所有测试；
  * 没有重复代码；
  * 体现系统中的全部设计理念；
  * 包括尽量少的实体，比如类、方法、函数等。
* 时时保持代码整洁，“让营地比你来时更干净。”

### 二、有意义的命名

* 选择体现本意的名称，并指明计量对象和计量单位的名称

    ``` markdown
    # 正例
    int daySinceCreation;
    int elapsedTimeInDays;
    ```

* 不使用小写字母`l`和大写字母`O`作为变量名，少使用外形相似度高的名称
* 对于同一作用范围的两样东西应做有意义的区分，不能添加数字系列来命名，也不能通过拼写错误来区分。废话也是没有意义的区分，假如有一个`Product`类，再有一个`ProductInfo`或`ProductData`类，意思并无区别，这是意义含混的废话。

    ``` markdown
    # 反例
    getActiveAccount();
    getActiveAccounts();
    getActiveAccountInfo();
    # 程序员怎么知道该调用哪一个函数呢？
    ```

* 废话都是冗余。`variable`一词不应该出现在变量名中，`table`一词不应该出现在表名中。`NameString`不会比`Name`更好，区分名称，应该以读者能鉴别的方式来区分。
* 使用读得出来的名称，不使用首字母简写，方便讨论时的交流。
  
    ``` markdown
    Date genymdhms; // 反例
    Date generationTimestamp; // 正例
    ```

* 使用可搜索的名称，对于数字常量来说，应该赋予其便于搜索的名称，即方便查找，也能体现作者的意图。

    ``` markdown
    # 正例
    final int WORK_DAYS_PER_WEEK = 5;
    ```

* 类名和对象名应该是名词或名词短语，例如`Customer`、`WikiPage`等，不应该是动词，避免使用`Manager`、`Data`、`Info`这样的类名；方法名应该是动词或动词短语，如`postPayment`、`deletePage`等，属性访问、属性修改和断言应该根据其值命名，并加上前缀`get`、`set`、`is`。
* 给每个抽象选一个词，并且一以贯之。例如避免使用`fetch`、`retrieve`、`get`等在多个类中给同种方法命名，这会让人觉得这几个方法在做不同的事。
* 添加有意义的语境，对于一个地址信息，将`firstName`、`lastName`、`street`、`houseNumber`、`city`、`state`等变量放在一起时读者很容易意识到这是个地址，但如果有一个方法中只有一个孤零零的`state`变量则会造成疑惑，因此可以添加前缀提供语境，例如`addrFirstName`、`addrLastName`、`addrState`，更好的解决方法是将其封装为一个`Address`类。同时也要避免添加无意义的语境，只要短名称足够清楚，就比长名称好，对于邮政地址、MAC地址和Web地址，在类名取名时分为`PostalAddress`、`MAC`、和`URI`会更好，这样的名称比在`address`上加前缀更为精确，**而精确正是命名的要点**。

### 三、函数
* 短小，函数的规则就是短小。
* 只做一件事，判断函数是否做了不只一件事，就是看它是否能再拆出一个函数。
* 