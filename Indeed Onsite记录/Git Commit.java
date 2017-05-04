/* =============================================================================
Question Description
=============================================================================*/
Given a git commit (wiki:...), each commit has an id. Find all the commits
that we have.
/* =============================================================================
code
=============================================================================*/
class GitNode{
    int id;
    List<GitNode> parents;
    public GitNode(int id){
        this.id = id;
        this.parents = new ArrayList<>();
    }
}
public class Git_Commit {
    //其实是找到了所有的parents,所以假设拿到的是最新的gitNode
    //只有这种难度，才能大部分人都秒做吧。
    public List<GitNode> findAllCommits(GitNode node){
        List<GitNode> res = new ArrayList<>();
        Queue<GitNode> queue = new LinkedList<>();
        Set<GitNode> visited = new HashSet<>(); //去重

        queue.offer(node);
        visited.add(node);

        while (!queue.isEmpty()){
            GitNode cur = queue.poll();
            res.add(cur);
            for (GitNode par: cur.parents){
                if (!visited.contains(par)){
                    queue.offer(par);
                    visited.add(par);
                }
            }
        }
        return res;
    }
}
/* =============================================================================
Follow Up
=============================================================================*/
找到两个commit的最近公共parent commit。而且被要求优化，因为是follow up，所以到时候时间肯定
剩下不多，面试时候要直接出最优解。
/* =============================================================================
Follow Up code
=============================================================================*/
    public GitNode findLCA(GitNode node1, GitNode node2){
        if (node1 == null || node2 == null) return null;

        Queue<GitNode> q1 = new LinkedList<>();
        q1.offer(node1);
        Queue<GitNode> q2 = new LinkedList<>();
        q2.offer(node2);

        Set<GitNode> s1 = new HashSet<>();
        Set<GitNode> s2 = new HashSet<>();
        s1.add(node1);
        s2.add(node2);
//        int len1 = 1, len2 = 1; //万一是要求最短路径长度呢。

        //while里面是&&,因为一旦其中一个终结那也不用搜了。
        while (!q1.isEmpty() && !q2.isEmpty()){
            //每个BFS都是一层一层的扫
            int size1 = q1.size();
            while (size1-- > 0){
                GitNode cur1 = q1.poll();
                for (GitNode par1 : cur1.parents) {
                    if (s2.contains(par1)){
                        return par1;
                    }
                    if (!s1.contains(par1)){
                        q1.offer(par1);
                        s1.add(par1);
                    }
                }
            }

            int size2 = q2.size();
            while (size2-- > 0){
                GitNode cur2 = q2.poll();
                for (GitNode par2 : cur2.parents) {
                    if (s1.contains(par2)){
                        return par2;
                    }
                    if (!s2.contains(par2)){
                        q2.offer(par2);
                        s2.add(par2);
                    }
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Git_Commit test = new Git_Commit();
        /*
        *
        *   5 <-  4  <- 2
        *    \       \
        *     \ <- 3 <- 1
        * */
        GitNode g1 = new GitNode(1);
        GitNode g2 = new GitNode(2);
        GitNode g3 = new GitNode(3);
        GitNode g4 = new GitNode(4);
        GitNode g5 = new GitNode(5);

        g1.parents.add(g3);
        g1.parents.add(g4);
        g2.parents.add(g4);
        g3.parents.add(g5);
        g4.parents.add(g5);

        GitNode res = test.findLCA(g2, g3);
        System.out.println(res.id);
    }
/* =============================================================================
题目内容：
=============================================================================*/
给出一个Git的commit，找出所有的parents。每个节点都有一个或多个parent。
另外每个commit都是带着ID的。就是没太懂它是输出所有的commit还是要求逐层打印。
第二题就是找到两个commit的公共祖先。
Git的commit是可以分叉的也可以合并，所以这题其实是个图。
想来真是好久没弄github了。
其实就是BFS和双向BFS，注意好对复杂度的分析吧。优化就是双向BFS吧。
好像不太对。
/* =============================================================================
地里面经总结
=============================================================================*/
<A> git commit的题，也是面经题。第一问给一个commit（node），BFS输出所有commits (nodes)。
    第二问，两个commits （nodes），找到他们的最近的公共parent，就是先BFS一个，
    然后用map记录下其各个parent到这个commit(node)的距离，然后BFS第二个commit(node)，碰到在map里的node，
    就算一个总距离，然后更新最短距离和的点，最后最短距离和的点就是结果了，写完面试官也表示很满意。
    这个注意解释下BFS的复杂度为什么是O（V+E），他会问为什么不是O(V)之类的。
<B> git version。找到全部commits，让实现bfs。然后让找两个commit最早的公共commit，
    先bfs搜索其中一个commit的所有ancestor，用hashmap存一下，然后bfs搜索第二个commit的祖先。
    这里有两个地方可以提前结束搜索，提出来应该很好。
<C> 是git commit，follow－up是个lowest common anscester，写第一问的时候竟然脑残出了个小bug，
    在面试官提示下改了，不知道影响不影响结果阿，
<D> Git commit. 第一题：BFS找出parents。第二题：找共同祖先。
<E> 图的题目，给了一堆节点，每个节点有个parent，先是找所有路径。很简单秒做，
    然后follow up是给两个点找最小公共祖先，两遍bfs也很简单做了出来。但是小哥各种问时间复杂度什么的，绕蒙了。。
<F> 第二题是给两个graph中的node，找出这两个node的最近公共祖先，这里最近公共祖先的定义是距离这两个node的path和最小，
    还是用bfs做，followup了一下怎么优化。
<G> 第一道是graph的逐层打印，和tree的逐层打印相似。
<H> 一个有向图，但是有环，用BFS进行按层打印, 已知图中两个点，输入参数只有这两个点，返回他俩的最低公共祖先，仍然存在环。
    public Node getNode(Node n1, Node n2);
<I> git version，从新到旧扫描，就是有向图的BFS，
    follow up是给出图里的两个源节点，找距离这两个原点的最近的公共节点，分别从这两个节点BFS就行。
<J> 现在更新一下input，说的更清楚一点，第一题第一问input是一个parent pointer的node，
    def __init__(self,id): self.parent = [] self.id = id.
