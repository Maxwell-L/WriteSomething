---
layout: post
title: "Linux安装配置JDK"
date: 2020-10-08
categories: Linux
---

### 环境
* CentOS 7.3
* JDK1.8

### 下载安装
* `wget`从官网下载 JDK1.8
```
wget https://download.oracle.com/otn/java/jdk/8u261-b12/a4634525489241b9a9e1aa73d9e118e6/jdk-8u261-linux-x64.tar.gz?AuthParam=1600775710_6f5155200ebd820c96fd9bfd8a80dd63
```
* 创建 /usr/java 目录
```
mkdir /usr/java
```

* 将 jdk1.8 的包移动到 /usr/java 目录下
```
mv jdk-8u261-linux-x64.tar.gz\?AuthParam\=1600775710_6f5155200ebd820c96fd9bfd8a80dd63 /usr/java
```
* 使用`tar -zxvf`解压
```
tar -zxvf jdk-8u261-linux-x64.tar.gz\?AuthParam\=1600775710_6f5155200ebd820c96fd9bfd8a80dd63
```

### 配置路径
* 打开 /etc/profile
```
vim /etc/profile
```
* 修改内容
1. i 键进入插入模式
2. 添加以下内容:
```
export JAVA_HOME=/usr/java/jdk1.8
export JRE_HOME=${JAVA_HOME}/jre
export CLASSPATH=.:${JAVA_HOME}/lib:${JRE_HOME}/lib:$CLASSPATH
export JAVA_PATH=${JAVA_HOME}/bin:${JRE_HOME}/bin
export PATH=$PATH:${JAVA_PATH}
```
3. 单击ESC键退出插入模式
4. 输入 `:wq` 保存退出
5. 使配置生效
```
source /etc/profile
```

### 检查
```
java -version
```
