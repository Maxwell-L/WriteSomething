## **30 - 串联所有单词的子串**
----------------------------

### **题目描述**
给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。

注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。

 

示例 1：

输入：
  s = "barfoothefoobarman",  
  words = ["foo","bar"]  
输出：[0,9]  
解释：
从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
输出的顺序不重要, [9,0] 也是有效答案。  

示例 2：

输入：
  s = "wordgoodgoodgoodbestword",  
  words = ["word","good","best","word"]  
输出：[]


链接：[https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words](https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words)



### **题解**
``` java
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if(s == null || words == null || s.length() == 0 || words.length == 0) {
            return res;
        }
        int len = words[0].length();
        if(s.length() < len * words.length) {
            return res;
        }
        HashMap<String, Integer> hm = new HashMap<>();
        // hm 保存 words 中的单词和出现次数
        for(String word : words) {
            Integer t = null;
            if((t = hm.get(word)) == null) {
                hm.put(word, 1);
            } else {
                hm.put(word, t + 1);
            }
        } 
        // 以 i 为开始索引
        for(int i = 0; i < len; i++) {
            HashMap<String, Integer> hmCur = new HashMap<>();
            int begin = i, count = 0;
            // 步长 len
            for(int j = i; j + len <= s.length(); j += len) {
                String str = s.substring(j, j + len);
                Integer totalT = null;
                // words 不存在 str，所有数据清空，begin 转移到下一个单词起始位置
                if((totalT = hm.get(str)) == null) {
                    begin = j + len;
                    count = 0;
                    hmCur.clear();
                    continue;
                }  
                Integer curT = null;
                // str 存在且 hmCur 中此单词未满
                if((curT = hmCur.get(str)) == null || curT < totalT) {
                    if(curT == null) {
                        hmCur.put(str, 1);
                    } else {
                        hmCur.put(str, curT + 1);
                    }
                    count++;
                    // 匹配成功
                    if(count == words.length) {
                        res.add(begin);
                        // 删除第一个单词，begin 更改
                        String rmWord = s.substring(begin, begin + len);
                        int t = 0;
                        if((t = hmCur.get(rmWord)) == 1) {
                            hmCur.remove(rmWord);
                        } else {
                            hmCur.put(rmWord, t - 1);
                        }
                        count--;
                        begin += len;
                    }
                    continue;
                }
                // 当前 str 在 hmCur 中已满
                if(curT == totalT) {
                    // 查找当前匹配部分第一个 str，并将其前面的单词删除 
                    while(begin + len < s.length() && !s.substring(begin, begin + len).equals(str)) {
                        String rmWord = s.substring(begin, begin + len);
                        int t = hmCur.get(rmWord);
                        if(t == 1) {
                            hmCur.remove(rmWord);
                        } else {
                            hmCur.put(rmWord, t - 1);
                        }
                        begin += len;
                        count--;
                    }
                    begin += len;
                }
            }
        }
        return res;
    }
}
```



[返回目录](https://maxwell-l.github.io/WriteSomething/something/leetcode)