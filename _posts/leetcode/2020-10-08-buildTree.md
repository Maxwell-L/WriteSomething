---
layout: post
title: "105 - 从前序与中序遍历序列构造二叉树"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

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

链接：[https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal)






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
        if(preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode node = new TreeNode(preorder[0]);
        int mid = 0;
        for(int i = 0; i < preorder.length; i++) {
            if(preorder[0] == inorder[i]){
                mid = i;
                break;
            }
        }
        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, mid + 1);
        int[] rightPreorder = Arrays.copyOfRange(preorder, mid + 1, preorder.length);
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, mid);
        int[] rightInorder = Arrays.copyOfRange(inorder, mid + 1, inorder.length);
        node.left = buildTree(leftPreorder, leftInorder);
        node.right = buildTree(rightPreorder, rightInorder);
        return node;
    }
}
```

[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)