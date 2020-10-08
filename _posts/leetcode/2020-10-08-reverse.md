## **7 - 整数反转**
---------------------

### **题目描述**
给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:

输入: 123
输出: 321  

示例 2:

输入: -123
输出: -321  

示例 3:

输入: 120
输出: 21  

注意:

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2<sup>31</sup>,  2<sup>31</sup> − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。


链接：[https://leetcode-cn.com/problems/reverse-integer](https://leetcode-cn.com/problems/reverse-integer)


### **题解**
``` java
class Solution {
    public int reverse(int x) {
        if(x == Integer.MIN_VALUE) {
            return 0;
        }
        int flag = x > 0 ? 1 : -1;
        x = Math.abs(x);
        int res = 0, compare = Integer.MAX_VALUE / 10;
        while(x > 0 && res <= compare) {
            res = res * 10 + x % 10;
            x = x / 10;
        }
        if(res <= 0 || x > 0) {
            return 0;
        }
        return res * flag;
    }
}
```

[返回目录](https://maxwell-l.github.io/WriteSomething/something/leetcode)