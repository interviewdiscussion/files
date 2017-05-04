public class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k < 1) {
            return 0;
        }
        int n = prices.length;
        if (k >= n / 2) {//this means we can have have enough transaction times to buy and sell for every two days
            return getMaxProfit(prices);//just like prob 2
        }
        int[][] local = new int[n][k + 1];//local[i][j] means the max profit, and there must be a transaction sold on day i
        int[][] global = new int[n][k + 1];//global[i][j] means the max profit from day 0 to i, with k transactions
        for (int i = 1; i < n; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = 1; j <= k; j++) {
                //why global[i-1][j-1]+Math.max(diff,0)?cuz local[i][j] means 1 transaction that must be sold on day i
                //we can't guarantee we have a transaction that was sold on day i-1,so we need 1 transaction time left,so j-1
                //if diff>0 we may use it to buy on i-1 day and sell on i day to earn positive profit
                //if diff<=0 we may use it to buy&sell on i day to earn zero profit
                
                //but why local[i-1][j]?why not j-1?cuz we know that local[i-1][j] must have a
                //transaction that was sold on day i-1,we can merge that transaction with the one that was sold on day i
                //so that we don't waste a transaction time, cuz "sell on day i-1" and "buy on day i-1 and sell on day i"
                //these two transactions can be merged into a transaction that sell on day i
                
                //why +diff?why not max(diff,0)?cuz we merge the last transaction of day i-1,so no matter profitable or not,
                //we have to sell the stock, which we bought before day i-1, on day i
                
                //global[i-1][j] means we don't buy or sell on day i, local[i][j] means we have a transaction sold on day i
                local[i][j] = Math.max(global[i - 1][j - 1] + Math.max(diff, 0), local[i - 1][j] + diff);
                global[i][j] = Math.max(global[i - 1][j], local[i][j]);
            }
        }
        return global[n - 1][k];//k, not k + 1 !!!
    }
    
    private int getMaxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1];
            }
        }
        return profit;
    }
}