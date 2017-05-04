class UndirectedGraphNode {
    int val;
    List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) {
        val = x;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
};

public class Solution {
   //output shortest path length between two nodes
   private static int shortestPath(UndirectedGraphNode a, UndirectedGraphNode b) {
       if (a == null || b == null) {
           return -1;
       }
       Queue<UndirectedGraphNode> queue = new LinkedList<>();
       HashSet<UndirectedGraphNode> set = new HashSet<>();
       int res = 0;
       queue.offer(a);
       set.add(a);
       while (!queue.isEmpty()) {
           int size = queue.size();
           for (int i = 0; i < size; i++) {
               UndirectedGraphNode curr = queue.poll();
               if (curr == b) {
                   return res;
               }
               for (UndirectedGraphNode neighbor : curr.neighbors) {
                   if (set.contains(neighbor)) {
                       continue;
                   }
                   queue.offer(neighbor);
                   set.add(neighbor);
               }
           }
           res++;
       }
       return -1;
   }

   //output shortest path string between two nodes
   private static String shortestPath2(UndirectedGraphNode a, UndirectedGraphNode b) {
       if (a == null || b == null) {
           return "";
       }
       Queue<UndirectedGraphNode> queue = new LinkedList<>();
       Queue<String> paths = new LinkedList<>();
       HashSet<UndirectedGraphNode> set = new HashSet<>();
       queue.offer(a);
       paths.offer(a.val + "");
       set.add(a);
       while (!queue.isEmpty()) {
           int size = queue.size();
           for (int i = 0; i < size; i++) {
               UndirectedGraphNode curr = queue.poll();
               String path = paths.poll();
               if (curr == b) {
                   return path;
               }
               for (UndirectedGraphNode neighbor : curr.neighbors) {
                   if (set.contains(neighbor)) {
                       continue;
                   }
                   queue.offer(neighbor);
                   paths.offer(path + " " + neighbor.val);//neighbor.val, not neighbor !!!
                   set.add(neighbor);
               }
           }
       }
       return "";
   }
}