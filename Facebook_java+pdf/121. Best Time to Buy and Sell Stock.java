## Idea
* Create two values to track min price and max profit
* Max value must be behind the min value
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int profit = 0;//max profit from 0(starting boundary) to i day if at most buy&sell once
        //it also means the latest day we can sell it's day i
        int min = prices[0];//the lowest buy price from 0 to i day
        for (int i : prices) {
            min = Math.min(min, i);//each time we meet price[i] that's smaller than previous min,we buy stock on that day
            profit = Math.max(profit, i - min);//try to sell stock each time we meet a new price
        }//"profit" means we don't sell on day i,the latter(i - min) means we do
        return profit;
    }
}