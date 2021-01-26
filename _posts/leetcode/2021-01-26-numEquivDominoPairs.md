---
layout: post
title: "1128. 等价多米诺骨牌对的数量"
date: 2021-01-26
categories: LeetCode
author: Maxwell-L
---

### **题目描述**
给你一个由一些多米诺骨牌组成的列表 `dominoes`。

如果其中某一张多米诺骨牌可以通过旋转 `0` 度或 `180` 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。

形式上，`dominoes[i] = [a, b]` 和 `dominoes[j] = [c, d]` 等价的前提是 `a==c` 且 `b==d`，或是 `a==d` 且 `b==c`。

在 `0 <= i < j < dominoes.length` 的前提下，找出满足 `dominoes[i]` 和 `dominoes[j]` 等价的骨牌对 `(i, j)` 的数量。

 

示例：
```
输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
输出：1 
```

提示：
* `1 <= dominoes.length <= 40000`
* `1 <= dominoes[i][j] <= 9`


链接：https://leetcode-cn.com/problems/number-of-equivalent-domino-pairs


### **题解**
``` java
class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int[] domino : dominoes) {
            int tmp = Math.min(domino[0], domino[1]) * 10 + Math.max(domino[0], domino[1]);
            Integer count = null;
            if ((count = map.get(tmp)) != null) {
                res += count;
            }
            map.put(tmp, count == null ? 1 : count + 1);
        }
        return res;
    }
}
```



[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)