## **40 - 组合总和 II**
----------------------

### **题目描述**
给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在每个组合中只能使用一次。

说明：

所有数字（包括目标数）都是正整数。  
解集不能包含重复的组合。   

示例 1:

输入: candidates = [10,1,2,7,6,1,5], target = 8,  
所求解集为:
[[1, 7], [1, 2, 5], [2, 6], [1, 1, 6]]

示例 2:

输入: candidates = [2,5,2,1,2], target = 5,  
所求解集为:
[[1,2,2], [5]]


链接：[https://leetcode-cn.com/problems/combination-sum-ii](https://leetcode-cn.com/problems/combination-sum-ii)



### **题解**
``` java
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates != null && candidates.length != 0) {
            Arrays.sort(candidates);
            help(result, new ArrayList<Integer>(), 0, candidates, target);
        }
        return result;
    }

    private void help(List<List<Integer>> result, List<Integer> temp, int index, int[] candidates, int target) {
        if(index == candidates.length) {
            return;
        }
        for(int i = index; i < candidates.length; i++) {
            if(target < candidates[i]) {
                return;
            }
            if(i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            List<Integer> newTemp = new ArrayList<>(temp);
            newTemp.add(candidates[i]);
            if(target == candidates[i]) {
                result.add(newTemp);
                return;
            }
            help(result, newTemp, i + 1, candidates, target - candidates[i]);
        }
    }
}
```


[返回目录](https://maxwell-l.github.io/WriteSomething/something/leetcode)