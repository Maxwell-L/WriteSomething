---
layout: post
title: "176 - 第二高的薪水"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
SQL架构  

编写一个 SQL 查询，获取 `Employee` 表中第二高的薪水（Salary） 。

```
+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
```

例如上述 `Employe`e 表，SQL查询应该返回 `200` 作为第二高的薪水。如果不存在第二高的薪水，那么查询应返回 `null`。


```
+---------------------+
| SecondHighestSalary |
+---------------------+
| 200                 |
+---------------------+
```

链接：[https://leetcode-cn.com/problems/second-highest-salary](https://leetcode-cn.com/problems/second-highest-salary)


### **题解**
``` sql
# Write your MySQL query statement below
SELECT
    (SELECT DISTINCT Salary
    FROM Employee
    ORDER BY Salary DESC
    LIMIT 1 OFFSET 1)
AS SecondHighestSalary;
```


[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)