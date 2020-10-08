---
layout: post
title: "3 - 无重复字符的最长子串"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。

示例 1:

输入: "abcabcbb"  
输出: 3   
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。  

示例 2:

输入: "bbbbb"  
输出: 1  
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
  
示例 3:

输入: "pwwkew"  
输出: 3  
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。  

链接：[https://leetcode-cn.com/problems/longest-substring-without-repeating-characters](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters)



### **题解**
``` java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> hm = new HashMap<>();
        int maxLen = 0, i = 0, j = 0;
        while(i < s.length() && j < s.length()) {
            Integer t = null;
            if((t = hm.get(s.charAt(j))) != null && t >= i) {
                maxLen = maxLen > (j - i) ? maxLen : j - i;
                i = t + 1;
            } 
            hm.put(s.charAt(j), j);
            j++;
        }
        maxLen = maxLen > (j - i) ? maxLen : j - i;
        return maxLen;
    }
}
```


[返回目录](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)