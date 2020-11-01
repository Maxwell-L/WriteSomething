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

* 在 **Android Studio** 中安装 **Android SDK**，首次运行 Android Studio 会提示无法访问 Android SDK，如下图所示，点击 `Cancel` 进入下个界面 -> `Next`

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello7.png)
    ![图片加载失败](https://maxwell-blog.cn/image/androidhello8.png)
    
    自行设置 **Android SDK Location**，其余默认，`Next` 进入安装

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello9.png)
    ![图片加载失败](https://maxwell-blog.cn/image/androidhello10.png)

    等待安装完成

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello11.png)

### **创建 Hello World 项目**
* 创建新项目，设置项目名称，项目语言等等，`Finish`

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello12.png)

* 项目打开后，Android Studio 会自动构建项目，可能出现 gradle-6.5-bin.zip 下载失败的情况

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello13.png)

    此时关闭 Android Studio，打开项目下的 gradle/wrapper 目录，修改 `gradle-wrapper.properties` 文件，将 zip 包下载地址改为本地

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello14.png)

    为了避免依赖的 pom 文件和 jar 包下载缓慢或失败，可以修改项目目录下的 `build.gradle` 文件，在 `buildscript` 和 `allprojects` 下的 `repositories` 都添加上阿里云仓库 `maven{ url 'http://maven.aliyun.com/nexus/content/groups/public/'}`：

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello15.png)

    修改完成后重新打开 Android Studio -> 构建成功

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello16.png)


### **配置虚拟设备**
* 运行项目前先为项目添加一个虚拟设备

    ![图片加载失败](https://maxwell-blog.cn/image/androidhello17.png)
    ![图片加载失败](https://maxwell-blog.cn/image/androidhello18.png)
    ![图片加载失败](https://maxwell-blog.cn/image/androidhello19.png)
    ![图片加载失败](https://maxwell-blog.cn/image/androidhello20.png)

    这里选择了一个跟自己手机一样的 Android 10.0，后面一路各种 `Next`、`Accept`，等待安装完成。
