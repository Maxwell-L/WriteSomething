---
layout: post
title: "34 - 在排序数组中查找元素的第一个和最后一个位置"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。

你的算法时间复杂度必须是 O(log n) 级别。

如果数组中不存在目标值，返回 [-1, -1]。

示例 1:

输入: nums = [5,7,7,8,8,10], target = 8  
输出: [3,4]  

示例 2:

输入: nums = [5,7,7,8,8,10], target = 6  
输出: [-1,-1]


链接：[https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array)



### **题解**
``` java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int[] res = new int[] {-1, -1};
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                res[0] = mid;
                right = mid - 1;
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        left = 0; right = nums.length - 1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(nums[mid] == target) {
                res[1] = mid;
                left = mid + 1;
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return res;
    }
}
```




[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)