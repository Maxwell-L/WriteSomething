## **数组中的逆序对**
---------------------
* **题目描述**  
在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P%1000000007。
* **思路**  
通过归并排序将数组从大到小进行排序并在排序过程中将逆序对进行计数。每次往数组中加入右数组的数时记录左数组比该数大的个数。
* **代码**
    ``` java
    public class Solution {
        public int InversePairs(int [] array) {
            int[] help = new int[array.length];
            return merge(array, help, 0, array.length - 1);
        }
        
        private static int merge(int[] array, int[] help, int left, int right) {
            if(left >= right) {
                return 0;
            }
            int mid = (left + right) / 2;
            int l = merge(array, help, left, mid);
            int r = merge(array, help, mid + 1, right);
            int index1 = left, index2 = mid + 1;
            int count = (l + r) % 1000000007;
            for(int i = left; i <= right; i++) {
                if(index2 <= right && (index1 > mid || array[index2] > array[index1])) {
                    help[i] = array[index2++];
                    count += index1 - left;
                    if(count > 1000000007) {
                        count %= 1000000007;
                    }
                } else {
                    help[i] = array[index1++];
                }
            }
            for(int i = left; i <= right; i++) {
                array[i] = help[i];
            }
            return count;
        }
    }
    ```

[返回目录](https://maxwell-l.github.io/WriteSomething/something/swordoffer)