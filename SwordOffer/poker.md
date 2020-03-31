## **扑克牌顺子**
-------------------
* **题目描述**  
LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。 现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何， 如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
* **思路**  
题目中输入的`numbers`是五张牌的点数，其中 0 表示大小王，定义一个`card`数组暂存各点数的张数，若出现非 0 的重复点数说明不可能构成顺子，直接返回`false`；在遍历过程中用`max`保存数组中非 0 的最大值，用 `min` 保存非 0 的最小值，最后若`max == 0`（或`min == 14`）说明数组全为 0，否则判断最大最小值之间是否小于等于 4。
* **代码**  

    ``` java
    public class Solution {
        public boolean isContinuous(int [] numbers) {
            if(numbers.length < 5) {
                return false;
            }
            int[] card = new int[14];
            int max = 0, min = 14;
            for(int i = 0; i < numbers.length; i++) {
                if(numbers[i] > 0 && card[numbers[i]] > 0) {
                    return false;
                }
                card[numbers[i]]++;
                if(numbers[i] != 0) {
                    if(max < numbers[i]) {
                        max = numbers[i];
                    }
                    if(min > numbers[i]) {
                        min = numbers[i];
                    }
                }
            }
            return (max == 0 || max - min <= 4);
        }
    }
    ```

[返回目录](https://maxwell-l.github.io/WriteSomething/something/swordoffer)