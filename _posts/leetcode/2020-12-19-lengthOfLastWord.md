---
layout: post
title: "58. 最后一个单词的长度"
date: 2020-12-19
categories: LeetCode
author: Maxwell-L
---

### **题目描述**
给定一个仅包含大小写字母和空格 `' '` 的字符串 `s`，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。

如果不存在最后一个单词，请返回 0 。

说明：一个单词是指仅由字母组成、不包含任何空格字符的 **最大子字符串**。

 

示例:
```
输入: "Hello World"
输出: 5
```

链接：[https://leetcode-cn.com/problems/length-of-last-word](https://leetcode-cn.com/problems/length-of-last-word)

### **题解**
``` java
class Solution {
    public int lengthOfLastWord(String s) {
        String[] words = s.split(" ");
        return words.length == 0 ? 0 : words[words.length - 1].length();
    }
}
```

[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)