---
layout: post
title: "22 - 括号生成"
date: 2020-10-08
categories: LeetCode
---


### **题目描述**
数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

 

示例：

输入：n = 3  
输出：[  
       "((()))",  
       "(()())",  
       "(())()",  
       "()(())",  
       "()()()"  
     ]


链接：[https://leetcode-cn.com/problems/generate-parentheses](https://leetcode-cn.com/problems/generate-parentheses)




### **题解**
``` java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        char[] parenthesis = new char[2 * n];
        help(res, n, 0, 0, parenthesis);
        return res;
    }

    private void help(List<String> res, int n, int left, int right, char[] parenthesis) {
        if(left == n && right == n) {
            res.add(String.valueOf(parenthesis));
            return;
        }
        if(left < n) {
            parenthesis[left + right] = '(';
            help(res, n, left + 1, right, parenthesis);
        }
        if(right < left) {
            parenthesis[left + right] = ')';
            help(res, n, left, right + 1, parenthesis);
        }
    }
}
```





[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)