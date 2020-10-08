---
layout: post
title: "17 - 电话号码的字母组合"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

![图片加载失败](https://maxwell-blog.cn/image/letterCombinations.png)

示例:

输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].  

说明:
尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。


链接：[https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number)



### **题解**
``` java
class Solution {
    public List<String> letterCombinations(String digits) {
        String[] letters = new String[] {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> res = new ArrayList<>();
        if(digits != null && digits.length() != 0) {
            char[] combination = new char[digits.length()];
            help(digits, 0, letters, combination, res);
        }
        return res;
    }
    
    private static void help(String digits, int index, String[] letters, char[] combination, List<String> res) {
        if(index == digits.length()) {
            res.add(String.valueOf(combination));
            return;
        }
        String letter = letters[(digits.charAt(index) - '0') - 2];
        for(int i = 0; i < letter.length(); i++) {
            combination[index] = letter.charAt(i);
            help(digits, index + 1, letters, combination, res);
        }
    }
}
```





[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)