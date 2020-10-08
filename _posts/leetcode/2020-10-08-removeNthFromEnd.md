---
layout: post
title: "19 - 删除链表的倒数第N个节点"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.
说明：

给定的 n 保证是有效的。

进阶：

你能尝试使用一趟扫描实现吗？


链接：[https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list](https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list)


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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode res = new ListNode(0);
        res.next = head;
        ListNode p1 = res, p2 = res;
        for(int i = 0; i <= n; i++) {
            p2 = p2.next;
        }
        while(p2 != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return res.next;
    }
}
```


[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)