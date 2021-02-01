---
layout: post
title: "803. 打砖块"
date: 2021-01-16
categories: LeetCode
author: Maxwell-L
---

### **题目描述**
有一个 `m x n` 的二元网格，其中 `1` 表示砖块，`0` 表示空白。砖块 **稳定**（不会掉落）的前提是：
* 一块砖直接连接到网格的顶部，或者
* 至少有一块相邻（4 个方向之一）砖块 **稳定** 不会掉落时

给你一个数组 `hits` ，这是需要依次消除砖块的位置。每当消除 `hits[i] = (rowi, coli)` 位置上的砖块时，对应位置的砖块（若存在）会消失，然后其他的砖块可能因为这一消除操作而掉落。一旦砖块掉落，它会立即从网格中消失（即，它不会落在其他稳定的砖块上）。

返回一个数组 `result` ，其中 `result[i]` 表示第 `i` 次消除操作对应掉落的砖块数目。

**注意**，消除可能指向是没有砖块的空白位置，如果发生这种情况，则没有砖块掉落。

 

示例 1：
```
输入：grid = [[1,0,0,0],[1,1,1,0]], hits = [[1,0]]
输出：[2]
解释：
网格开始为：
[[1,0,0,0]，
 [1,1,1,0]]
消除 (1,0) 处加粗的砖块，得到网格：
[[1,0,0,0]
 [0,1,1,0]]
两个加粗的砖不再稳定，因为它们不再与顶部相连，也不再与另一个稳定的砖相邻，因此它们将掉落。得到网格：
[[1,0,0,0],
 [0,0,0,0]]
因此，结果为 [2] 。
```

示例 2：
```
输入：grid = [[1,0,0,0],[1,1,0,0]], hits = [[1,1],[1,0]]
输出：[0,0]
解释：
网格开始为：
[[1,0,0,0],
 [1,1,0,0]]
消除 (1,1) 处加粗的砖块，得到网格：
[[1,0,0,0],
 [1,0,0,0]]
剩下的砖都很稳定，所以不会掉落。网格保持不变：
[[1,0,0,0], 
 [1,0,0,0]]
接下来消除 (1,0) 处加粗的砖块，得到网格：
[[1,0,0,0],
 [0,0,0,0]]
剩下的砖块仍然是稳定的，所以不会有砖块掉落。
因此，结果为 [0,0] 。
```
 

提示：
* `m == grid.length`
* `n == grid[i].length`
* `1 <= m, n <= 200`
* `grid[i][j]` 为 `0` 或 `1`
* 1 <= hits.length <= 4 * 10<sup>4</sup>
* `hits[i].length == 2`
* `0 <= xi <= m - 1`
* `0 <= yi <= n - 1`
* 所有 `(xi, yi)` 互不相同



链接：[https://leetcode-cn.com/problems/bricks-falling-when-hit](https://leetcode-cn.com/problems/bricks-falling-when-hit)



### **题解**
``` java
class Solution {
    private int rows;
    private int cols;
    private static final int[][] DIRECTIONS = { {0, 1}, {1, 0}, {0, -1}, {-1, 0} };

    public int[] hitBricks(int[][] grid, int[][] hits) {
        rows = grid.length;
        cols = grid[0].length;
        int n = rows * cols;
        UnionFind unionFind = new UnionFind(n + 1);
        int[][] copy = new int[rows][cols];
        // 复制 grid
        for (int i = 0; i < copy.length; i++) {
            copy[i] = grid[i].clone();
        }
        // 将 hits 的砖块设置为 0
        for (int[] hit : hits) {
            copy[hit[0]][hit[1]] = 0;
        }
        // 将其余砖块加入并查集中
        for (int j = 0; j < cols; j++) {
            if (copy[0][j] == 1) {
                unionFind.union(j, n);
            }
        }
        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (copy[i][j] == 1) {
                    if (copy[i - 1][j] == 1) {
                        unionFind.union(getIndex(i - 1, j), getIndex(i, j));
                    }
                    if (j > 0 && copy[i][j - 1] == 1) {
                        unionFind.union(getIndex(i, j - 1), getIndex(i, j));
                    }
                }
            }
        }
        // 复原打掉的砖块
        int[] res = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            int hitX = hits[i][0];
            int hitY = hits[i][1];
            if (grid[hitX][hitY] == 0) {
                continue;
            }
            int pre = unionFind.getSize(n);
            if (hitX == 0) {
                unionFind.union(hitY, n);
            }
            for (int[] direction : DIRECTIONS) {
                int x = hitX + direction[0];
                int y = hitY + direction[1];
                if (isLegalPosition(x, y) && copy[x][y] == 1) {
                    unionFind.union(getIndex(hitX, hitY), getIndex(x, y));
                }
            }
            int cur = unionFind.getSize(n);
            res[i] = Math.max(0, cur - pre - 1);
            copy[hitX][hitY] = 1;
        }
        return res;
    }

    private int getIndex(int x, int y) {
        return x * cols + y;
    }

    private boolean isLegalPosition(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    private class UnionFind {
        private int[] parent;
        private int[] size;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX];
            }
        }

        public int getSize(int x) {
            int root = find(x);
            return size[root];
        }
    }
}
```



[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)