---
layout: post
title: "628 - 三个数的最大乘积"
date: 2021-01-20
categories: LeetCode
author: Maxwell-L
---

### **题目描述**
给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。

示例 1:
```
输入: [1,2,3]
输出: 6
```
示例 2:
```
输入: [1,2,3,4]
输出: 24
```

**注意**:
* 给定的整型数组长度范围是[3,10<sup>4</sup>]，数组中所有的元素范围是[-1000, 1000]。
* 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。


链接：[https://leetcode-cn.com/problems/maximum-product-of-three-numbers](https://leetcode-cn.com/problems/maximum-product-of-three-numbers)


### **题解**
``` java
class Solution {
    public int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = min1;
        int max1 = Integer.MIN_VALUE, max2 = max1, max3 = max1;
        for (int num : nums) {
            if (num < min1) {
                min2 = min1;
                min1 = num;
            } else if (num < min2) {
                min2 = num;
            }
            if (num > max1) {
                max3 = max2;
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max3 = max2;
                max2 = num;
            } else if (num > max3) {
                max3 = num;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
}
```

[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)