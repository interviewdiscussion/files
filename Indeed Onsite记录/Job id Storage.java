/* =============================================================================
Question Description
=============================================================================*/
You are given a list of jobs, each job has an ID number(type is long).
Implement two functions,
1.expire(long jobid) to set a job as "expired"
2.isexpired(long jobid) to check if a job is "expired"
/* =============================================================================
code
=============================================================================*/
//好像写的不太好，如果expire，就可以丢了，不需要继续存在map里面
//所以可以改成map remove expire jobid，但这样就不用map了，用个set就行。
//这也太容易了。
class Job_Storage{
    Map<Long, Boolean> record = new HashMap<>();
    public Job_id_Storage(List<Long> jobids){
        for (Long id: jobids) {
            record.put(id, true);
        }
    }
    public void expire(long jobid){
        if (record.containsKey(jobid)){
            record.put(jobid, false);
        }
    }
    public boolean isexpired(long jobid){
        return record.get(jobid);
    }
}
/* =============================================================================
Follow Up
=============================================================================*/
全放进map里面空间就不够了，面试中不让用map。
long是64个bit。
64bit的操作系统里面，16GB的内存如何存下4 Billion个jobid。
还有用16MB怎么存下一大堆jobid。
（意思是怎么存比较节约内存）。
expire的job id比较多，可以考虑如何压缩去存expire job id。

可能用bitSet，还有就是讨论job id的范围。还有trie可以用。
job id
（这里用4位表示long，前面的0省略）
0001
0010
0100
1000

开一个四个长度的bit数组（好像没有bit数组），这样的话如果4个job id都expire，只需要保存1111即可
检查某个job id是否expire，只需要检查某一位上是不是1就可以。
这样的话，存储空间从16位降到了4位。
这里面的hash function就是 id & 1111
/* =============================================================================
Follow Up code
=============================================================================*/

/* =============================================================================
题目内容：
=============================================================================*/
给一群jobid，类型是long，实现两个方法
1.expire(long jobid)
2.isexpired(long jobid)

那这题入门版就很容易了，用一个map，key是id，value是个boolean表示是否expired。
难度还是在follow up。
1个GB是大约10^9 byte，（2^30 bytes），1个Billion是10^9。
1个byte是8个bit。

内存消耗：memory consumption
/* =============================================================================
地里面经总结
=============================================================================*/
<A> 印度姐加上国人小哥shadow， 跟我聊了聊简历， 然后题是这样的，
    给你一个
    interface{
        void expire(long jobid)
        boolean isexpired(long jobid)
    }
    然你实现一下就行了。然后如果有很多JobId， 要想办法节省空间之类的。
<B> jobid storage, 就是给你jobid type是long，然后在64 bit的操作系统里，16gb内存 如何能存下4 Billion个jobid。
    然后实现expire 和isExpire的操作，这个其实比较次要的，更多的是比较open的讨论。
<C> 第二问 让实现方法 isExpire ,和expire 但是其实这不是重点，hashmap，大家都能实现，关键是如何用最节省内存的方法存下来，
<D> 给一堆jobid, implement两个function:
    void expire(long id),
    bool isExpired(long id)。
    如果job id 超多，如何用16MB的memory纪录expired 的id。（之前的人说是GB，这听力还出毛病了）

可能需要用到bloom filter的思想了。
http://www.cnblogs.com/heaad/archive/2011/01/02/1924195.html
下面这个博客里面的一些思想可以借鉴。
http://www.drfish.me/大数据/2015/12/07/大数据常用技巧之位图法/
