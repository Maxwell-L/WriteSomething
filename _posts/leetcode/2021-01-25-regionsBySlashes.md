---
layout: post
title: "959 - 由斜杠划分区域"
date: 2021-01-25
categories: LeetCode
author: Maxwell-L
---

### **题目描述**
在由 1 x 1 方格组成的 N x N `网格 grid` 中，每个 1 x 1 方块由 `/`、`\` 或空格构成。这些字符会将方块划分为一些共边的区域。

（请注意，反斜杠字符是转义的，因此 `\` 用 `"\\"` 表示。）。

返回区域的数目。

 

示例 1：
```
输入：
[
  " /",
  "/ "
]
输出：2
解释：2x2 网格如下：
```
![图片加载失败](https://maxwell-blog.cn/image/regionsBySlashes1.png)


示例 2：
```
输入：
[
  " /",
  "  "
]
输出：1
解释：2x2 网格如下：
```
![图片加载失败](https://maxwell-blog.cn/image/regionsBySlashes2.png)

示例 3：
```
输入：
[
  "\\/",
  "/\\"
]
输出：4
解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
2x2 网格如下：
```
![图片加载失败](https://maxwell-blog.cn/image/regionsBySlashes3.png)

示例 4：
```
输入：
[
  "/\\",
  "\\/"
]
输出：5
解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
2x2 网格如下：
```
![图片加载失败](https://maxwell-blog.cn/image/regionsBySlashes4.png)

示例 5：
```
输入：
[
  "//",
  "/ "
]
输出：3
解释：2x2 网格如下：
```
![图片加载失败](https://maxwell-blog.cn/image/regionsBySlashes5.png)
 

**提示**：
1. `1 <= grid.length == grid[0].length <= 30`
2. `grid[i][j]` 是 `'/'`、`'\'`、或 `' '`。


链接：[https://leetcode-cn.com/problems/regions-cut-by-slashes](https://leetcode-cn.com/problems/regions-cut-by-slashes)



### **题解**
``` java
class Solution {
    public int regionsBySlashes(String[] grid) {
        int n = grid.length;
        int[] parent = new int[n * n * 4];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                if (i < n - 1) {
                    union(parent, index * 4 + 1, (index + n) * 4);
                }
                if (j < n - 1) {
                    union(parent, index * 4 + 3, (index + 1) * 4 + 2);
                }
                if (grid[i].charAt(j) == '/') {
                    union(parent, index * 4, index * 4 + 2);
                    union(parent, index * 4 + 1, index * 4 + 3);
                } else if (grid[i].charAt(j) == '\\') {
                    union(parent, index * 4, index * 4 + 3);
                    union(parent, index * 4 + 1, index * 4 + 2);
                } else {
                    union(parent, index * 4, index * 4 + 1);
                    union(parent, index * 4, index * 4 + 2);
                    union(parent, index * 4, index * 4 + 3);
                }
            }
        }
        Set<Integer> rootSet = new HashSet<>();
        for (int i = 0; i < parent.length; i++) {
            rootSet.add(find(parent, i));
        }
        return rootSet.size();
    }

    public int find(int[] parent, int x) {
        if (x != parent[x]) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }

    public void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        parent[rootX] = rootY;
    }
}
```


[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)