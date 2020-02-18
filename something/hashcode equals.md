## hashcode & equals

* HashMap、HashSet中判断两个Key值是否相等时先判断两者的hashcode是否一样，若不同则判定两者不同；若相同，则再通过equals方法判断两者是否相同，若仍相同则判定两个Key值是同一个，否则不是同一个。故一般而言，hashcode和equals方法需要同时重写，否则在容器中应用时可能出错。

* 设定Help类，定义两个int型成员变量，重写其hashcode方法和equals方法使得当两个对象的成员变量相等时，hashcode方法返回同一个值，equals方法判定相等。
``` java
import java.util.*;
public class Help {
    private int x;
    private int y;

    public Help(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        return prime * (prime + x) + y;
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj) {
            return true;
        }
        if(obj == null) {
            return false;
        }
        if(this.getClass() != obj.getClass()) {
            return false;
        }
        Help help = (Help)obj;
        if(this.x != help.x) {
            return false;
        }
        return this.y == help.y;
    }

    public static void main(String[] args) {
        Help h1 = new Help(1, 1);
        Help h2 = new Help(2, 2);
        Help h3 = new Help(3, 3);
        Help h4 = new Help(1, 1);
        HashMap<Help, Integer> hm = new HashMap<>();
        hm.put(h1, 1); hm.put(h2, 1); hm.put(h3, 1); hm.put(h4, 1);
        System.out.println("HashMap 中的元素有 " + hm.size() + " 个");
//        HashSet<Help> hs = new HashSet<>();
//        hs.add(h1); hs.add(h2); hs.add(h3); hs.add(h4);
//        System.out.println("HashSet中的元素有 " + hs.size() + " 个");
    }
}
```
运行可以看到HashMap中元素有3个，可以推测h1和h4被判定为同一个Key值；注释掉重写的hashcode方法，运行结果为4个，说明当hashcode值不同时，Key值不同；保留hashcode方法，注释掉equals方法，运行结果也为4个，说明即使hashcode值相同，Key值是否相同也会再通过equals方法来判断。  
