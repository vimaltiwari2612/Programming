/*

 Best Time to Buy and Sell Stock
 
 Say you have an array for which the ith element is the price of a given stock on day i.

If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.

Note that you cannot sell a stock before you buy one.

Example 1:

Input: [7,1,5,3,6,4]
Output: 5
Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
             Not 7-1 = 6, as selling price needs to be larger than buying price.
Example 2:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.
*/

class Solution {

//Greatest right element - current = max
public int maxProfit(int[] prices) {
    
    int size = prices.length;
    if(size == 0) return 0;
    int[] rightMax = new int[size];
    
    rightMax[size-1] = prices[size - 1];
    //find the right max of each element,
    //this will the selling price of each element
    for(int i = size - 2 ; i>=0;i--){
        rightMax[i] = Math.max(rightMax[i+1],prices[i]);
       
    }
   
    int buy = 0,sell=0,max = 0;
    for(int i = 0;i<size;i++){
        //if you buy on ith day
        buy = prices[i];
        //sell on this price
        sell = rightMax[i];
      
        //if maximum profit, update it
        if(max < (sell - buy)){
            max = sell- buy;
        }
    }
    return max;
}
 
 //Function to find the days of buying and selling stock for max profit.
    int stockBuySell(int A[], int n) {
        // code here
        
        //need to find the maximum in right for each element
        //max right, after which there is a downfall or no element
        Stack<Integer> stack = new Stack<>();
        int[] NSR = new int[n];
        NSR[n-1] = n-1; //saving the last index
        stack.push(A[n-1]);
        for(int i = n-2 ; i >= 0 ; i--){
            if(A[i] > stack.peek()){//if current is greater then top, i.e. next item
                NSR[i] = i; //save the index
            }else{
                NSR[i] = NSR[i+1]; //else copy from next
            }
            stack.push(A[i]);
        }
		int maxProfit = 0;
        for(int i = 0; i < n ; i++){

            int buy = A[i];
            int sell = A[NSR[i]];
            int profit = sell - buy;
            if(maxProfit < profit){
				maxProfit = profit;
			}
        }
       //print the max profit
	   return maxProfit;
    }
}

