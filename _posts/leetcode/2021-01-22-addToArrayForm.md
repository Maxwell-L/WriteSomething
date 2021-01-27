---
layout: post
title: "989. 数组形式的整数加法"
date: 2021-01-22
categories: LeetCode
author: Maxwell-L
---

### **题目描述**
对于非负整数 `X` 而言，`X` 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 `X = 1231`，那么其数组形式为 `[1,2,3,1]`。

给定非负整数 `X` 的数组形式 `A`，返回整数 `X+K` 的数组形式。

 
示例 1：
```
输入：A = [1,2,0,0], K = 34
输出：[1,2,3,4]
解释：1200 + 34 = 1234
```
示例 2：
```
输入：A = [2,7,4], K = 181
输出：[4,5,5]
解释：274 + 181 = 455
```
示例 3：
```
输入：A = [2,1,5], K = 806
输出：[1,0,2,1]
解释：215 + 806 = 1021
```
示例 4：
```
输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
输出：[1,0,0,0,0,0,0,0,0,0,0]
解释：9999999999 + 1 = 10000000000
```
 

**提示：**
* `1 <= A.length <= 10000`
* `0 <= A[i] <= 9`
* `0 <= K <= 10000`
* 如果 `A.length > 1`，那么 `A[0] != 0`


链接：[https://leetcode-cn.com/problems/add-to-array-form-of-integer](https://leetcode-cn.com/problems/add-to-array-form-of-integer)


### **题解**
``` java
class Solution {
    public List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> result = new ArrayList<>();
        int carry = 0;
        for (int i = A.length - 1; i >= 0; i--) {
            if (K > 0) {
                carry = carry + K % 10;
                K /= 10;
            }
            A[i] += carry;
            carry = A[i] < 10 ? 0 : 1;
            A[i] %= 10;
            if (K == 0 && carry == 0) {
                break;
            }
        }
        K += carry;
        while (K != 0) {
            result.add(0, K % 10);
            K /= 10;
        }
        for (int a : A) {
            result.add(a);
        }
        return result;
    }
}
```



[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)