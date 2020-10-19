---
layout: post
title: "剑指 Offer 07. 重建二叉树"
date: 2020-10-19
categories: SwordOffer
author: Maxwell-L
---

### **题目描述**
输入某二叉树的前序遍历和中序遍历的结果，请重建该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

 

例如，给出
```
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
```
返回如下的二叉树：
```
    3
   / \
  9  20
    /  \
   15   7
```

限制：

`0 <= 节点个数 <= 5000`


链接：[https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof](https://leetcode-cn.com/problems/zhong-jian-er-cha-shu-lcof)

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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    private TreeNode buildTree(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight) {
        if(preLeft == preRight) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[preLeft]);
        int i = inLeft;
        while(inorder[i] != preorder[preLeft]) {
            i++;
        }
        node.left = buildTree(preorder, preLeft + 1, preLeft + 1 + (i - inLeft), inorder, inLeft, i);
        node.right = buildTree(preorder, preLeft + 1 + (i - inLeft), preRight, inorder, i + 1, inRight);
        return node;
    }
}
```

[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode)
