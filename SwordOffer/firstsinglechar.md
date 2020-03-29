## **字符流中第一个不重复的字符**
--------------------------------
* **题目描述**  
请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
* **思路**  
用 HashMap 记录字符出现的次数，用 LinkedList 保存字符输入的顺序；字符流插入时更新 HashMap 并将字符保存至队列，查找第一个不重复字符时检查队列头部的字符是否仅出现一次，若是则返回，若不是则弹出并继续检查下一个。
* **代码**  
    ``` java
    import java.util.LinkedList;
    import java.util.HashMap;

    public class Solution {
        private LinkedList<Character> queue = new LinkedList<>();
        private HashMap<Character, Integer> hs = new HashMap<>();
        //Insert one char from stringstream
        public void Insert(char ch) {
            queue.offer(ch);
            Integer i = null;
            if((i = hs.get(ch)) != null) {
                hs.put(ch, i + 1);
            } else {
                hs.put(ch, 1);
            }
        }
    //return the first appearence once char in current stringstream
        public char FirstAppearingOnce() {
            int count = 0;
            while(!queue.isEmpty()) {
                if((count = hs.get(queue.peek())) == 1) {
                    return queue.peek();
                }
                queue.poll();
            }
            return '#';
        }
    }
    ```

[返回目录](https://maxwell-l.github.io/WriteSomething/something/swordoffer)