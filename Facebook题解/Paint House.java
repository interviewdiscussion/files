public class Solution {
    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0] == null || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length;//costs[i][0/1/2] means min of total costs from house 0 to i,if we choose 0/1/2 color for house i
        for (int i = 1; i < n; i++) {
            costs[i][0] += Math.min(costs[i - 1][1], costs[i - 1][2]);//+=, not = !!! sum of costs of all houses !!!
            costs[i][1] += Math.min(costs[i - 1][0], costs[i - 1][2]);//if we choose one color for house i,the previous house
            costs[i][2] += Math.min(costs[i - 1][0], costs[i - 1][1]);//should be painted with a different color from house i
        }
        return Math.min(Math.min(costs[n - 1][0], costs[n - 1][1]), costs[n - 1][2]);
    }
}