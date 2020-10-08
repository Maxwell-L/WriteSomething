---
layout: post
title: "103 - 二叉树的锯齿形层次遍历"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

例如：
给定二叉树 `[3,9,20,null,null,15,7]`,
```
    3
   / \
  9  20
    /  \
   15   7
```
返回锯齿形层次遍历如下：
```
[
  [3],
  [20,9],
  [15,7]
]
```

链接：[https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal)







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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root != null) {
            LinkedList<TreeNode> nodeQueue = new LinkedList<>();
            nodeQueue.add(root);
            help(result, nodeQueue, 1);
        }
        return result;
    }

    private void help(List<List<Integer>> result, LinkedList<TreeNode> nodeQueue, int level) {
        if(nodeQueue.size() == 0) {
            return;
        }
        LinkedList<TreeNode> nextNodeQueue = new LinkedList<>();
        List<Integer> vals = new ArrayList<>();
        while(nodeQueue.size() > 0) {
            TreeNode node = nodeQueue.pollFirst();
            vals.add(node.val);
            if(level % 2 == 1) {
                if(node.left != null) {
                    nextNodeQueue.addFirst(node.left);
                }
                if(node.right != null) {
                    nextNodeQueue.addFirst(node.right);
                }
            } else {
                if(node.right != null) {
                    nextNodeQueue.addFirst(node.right);
                }
                if(node.left != null) {
                    nextNodeQueue.addFirst(node.left);
                }
            }
        }
        result.add(vals);
        help(result, nextNodeQueue, level + 1);
    }
}
```

[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)