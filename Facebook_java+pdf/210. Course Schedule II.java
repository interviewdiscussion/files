topological sorting O(V+E)
BFS
* Use a list to maintain prerequisites
* Add courses without prerequisites to queue
* Poll prerequisites and check its related courses, update the list, if all prerequisites is met, add it to queue
* Add all avaible courses to result
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] inDegrees = new int[numCourses];
        ArrayList<Integer> list = new ArrayList<>();//need a list to store the results
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        for (int[] pair : prerequisites) {
            graph.get(pair[1]).add(pair[0]);
            inDegrees[pair[0]]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) queue.offer(i);
        }
        while (!queue.isEmpty()) {
            int i = queue.poll();
            list.add(i);//add the results to the list when they are polled out
            for (int j : graph.get(i)) {
                inDegrees[j]--;
                if (inDegrees[j] == 0) queue.offer(j);
            }
        }
        if (list.size() != numCourses) return new int[0];//use list.size() != numCourses to determine whether cycle exists
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) res[i] = list.get(i);//convert arraylist to int[]
        return res;
    }
}