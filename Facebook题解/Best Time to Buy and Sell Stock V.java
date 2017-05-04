// each day can buy one share of stocks, each sell will sell all the shares
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int max = prices[prices.length - 1];
        int profit = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);//we will choose to buy one share on days that have prices[i] < max
            profit += max - prices[i];
        }
        return profit;
    }
}