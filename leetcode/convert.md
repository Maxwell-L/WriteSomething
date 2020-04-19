## **6 - Z 字形变换**
---------------------

### **题目描述**
将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

L&nbsp;&nbsp;&nbsp;&nbsp;C&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I&nbsp;&nbsp;&nbsp;R  
E&nbsp;T&nbsp;O&nbsp;E&nbsp;S&nbsp;I&nbsp;&nbsp;I&nbsp;G  
E&nbsp;&nbsp;&nbsp;&nbsp;D&nbsp;&nbsp;&nbsp;&nbsp;H&nbsp;&nbsp;N  
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

请你实现这个将字符串进行指定行数变换的函数：

string convert(string s, int numRows);  

示例 1:

输入: s = "LEETCODEISHIRING", numRows = 3
输出: "LCIRETOESIIGEDHN"  

示例 2:

输入: s = "LEETCODEISHIRING", numRows = 4
输出: "LDREOEIIECIHNTSG"
解释:

L&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;D&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;R  
E&nbsp;&nbsp;&nbsp;&nbsp;O&nbsp;E&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I&nbsp;&nbsp;I  
E&nbsp;C&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;I H&nbsp;&nbsp;&nbsp;&nbsp;N  
T&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;S&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;G  


链接：[https://leetcode-cn.com/problems/zigzag-conversion](https://leetcode-cn.com/problems/zigzag-conversion)



### **题解**
``` java
class Solution {
    public String convert(String s, int numRows) {
        if(numRows <= 1 || s.length() <= numRows) {
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int dist = numRows * 2 - 2;
        for(int i = 0; i < numRows; i++) {
            int index = i, mid = dist - index * 2;
            while(index < s.length()) {
                sb.append(s.charAt(index));
                if(mid != dist && mid != 0 && index + mid < s.length()) {
                    sb.append(s.charAt(index + mid));
                }
                index += dist;
            }
        }
        return sb.toString();
    }
}
```

[返回目录](https://maxwell-l.github.io/WriteSomething/something/leetcode)