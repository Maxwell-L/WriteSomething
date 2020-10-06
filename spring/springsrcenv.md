## **Spring 源码环境搭建**
-----------

### 搭建环境
* **JDK1.8.0_261**
* **Gradle-4.1-bin**
* **Spring-5.0.0.RELEASE**
* **IntelliJ IDEA Ultimate 2020.2**
* **Windows 10**

### **步骤**
* 下载 **Spring** 源码
[https://github.com/spring-projects/spring-framework/releases/tag/v5.0.0.RELEASE](https://github.com/spring-projects/spring-framework/releases/tag/v5.0.0.RELEASE)
* 下载 **Gradle**
[https://downloads.gradle-dn.com/distributions/gradle-4.1-bin.zip](https://downloads.gradle-dn.com/distributions/gradle-4.1-bin.zip)
    + 环境变量配置 **GRADLE_HOME**，并在 **Path** 上添加 `%GRADLE_HOME%\bin`
    + 环境变量添加 **GRADLE_USER_HOME** -> `%GRADLE_HOME%\.gradle`
    + **cmd** 下输入 `gradle -v` 可以看到 **Gradle** 版本信息  
    ![图片加载失败](https://maxwell-l.github.io/WriteSomething/image/springsrcenv1.png)
* 打开 **Spring-5.0.0.RELEASE** 文件夹，打开 gradle/wrapper 目录下的 `gradle-wrapper.properties`文件，将 `distributionUrl` 更改为本地的zip包，例如：

    ```
    distributionUrl=file:///XXX/XXX/gradle-4.1-bin.zip
    ```
    否则编译时会从远程下载zip包，速度慢很多
* 还是在 **Spring-5.0.0.RELEASE** 文件夹中，修改其中的 `build.gradle` 文件，配置阿里云镜像，加快编译速度，如图，加入红框中内容：  
![图片加载失败](https://maxwell-l.github.io/WriteSomething/image/springsrcenv2.png)  
![图片加载失败](https://maxwell-l.github.io/WriteSomething/image/springsrcenv3.png)
* 以管理员身份运行 `Windows PowerShell`，进入 **Spring-5.0.0.RELEASE** 目录下，运行 `.\gradlew :spring-oxm:compileTestJava`，由于网络问题可能失败，多尝试几次即可成功。  
![图片加载失败](https://maxwell-l.github.io/WriteSomething/image/springsrcenv4.png)
* 进入**IDEA**，配置 **GRADLE**，如图，设置 `gradle user home` 存放下载的包，IDEA会自动构建工程  
![图片加载失败](https://maxwell-l.github.io/WriteSomething/image/springsrcenv5.png)  
![图片加载失败](https://maxwell-l.github.io/WriteSomething/image/springsrcenv6.png) 



[返回首页](https://maxwell-l.github.io/WriteSomething)

    