---
layout: post
title: "剑指 Offer 06. 从尾到头打印链表"
date: 2020-10-19
categories: SwordOffer
author: Maxwell-L
---

### **题目描述**
输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

 

示例 1：
```
输入：head = [1,3,2]
输出：[2,3,1]
```

限制：

`0 <= 链表长度 <= 10000`


链接：[https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof](https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof)

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
    public int[] reversePrint(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode p = head;
        while(p != null) {
            stack.push(p.val);
            p = p.next;
        }
        int[] res = new int[stack.size()];
        for(int i = 0; i < res.length; i++) {
            res[i] = stack.pop();
        }
        return res;
    }
}
```

[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode)
