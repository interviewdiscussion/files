//3 ways to solve it: dfs(record last), bfs(delete last), union-find(arr roots)
//1. corner case: n == 1 && edges.length == 0. eg. n = 1, [], it's true
//2. edges.length != n - 1 --> a valid n-node tree should have n - 1 edges
//3. initialize roots[i] = -1
//4. find method --> cycle exists ? return false : union them
用 union find做
//union-find solution, O(edges * nodes) times
//dfs: https://discuss.leetcode.com/topic/21714/ac-java-graph-dfs-solution-with-adjacency-list
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (n == 1 && edges.length == 0) {//if we only have 1 node, we should return true;
            return true;
        }
        if (n < 1 || edges == null || edges.length != n - 1 || edges[0] == null || edges[0].length == 0) {
            return false;//edges.length != n - 1? a valid n-node tree should have n - 1 edges
        }
        int[] roots = new int[n];
        for (int i = 0; i < roots.length; i++) {
            roots[i] = -1;//initialize
        }
        for (int[] pair : edges) {//O(edges) time
            int x = find(roots, pair[0]);
            int y = find(roots, pair[1]);
            if (x == y) {//which means there is a cycle
                return false;
            }
            roots[x] = y;//union
        }
        return true;
    }
    
    private int find(int[] roots, int i) {//O(nodes) time, if it's inbalanced
        while (roots[i] != -1) {//should be roots[i], not i !
            i = roots[i];
        }
        return i;
    }
}
public class Solution {
    /**
     * @param n an integer
     * @param edges a list of undirected edges
     * @return true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {
        if (n == 1 && edges.length == 0) {//corner case!!
            return true;//eg. n = 1, [], this is true cuz node is null.
        }
        if (n < 1 || edges == null || edges.length != n - 1
            || edges[0] == null || edges[0].length == 0) {
            return false;
        }//edges.length != n - 1? a valid n-node tree should have n - 1 edges!
        int[] roots = new int[n];
        for (int i = 0; i < n; i++) {
            roots[i] = -1;//initialize all ints to -1
        }
        for (int[] pair : edges) {
            int x = find(roots, pair[0]);
            int y = find(roots, pair[1]);
            if (x == y) {//which means there is a cycle
                return false;
            }
            roots[x] = y;//no cycle? union them
        }
        return true;
    }
    
    private int find(int[] roots, int i) {
        while (roots[i] != -1) {
            i = roots[i];//find i
        }
        return i;
    }
}
