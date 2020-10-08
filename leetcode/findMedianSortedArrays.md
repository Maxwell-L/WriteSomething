## **4 - 寻找两个有序数组的中位数**
-----------------------------------

### **题目描述**
给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 nums1 和 nums2 不会同时为空。

示例 1:

nums1 = [1, 3]
nums2 = [2]

则中位数是 2.0  

示例 2:

nums1 = [1, 2]
nums2 = [3, 4]

则中位数是 (2 + 3) / 2 = 2.5  

链接：[https://leetcode-cn.com/problems/median-of-two-sorted-arrays](https://leetcode-cn.com/problems/median-of-two-sorted-arrays)


### **题解**
``` java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int k1 = (nums1.length + nums2.length + 1) / 2;
        int k2 = (nums1.length + nums2.length + 2) / 2;
        return (getKth(nums1, 0, nums2, 0, k1) + getKth(nums1, 0, nums2, 0, k2)) * 0.5;
        
    }

    private int getKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
        if(nums1.length - start1 > nums2.length - start2) {
            return getKth(nums2, start2, nums1, start1, k);
        }
        if(start1 >= nums1.length) {
            return nums2[start2 + k - 1];
        }
        if(k == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }
        int m = k / 2, index1 = start1 + m - 1, index2 = start2 + m - 1;
        if(index1 >= nums1.length) {
            index1 = nums1.length - 1;
        }
        if(nums1[index1] < nums2[index2]) {
            k -= index1 - start1 + 1;
            start1 = index1 + 1;
        } else {
            k -= index2 - start2 + 1;
            start2 = index2 + 1;
        }
        return getKth(nums1, start1, nums2, start2, k);
    }
}
```


[返回目录](https://maxwell-l.github.io/WriteSomething/something/leetcode)