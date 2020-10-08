## **5 - 最长回文子串**
----------------------

### **题目描述**
给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。  

示例 2：

输入: "cbbd"
输出: "bb"

链接：[https://leetcode-cn.com/problems/longest-palindromic-substring](https://leetcode-cn.com/problems/longest-palindromic-substring)



### **题解**

``` java
class Solution {
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 2) {
            return s;
        }
        int maxLen = 1, start = 0, end = 1;
        boolean dp[][] = new boolean[s.length()][s.length()];
        for(int right = 1; right < s.length(); ++right) {
            for(int left = 0; left < right; ++left) {
                if(s.charAt(left) == s.charAt(right) && (right - left <= 2 || dp[left + 1][right - 1])) {
                    dp[left][right] = true;
                    if(maxLen < right - left + 1) {
                        maxLen = right - left + 1;
                        start = left;
                        end = right + 1;
                    }
                }
            }
        }
        return s.substring(start, end);
    }
}
```


[返回目录](https://maxwell-l.github.io/WriteSomething/something/leetcode)