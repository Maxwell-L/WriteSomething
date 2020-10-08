## **23 - 合并K个排序链表**
---------------------------

### **题目描述**
合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:

输入:  
[  
  1->4->5,  
  1->3->4,  
  2->6  
]  
输出: 1->1->2->3->4->4->5->6


链接：[https://leetcode-cn.com/problems/merge-k-sorted-lists](https://leetcode-cn.com/problems/merge-k-sorted-lists)



### **题解**
``` java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>((ListNode l1, ListNode l2) -> l1.val - l2.val);
        ListNode res = new ListNode(0), p = res;
        for(int i = 0; i < lists.length;i++) {
            if(lists[i] != null) {
                priorityQueue.offer(lists[i]);
            }
        }
        while(!priorityQueue.isEmpty()) {
            p.next = priorityQueue.poll();
            p = p.next;
            if(p.next != null) {
                priorityQueue.offer(p.next);
            }
        }
        return res.next;
    }
}
```



[返回目录](https://maxwell-l.github.io/WriteSomething/something/leetcode)