public class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0] == null || costs[0].length == 0) {
            return 0;
        }
        int n = costs.length;
        int m = costs[0].length;
        int min1 = -1;//store the index of color that has min cost of house i
        int min2 = -1;
        for (int i = 0; i < n; i++) {//i = 0, not i = 1 !!!
            int lastMin1 = min1;//we need to store last min1&min2 colors before we look for curr house's min1&min2 colors
            int lastMin2 = min2;
            min1 = -1;//remember to update min1&min2 to -1 !!!
            min2 = -1;
            for (int j = 0; j < m; j++) {//for all house i with color j
                if (lastMin1 != j) {
                    costs[i][j] += lastMin1 == -1 ? 0 : costs[i - 1][lastMin1];//+=,not= !need original value of costs[i][j]
                } else {
                    costs[i][j] += lastMin2 == -1 ? 0 : costs[i - 1][lastMin2];//if it's first value,costs[i][j]+0=costs[i][j]
                }
                if (min1 == -1 || costs[i][j] < costs[i][min1]) {//if the cost of color j is less than color min1
                    min2 = min1;
                    min1 = j;
                } else if (min2 == -1 || costs[i][j] < costs[i][min2]) {
                    min2 = j;
                }
            }
        }
        return costs[n - 1][min1];//no need to scan the min again cuz it has been stored
    }
}