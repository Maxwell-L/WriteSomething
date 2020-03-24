## **HashMap扩容机制（JDK1.8）**
---------------------------------
### **源码解析**
``` java
final Node<K,V>[] resize() {
    // oldTab 指向原数组
    Node<K,V>[] oldTab = table;
    // oldCap 赋为原容量
    int oldCap = (oldTab == null) ? 0 : oldTab.length;
    // oldThr 赋为原扩容阈值
    int oldThr = threshold;
    // 定义新容量 newCap, 新扩容阈值 newThr
    int newCap, newThr = 0;
    if (oldCap > 0) {
        // 若原容量已大于最大容量，将扩容阈值设为Integer.MAX_VALUE，即无法再扩容
        // 返回原数组
        if (oldCap >= MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return oldTab;
        }
        // 新容量赋为原容量的两倍，若新容量小于最大容量值，
        // 新扩容阈值赋为原扩容阈值的两倍 
        else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
                    oldCap >= DEFAULT_INITIAL_CAPACITY)
            newThr = oldThr << 1; // double threshold
    }
    else if (oldThr > 0) // initial capacity was placed in threshold
        newCap = oldThr;
    else {               // zero initial threshold signifies using defaults
        newCap = DEFAULT_INITIAL_CAPACITY;
        newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
    }
    if (newThr == 0) {
        float ft = (float)newCap * loadFactor;
        newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
                    (int)ft : Integer.MAX_VALUE);
    }
    threshold = newThr;
    @SuppressWarnings({"rawtypes","unchecked"})
    // 初始化新数组
    Node<K,V>[] newTab = (Node<K,V>[])new Node[newCap];
    // 将原数组引用指向新数组
    table = newTab;
    if (oldTab != null) {
        // for遍历原数组元素
        for (int j = 0; j < oldCap; ++j) {
            Node<K,V> e;
            // 若对应数组位置不为空，进行转移
            if ((e = oldTab[j]) != null) {
                // 将原数组该位置赋为空
                oldTab[j] = null;
                // 若该位置结点next为空，该位置没有出现过哈希冲突，
                // 直接通过 e.hash & (newCap - 1) 找到新数组对应位置并赋值
                if (e.next == null)
                    newTab[e.hash & (newCap - 1)] = e;
                // 若该结点是红黑树结点
                else if (e instanceof TreeNode)
                    ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
                // 否则，该结点为链表结点，遍历链表
                else { // preserve order
                // 扩容后结点的位置要么和原来一致，要么是原来位置加上oldCap [1]
                // 定义原来位置的链表头和尾：loHead, loTail
                // 定义原来位置加上oldCap的链表头和尾：hiHead, hiTail
                    Node<K,V> loHead = null, loTail = null;
                    Node<K,V> hiHead = null, hiTail = null;
                    Node<K,V> next;
                    do {
                        next = e.next;
                        // 若 e.hash 和 oldCap 相与为 0，则为原来的位置 [2]
                        if ((e.hash & oldCap) == 0) {
                            if (loTail == null)
                                loHead = e;
                            else
                                loTail.next = e;
                            loTail = e;
                        }
                        // 否则为原来位置 + oldCap
                        else {
                            if (hiTail == null)
                                hiHead = e;
                            else
                                hiTail.next = e;
                            hiTail = e;
                        }
                    } while ((e = next) != null);
                    // 将新生成的两个链表分别赋到对应的位置上
                    if (loTail != null) {
                        loTail.next = null;
                        newTab[j] = loHead;
                    }
                    if (hiTail != null) {
                        hiTail.next = null;
                        newTab[j + oldCap] = hiHead;
                    }
                }
            }
        }
    }
    return newTab;
}
```

* **[1]** 结点位置的计算是 index = hash & (cap - 1)，当 cap 为 2^n 时，hash & (cap - 1) = hash % cap（& 比 % 的运算速度快）。扩容时，newCap = oldCap << 1，hash不变的情况下 hash % newCap 可能等于原 index，也可能为 index + oldCap（31 % 16 = 15，47 % 16 = 15，扩容后，31 % 32 = 31，47 % 32 = 15）。
* **[2]** 由 **[1]** 可推出，当 hash / oldCap 为偶数时，新位置为原位置，当 hash / oldCap 为奇数时，新位置为原位置 + oldCap。由于哈希表中 cap 为 2^n，即只有1位为1，其余全为0，故 hash & cap 后的结果要么为 0，要么为 cap。当相与后为 0 时，说明 hash / oldCap 为偶数，故用 loHead、loTail 保存，否则用 hiHead、hiTail保存。


[返回首页](https://maxwell-l.github.io/WriteSomething)