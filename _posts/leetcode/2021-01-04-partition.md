---
layout: post
title: "86 - 分隔链表"
date: 2021-01-04
categories: LeetCode
author: Maxwell-L
---

### **题目描述**
给你一个链表和一个特定值 `x` ，请你对链表进行分隔，使得所有小于 `x` 的节点都出现在大于或等于 `x` 的节点之前。

你应当保留两个分区中每个节点的初始相对位置。

 

示例：
```
输入：head = 1->4->3->2->5->2, x = 3
输出：1->2->2->4->3->5
```


链接：[https://leetcode-cn.com/problems/partition-list](https://leetcode-cn.com/problems/partition-list)


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
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0), big = new ListNode(0);
        ListNode p = small, q = big;
        while (head != null) {
            if (head.val < x) {
                p.next = head;
                p = p.next;
            } else {
                q.next = head;
                q = q.next;
            }
            head = head.next;
        }
        q.next = null;
        p.next = big.next;
        return small.next;
    }
}
```

[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)