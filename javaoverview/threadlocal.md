## **ThreadLocal源码阅读**
------

* **ThreadLocal** 提供了线程独有的局部变量，每个线程都可以通过 `set` 和 `get` 来修改或获取且不会和其它线程的操作产生冲突，实现线程间的数据隔离。
* **ThreadLocal** 