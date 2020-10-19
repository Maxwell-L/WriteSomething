---
layout: post
title: "剑指 Offer 05. 替换空格"
date: 2020-10-19
categories: SwordOffer
author: Maxwell-L
---

### **题目描述**
请实现一个函数，把字符串 `s` 中的每个空格替换成"%20"。

 

示例 1：
```
输入：s = "We are happy."
输出："We%20are%20happy."
```

限制：

`0 <= s 的长度 <= 10000`


链接：[https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof](https://leetcode-cn.com/problems/ti-huan-kong-ge-lcof)


### **题解**
``` java
class Solution {
    public String replaceSpace(String s) {
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == ' ') {
                res.append("%20");
            } else {
                res.append(c);
            }
        }
        return res.toString();
    }
}
```

[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode)