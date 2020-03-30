## **滑动窗口的最大值**
------------------------
* **题目描述**  
给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个： {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
* **思路**  
使用一个双端队列来保存当前最大值的下标，对于每一个元素，比较当前队列尾部记录的下标对应的值与当前值的大小关系，若当前值大，则将队尾记录弹出继续比较，否则将自身的下标入队。（此时过期的下标已经被记录，弹出不影响；而未过期的下标在窗口内已有当前值作为窗口内更大的值，故弹出也不影响）。  
遍历数组的循环内判断当前遍历的长度是否已经达到窗口大小，若是则判断双端队列队首的元素是否过期，过期弹出继续判断，否则将队首记录的下标的值添加进结果的 ArrayList。
* **代码**

    ``` java
    import java.util.ArrayList;
    import java.util.LinkedList;
    public class Solution {
        public ArrayList<Integer> maxInWindows(int [] num, int size) {
            ArrayList<Integer> res = new ArrayList<>();
            if(size > 0) {
                LinkedList<Integer> deque = new LinkedList<>();
                for(int i = 0; i < num.length; i++) {
                    while(!deque.isEmpty() && num[deque.getLast()] < num[i]) {
                        deque.pollLast();
                    }
                    deque.offer(i);
                    if(i >= size - 1) {
                        while(i - deque.getFirst() >= size) {
                            deque.pollFirst();
                        }
                        res.add(num[deque.getFirst()]);
                    }
                }
            }
            return res;
        }
    }
    ```

[返回目录](https://maxwell-l.github.io/WriteSomething/something/swordoffer)