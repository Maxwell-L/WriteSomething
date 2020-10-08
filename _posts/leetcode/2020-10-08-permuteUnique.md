---
layout: post
title: "47 - 全排列 II"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
给定一个可包含重复数字的序列，返回所有不重复的全排列。

示例:

输入:   
[1,1,2]  
输出:  
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]


链接：[https://leetcode-cn.com/problems/permutations-ii](https://leetcode-cn.com/problems/permutations-ii)




### **题解**
``` java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if(nums != null && nums.length != 0) {
            int[] flag = new int[nums.length];
            Arrays.sort(nums);
            help(result, new ArrayList<Integer>(), nums, flag);
        }
        return result;
    }

    private void help(List<List<Integer>> result, List<Integer> temp, int[] nums, int[] flag) {
        if(temp.size() == nums.length) {
            result.add(temp);
            return;
        }
        for(int i = 0; i < nums.length; i++) {
            if(i > 0 && nums[i] == nums[i - 1] && flag[i - 1] == 0) {
                continue;
            }
            if(flag[i] == 0) {
                List<Integer> newTemp = new ArrayList<>(temp);
                newTemp.add(nums[i]);
                flag[i] = 1;
                help(result, newTemp, nums, flag);
                flag[i] = 0;
            }
        }
    }
}
```


[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)