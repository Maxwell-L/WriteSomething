---
layout: post
title: "25 - K 个一组翻转链表"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。

如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。

 

示例：

给你这个链表：1->2->3->4->5

当 k = 2 时，应当返回: 2->1->4->3->5

当 k = 3 时，应当返回: 3->2->1->4->5

 

说明：

你的算法只能使用常数的额外空间。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。


链接：[https://leetcode-cn.com/problems/reverse-nodes-in-k-group](https://leetcode-cn.com/problems/reverse-nodes-in-k-group)



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
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null || k <= 1) {
            return head;
        }
        ListNode res = new ListNode(0);
        ListNode pre = res, h = head, t = head;
        while(t != null) {
            int len = 0;
            while(len < k && t != null) {
                t = t.next;
                len++;
            }
            if(len != k) {
                break;
            }   
            // reverse [h, t)
            ListNode a = h, b = a.next, c = b;
            while(c != t) {
                c = b.next;
                b.next = a;
                a = b;
                b = c;
            }
            pre.next = a;
            h.next = c;
            pre = h;
            h = c;
            t = h;
        }
        return res.next == null ? head : res.next;
    }
}
```



[返回目录](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)