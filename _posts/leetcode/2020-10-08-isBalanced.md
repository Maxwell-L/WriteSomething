---
layout: post
title: "110. 平衡二叉树"
date: 2020-10-08
categories: LeetCode
---


### **题目描述**
给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。

示例 1:

给定二叉树 [3,9,20,null,null,15,7]
```
    3
   / \
  9  20
    /  \
   15   7
```
返回 true 。

示例 2:

给定二叉树 [1,2,2,3,3,null,null,4,4]
```
       1
      / \
     2   2
    / \
   3   3
  / \
 4   4
```
返回 false 。


链接：[https://leetcode-cn.com/problems/balanced-binary-tree](https://leetcode-cn.com/problems/balanced-binary-tree)





### **题解**
``` java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null || (root.left == null && root.right == null)) {
            return true;
        }
        if(isBalanced(root.left) && isBalanced(root.right)) {
            return Math.abs(depth(root.left) - depth(root.right)) <= 1;
        }
        return false;
    }

    private int depth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        return 1 + Math.max(depth(root.left), depth(root.right));
    }
}
```

[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)