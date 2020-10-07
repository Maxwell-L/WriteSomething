## **类加载器 & 双亲委托模型**
---------------------------------

### **类加载器 - ClassLoader**
* 负责查找、加载、校验字节码的应用程序（java.lang.ClassLoader)
* JVM四级类加载器
    + 启动类加载器（Bootstrap）
    + 扩展类加载器（Extension)
    + 应用类加载器（App)
    + 用户自定义加载器（Plugin） 

![图片加载失败](https://maxwell-blog.cn/image/classloader.png)

### **双亲委托模型**
* 类加载器双亲委托
    + 判断类是否已经加载
    + 若无，找父加载器加载
    + 若再无，由当前加载器加载

源码：  
``` java 
protected Class<?> loadClass(String name, boolean resolve)
        throws ClassNotFoundException
    {
        synchronized (getClassLoadingLock(name)) {
            // First, check if the class has already been loaded
            Class<?> c = findLoadedClass(name);
            if (c == null) {
                long t0 = System.nanoTime();
                try {
                    if (parent != null) {
                        c = parent.loadClass(name, false);
                    } else {
                        c = findBootstrapClassOrNull(name);
                    }
                } catch (ClassNotFoundException e) {
                    // ClassNotFoundException thrown if class not found
                    // from the non-null parent class loader
                }

                if (c == null) {
                    // If still not found, then invoke findClass in order
                    // to find the class.
                    long t1 = System.nanoTime();
                    c = findClass(name);

 					// this is the defining class loader; record the stats
                    sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                    sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                    sun.misc.PerfCounter.getFindClasses().increment();
                }
            }
            if (resolve) {
                resolveClass(c);
            }
            return c;
        }
    }

```
  
[返回首页](http://maxwell-blog.cn)