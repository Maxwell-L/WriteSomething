## **177 - 第N高的薪水**
---------------------------

### **题目描述**
SQL架构  

编写一个 SQL 查询，获取 `Employee` 表中第n高的薪水（Salary） 。


|Id|Salary|
|:-|:-|
|1|100|
|2|200|
|3|300|

例如上述 `Employee` 表，n = 2 时，应返回第二高的薪水 `200`。如果不存在第 n 高的薪水，那么查询应返回 `null`。


|getNthHighestSalary(2)|
|:-|
|200|


链接：[https://leetcode-cn.com/problems/nth-highest-salary](https://leetcode-cn.com/problems/nth-highest-salary)


### **题解**
``` sql
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    SET N := N - 1;
  RETURN (
      # Write your MySQL query statement below.
      SELECT (
          SELECT DISTINCT Salary
          FROM Employee
          ORDER BY Salary DESC
          LIMIT 1 OFFSET N
      )
  );
END
```


[返回目录](https://maxwell-l.github.io/WriteSomething/something/leetcode)