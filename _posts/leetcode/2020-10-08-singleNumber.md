---
layout: post
title: "260 - 只出现一次的数字 III"
date: 2020-10-08
categories: LeetCode
---

### **题目描述**
给定一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。

示例 :
```
输入: [1,2,1,3,2,5]
输出: [3,5]
```
注意：

1. 结果输出的顺序并不重要，对于上面的例子， [5, 3] 也是正确答案。
2. 你的算法应该具有线性时间复杂度。你能否仅使用常数空间复杂度来实现？


链接：[https://leetcode-cn.com/problems/single-number-iii](https://leetcode-cn.com/problems/single-number-iii)



### **题解**
``` java
class Solution {
    public int[] singleNumber(int[] nums) {
        int[] res = new int[2];
        int bit = 0;
        for(int num : nums) {
            bit ^= num;
        }
        int tmp = bit & (-bit), first = 0;
        for(int num : nums) {
            if((tmp & num) == 0) {
                first ^= num;
            }
        }
        res[0] = first;
        res[1] = bit ^ first;
        return res;
    }
}
```

[返回目录](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)