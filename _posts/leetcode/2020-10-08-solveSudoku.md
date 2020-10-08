---
layout: post
title: "37 - 解数独"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
编写一个程序，通过已填充的空格来解决数独问题。

一个数独的解法需遵循如下规则：

1. 数字 1-9 在每一行只能出现一次。
2. 数字 1-9 在每一列只能出现一次。
3. 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。


空白格用 '.' 表示。

![图片加载失败](https://maxwell-blog.cn/image/solvesudoku1.png)

一个数独。

![图片加载失败](https://maxwell-blog.cn/image/solvesudoku2.png)

答案被标成红色。

Note:
* 给定的数独序列只包含数字 1-9 和字符 '.' 。
* 你可以假设给定的数独只有唯一解。
* 给定数独永远是 9x9 形式的。


链接：[https://leetcode-cn.com/problems/sudoku-solver](https://leetcode-cn.com/problems/sudoku-solver)



### **题解**
``` java
class Solution {
    public void solveSudoku(char[][] board) {
        Map<Integer, Set<Integer>> rowMap = new HashMap<>();
        Map<Integer, Set<Integer>> colMap = new HashMap<>();
        Map<Integer, Set<Integer>> gridMap = new HashMap<>();
        for(int i = 0; i < 9; i++) {
            rowMap.put(i, new HashSet<Integer>());
            colMap.put(i, new HashSet<Integer>());
            gridMap.put(i, new HashSet<Integer>());
        }
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.'){
                    continue;
                }
                int num = (int) (board[i][j] - '0');
                rowMap.get(i).add(num);
                colMap.get(j).add(num);
                gridMap.get((i / 3) * 3 + j / 3).add(num);
            }
        }
        help(board, 0, 0, rowMap, colMap, gridMap);
    }

    private boolean help(char[][] board, int row, int col, 
                    Map<Integer, Set<Integer>> rowMap, 
                    Map<Integer, Set<Integer>> colMap,
                    Map<Integer, Set<Integer>> gridMap) {
        if(row == 9 || col == 9) {
            return true;
        }
        if(board[row][col] != '.') {
            return col == 8 ? help(board, row + 1, 0, rowMap, colMap, gridMap) : help(board, row, col + 1, rowMap, colMap, gridMap);
        } else {
            for(int n = 1; n <= 9; n++) {
                if(!rowMap.get(row).contains(n) && 
                !colMap.get(col).contains(n) &&
                !gridMap.get((row / 3) * 3 + col / 3).contains(n)) {
                    board[row][col] = (char) (n + '0');
                    rowMap.get(row).add(n);
                    colMap.get(col).add(n);
                    gridMap.get((row / 3) * 3 + col / 3).add(n);
                    boolean flag = col == 8 ? help(board, row + 1, 0, rowMap, colMap, gridMap) : help(board, row, col + 1, rowMap, colMap, gridMap);
                    if(flag)  {
                        return true;
                    }
                    rowMap.get(row).remove(n);
                    colMap.get(col).remove(n);
                    gridMap.get((row / 3) * 3 + col / 3).remove(n); 
                    board[row][col] = '.';   
                }
            }
            return false;
        }
    }
}
```



[返回目录](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)