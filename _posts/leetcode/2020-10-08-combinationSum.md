---
layout: post
title: "39 - 组合总和"
date: 2020-10-08
categories: LeetCode
---


### **题目描述**
给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以无限制重复被选取。

说明：

所有数字（包括 target）都是正整数。  
解集不能包含重复的组合。   

示例 1：

输入：candidates = [2,3,6,7], target = 7,  
所求解集为：  
[[7], [2,2,3]]  

示例 2：

输入：candidates = [2,3,5], target = 8,  
所求解集为：  
[[2,2,2,2], [2,3,3], [3,5]]
 

提示：

1 <= candidates.length <= 30  
1 <= candidates[i] <= 200  
candidate 中的每个元素都是独一无二的。  
1 <= target <= 500  


链接：[https://leetcode-cn.com/problems/combination-sum](https://leetcode-cn.com/problems/combination-sum)



### **题解**
``` java
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates != null && candidates.length != 0) {
            Arrays.sort(candidates);
            help(result, new ArrayList<Integer>(), 0, candidates, target);
        }
        return result;
    }

    private void help(List<List<Integer>> result, List<Integer> temp, int index, int[] candidates, int target) {
        if(index == candidates.length || candidates[index] > target) {
            return;
        }
        for(int i = index; i < candidates.length; i++) {
            if(candidates[i] > target) {
                return;
            }
            List<Integer> newTemp = new ArrayList<>(temp);
            newTemp.add(candidates[i]);
            if(candidates[i] == target) {
                result.add(newTemp);
                return;
            }
            help(result, newTemp, i, candidates, target - candidates[i]);
        }
    }
}
```


[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)