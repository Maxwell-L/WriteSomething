---
layout: post
title: "239 - 滑动窗口最大值"
date: 2021-01-04
categories: LeetCode
author: Maxwell-L
---

### **题目描述**
给你一个整数数组 `nums`，有一个大小为 `k` 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 `k` 个数字。滑动窗口每次只向右移动一位。

返回滑动窗口中的最大值。

 

示例 1：
```
输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
输出：[3,3,5,5,6,7]
解释：
滑动窗口的位置                最大值
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```
示例 2：
```
输入：nums = [1], k = 1
输出：[1]
```
示例 3：
```
输入：nums = [1,-1], k = 1
输出：[1,-1]
```
示例 4：
```
输入：nums = [9,11], k = 2
输出：[11]
```
示例 5：
```
输入：nums = [4,-2], k = 2
输出：[4]
```

提示：
* 1 <= nums.length <= 10<sup>5</sup>
* -10<sup>4</sup> <= nums[i] <= 10<sup>4</sup>
* 1 <= k <= nums.length


链接：[https://leetcode-cn.com/problems/sliding-window-maximum](https://leetcode-cn.com/problems/sliding-window-maximum)


### **题解**
``` java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                res[index++] = nums[deque.peekFirst()];
            }
        }
        return res;
    }
}
```

[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)
