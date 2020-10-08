## **丑数**
-----------------
* **题目描述**  
把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
* **思路**  
每一个新的丑数都是原来的某一个丑数乘 2 或 3 或 5，用一个数组保存范围内的丑数，三个`int`型变量分别记录三个丑数的下标，这三个丑数分别对应乘 2、乘 3、乘 5 后比当前丑数大的丑数，用作下一次循环的判断，下一个丑数既是这三者乘对应的数后的最小值。
* **代码**  

    ``` java
    public class Solution {
        public int GetUglyNumber_Solution(int index) {
            if(index <= 0) {
                return 0;
            }
            int[] uglyNumber = new int[index];
            uglyNumber[0] = 1;
            int two = 0, three = 0, five = 0;
            for(int i = 1; i < index; i++) {
                int tmp = Math.min(uglyNumber[two] * 2, 
                                Math.min(uglyNumber[three] * 3, uglyNumber[five] * 5));
                uglyNumber[i] = tmp;
                while(uglyNumber[two] * 2 <= tmp) {
                    ++two;
                }
                while(uglyNumber[three] * 3 <= tmp) {
                    ++three;
                }
                while(uglyNumber[five] * 5 <= tmp) {
                    ++five;
                }
            }
            return uglyNumber[index - 1];
        }
    }
    ```

[返回目录](https://maxwell-l.github.io/WriteSomething/something/swordoffer)