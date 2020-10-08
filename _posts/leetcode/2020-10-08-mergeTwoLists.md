---
layout: post
title: "21 - 合并两个有序链表"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

示例：

输入：1->2->4, 1->3->4  
输出：1->1->2->3->4->4


链接：[https://leetcode-cn.com/problems/merge-two-sorted-lists](https://leetcode-cn.com/problems/merge-two-sorted-lists)



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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(0);
        ListNode p = res, p1 = l1, p2 = l2;
        while(p1 != null && p2 != null) {
            if(p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }
        p.next = p1 != null ? p1 : p2;
        return res.next;
    }
}
```


[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)