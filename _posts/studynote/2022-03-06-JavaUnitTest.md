---
layout: post
title: "Java 并发编程"
date: 2021-09-04
author: linliangqi
categories: StudyN2-03-06
---

- [Java单测框架](#java单测框架)
  - [Ⅰ. 什么是单测](#ⅰ-什么是单测)
  - [Ⅱ. 为什么需要单测](#ⅱ-为什么需要单测)
  - [Ⅲ. 单测的F.I.R.S.T.原则](#ⅲ-单测的first原则)
  - [一、单测框架对比](#一单测框架对比)
  - [二、Mockito框架使用](#二mockito框架使用)
    - [1. 在项目中使用Mockito](#1-在项目中使用mockito)
      - [1.1 初始化Mockito](#11-初始化mockito)
      - [1.2 @Mock和@Spy](#12-mock和spy)
      - [1.3 @InjectMocks](#13-injectmocks)
      - [1.4 @Before和@After](#14-before和after)
      - [1.5 @BeforeClass和@AfterClass](#15-beforeclass和afterclass)
      - [1.6 assert和verify](#16-assert和verify)
      - [1.7 Mock静态方法](#17-mock静态方法)
    - [2. 反射工具](#2-反射工具)

# Java单测框架

## Ⅰ. 什么是单测

单元测试是指对软件中最小可测试单元进行检查和验证，在项目中一般最小可测试单元就是一个方法，每一个单测就是对一个方法代码逻辑的检验，检查对于每种输入对应的输出是否符合预期

## Ⅱ. 为什么需要单测

* 验证业务代码逻辑、功能的正确性
* 保证代码的鲁棒性
* 避免重构影响原来代码功能或引入bug

## Ⅲ. 单测的F.I.R.S.T.原则

* 快速（Fast），测试代码应该能快速运行
* 独立（Independent），测试应该相互独立，每个测试应该能独立运行
* 可重复（Repeatable），测试应当在任何环境可以重复通过，生产环境、质检环境甚至是无网络的环境下都能正常运行
* 自足验证（Self-Validating），测试应该有布尔值输出，无论成功失败都不应该通过查看日志文件来确认测试是否通过
* 及时（Timely），测试应该及时编写，单元测试应该在生产代码编写前写好（或构思好），如果在编写生产代码后编写测试，可能因为某些生产代码本身难以测试而不去设计可测试的代码

## 一、单测框架对比

实际工作中，我们调研并实际运用了Mockito、Spock、PowerMock、JMockit等单测框架进行单测的编写，这些框架都提供了一定的Mock能力以支持上述提到的FIRST原则，下面对这几种单测框架做一个能力上的对比

| 单测框架  | mock普通方法 | mock static 方法 | mock final 方法 | 语言   |
| :-------- | ------------ | ---------------- | --------------- | ------ |
| Mockito   | 支持         | 3.4.0版本后支持  | 支持            | Java   |
| Spock     | 支持         | 不支持           | 不支持          | Groovy |
| PowerMock | 支持         | 支持             | 支持            | Java   |
| JMockit   | 支持         | 支持             | 支持            | Java   |

我们在实际使用中已Mockito、Spock为主，PowerMock和JMockit主要用于协助Mock一些静态方法，相比之下Mockito比较容易上手；Spock由于是使用Groovy有一定的学习成本，且静态方法需要配合PowerMock或JMockit使用，但熟练后写单测效率会更高；PowerMock通过自定义类加载器进行Mock，可能出现单测运行顺序不同而导致失败的情况；JMockit实际使用在Mock静态方法时更方便，但对于公司流水线的兼容需要更多的pom配置



## 二、Mockito框架使用

### 1. 在项目中使用Mockito

> pom.xml中引入依赖
>
> ``` xml
> <dependency>
>     <groupId>org.mockito</groupId>
>     <artifactId>mockito-inline</artifactId>
>     <version>3.4.0</version>
>     <scope>test</scope>
> </dependency>
> ```

#### 1.1 初始化Mockito

Mockito提供了Mock测试类所依赖的对象的功能，而为了使用这些功能，我们需要在测试类上添加一些东西：

``` java
@RunWith(MockitoJUnitRunner.class)
public class DemoServiceTest {
    
}
```

注解@RunWith(MockitoJUnitRunner.class)可以让下面介绍的@InjectMocks、@Mock、@Spy等注解生效。如果你使用的JUnit版本较低不支持使用该注解，你可以在@Before注解的方法下进行初始化：

``` java
public class DemoServiceTest {
    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }
}
```

#### 1.2 @Mock和@Spy

@Mock和@Spy可以用来mock被测试类依赖的对象，两个注解不同点在于：

| 注解  | mock普通对象 | mock 集合类型对象 | 是否调用真实方法             |
| ----- | ------------ | ----------------- | ---------------------------- |
| @Mock | 支持         | 不支持            | 不调用真实方法，直接返回null |
| @Spy  | 支持         | 支持              | 调用真实方法                 |

@Spy除了用来注解在你需要真实调用方法的对象上外，还有一个比较重要的用处，在SpringBoot项目中我们可能会用到@Autowired注解在Map或List上，以此将接口的实现类注入到集合中，这种情况下我们就不得不用@Spy来注解，集合中的对象可以再通过@Mock注解后手动放入集合中。

而@Mock不调用真实方法，我们可以通过when(service.callSomeMethod(param)).thenReturn(response)来mock返回值，或者when(service.callSomeMethod(param)).thenThrow(exception)来mock抛出异常，其中service是我们mock的对象，callSomeMethod是mock的对象调用的方法，param是调用方法的参数，response和exception是你打算让这个方法返回的结果或异常，如果你需要让mock的对象真实调用方法，可以通过when(...).thenCallRealMethod()来实现。

#### 1.3 @InjectMocks

@InjectsMocks一般用在注解在被测试类的对象上，这个注解会把@Mock和@Spy的对象注入到@InjectsMock注解的对象中，下面通过具体的demo展示1.2和1.3这几个注解的使用方法：

```java
/**
 * 被测试类
 */
@Service
public class DemoService {
    @Autowired
    private Map<String, IService> serviceMap;
    
    public Response fetchData(String beanName) {
        if (serviceMap.containsKey(beanName)) {
            return serviceMap.get(beanName).getData();
        }
        return null;
    }
}
```

``` java
@RunWith(MockitoJUnitRunner.class)
public class DemoServiceTest {
    @Spy
    private Map<String, IService> serviceMap = new HashMap<>();
    
    @Mock
    private DefaultService defaultService;
    
    @InjectMocks
    private DemoService demoService;
    
    @Test
    public void fetchData() {
        serviceMap.put("defaultService", defaultService);
        
        when(service.fetchData()).thenReturn(new Response());
        
        Response response1 = demoService.fetchData("defaultService");
        Response response2 = demoService.fetchData("unknownService");
        
        Assert.assertNotNull(response1);
        Assert.assertNull(response2);
    }
}
```

#### 1.4 @Before和@After

@Before和@After注解在成员方法上，被注解的方法会在测试类中的每个单测方法运行前或运行后执行。

#### 1.5 @BeforeClass和@AfterClass

@BeforeClass和@AfterClass注解在静态方法上，被注解的方法会在测试类的第一个测试方法运行前执行和在最后一个测试方法运行后执行。

#### 1.6 assert和verify

由FIRST原则可知，测试应该有布尔值输出，除了上面demo已经展现的assert断言方法，Mockito还提供了verify供我们判断测试方法是否调用了某个方法以及调用了几次，下面通过demo展示该如何使用verify：

``` java
@Test
public void fetchData() {
    serviceMap.put("defaultService", defaultService);

    when(service.fetchData()).thenReturn(new Response());

    Response response1 = demoService.fetchData("defaultService");
    verify(defaultService, times(1)).fetchData();
    
    Response response2 = demoService.fetchData("unknownService");
    verify(defaultService, times(1)).fetchData();
}
```

第一个verify表示验证defaultService方法是否会调用fetchData方法1次，需要注意的是verify在一个测试方法里的次数是累计的，所以第二个verify其实验证的是第二次执行demoService.fetchData时，defaultService没有调用fetchData方法。

#### 1.7 Mock静态方法

业务代码中有时可能会使用第三方工具类并调用其静态方法，Mockito在版本3.4.0之后支持了mock静态方法。具体使用方法如下：

假设现在有一个工具类UserUtils，其中静态方法getUser用于获取当前用户信息：

``` java
public class UserUtils {
    public static User getUser() {
        return context.get();
    }
}

class User {
    private Long id;
    private String name;
}
```

而我们的业务代码有一个获取当前用户名称的方法：

``` java
public class DemoService {
    public String getCurrentUserName() {
        return UserUtils.getUser().getName();
    }
}
```

当我们在执行单测时，由于并没有真正登录，getUser方法返回的会是null，这就导致这个方法会抛异常，我们可以通过Mockito对其进行UserUtils的getUser方法进行mock：

``` java
public class DemoServiceTest {

    private DemoService demoService = new DemoService();

    @Test
    public void getCurrentUserName() {
        MockedStatic<UserUtils> theMock = Mockito.mockStatic(UserUtils.class);
        theMock.when(UserUtils::getUser).thenReturn(new User(1L, "name"));

        String name = demoService.getCurrentUserName();
        Assert.assertEquals("name", name);
    }
}
```

### 2. 反射工具

Mockito不支持直接调用私有方法，但是Spring Frameword自带了一个反射测试工具类ReflectionTestUtils，利用这个工具类可以便捷的给测试类设置private变量（非final），也可以调用私有方法，具体如下：

``` java
 @Test
public void fetchData() {
    serviceMap.put("defaultService", defaultService);

    when(defaultService.fetchData()).thenReturn(new Response());

    Response response = ReflectionTestUtils.invokeMethod(demoService, "fetchData", "defaultService");

    assertNotNull(response);
}
```

如果私有方法有多个入参，按顺序写就行。










