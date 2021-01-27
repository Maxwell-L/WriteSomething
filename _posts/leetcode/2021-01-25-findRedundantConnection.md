---
layout: post
title: "684. 冗余连接"
date: 2021-01-25
categories: LeetCode
author: Maxwell-L
---

### **题目描述**
在本问题中, 树指的是一个连通且无环的**无向**图。

输入一个图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。

结果图是一个以`边`组成的二维数组。每一个边的元素是一对`[u, v]` ，满足 `u < v`，表示连接顶点 `u` 和 `v` 的无向图的边。

返回一条可以删去的边，使得结果图是一个有着N个节点的树。如果有多个答案，则返回二维数组中最后出现的边。答案边 `[u, v]` 应满足相同的格式 `u < v`。

示例 1：
```
输入: [[1,2], [1,3], [2,3]]
输出: [2,3]
解释: 给定的无向图为:
  1
 / \
2 - 3
```

示例 2：
```
输入: [[1,2], [2,3], [3,4], [1,4], [1,5]]
输出: [1,4]
解释: 给定的无向图为:
5 - 1 - 2
    |   |
    4 - 3
```

**注意**:
* 输入的二维数组大小在 3 到 1000。
* 二维数组中的整数在1到N之间，其中N是输入数组的大小。


链接：[https://leetcode-cn.com/problems/redundant-connection](https://leetcode-cn.com/problems/redundant-connection)



### **题解**
``` java
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int[] result = new int[2];
        UnionFind unionFind = new UnionFind(edges.length + 1);
        for (int[] edge : edges) {
            boolean flag = unionFind.union(edge[0], edge[1]);
            if (!flag) {
                result[0] = edge[0];
                result[1] = edge[1];
            }
        }
        return result;
    }

    public class UnionFind {
        private int[] parent;

        public UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (x != parent[x]) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
                return true;
            }
            return false;
        }
    }
}
```


[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)