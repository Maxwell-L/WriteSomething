## **Redis 五种常用数据类型**
-----------
`Redis`中以`key-value`的结构存储数据，其中`key`都是以`string`类型存储，一般讨论数据类型都是指的`value`的类型。[官方文档](https://redis.io/topics/data-types)

### **string**
#### 数值增减操作  

|操 作|作 用|
|:-|:-|
|**incr** &nbsp;*key*|*key* 增加 1|
|**incrby** &nbsp;*key increment*|*key* 增加 *increment*|
|**incrbyfloat** &nbsp;*key increment*|*key* 增加 *increment* (`float`)|
|**decr** &nbsp;*key*|*key* 减少 1|
|**decrby** &nbsp;*key decrement*|*key* 减少 *decrement*|

* 应用场景  
数据库分库分表后可能出现的自增主键重复，可以用`Redis`来生成主键，由于`Redis`是单线程执行的，故可以保证高并发下主键生成的唯一性。  

#### 单指令操作与多指令操作的选择
* 单指令`set`发送给`Redis`时间短，但要发送多次。
* 多指令`mset`发送时间稍长，但只需发送一次。  

#### 数据时效性

|操 作|作 用|
|:-|:-|
|**setex** &nbsp;*key seconds value*|设置 *key-value* *seconds* 秒后失效|
|**psetex** &nbsp;*key milliseconds value*|设置 *key-value* *milliseconds* 毫秒后失效|

* 应用场景  
电视节目投票有一定时间内投票次数的限制，若投一次票后将投票人`id`存在`Redis`中并设置失效时间，再投票时查询`Redis`是否有投票人信息即可做到限制投票次数。
------------------------------------------
### **hash**
一个`key`值对应一个哈希表，`hash`的结构是`field-value`。
#### `hash`类型基本操作  

|操 作|作 用|
|:-|:-|
|**hset** &nbsp;*key field value*|设置 *key* 值对应的 *field-value*|
|**hget** &nbsp;*key field*|获取 *key* 值对应`hash`中 *field* 的 *value* 值|
|**hgetall** &nbsp;*key*|获取 *key* 值对应的所有 *field-value*|
|**hdel** &nbsp;*key field [field ...]*|删除 *key* 值对应`hash`中一个或多个 *field*|
|**hlen** &nbsp;*key*|获取`hash`中 *field* 的数量|

* 应用场景  
购物车中商品和数量可以作为一对 *field-value*，用户作为 *key*，*hset* 用于新增商品 *hlen* 用于获取购物车中商品数量，*hgetall* 用于全选，*hdel* 用于删除操作...
------------------------------------------
### **list**
一个 *key* 值对应一个 `list`，底层用双向链表实现。
#### `list`类型基本操作  

|操 作|作 用|
|:-|:-|
|**lpush** &nbsp;*key value [value ...]*|从 *key* 值对应`list`左侧添加一个或多个 *value*|
|**rpush** &nbsp;*key value [value ...]*|从 *key* 值对应`list`右侧添加一个或多个 *value*|
|**lrange** &nbsp;*key start stop*|获取 *key* 值对应的`list`从索引 *start* 到 *stop* 的所有值|
|**lrange** &nbsp;*key index*|获取 *key* 值对应的`list`中索引为 *index* 的值|
|**llen** &nbsp;*key*|获取 *key* 值对应`list`的长度|
|**lpop** &nbsp;*key*|获取 *key* 值对应`list`左侧的第一个值并移除|
|**rpop** &nbsp;*key*|获取 *key* 值对应`list`右侧的第一个值并移除|
|**blpop** &nbsp;*key [key ...] timeout*|阻塞获取一个或多个 *key* 值对应`list`左侧的第一个值并移除，最多阻塞 *timeout* 秒|
|**brpop** &nbsp;*key [key ...] timeout*|阻塞获取一个或多个 *key* 值对应`list`右侧的第一个值并移除，最多阻塞 *timeout* 秒|
|**lrem** &nbsp;*key count value*|删除 *key* 值对应`list`中 *count* 个 *value*|

* 应用场景  
  + 朋友圈点赞可以用 *rpush* 将点赞用户加入`list`，查看点赞的朋友可以用 *lrange* 获取点赞名单，取消点赞可以用 *lrem* 从`list`中移除该用户...  
  + 多台服务器产生日志，可以将日志发送到`Redis`中，由于`Redis`是单线程的，故可以将日志按照提交的时间顺序展示出来。
------------------------------------------
### **set**
底层采用和`hash`一样的结构，但 *value* 值放在 `hash` *field* 的位置，`hash` *value* 的位置置为 *nil*（类似`Java`中的`HashMap`和`HashSet`）。
#### `set`类型基本操作  

|操 作|作 用|
|:-|:-|
|**sadd** &nbsp;*key member [member ...]*|从 *key* 值对应的`set`中添加一个或多个 *member*|
|**smembers** &nbsp;*key*|获取 *key* 值对应`set`中所有的 *member*|
|**srem** &nbsp;*key member [member ...]*|从 *key* 值对应的`set`中删除一个或多个 *member*|
|**scard** &nbsp;*key*|获取 *key* 值对应的`set`中 *member* 的数量|

* 应用场景  
  + 统计网站访问量，记录不同 *cookie* 数量、不同 *ip* 地址。  
  + 实现黑白名单，过滤黑名单中的用户、设备、*ip*。  
  
#### `set`类型操作随机数据

|操 作|作 用|
|:-|:-|
|**srandmember** &nbsp;*key [count]*|从 *key* 值对应的`set`中随机获取 *count* 个 *member*|
|**spop** &nbsp;*key [count]*|从 *key* 值对应的`set`中随机获取 *count* 个 *member* 并移除|

* 应用场景  
从热点数据（热门歌单、热点新闻、热卖商品 ...）中随机推荐一部分到用户首页，应用于随机推荐类信息检索。  

#### `set`类型交并差操作

|操 作|作 用|
|:-|:-|
|**sinter** &nbsp;*key [key ...]*|获取多个 *key* 对应的`set`的交集|
|**sunion** &nbsp;*key [key ...]*|获取多个 *key* 对应的`set`的并集|
|**sdiff** &nbsp;*key [key ...]*|获取多个 *key* 对应的`set`的差集|
|**sinterstore** &nbsp;*destination key [key ...]*|获取多个 *key* 对应的`set`的交集并存储到 *destination* 中|
|**sunionstore** &nbsp;*destination key [key ...]*|获取多个 *key* 对应的`set`的并集并存储到 *destination* 中|
|**sdiffstore** &nbsp;*destination key [key ...]*|获取多个 *key* 对应的`set`的差集并存储到 *destination* 中|

![图片加载失败](https://maxwell-l.github.io/WriteSomething/image/redis1.png)

* 应用场景  
获取共同好友、共同关注、共同群聊...还可以进行更深层次的关联搜索...
------------------------------------------
### **sorted_set**
在`set`的存储结构上增加可排序字段 `score` 。
#### `sorted_set`类型基本操作

|操 作|作 用|
|:-|:-|
|**zadd** &nbsp;*key score member [score member ...]*|从 *key* 值对应的`sorted_set`中添加一个或多个 *score-member*|
|**zrange** &nbsp;*key start stop [WITHSCORES]*|获取 *key* 值对应`sorted_set`中按照 *score* 值从小到大排序的第 *start* 个到第 *stop* 个 *member*，可选参数 *WITHSCORES* 可以同时获取 *score*|
|**zrevrange** &nbsp;*key start stop [WITHSCORES]*|获取 *key* 值对应`sorted_set`中按照 *score* 值从大到小排序的第 *start* 个到第 *stop* 个 *member*，可选参数 *WITHSCORES* 可以同时获取 *score*|
|**zrem** &nbsp;*key member [member ...]*|从 *key* 值对应的`sorted_set`中移除一个或多个 *member*|
|**zrangebyscore** &nbsp;*key min max [WITHSCORES] [LIMIT offset count]*|获取 *key* 值对应的`sorted_set`中 *score* 在 *min~max* 范围内的 *member*，可选参数 *LIMIT* 用法与 MySQL一致|
|**zrevrangebyscore** &nbsp;*key max min [WITHSCORES] [LIMIT offset count]*|作用类比 **zrangebyscore**（逆序）|
|**zremrangebyrank** &nbsp;*key start stop*|删除 *key* 值对应`sorted_set`中按照 *score* 值从小到大排序的第 *start* 个到第 *stop* 个 *member*|
|**zremrangebyscore** &nbsp;*key min max*|删除 *key* 值对应`sorted_set`中 *score* 值在 *min~max* 范围内的 *member*|
|**zcard** &nbsp;*key*|获取 *key* 值对应的`sorted_set`中 *member* 的数量|
|**zcount** &nbsp;*key min max*|获取 *key* 值对应`sorted_set`中 *score* 值在 *min~max* 范围内 *member* 的数量|
|**zrank** &nbsp;*key member*|获取 *key* 值对应`sorted_set`中 *member* 的排序|
|**zscore** &nbsp;*key member*|获取 *key* 值对应`sorted_set`中 *member* 的 *score*|
|**zincrby** &nbsp;*key increment member*|对 *key* 值对应`sorted_set`中 *member* 的 *score* 增加 *increment*|  
  
* 应用场景  
  + 对于基于时间线的任务，可以采用`sorted_set`存储任务，并用 *score* 记录截止日期（时间戳），按照时间戳先后顺序执行任务。  
  + 对于带有权重的任务，可以用 *score* 记录权重，还可以通过对 *score* 做分段实现多个权重条件。  


[返回首页](https://maxwell-l.github.io/WriteSomething)
    








