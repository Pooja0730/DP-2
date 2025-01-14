// Time Complexity : O(m*n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach


/*
 * 1. Coin change - 2
 */
class Solution1 {
    //Recursion approach
    /*public int change(int amount, int[] coins) {
        if(coins == null) return 0;
        return helper(coins, amount , 0);
    }
    
    private int helper(int[] coins, int amount, int index){
        //base
        if(amount == 0) return 1;
        if(amount < 0 || index == coins.length) return 0;
        //logic
        //when coin is chosen
        int case1 = helper(coins, amount - coins[index], index);
        //when coin isn't chosen
        int case2 = helper(coins, amount, index + 1);
        
        return case1 + case2;
    }
    */
    
    public int change(int amount, int[] coins) {
        if(coins == null) return 0;
        int[][] dp = new int[coins.length + 1][amount + 1];
        for(int i= 0;i<dp.length;i++){
            dp[i][0] = 1;
        }
        for(int i = 1; i<dp.length;i++){
            for(int j = 1; j<dp[0].length;j++){
                if(j < coins[i-1]){
                    dp[i][j] = dp[i-1][j];
                }
                else{
                    dp[i][j] = dp[i-1][j] + dp[i][j - coins[i-1]];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length-1];
    }
}

//Time Complexity : 1. Order of exponential; 2. O(mn); 3. O(n);
//Space Complexity : 1. O(n); 2. O(n); 3. O(n));
//Did this code successfully run on Leetcode : No
//Any problem you faced while coding this :


//Your code here along with comments explaining your approach

/*
 * 2. Paint house - DP
 * */
class Solution2 {
	/*//Recursive approach
	public static int minCost(int[][] costs){
		if(costs == null || costs.length == 0) return 0;
		int caseR = helper(costs, 0, 0, 0);
		int caseG = helper(costs, 0, 1, 0);
		int caseB = helper(costs, 0, 2, 0);
		return Math.min(caseR, Math.min(caseG, caseB));
	}
	
	private static int helper(int[][] costs, int row, int color, int min){
		//base
		if(row == costs.length) return min;
		//logic
		if(color == 0){
			return Math.min(
					helper(costs, row + 1, 1, min + costs[row][0]),
					helper(costs, row + 1, 2, min + costs[row][0])
			);
		}
		if(color == 1){
			return Math.min(
					helper(costs, row + 1, 0, min + costs[row][1]),
					helper(costs, row + 1, 2, min + costs[row][1])
			);
		}
		return Math.min(
				helper(costs, row + 1, 0, min + costs[row][2]),
				helper(costs, row + 1, 1, min + costs[row][2])
		);
	}
	*/
	
	/*//using DP by manipulating the same matrix
	public static int minCost(int[][] costs){
		if(costs == null || costs.length == 0) return 0;
		for(int i = costs.length-2; i>=0; i--){
			costs[i][0] = costs[i][0] + Math.min(costs[i+1][1], costs[i+1][2]);
			costs[i][1] = costs[i][1] + Math.min(costs[i+1][0], costs[i+1][2]);
			costs[i][2] = costs[i][2] + Math.min(costs[i+1][0], costs[i+1][1]);
		}
		return Math.min(costs[0][0], Math.min(costs[0][1], costs[0][2]));
	}
	*/
	
	//using dp but not manipulating the same matrix
	public static int minCost(int[][] costs){
		if(costs == null || costs.length == 0) return 0;
		int cRed = costs[costs.length-1][0];
		int cGreen = costs[costs.length-1][1];
		int cBlue = costs[costs.length-1][2];
		for(int i = costs.length-2; i>=0; i--){
			int tempR = cRed;
			int tempG = cGreen;
			int tempB = cBlue;
			cRed = costs[i][0] + Math.min(tempG, tempB);
			cGreen = costs[i][1] + Math.min(tempR, tempB);
			cBlue = costs[i][2] + Math.min(tempR, tempG);
		}
		return Math.min(cRed, Math.min(cGreen, cBlue));
	}
}
