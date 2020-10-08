---
layout: post
title: "54 - 螺旋矩阵"
date: 2020-10-08
categories: LeetCode
---

### **题目描述** 
给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

示例 1:
```
输入:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
输出: [1,2,3,6,9,8,7,4,5]
```
示例 2:
```
输入:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
输出: [1,2,3,4,8,12,11,10,9,5,6,7]
```

链接：[https://leetcode-cn.com/problems/spiral-matrix](https://leetcode-cn.com/problems/spiral-matrix)



### **题解**
``` java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        if(matrix != null && matrix.length != 0 && matrix[0].length != 0) {
            int left = 0, right = matrix[0].length - 1;
            int top = 0, bottom = matrix.length - 1;
            while(left <= right && top <= bottom) {
                for(int i = left; i <= right; i++) {
                    result.add(matrix[top][i]);
                }
                for(int i = top + 1; i <= bottom; i++) {
                    result.add(matrix[i][right]);
                }
                for(int i = right - 1; i > left && bottom > top; i--) {
                    result.add(matrix[bottom][i]);
                }
                for(int i = bottom; i > top && right > left; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
                right--;
                top++;
                bottom--;
            }
        }
        return result;
    }
}
```


[返回目录](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)