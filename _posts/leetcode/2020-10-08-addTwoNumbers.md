---
layout: post
title: "2 - 两数相加"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

示例：

输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
输出：7 -> 0 -> 8
原因：342 + 465 = 807  

链接：[https://leetcode-cn.com/problems/add-two-numbers](https://leetcode-cn.com/problems/add-two-numbers)

### **题解**
> Java
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = new ListNode(0), p = result;
        int carry = 0;
        while(l1 != null && l2 != null) {
            p.next = new ListNode((l1.val + l2.val + carry) % 10);
            carry = (l1.val + l2.val + carry) / 10;
            l1 = l1.next;
            l2 = l2.next;
            p = p.next;
        }
        if(l1 == null) {
            while(l2 != null) {
                p.next = new ListNode((l2.val + carry) % 10);
                carry = (l2.val + carry) / 10;
                l2 = l2.next;
                p = p.next;
            }
        } else {
            while(l1 != null) {
                p.next = new ListNode((l1.val + carry) % 10);
                carry = (l1.val + carry) / 10;
                l1 = l1.next;
                p = p.next;
            }
        }
        if(carry != 0) {
            p.next = new ListNode(carry);
        }
        return result.next;
    }
}
```

> Go
``` go
/**
 * Definition for singly-linked list.
 * type ListNode struct {
 *     Val int
 *     Next *ListNode
 * }
 */
func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
    head := &ListNode{}
    last := head
    carry := 0
    for l1 != nil || l2 != nil {
        n1, n2 := 0, 0
        if l1 != nil {
            n1 = l1.Val
            l1 = l1.Next
        }
        if l2 != nil {
            n2 = l2.Val
            l2 = l2.Next
        }
        sum := (n1 + n2 + carry) % 10
        carry = (n1 + n2 + carry) / 10
        last.Next = &ListNode{sum, nil}
        last = last.Next
    }
    if carry != 0 {
        last.Next = &ListNode{carry, nil}
    }
    return head.Next
}
```

[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)