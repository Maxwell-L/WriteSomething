## **146 - LRU�������**
----------------------

### **��Ŀ����**
�����������յ����ݽṹ����ƺ�ʵ��һ��? **LRU (�������ʹ��) �������**����Ӧ��֧�����²����� ��ȡ���� `get` �� д������ `put` ��

��ȡ���� `get(key)` - ����ؼ��� (key) �����ڻ����У����ȡ�ؼ��ֵ�ֵ�����������������򷵻� -1��
д������ `put(key, value)` - ����ؼ����Ѿ����ڣ�����������ֵ������ؼ��ֲ����ڣ��������顸�ؼ���/ֵ���������������ﵽ����ʱ����Ӧ����д��������֮ǰɾ�����δʹ�õ�����ֵ���Ӷ�Ϊ�µ�����ֵ�����ռ䡣

?

����:

���Ƿ������?O(1) ʱ�临�Ӷ�����������ֲ�����

?

ʾ��:
``` java
LRUCache cache = new LRUCache( 2 /* �������� */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // ����  1
cache.put(3, 3);    // �ò�����ʹ�ùؼ��� 2 ����
cache.get(2);       // ���� -1 (δ�ҵ�)
cache.put(4, 4);    // �ò�����ʹ�ùؼ��� 1 ����
cache.get(1);       // ���� -1 (δ�ҵ�)
cache.get(3);       // ����  3
cache.get(4);       // ����  4
```

���ӣ�[https://leetcode-cn.com/problems/lru-cache](https://leetcode-cn.com/problems/lru-cache)



### **���**
``` java
class LRUCache {
    class DoubleLinkedNode {
        public int key;
        public int value;
        public DoubleLinkedNode prev;
        public DoubleLinkedNode next;
        public DoubleLinkedNode() {}

        public DoubleLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private int size;
    private DoubleLinkedNode head;
    private DoubleLinkedNode tail;
    private Map<Integer, DoubleLinkedNode> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        head = new DoubleLinkedNode();
        tail = new DoubleLinkedNode();
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if(!map.containsKey(key)) {
            return -1;
        }
        DoubleLinkedNode node = map.get(key);
        addNodeToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(!map.containsKey(key)) {
            if(size == capacity) {
                deleteTailNode();
            } else {
                size++;
            }
            DoubleLinkedNode node = new DoubleLinkedNode(key, value);       
            addNodeToHead(node);  
            map.put(key, node);
        } else {
            DoubleLinkedNode node = map.get(key);
            addNodeToHead(node);
            node.value = value;
        }
    }

    private void addNodeToHead(DoubleLinkedNode node) {
        if(node.prev != null && node.next != null) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }
        node.next = head.next;
        head.next = node;
        node.next.prev = node;
        node.prev = head;
    }

    private void deleteTailNode() {
        DoubleLinkedNode node = tail.prev;
        map.remove(node.key);
        tail.prev.prev.next = tail;
        tail.prev = tail.prev.prev;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```


[����Ŀ¼](https://maxwell-l.github.io/WriteSomething/something/leetcode)