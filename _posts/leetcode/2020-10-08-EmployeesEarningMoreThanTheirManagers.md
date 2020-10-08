---
layout: post
title: "181 - 超过经理收入的员工"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
Employee 表包含所有员工，他们的经理也属于员工。每个员工都有一个 Id，此外还有一列对应员工的经理的 Id。
```
+----+-------+--------+-----------+
| Id | Name  | Salary | ManagerId |
+----+-------+--------+-----------+
| 1  | Joe   | 70000  | 3         |
| 2  | Henry | 80000  | 4         |
| 3  | Sam   | 60000  | NULL      |
| 4  | Max   | 90000  | NULL      |
+----+-------+--------+-----------+
```
给定 Employee 表，编写一个 SQL 查询，该查询可以获取收入超过他们经理的员工的姓名。在上面的表格中，Joe 是唯一一个收入超过他的经理的员工。
```
+----------+
| Employee |
+----------+
| Joe      |
+----------+
```

链接：[https://leetcode-cn.com/problems/employees-earning-more-than-their-managers/](https://leetcode-cn.com/problems/employees-earning-more-than-their-managers/)

### **题解**
``` sql
# Write your MySQL query statement below
SELECT t1.Name Employee
FROM Employee t1 LEFT JOIN Employee t2
ON t1.managerId = t2.id
WHERE t1.Salary > t2.Salary;
```



[返回目录](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)