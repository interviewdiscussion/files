/* =============================================================================
Question Description
=============================================================================*/
Interviewees said this question(and follow up) is the same as Leetcode 243
/* =============================================================================
code
=============================================================================*/
//思路太容易，写出来就3分钟
public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1;
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < words.length; i++){
            if(words[i].equals(word1)){
                p1 = i;
            }
            else if(words[i].equals(word2)){
                p2 = i;
            }

            if(p1 != -1 && p2 != -1){
                int temp = Math.abs(p1 - p2);
                res = Math.min(res, temp);
            }
        }
        return res;
    }
}
/* =============================================================================
Follow Up
=============================================================================*/
a.optimize it if the function will be called multiple times (most likely LC 244)
b.two words can be the same
/* =============================================================================
Follow Up code
=============================================================================*/
a.用map记录一下就行，记录词和它出现的所有位置。
然后找最短距离的时候，双指针挪一下就可以了。这道题在题库里面都是简单题。
b.每次遇到目标词的时候，把两个词一样的情况单独挑出来。更小id往高跳。其他情况就是基本情况。
//大晚上困的不行，map添加的时候写错了点儿。
public class WordDistance {
    Map<String, List<Integer>> record = new HashMap<>();
    public WordDistance(String[] words) {
        for(int i = 0; i < words.length; i++){
            String cur = words[i];
            List<Integer> list = new ArrayList<>();
            if(record.containsKey(cur)){
                list = record.get(cur);
            }
            list.add(i);
            record.put(cur, list);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = record.get(word1);
        List<Integer> l2 = record.get(word2);
        int res = Integer.MAX_VALUE;
        if(l1 == null || l2 == null) return res;

        int p1 = 0, p2 = 0;
        while(p1 < l1.size() && p2 < l2.size()){
            int temp = Math.abs(l1.get(p1) - l2.get(p2));
            res = Math.min(res, temp);
            if(l1.get(p1) < l2.get(p2)){
                p1++;
            }
            else {
                p2++;
            }
        }
        return res;
    }
}
/* =============================================================================
题目内容：
=============================================================================*/
就是leetcode上面的243.shortest word distance。follow up应该就是244，245
如果最后要输出前面三个和后面三个的话，记录下符合条件的index即可。
/* =============================================================================
地里面经总结
=============================================================================*/
<A> 面试官一进来就说，我给你随便出道题然后你nail it你就可以回家啦。然后果然我就特别快做完了，
    题是shortest word distance。最后因为做得比较快，就加了两个follow up，我就基本一边跟他讨论一边做出来的。
<B> WORD DISTANCE I, II, II注意BUG
<C> 给了一个string： Indeed use python and java to deal with the python python and is for java ...
    然后找到java 和python距离最小的一个，然后输出。在上面就是python and java,
    在输出的时候还要输出其前三个和后三个： Indeed use python and java to deal with.
