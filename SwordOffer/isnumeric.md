## **表示数值的字符串**
----------------------
* **题目描述**  
请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。例如，字符串"+100", "5e2", "-123", "3.1416"和"-1E-16"都表示数值。 但是"12e", "1a3.14", "1.2.3", "+-5"和"12e+4.3"都不是。
* **思路**  
将整个字符串分成三部分进行判断：小数点前、小数点后、e/E之后，每部分若格式正确则返回该部分结束后的`index`，否则返回 -1。
* **代码**  

    ``` java
    public class Solution {
        public boolean isNumeric(char[] str) {
            if(str.length == 0) {
                return false;
            }
            int index = beforePoint(str, 0);
            if(index < str.length && index > 0 && str[index] == '.') {
                index = afterPoint(str, index + 1);
            }
            if(index < str.length && index > 0) {
                index = afterE(str, index + 1);
            }
            return index > 0;
        }
        
        private static int beforePoint(char[] str, int index) {
            if(str[index] == '+' || str[index] == '-') {
                index++;
            }
            while(index < str.length && str[index] >= '0' && str[index] <= '9') {
                index++;
            }
            if(index == str.length || str[index] == '.' || str[index] == 'e' || str[index] == 'E') {
                return index;
            } 
            return -1;
        }
        
        private static int afterPoint(char[] str, int index) {
            if(index >= str.length) {
                return -1;
            }
            while(index < str.length && str[index] >= '0' && str[index] <= '9') {
                index++;
            }
            if(index == str.length || str[index] == 'e' || str[index] == 'E') {
                return index;
            } 
            return -1;
        }
        
        private static int afterE(char[] str, int index) {
            if(index >= str.length) {
                return -1;
            }
            if(str[index] == '+' || str[index] == '-') {
                index++;
            }
            while(index < str.length && str[index] >= '0' && str[index] <= '9') {
                index++;
            }
            return index == str.length ? index : -1;
        }
    }
    ```

[返回目录](https://maxwell-l.github.io/WriteSomething/something/swordoffer)