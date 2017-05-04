public class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        int[] inDegrees = new int[numCourses];
        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());//initialization
        for (int[] pair : prerequisites) {//bulid the graph by using pair[1] as indices, add all pair[0] to that indexed array
            graph.get(pair[1]).add(pair[0]);
            inDegrees[pair[0]]++;//which means we go to pair[0] once
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] == 0) queue.offer(i);//ints that have 0 indegree are entrances, put them into the queue
        }
        while (!queue.isEmpty()) {
            int i = queue.poll();
            for (int j : graph.get(i)) {//iterate every course j that comes out from prerequisites course i
                inDegrees[j]--;
                if (inDegrees[j] == 0) queue.offer(j);//indegree == 0 means this course can be the next entrance now
            }
        }
        for (int i = 0; i < numCourses; i++) {
            if (inDegrees[i] != 0) return false;//check whether cycle exists
        }
        return true;
    }
}
//bfs solution with topological sort