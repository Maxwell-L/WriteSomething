---
layout: post
title: "Win10 下安装 MySQL"
date: 2020-11-12
categories: MySQL
author: Maxwell-L
---

### **环境**
* **Windows 10**
* **MySQL 8.0.22**

### **下载**
* [下载地址](https://dev.mysql.com/downloads/mysql/)

* 官网上下载 **MySQL 8.0.22** 压缩包

    ![图片加载失败](https://maxwell-blog.cn/image/installmysql1.png)

* 下载完成后解压

### **安装配置**
* 打开 MySQL 解压目录，新增 **my.ini** 文件

    ![图片加载失败](https://maxwell-blog.cn/image/installmysql2.png)

    文件内容如下：

    ``` ini
    [mysqld]
    # 设置3306端口
    port=3306
    # 设置mysql的安装目录，一定要与安装路径保持一致
    basedir=D:\devtools\mysql-8.0.22-winx64
    # 设置mysql数据库的数据的存放目录
    datadir=D:\devtools\mysql-8.0.22-winx64\Data
    # 允许最大连接数
    max_connections=200
    # 允许连接失败的次数。
    max_connect_errors=10
    # 服务端使用的字符集默认为utf8mb4
    character-set-server=utf8mb4
    # 创建新表时将使用的默认存储引擎
    default-storage-engine=INNODB
    # 默认使用“mysql_native_password”插件认证
    # mysql_native_password
    default_authentication_plugin=mysql_native_password
    [mysql]
    # 设置mysql客户端默认字符集
    default-character-set=utf8mb4
    [client]
    # 设置mysql客户端连接服务端时默认使用的端口
    port=3306
    default-character-set=utf8mb4
    ```
* 以管理员模式运行 **cmd**，进入 MySQL 安装目录下的 bin 文件夹，输入 `mysqld --initialize --console`

    ![图片加载失败](https://maxwell-blog.cn/image/installmysql3.png)

    可以看到红框处生成了一个密码，需要先复制下来以备后面登录使用

* 输入 `mysqld --install` 安装 MySQL，如图显示则安装成功

    ![图片加载失败](https://maxwell-blog.cn/image/installmysql4.png)

* 输入 `net start mysql` 启动 MySQL

    ![图片加载失败](https://maxwell-blog.cn/image/installmysql5.png)

* 连接 MySQL，输入 `mysql -u root -p[密码]`，密码为前面几步自动生成的密码，与 `-p` 之间不需要留空格

    ![图片加载失败](https://maxwell-blog.cn/image/installmysql6.png)

    如图显示则连接成功

* 自动生成的密码十分难记，可以自定义密码，在上一步已连接状态下输入
    ``` sql
    ALTER USER 'root'@'localhost' IDENTIFIED BY '自己定义的密码'
    ```

    修改完后输入 `exit` 退出

    ![图片加载失败](https://maxwell-blog.cn/image/installmysql7.png)

* 使用更改后的密码重新连接 MySQL

    ![图片加载失败](https://maxwell-blog.cn/image/installmysql8.png)