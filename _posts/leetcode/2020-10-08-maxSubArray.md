---
layout: post
title: "53 - 最大子序和"
date: 2020-10-08
categories: LeetCode
---

### **题目描述** 
给定一个整数数组 `nums` ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

示例:
```
输入: [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```
进阶:

如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。


链接：[https://leetcode-cn.com/problems/maximum-subarray](https://leetcode-cn.com/problems/maximum-subarray)




### **题解**
``` java
class Solution {
    public int maxSubArray(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        int prev = nums[0], result = nums[0];
        for(int i = 1; i < nums.length; i++) {
            prev = prev >= 0 ? prev + nums[i] : nums[i];
            if(prev > result) {
                result = prev;
            }
        }
        return result;
    }
}
```


[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)