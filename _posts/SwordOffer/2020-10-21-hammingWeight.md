---
layout: post
title: "剑指 Offer 15. 二进制中1的个数"
date: 2020-10-21
categories: SwordOffer
author: Maxwell-L
---

### **题目描述**
请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。

示例 1：
```
输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
```
示例 2：
```
输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
```
示例 3：
```
输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```

链接：[https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof](https://leetcode-cn.com/problems/er-jin-zhi-zhong-1de-ge-shu-lcof)

### **题解**
``` java
public class Solution {
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int res = 0;
        while(n != 0) {
            if((n & 1) == 1) {
                res++;
            }
            n >>>= 1;
        }
        return res;
    }
}
```

[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode)