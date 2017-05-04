/* =============================================================================
Question Description
=============================================================================*/
Given a tree,(binary tree possibily) every tree edge has a cost， find the least
cost or find the leaf node that the cost of path that from root to leaf is the
least.
/* =============================================================================
code
=============================================================================*/
//Edge和Node2都是给好的，里面的变量类型到时候和面试官讨论吧。
class Edge{
    Node2 node; //表示这个edge的尾巴指向哪里。
    int cost;
    public Edge(Node2 n, int cost) {
        this.node = n;
        this.cost = cost;
    }
}
class Node2 {
    List<Edge> edges; //表示从这个头出发的所有edge
    public Node2(){
        this.edges = new ArrayList<>();
    }
}

public class Root_to_Leaf_Min_Cost{
    int minCost = Integer.MAX_VALUE;
    //返回最短路径上面的所有Edge
    public List<Edge> getMinPath(Node2 root){
        List<Edge> res = new ArrayList<>();
        List<Edge> temp = new ArrayList<>();
        dfs(res, temp, root, 0);
        return res;
    }
    //就是普通的DFS
    public void dfs(List<Edge> res, List<Edge> temp, Node2 root, int curCost){
        if (root == null){
            return;
        }
        if (root.edges.size() == 0){
            if (curCost < minCost){
                minCost = curCost;
                res.clear();
                res.addAll(temp);
                return;
            }
        }
        for (Edge e : root.edges){
            Node2 next = e.node;
            temp.add(e);
            dfs(res, temp, next, curCost+e.cost);
            temp.remove(temp.size()-1);
        }
    }
    //这个只返回个最小cost
    public int getMinCost(Node2 root){
        if (root == null) {
            return 0;
        }
        helper(root, 0);
        return minCost;
    }
    public void helper(Node2 root, int curCost){
        if (root.edges.size() == 0){
            minCost = Math.min(minCost, curCost);
            return;
        }
        for (Edge e : root.edges){
            Node2 next = e.node;
            helper(next, curCost + e.cost);
        }
    }
    public static void main(String[] args) {
        Root_to_Leaf_Min_Cost test = new Root_to_Leaf_Min_Cost();
        /*
        *       n1
        *   e1 /  \ e3
        *     n2   n3
        * e2 /
        *   n4
        *
        * */
        Node2 n1 = new Node2();
        Node2 n2 = new Node2();
        Node2 n3 = new Node2();
        Node2 n4 = new Node2();
        Edge e1 = new Edge(n2,1);
        Edge e2 = new Edge(n4,2);
        Edge e3 = new Edge(n3,5);
        n1.edges.add(e1);
        n1.edges.add(e3);
        n2.edges.add(e2);

        int res = test.getMinPath(n1);
        System.out.println("3 = "+res);
    }
}

/* =============================================================================
Follow Up
=============================================================================*/
改树为DAG，那leaf变成了出度为0的node，同样求实现方法。
小问题很多，开始是简单方法来做。然后逼出dijkstra方法。
问dfs能不能用在图里面，如果能，（肯定能），改进方法就是改成dijkstra。
或者就是用map记录走过的node，这样的话减少计算。
复杂度是O(Elog(V))

感觉写的不对，应该每个点搜的时候都要带个返回值，返回从当前点到叶子的最短距离。
这样优化出来是O(E+V)
/* =============================================================================
Follow Up code
=============================================================================*/
//改良的DFS，就是用map记录的DFS
    int minCost = Integer.MAX_VALUE;
    Map<Node2, Integer> dist = new HashMap<>();
    public List<Edge> getMinPathInGraph(Node2 root){
        List<Edge> res = new ArrayList<>();
        List<Edge> temp = new ArrayList<>();
        dfsInGraph(res, temp, root, 0);
        return res;
    }
    public void dfsInGraph(List<Edge> res, List<Edge> temp, Node2 node, int curCost){
        if (node == null) return;
        if (dist.containsKey(node) && curCost >= dist.get(node)) return;
        dist.put(node, curCost);
        if (node.edges.size() == 0){
            if (curCost < minCost){
                minCost = curCost;
                res.clear();
                res.addAll(temp);
            }
            return;
        }

        for (Edge e : node.edges){
            Node2 next = e.node;
            temp.add(e);
            dfsInGraph(res, temp, next, curCost + e.cost);
            temp.remove(temp.size()-1);
        }
    }
//dijkstra，写的真难看
    public List<Edge> getMinPathInGraph(Node2 root){
        int minCost = Integer.MAX_VALUE;
        Node2 resNode = null;
        List<Edge> res = new ArrayList<>();
        Map<Node2, Integer> dist = new HashMap<>();
        Map<Node2, Node2> findPar = new HashMap<>();
        PriorityQueue<Node2> pq = new PriorityQueue<>(new Comparator<Node2>() {
            @Override
            public int compare(Node2 o1, Node2 o2) {
                return dist.get(o1) - dist.get(o2);
            }
        });

        dist.put(root, 0);
        findPar.put(root, null);
        pq.offer(root);
        while (!pq.isEmpty()){
            Node2 cur = pq.poll();
            int d = dist.get(cur);
            if (cur.edges.size() == 0){
                int curCost = dist.get(cur);
                if (curCost < minCost){
                    minCost = curCost;
                    resNode = cur;
                }
            }
            for (Edge e : cur.edges) {
                Node2 next = e.node;
                int tempD = e.cost + d;
                if (!dist.containsKey(next)){
                    dist.put(next, tempD);
                    findPar.put(next, cur);
                    pq.offer(next);
                }
                else {
                    if (tempD < dist.get(next)){
                        pq.remove(next);
                        dist.put(next, tempD);
                        findPar.put(next, cur);
                        pq.offer(next);
                    }
                }
            }
        }

//      下面都是为了输出所有的边才写的，就是把所有的node都抓出来，然后去找连接的边
        List<Node2> tempList = new ArrayList<>();
        while (resNode != null){
            tempList.add(0, resNode);
            resNode = findPar.get(resNode);
        }
        for (int i = 0; i < tempList.size()-1; i++){
            Node2 cur = tempList.get(i);
            for (Edge e : cur.edges) {
                if (e.node.equals(tempList.get(i+1))){
                    res.add(e);
                }
            }
        }
        return res;
    }
/* =============================================================================
题目内容：
=============================================================================*/
给出一个二叉树，找到从root到leaf的最短路径。返回最短路径。或者返回最小值。

这个类的实现方法其实就是摆明了DFS一下。那么需要两个成员变量，一个记录最小node，一个记录最小cost
dfs的function的返回是void的。
所以还是返回一条路径吧，有了一条路径，最小cost也就能求出来了。

还是写带返回值的递归吧，这样能从叶子往顶点去搜。
/* =============================================================================
地里面经总结
=============================================================================*/
<A> 小白哥， 很喜欢写note, 话不算多， 然后实现 n-ary tree root to leaf min cost. 找那个leaf。
    然后再推广到DAG， 然后在优化。
<B> 一个tree，每个edge都有个cost，求最小cost的leaf。
<C> 给一颗binary tree，有以下数据结构
    class Edge {
            Node node; //表示尾巴的node，因为头的node是固定的。
            int cost; //大于等于0
    }
    class Node {
            List<Edge> edges; //这里说明头的node是固定的。
    }
    找从root到叶节点cost之和最小的路径，返回该leaf node。（dfs）
    follow-up：如果不是binary tree的结构，而是任意的单向图，问代码还是否work（yes）
    有没有优化的地方？（我用hashmap存下每个节点到叶节点的distance，这样再次访问到该叶节点就不必dfs下去）。时间复杂度？
    （优化后是O（V+E））//说的真好

2014年一个面经。
用到了dijkstra，只有对概念残存的印象，现场就开始硬着头皮写，
好在面试官之前已经说了dijkstra只看思路，不太看细节，所以写完面试官还比较满意，算是蒙混过关吧。

