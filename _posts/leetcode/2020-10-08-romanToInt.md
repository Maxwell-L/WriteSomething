---
layout: post
title: "13 - 罗马数字转整数"
date: 2020-10-08
categories: LeetCode
---


### **题目描述**
罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。

字符|数值
:-:|:-:  
I|1  
V|5  
X|10  
L|50  
C|100  
D|500  
M|1000  

例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。  
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和90。     
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。  
给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。

示例 1:

输入: 3  
输出: "III"  

示例 2:

输入: 4  
输出: "IV"  

示例 3:

输入: 9  
输出: "IX"  

示例 4:

输入: 58  
输出: "LVIII"  
解释: L = 50, V = 5, III = 3.  

示例 5:

输入: 1994  
输出: "MCMXCIV"  
解释: M = 1000, CM = 900, XC = 90, IV = 4.


链接：[https://leetcode-cn.com/problems/integer-to-roman](https://leetcode-cn.com/problems/integer-to-roman)


### **题解**
``` java
class Solution {
    public int romanToInt(String s) {
        HashMap<Character, Integer> hm = new HashMap<>() {
            {
                put('I', 1); put('V', 5); put('X', 10); put('L', 50);
                put('C', 100); put('D', 500); put('M', 1000);
            }
        };
        int res = 0, pre = Integer.MAX_VALUE;
        for(int i = 0; i < s.length(); i++) {
            int cur = hm.get(s.charAt(i));
            res += cur;
            if(pre < cur) {
                res -= 2 * pre;
            }
            pre = cur;
        }
        return res;
    }
}
```


[返回目录](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)