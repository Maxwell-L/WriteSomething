---
layout: post
title: "49 - 字母异位词分组"
date: 2020-10-08
categories: LeetCode
---


### **题目描述**
给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。

示例:
```
输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```
说明：

* 所有输入均为小写字母。
* 不考虑答案输出的顺序。


链接：[https://leetcode-cn.com/problems/group-anagrams](https://leetcode-cn.com/problems/group-anagrams)



### **题解**
``` java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for(String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String s = String.valueOf(arr);
            if(map.get(s) == null) {
                map.put(s, new ArrayList<>());
            }
            map.get(s).add(str);
        }
        map.forEach((key, value) -> {
            result.add(value);
        });
        return result;
    }
}
```

[返回目录](https://maxwell-blog.cn/leetcode/2020/10/08/leetcode.html)