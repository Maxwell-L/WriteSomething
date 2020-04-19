## **16 - 最接近的三数之和**
------------------------------

### **题目描述**
给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).


链接：[https://leetcode-cn.com/problems/3sum-closest](https://leetcode-cn.com/problems/3sum-closest)



### **题解**
``` java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < nums.length - 2; i++) {
            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }     
            int left = i + 1, right = nums.length - 1;
            while(left < right) {
                int tmp = nums[i] + nums[left] + nums[right];
                if(tmp == target) {
                    return target;
                }             
                res = Math.abs(res - target) < Math.abs(tmp - target) ? res : tmp;
                if(tmp < target){
                    do {
                        ++left;
                    } while(left < right && nums[left] == nums[left - 1]);
                } else {
                    do {
                        --right;
                    } while(right > left && nums[right] == nums[right + 1]);
                }
            } 
        }
        return res;
    }
}
```


[返回目录](https://maxwell-l.github.io/WriteSomething/something/leetcode)
