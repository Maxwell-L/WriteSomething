---
layout: post
title: "41 - 缺失的第一个整数"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。


示例 1:

输入: [1,2,0]   
输出: 3   

示例 2:

输入: [3,4,-1,1]   
输出: 2  

示例 3:

输入: [7,8,9,11,12]   
输出: 1
 

提示：

你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。


链接：[https://leetcode-cn.com/problems/first-missing-positive](https://leetcode-cn.com/problems/first-missing-positive)




### **题解**
``` java
class Solution {
    public int firstMissingPositive(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            while(nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, nums[i] - 1, i);
            }
        }
        for(int i = 0; i < nums.length; i++) {
            if(i + 1 != nums[i]) {
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    private void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}
```




[返回目录](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)