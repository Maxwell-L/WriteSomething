---
layout: post
title: "643. 子数组最大平均数 I"
date: 2021-02-06
categories: LeetCode
author: Maxwell-L
---

### **题目描述**
给定 `n` 个整数，找出平均数最大且长度为 `k` 的连续子数组，并输出该最大平均数。

 

示例：
```
输入：[1,12,-5,-6,50,3], k = 4
输出：12.75
解释：最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
```
 

提示：
* 1 <= `k` <= `n` <= 30,000。
* 所给数据范围 [-10,000，10,000]。


链接：[https://leetcode-cn.com/problems/maximum-average-subarray-i](https://leetcode-cn.com/problems/maximum-average-subarray-i)



### **题解**
``` java
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double sum = 0.0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        double avg = sum / k;
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i - k] + nums[i];
            if (avg < sum / k) {
                avg = sum / k;
            }
        }
        return avg;
    }
}
```


[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)