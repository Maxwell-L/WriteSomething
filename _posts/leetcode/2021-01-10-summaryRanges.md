---
layout: post
title: "228 - 汇总区间"
date: 2021-01-10
categories: LeetCode
author: Maxwell-L
---

### **题目描述**
给定一个无重复元素的有序整数数组 `nums` 。

返回 恰好覆盖数组中所有数字 的 最小有序 区间范围列表。也就是说，`nums` 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 `nums` 的数字 `x` 。

列表中的每个区间范围 `[a,b]` 应该按如下格式输出：
* `"a->b"` ，如果 `a != b`
* `"a"` ，如果 `a == b`
 

示例 1：
```
输入：nums = [0,1,2,4,5,7]
输出：["0->2","4->5","7"]
解释：区间范围是：
[0,2] --> "0->2"
[4,5] --> "4->5"
[7,7] --> "7"
```

示例 2：
```
输入：nums = [0,2,3,4,6,8,9]
输出：["0","2->4","6","8->9"]
解释：区间范围是：
[0,0] --> "0"
[2,4] --> "2->4"
[6,6] --> "6"
[8,9] --> "8->9"
```

示例 3：
```
输入：nums = []
输出：[]
```

示例 4：
```
输入：nums = [-1]
输出：["-1"]
```
示例 5：
```
输入：nums = [0]
输出：["0"]
```

提示：
* `0 <= nums.length <= 20`
* -2<sup>31</sup> <= nums[i] <= 2<sup>31</sup> - 1
* `nums` 中的所有值都 **互不相同**
* `nums` 按升序排列


链接：[https://leetcode-cn.com/problems/summary-ranges](https://leetcode-cn.com/problems/summary-ranges)


### **题解**
``` java
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums.length != 0) {
            int start = nums[0];
            for (int i = 0; i < nums.length; i++) {
                if (i != 0 && nums[i] - nums[i - 1] != 1) {
                    String str = String.valueOf(start);
                    if (start != nums[i - 1]) {
                        str += "->";
                        str += String.valueOf(nums[i - 1]);
                    }
                    result.add(str);
                    start = nums[i];
                }
                if (i + 1 == nums.length) {
                    String str = String.valueOf(start);
                    if (start != nums[i]) {
                        str += "->";
                        str += String.valueOf(nums[i]);
                    }
                    result.add(str);
                }
            }
        }
        return result;
    }
}
```

[返回](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)