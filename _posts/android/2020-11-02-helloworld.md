---
layout: post
title: "Hello World"
date: 2020-11-02
categories: Android
author: Maxwell-L
---

### **环境**
* **JDK1.8.0_271**   
* **Gradle-6.5-bin**  
* **Android Studio 4.1**  
* **Windows 10**

### **Gradle 配置安装**
* 下载 [gradle-6.5-bin.zip](https://downloads.gradle-dn.com/distributions/gradle-6.5-bin.zip) 压缩包并解压

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello1.png)

* 配置环境变量 **GRADLE_HOME** 和 **GRADLE_USER_HOME**

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello2.png)
    ![图片加载失败](https://maxwell-blog.cn/image/androidhello3.png)

    并在 **Path** 下新增 `%GRADLE_HOME%\bin`

* 配置完成后打开 **cmd**，键入 `gradle -v` 可以看到 gradle 版本信息

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello3_2.png)

### **Android Studio 配置安装**
* 官网下载 [Android Studio](https://developer.android.google.cn/studio/)，下载完成后开始安装

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello4.png)
    ![图片加载失败](https://maxwell-blog.cn/image/androidhello5.png)
    ![图片加载失败](https://maxwell-blog.cn/image/androidhello6.png)

* 在 **Android Studio** 中安装 **Android SDK**，首次运行 Android Studio 会提示无法访问 Android SDK，如下图所示，点击 `cancel` 进入下个界面 -> `Next`

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello7.png)
    ![图片加载失败](https://maxwell-blog.cn/image/androidhello8.png)
    
    自行设置 **Android SDK Location**，其余默认，`Next` 进入安装

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello9.png)
    ![图片加载失败](https://maxwell-blog.cn/image/androidhello10.png)

    等待安装完成

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello11.png)

