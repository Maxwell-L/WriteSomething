## **1 - 两数之和**
------------------------

### **题目描述**
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。  
  
示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]  

链接：https://leetcode-cn.com/problems/two-sum

### **题解**
  
``` java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            Integer t = null;
            if((t = hm.get(target - nums[i])) != null) {
                res[0] = t;
                res[1] = i;
                return res;
            }
            hm.put(nums[i], i);
        }
        return res;
    }
}
```

  
[返回目录](https://maxwell-l.github.io/WriteSomething/something/leetcode)