## **144 - 二叉树的前序遍历**
-------------------------

### **题目描述** 
给定一个二叉树，返回它的*前序*遍历。

示例:
```
输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [1,2,3]
```
进阶: 递归算法很简单，你可以通过迭代算法完成吗？


链接：[https://leetcode-cn.com/problems/binary-tree-preorder-traversal](https://leetcode-cn.com/problems/binary-tree-preorder-traversal)


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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while(node != null || !stack.empty()) {
            if(node == null) {
                node = stack.pop();
            }
            if(node.right != null) {
                stack.push(node.right);
            }
            result.add(node.val);
            node = node.left;
        }
        return result;
    }
}
```


[返回目录](https://maxwell-l.github.io/WriteSomething/something/leetcode)