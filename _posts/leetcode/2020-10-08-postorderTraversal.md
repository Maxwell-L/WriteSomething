## **145 - 二叉树的后序遍历**
-------------------------

### **题目描述** 
给定一个二叉树，返回它的*后序*遍历。

示例:
```
输入: [1,null,2,3]
   1
    \
     2
    /
   3

输出: [3,2,1]
```
进阶: 递归算法很简单，你可以通过迭代算法完成吗？


链接：[https://leetcode-cn.com/problems/binary-tree-postorder-traversal](https://leetcode-cn.com/problems/binary-tree-postorder-traversal)


### **题解**
``` java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root != null) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode node = root, last = root;
            stack.push(root);
            while(!stack.empty()) {
                node = stack.peek();
                if((node.left == null && node.right == null) || 
                (node.right == null && node.left == last) || 
                node.right == last) {
                    result.add(stack.pop().val);
                    last = node;
                } else {
                    if(node.right != null) {
                        stack.push(node.right);
                    }
                    if(node.left != null) {
                        stack.push(node.left);
                    }
                }
            }
        }
        return result;
    }
}
```


[返回目录](https://maxwell-l.github.io/WriteSomething/something/leetcode)