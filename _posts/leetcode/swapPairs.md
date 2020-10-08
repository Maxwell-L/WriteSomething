## **24 - 两两交换链表中的节点**
--------------------------------

### **题目描述**
给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。


示例:

给定 1->2->3->4, 你应该返回 2->1->4->3.


链接：[https://leetcode-cn.com/problems/swap-nodes-in-pairs](https://leetcode-cn.com/problems/swap-nodes-in-pairs)



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
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        ListNode res = new ListNode(0);
        ListNode pre = res, n1 = head, n2 = head.next;
        while(n1 != null && n2 != null) {
            pre.next = n2;
            n1.next = n2.next;
            n2.next = n1;
            pre = n1;
            n1 = n1.next;
            n2 = n1 == null ? null : n1.next;
        }
        return res.next;
    }
}
```




[返回目录](https://maxwell-l.github.io/WriteSomething/something/leetcode)