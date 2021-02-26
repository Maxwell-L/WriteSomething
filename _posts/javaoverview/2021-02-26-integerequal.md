---
layout: post
title: "100 == 100 ?, 1000 != 1000 ?"
date: 2021-02-26
categories: Java
author: Maxwell-L
---

``` java
public class IntegerCacheDemo {
    public static void main(String[] args) {
        Integer i1 = 100;
        Integer i2 = 100;
        System.out.println("i1 == i2: " + (i1 == i2));
        Integer i3 = 1000;
        Integer i4 = 1000;
        System.out.println("i3 == i4: " + (i3 == i4));
    }
}
```

如代码所示：将常量 `100` 和 `1000` 分别赋予 `i1`、`i2`、`i3`、`i4`，运行得到的结果却是一个为 `true` 一个为 `false`：

```
i1 == i2: true
i3 == i4: false
```

在 IDEA 中设置断点，进入调试模式，`Step Into`：  
![图片加载失败](https://maxwell-blog.cn/image/integercache1.png)

![图片加载失败](https://maxwell-blog.cn/image/integercache2.png)

可以看到在进行赋值操作时调用了 `Integer.java` 类下的 `valueOf(int i)` 方法，方法中 `IntegerCache` 为 `Integer` 的静态内部类：
``` java
private static class IntegerCache {
    static final int low = -128;
    static final int high;
    static final Integer cache[];

    static {
        // high value may be configured by property
        int h = 127;
        String integerCacheHighPropValue =
            sun.misc.VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
        if (integerCacheHighPropValue != null) {
            try {
                int i = parseInt(integerCacheHighPropValue);
                i = Math.max(i, 127);
                // Maximum array size is Integer.MAX_VALUE
                h = Math.min(i, Integer.MAX_VALUE - (-low) -1);
            } catch( NumberFormatException nfe) {
                // If the property cannot be parsed into an int, ignore it.
            }
        }
        high = h;

        cache = new Integer[(high - low) + 1];
        int j = low;
        for(int k = 0; k < cache.length; k++)
            cache[k] = new Integer(j++);

        // range [-128, 127] must be interned (JLS7 5.1.7)
        assert IntegerCache.high >= 127;
    }

    private IntegerCache() {}
}
```
观察到上图中 `IntegerCache.low` 的值为 `-128`，`IntegerCache.high` 的值可以通过**JVM参数**进行设置(`-Djava.lang.Integer.IntegerCache.high=xxx`)，但若小于 `127` 则该值的设置无效，仍为 `127`，若太大(超过 `Integer.MAX_VALUE - (128) - 1`)，则为 `Integer.MAX_VALUE - (128) - 1`。

故当直接对 `Integer` 对象赋值时，如 `Integer i1 = x`，如果 `x` 在 `IntegerCache.low` 和 `IntegerCache.high` 之间时，会直接返回 `cache` 数组中的 `Integer` 对象，此时显现出来的就是 `100 == 100`，而超过这个范围时，`valueOf(int i)` 方法会直接 **new** 一个新的 `Integer` 对象，显现出来的就是 `1000 != 1000`，**本质上是因为前者比较的是同一个 `Integer` 对象，而后者比较的是不同的 `Integer` 对象。**