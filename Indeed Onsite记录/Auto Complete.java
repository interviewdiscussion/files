/* =============================================================================
Question Description
Say I'm typing on a phone. Given a prefix String,and a dictionary.
Find all auto-complete word for the given prefix string
=============================================================================*/
/* =============================================================================
code
=============================================================================*/
class TrieNode{
    boolean hasWord;
    TrieNode[] children;
    //constructor忘了写括号了
    public TrieNode(){
        this.hasWord = false;
        this.children = new TrieNode[26];
    }
    public void insert(String word, int index){
        if (index == word.length()){
            hasWord = true;
            return;
        }
        //这里居然忘了写 -'a' 太不应该了
        int pos = word.charAt(index)-'a';
        if (children[pos] == null){
            children[pos] = new TrieNode();
        }
        children[pos].insert(word, index+1);
    }
    public TrieNode find(String prefix, int index){
        if (index == prefix.length()){
            return this;
        }
        int pos = prefix.charAt(index)-'a';
        if (children[pos] == null){
            return null;
        }
        return children[pos].find(prefix, index+1);
    }
}
//就是trie和DFS的结合，感觉写的很好。
public class Auto_Complete {
    TrieNode root;
    public Auto_Complete(List<String> words){
        this.root = new TrieNode();
        for (String word: words) {
            root.insert(word, 0);
        }
    }
    public List<String> find(String prefix){
        List<String> res = new ArrayList<>();
        TrieNode cur = root;
        TrieNode pRoot = cur.find(prefix, 0);
        helper(res, pRoot, prefix);
        return res;
    }
    public void helper(List<String> res, TrieNode pRoot, String curS){
        if (pRoot == null){
            return;
        }
        if (pRoot.hasWord){
            res.add(curS);
        }

        String tempS = curS;
        for (int i = 0; i < 26; i++){
            char c = (char)('a'+i);
            helper(res, pRoot.children[i], tempS + c);
        }
    }
    public static void main(String[] args) {

        List<String> words = new ArrayList<>();
        words.add("ab");
        words.add("a");
        words.add("de");
        words.add("abde");

        Auto_Complete test = new Auto_Complete(words);
        String prefix = "ab";
        List<String> res = test.find(prefix);
        System.out.println(res);
    }
}
/* =============================================================================
Follow Up
=============================================================================*/
no follow up found for indeed
Google遇上的是不同的词有不同的频率，优先输出高频词。
思路就是每个trie node加上一个数字，向下遍历的时候优先输出高频的child即可。
/* =============================================================================
Follow Up code
=============================================================================*/

/* =============================================================================
题目内容：
=============================================================================*/
自动补全，输入是个前缀字符串，另一个输入是个一堆单词。

所以思路就非常简单，用个trie就行。用前缀找到子树，然后DFS一遍把所有的单词输出即可。
/* =============================================================================
地里面经总结
=============================================================================*/
auto-complete, 输入一个前缀，要求返回所有的包含前缀的words. 用Trie
    public class AutoComplete{
        public AutoComplete(List<String> words){

        }
        public List<String> find(String prefix){

        }
    }
//就是Google的第四轮
所以难度不大，但是发现它只出现过一次。