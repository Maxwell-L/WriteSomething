## **1116 - 打印零与奇偶数**
---------------------

### **题目描述**
假设有这么一个类：
```
class ZeroEvenOdd {
  public ZeroEvenOdd(int n) { ... }      // 构造函数
  public void zero(printNumber) { ... }  // 仅打印出 0
  public void even(printNumber) { ... }  // 仅打印出 偶数
  public void odd(printNumber) { ... }   // 仅打印出 奇数
}
```
相同的一个 `ZeroEvenOdd` 类实例将会传递给三个不同的线程：
1. 线程 A 将调用 `zero()`，它只输出 0 。
2. 线程 B 将调用 `even()`，它只输出偶数。
3. 线程 C 将调用 `odd()`，它只输出奇数。

每个线程都有一个 `printNumber` 方法来输出一个整数。请修改给出的代码以输出整数序列 `010203040506`... ，其中序列的长度必须为 2n。

 

示例 1：
```
输入：n = 2
输出："0102"
说明：三条线程异步执行，其中一个调用 zero()，另一个线程调用 even()，最后一个线程调用odd()。正确的输出为 "0102"。
```
示例 2：
```
输入：n = 5
输出："0102030405"
```

链接：[https://leetcode-cn.com/problems/print-zero-even-odd](https://leetcode-cn.com/problems/print-zero-even-odd)



### **题解**
``` java
class ZeroEvenOdd {
    private int n;
    private Semaphore z = new Semaphore(1);
    private Semaphore o = new Semaphore(0);
    private Semaphore e = new Semaphore(0);
    
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 0; i < n; i++) {
            z.acquire();
            printNumber.accept(0);
            if(i % 2 == 0) {
                o.release();
            } else {
                e.release();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 0; i < n / 2; i++) {
            e.acquire();
            printNumber.accept((i + 1) * 2);
            z.release();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 0; i < (n + 1) / 2; i++) {
            o.acquire();
            printNumber.accept(i * 2 + 1);
            z.release();
        }
    }
}
```

[返回目录](https://maxwell-l.github.io/WriteSomething/something/leetcode)