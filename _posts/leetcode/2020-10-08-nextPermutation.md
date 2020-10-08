---
layout: post
title: "31 - 下一个排列"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
1,2,3 → 1,3,2   
3,2,1 → 1,2,3   
1,1,5 → 1,5,1   


链接：[https://leetcode-cn.com/problems/next-permutation](https://leetcode-cn.com/problems/next-permutation)



### **题解**
``` java
class Solution {
    public void nextPermutation(int[] nums) {
        if(nums == null || nums.length < 2) {
            return;
        }
        int left = 0, right = nums.length - 1;
        for(int i = right; i > 0; i--) {
            if(nums[i] > nums[i - 1]) {
                left = i;
                while(right > left && nums[right] <= nums[i - 1]) {
                    right--;
                }
                swap(nums, i - 1, right);
                right = nums.length - 1;
                break;
            } 
        }
        while(left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    private void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}
```




[返回目录](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)