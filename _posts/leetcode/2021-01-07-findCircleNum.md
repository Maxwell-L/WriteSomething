--- 
layout: post
title: "547. 省份数量"
date: 2021-01-07
categories: LeetCode
author: Maxwell-L
---

### **题目描述**
有 `n` 个城市，其中一些彼此相连，另一些没有相连。如果城市 `a` 与城市 `b` 直接相连，且城市 `b` 与城市 `c` 直接相连，那么城市 `a` 与城市 `c` 间接相连。

**省份** 是一组直接或间接相连的城市，组内不含其他没有相连的城市。

给你一个 `n x n` 的矩阵 `isConnected` ，其中 `isConnected[i][j] = 1` 表示第 `i` 个城市和第 `j` 个城市直接相连，而 `isConnected[i][j] = 0` 表示二者不直接相连。

返回矩阵中 **省份** 的数量。

示例 1：

![图片加载失败](https://maxwell-blog.cn/image/findCircleNum1.png)

```
输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
输出：2
```
示例 2：

![图片加载失败](https://maxwell-blog.cn/image/findCircleNum2.png)

```
输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
输出：3
```

提示：
* `1 <= n <= 200`
* `n == isConnected.length`
* `n == isConnected[i].length`
* `isConnected[i][j] 为 1 或 0`
* `isConnected[i][i] == 1`
* `isConnected[i][j] == isConnected[j][i]`


链接：[https://leetcode-cn.com/problems/number-of-provinces](https://leetcode-cn.com/problems/number-of-provinces)




### **题解**
``` java
class Solution {
    public int findCircleNum(int[][] isConnected) {
        boolean[] visited = new boolean[isConnected.length];
        int res = 0;
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                res++;
                dfs(isConnected, visited, i);
            }
        }
        return res;
    }

    private void dfs(int[][] isConnected, boolean[] visited, int index) {
        visited[index] = true;
        for (int i = 0; i < isConnected.length; i++) {
            if (i != index && isConnected[index][i] == 1 && !visited[i]) {
                dfs(isConnected, visited, i);
            }
        }
        return;
    }
}
```

[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)