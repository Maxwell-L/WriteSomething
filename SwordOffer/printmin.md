## **把数组排成最小的数**
-------------------------
* **题目描述**  
输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
* **思路**  
用`Arrays`类中的`sort(T[] a, Comparator<? super T> c)`方法对数据进行排序。其中`Comparator`实现两个数据间的比较方式，将两个数据按照 ab 和 ba 两种方式排列比较大小。
* **代码**  

    ``` java
    import java.util.Arrays;
    import java.util.Comparator;

    public class Solution {
        public String PrintMinNumber(int [] numbers) {
            Integer[] copy = new Integer[numbers.length];
            for(int i = 0; i < numbers.length; i++) {
                copy[i] = numbers[i];
            }
            Arrays.sort(copy, new Comparator<Integer>() {
                public int compare(Integer i1, Integer i2) {
                    String s1 = i1 + "" + i2;
                    String s2 = i2 + "" + i1;
                    for(int i = 0; i < s1.length(); i++) {
                        if(s1.charAt(i) != s2.charAt(i)) {
                            return s1.charAt(i) - s2.charAt(i);
                        }
                    }
                    return 0;
                }
            });
            StringBuilder sb = new StringBuilder();
            for(Integer i : copy) {
                sb.append(i);
            }
            return sb.toString();
        }
    }
    ```

[返回目录](https://maxwell-l.github.io/WriteSomething/something/swordoffer)