//O(1) space solution, https://discuss.leetcode.com/topic/5934/is-it-best-solution-with-o-n-o-1
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int hold1 = Integer.MIN_VALUE;//initialize hold to Integer.MIN_VALUE cuz hold can be negative
        int hold2 = Integer.MIN_VALUE;
        int sell1 = 0;//initialize sell to 0 cuz it's at least 0 (zero profit if we don't have any transactions)
        int sell2 = 0;
        for (int i : prices) {
            sell2 = Math.max(sell2, hold2 + i);//max of - firstBuyPrice + firstSellPrice - secondBuyPrice + secondSellPrice
            hold2 = Math.max(hold2, sell1 - i);//max of - firstBuyPrice + firstSellPrice - secondBuyPrice
            sell1 = Math.max(sell1, hold1 + i);//max of - firstBuyPrice + firstSellPrice
            hold1 = Math.max(hold1, -i);//max of - firstBuyPrice
        }
        return sell2;
    }
}
//basically we wanna get a max of - firstBuyPrice + firstSellPrice - secondBuyPrice + secondSellPrice, which means the total
//profit(diff of buy&sell prices) of the two transactions,each time we meet a new i,we try to update the max of these 4 sum

//order of updating these 4 variables also matters: sell2 based on hold2; hold2 based on sell1; sell1 based on hold1;
//updating sell1 before hold1 is to create a 1-day time difference between 1st sell and 1st buy, instead of on the same day
//updating sell2 before hold2 is to create a 1-day time difference between 2st sell and 2st buy, instead of on the same day
//updating sell2&hold2 before sell1&hold1 is to create a 1-day time difference between them, instead of on the same day\

//sell2: max val of (-firstBuyPrice + firstSellPrice -secondBuyPrice + secondSellPrice) if we've just sold 2nd stock so far
//hold2: max val of (-firstBuyPrice + firstSellPrice -secondBuyPrice) if we've just buy  2nd stock so far
//sell1: max val of (-firstBuyPrice + firstSellPrice) if we've just sold 1nd stock so far
//hold1: max val of (-firstBuyPrice) if we've just buy  1st stock so far


//O(n) space solution, apply the solution we have on prob 4
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int n = prices.length;
        int[][] local = new int[n][3];//local[i][j] means the max profit, and there must be a transaction sold on day i
        int[][] global = new int[n][3];//global[i][j] means the max profit from day 0 to i, with k transactions
        for (int i = 1; i < n; i++) {
            int diff = prices[i] - prices[i - 1];
            for (int j = 1; j <= 2; j++) {
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
        return global[n - 1][2];//2, not 3
    }
}
//O(n) space solution
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[] left = new int[prices.length];//max profit from 0(starting boundary) to i day if at most buy&sell once
        //it also means the latest day we can sell it's day i
        int[] right = new int[prices.length];//max profit from length-1(starting boundary) to i day if at most buy&sell once
        //it also means the earliest day we can buy it's day i
        
        //this part is just like buy&sell problem 1, though you need to store each 0 to i day's max profit
        left[0] = 0;//this variable works the same as the "profit" variable in prob 1, should be 0 if you buy&sell at day 0
        int min = prices[0];//the lowest buy price from 0 to i day
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            left[i] = Math.max(left[i - 1], prices[i] - min);//left[i-1] means we don't sell on day i,the latter means we do
        }
        
        //this part is a reversed version of prob 1,why reverse? cuz our starting boundary is length-1,instead of start from i
        right[prices.length - 1] = 0;//the highest sell price from length - 1 to i day
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; i--) {
            max = Math.max(max, prices[i]);
            right[i] = Math.max(right[i + 1], max - prices[i]);//right[i+1] means we don't buy on day i,the latter means we do
        }
        
        //check which day it's the best deadline of finishing the first transaction and opening for the second transaction
        //this doesn't mean we need to sell old stock on day i,or buy new stock on day i,it's just a pivot of two transactions
        int profit = 0;
        for (int i = 0; i < prices.length; i++) {
            profit = Math.max(profit, left[i] + right[i]);
        }
        return profit;
    }
}