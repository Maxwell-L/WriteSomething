---
layout: post
title: "264 - 丑数 II"
date: 2020-10-15
categories: LeetCode
author: Maxwell-L
---

### **题目描述**
编写一个程序，找出第 n 个丑数。

丑数就是质因数只包含 2, 3, 5 的正整数。

示例:

```
输入: n = 10
输出: 12
解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。
```

说明:  
1. `1` 是丑数。
2. `n` 不超过1690。


链接：[https://leetcode-cn.com/problems/ugly-number-ii](https://leetcode-cn.com/problems/ugly-number-ii)


### **题解**
``` java
class Solution {
    private static final int N = 1690;

    private static final int[] UGLY_NUMBER;
    
    static {
        UGLY_NUMBER = new int[N];
        int i2 = 0, i3 = 0, i5 = 0;
        UGLY_NUMBER[0] = 1;
        for(int i = 1; i < N; i++) {
            UGLY_NUMBER[i] = Math.min(UGLY_NUMBER[i2] * 2, 
            Math.min(UGLY_NUMBER[i3] * 3, UGLY_NUMBER[i5] * 5));
            if(UGLY_NUMBER[i] == UGLY_NUMBER[i2] * 2) {
                i2++;
            }
            if(UGLY_NUMBER[i] == UGLY_NUMBER[i3] * 3) {
                i3++;
            }
            if(UGLY_NUMBER[i] == UGLY_NUMBER[i5] * 5) {
                i5++;
            }
        }
    }

    public int nthUglyNumber(int n) {
        return UGLY_NUMBER[n - 1];
    }
}
```


[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)