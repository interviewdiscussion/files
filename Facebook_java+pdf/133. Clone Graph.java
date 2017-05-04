如果不联通，定义hashset，set<node> 没copy完一个，就remove
/**
 * Definition for undirected graph.
 * class UndirectedGraphNode {
 *     int label;
 *     List<UndirectedGraphNode> neighbors;
 *     UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
 * };
 */
//O(m+n) time: num of nodes + num of edges, O(m) space: num of nodes
public class Solution {
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return node;
        }
        ArrayList<UndirectedGraphNode> nodes = getNodes(node);//get all unique nodes
        HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();//key is the old node, value is the new node
        
        //O(m) time
        for (UndirectedGraphNode n : nodes) {
            map.put(n, new UndirectedGraphNode(n.label));//create the old node --> new node mapping
        }
        
        //below's nested loop takes O(nodes * average num of neighbors(connected edges) of each node)
        //it can also be seen as O(m + n) time, cuz for each edge, it's only(at most) traversed twice:
        //for each edge,
        //first traverse is to create a mapping to its neighbor,
        //second traverse is to create a mapping of its neighbor to itself
        //eg. 1    --->     2<--1     --->     2<-->1
        for (UndirectedGraphNode n : nodes) {//for each unique old node
            UndirectedGraphNode newNode = map.get(n);//should get the new node from map
            for (UndirectedGraphNode neighbor : n.neighbors) {//for each old node's neighbor
                newNode.neighbors.add(map.get(neighbor));//should get the new neighbor from map
            }
        }
        return map.get(node);
    }
    
    private ArrayList<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        HashSet<UndirectedGraphNode> set = new HashSet<>();
        queue.offer(node);
        set.add(node);
        
        //this is also at most O(m+n) time
        while (!queue.isEmpty()) {
            UndirectedGraphNode n = queue.poll();
            for (UndirectedGraphNode neighbor : n.neighbors) {
                if (!set.contains(neighbor)) {//we only put unique nodes into the queue and set
                    set.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return new ArrayList<>(set);
    }
}

Map<UndirectedGraphNode,UndirectedGraphNode> map=new HashMap<>();
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        return helper(node);
    }
    public UndirectedGraphNode helper(UndirectedGraphNode node){
        if(node==null) return null;
        if(map.containsKey(node)) return map.get(node);
        UndirectedGraphNode dup=new UndirectedGraphNode(node.label);
        map.put(node,dup);
        for(UndirectedGraphNode neighbor:node.neighbors){
            UndirectedGraphNode clone=helper(neighbor);
            dup.neighbors.add(clone);
        }
        return dup;
    }
