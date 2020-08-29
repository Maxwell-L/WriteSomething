## **39 - ����ܺ�**
----------------------

### **��Ŀ����**
����һ�����ظ�Ԫ�ص�����?candidates?��һ��Ŀ����?target?���ҳ�?candidates?�����п���ʹ���ֺ�Ϊ?target?����ϡ�

candidates?�е����ֿ����������ظ���ѡȡ��

˵����

�������֣�����?target��������������  
�⼯���ܰ����ظ�����ϡ�?  

ʾ��?1��

���룺candidates = [2,3,6,7], target = 7,  
����⼯Ϊ��  
[[7], [2,2,3]]  

ʾ��?2��

���룺candidates = [2,3,5], target = 8,  
����⼯Ϊ��  
[[2,2,2,2], [2,3,3], [3,5]]
?

��ʾ��

1 <= candidates.length <= 30  
1 <= candidates[i] <= 200  
candidate �е�ÿ��Ԫ�ض��Ƕ�һ�޶��ġ�  
1 <= target <= 500  


���ӣ�[https://leetcode-cn.com/problems/combination-sum](https://leetcode-cn.com/problems/combination-sum)



### **���**
``` java
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if(candidates != null && candidates.length != 0) {
            Arrays.sort(candidates);
            help(result, new ArrayList<Integer>(), 0, candidates, target);
        }
        return result;
    }

    private void help(List<List<Integer>> result, List<Integer> temp, int index, int[] candidates, int target) {
        if(index == candidates.length || candidates[index] > target) {
            return;
        }
        for(int i = index; i < candidates.length; i++) {
            if(candidates[i] > target) {
                return;
            }
            List<Integer> newTemp = new ArrayList<>(temp);
            newTemp.add(candidates[i]);
            if(candidates[i] == target) {
                result.add(newTemp);
                return;
            }
            help(result, newTemp, i, candidates, target - candidates[i]);
        }
    }
}
```


[����Ŀ¼](https://maxwell-l.github.io/WriteSomething/something/leetcode)