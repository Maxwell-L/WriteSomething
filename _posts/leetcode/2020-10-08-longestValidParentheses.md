---
layout: post
title: "32 - 最长有效括号"
date: 2020-10-08
categories: LeetCode
---


### **题目描述**
给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

示例 1:

输入: "(()"  
输出: 2  
解释: 最长有效括号子串为 "()"  

示例 2:

输入: ")()())"  
输出: 4  
解释: 最长有效括号子串为 "()()"


链接：[https://leetcode-cn.com/problems/longest-valid-parentheses](https://leetcode-cn.com/problems/longest-valid-parentheses)



### **题解**
``` java
class Solution {
    public int longestValidParentheses(String s) {
        int[] dp = new int[s.length()];
        int longest = 0;
        for(int i = 1; i < s.length(); i++) {
            if(s.charAt(i) == ')') {
                if(s.charAt(i - 1) == '(') {
                    dp[i] = i >= 2 ? dp[i - 2] + 2 : 2;
                } else {
                    if(i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                        if(i - dp[i - 1] - 2 >= 0) {
                            dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2];
                        } else {
                            dp[i] = dp[i - 1] + 2;
                        }
                    }    
                }
            }
            longest = Math.max(longest, dp[i]);
        }
        return longest;
    }
}
```





[返回目录](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)