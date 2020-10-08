---
layout: post
title: "14 - 最长公共前缀"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

示例 1:

输入: ["flower","flow","flight"]  
输出: "fl"  

示例 2:

输入: ["dog","racecar","car"]  
输出: ""  
解释: 输入不存在公共前缀。  

说明:

所有输入只包含小写字母 a-z 。


链接：[https://leetcode-cn.com/problems/longest-common-prefix](https://leetcode-cn.com/problems/longest-common-prefix)



### **题解**
``` java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0) {
            return "";
        }
        int index = 0, minLen = Integer.MAX_VALUE;
        for(String str : strs) {
            minLen = minLen < str.length() ? minLen : str.length();
        }
        StringBuilder sb = new StringBuilder();
        boolean flag = true;
        char c = ' ';
        while(flag && minLen-- > 0) {
            for(int i = 0; i < strs.length; i++) {
                if(i == 0) {
                    c = strs[0].charAt(index);
                } else {
                    if(c != strs[i].charAt(index)) {
                        flag = false;
                        break;
                    }
                }
            }
            if(flag) {
                sb.append(c);
                ++index;
            }   
        }
        return sb.toString();
    }
}
```


[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)