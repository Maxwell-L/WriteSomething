---
layout: post
title: "182 - 查找重复的电子邮箱"
date: 2020-10-08
categories: LeetCode
---


### **题目描述**
编写一个 SQL 查询，查找 Person 表中所有重复的电子邮箱。

示例：
```
+----+---------+
| Id | Email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+
```
根据以上输入，你的查询应返回以下结果：
```
+---------+
| Email   |
+---------+
| a@b.com |
+---------+
```
说明：所有电子邮箱都是小写字母。

链接：[https://leetcode-cn.com/problems/duplicate-emails](https://leetcode-cn.com/problems/duplicate-emails)


### **题解**
``` sql
# Write your MySQL query statement below
SELECT Email
FROM Person
GROUP BY Email
HAVING count(Email) > 1;
```


[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)