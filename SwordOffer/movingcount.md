## **机器人的运动范围**
----------------------
* **题目描述**  
地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，每一次只能向左，右，上，下四个方向移动一格，但是不能进入行坐标和列坐标的数位之和大于k的格子。 例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。但是，它不能进入方格（35,38），因为3+5+3+8 = 19。请问该机器人能够达到多少个格子？
* **思路**  
  + 设计一个函数计算当前坐标的数位之和。
  + 设置一个`visit`数组记录已经过的结点，从(0, 0)开始采用回溯法分别查看上、下、左、右的位置是否符合要求，是则进入递归，并将该结点对应的`visit`位置设为`true`，结果返回回溯的结果和加上 1（自身的位置）。
* **代码**  

    ``` java
    public class Solution {
        public int movingCount(int threshold, int rows, int cols) {
            boolean[][] visit = new boolean[rows][cols];
            if(threshold >= 0) {
                return grid(threshold, visit, 0, 0);
            }
            return 0;
        }
        
        private static int grid(int threshold, boolean[][] visit, int row, int col) {
            visit[row][col] = true;
            int count = 0;
            if(row > 0 && sum(row - 1, col) <= threshold && !visit[row - 1][col]) {
                count += grid(threshold, visit, row - 1, col);
            }
            if(row < visit.length - 1 && sum(row + 1, col) <= threshold && !visit[row + 1][col]) {
                count += grid(threshold, visit, row + 1, col);
            }
            if(col > 0 && sum(row, col - 1) <= threshold && !visit[row][col - 1]) {
                count += grid(threshold, visit, row, col - 1);
            }
            if(col < visit[0].length - 1 && sum(row, col + 1) <= threshold && !visit[row][col + 1]) {
                count += grid(threshold, visit, row, col + 1);
            }
            return count + 1;
        }
        
        private static int sum(int row, int col) {
            int res = 0;
            while(row != 0) {
                res += row % 10;
                row /= 10;
            }
            while(col != 0) {
                res += col % 10;
                col /= 10;
            }
            return res;
        }
    }
    ```

[返回目录](https://maxwell-l.github.io/WriteSomething/something/swordoffer)