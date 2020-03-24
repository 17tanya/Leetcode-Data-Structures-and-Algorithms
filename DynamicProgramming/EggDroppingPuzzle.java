/*
https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/

Question: You are given n eggs and specified a number of k floors. Write an algorithm to find the minimum number of drops is required to know the floor from which if the egg is dropped, it will break.
Assumptions - 
…..An egg that survives a fall can be used again.
…..A broken egg must be discarded.
…..The effect of a fall is the same for all eggs.
…..If an egg breaks when dropped, then it would break if dropped from a higher floor.
…..If an egg survives a fall then it would survive a shorter fall.

Optimal Substructure
When we drop an egg from a floor x, there can be two cases (1) The egg breaks (2) The egg doesn’t break.

1) If the egg breaks after dropping from xth floor, then we only need to check for floors lower than x with remaining eggs; so the problem reduces to x-1 floors and n-1 eggs
2) If the egg doesn’t break after dropping from the xth floor, then we only need to check for floors higher than x; so the problem reduces to k-x floors and n eggs.

Since we need to minimize the number of trials in worst case, we take the maximum of two cases. We consider the max of above two cases for every floor and choose the floor which yields minimum number of trials.

  k ==> Number of floors
  n ==> Number of Eggs
  eggDrop(n, k) ==> Minimum number of trials needed to find the critical
                    floor in worst case.
  eggDrop(n, k) = 1 + min{max(eggDrop(n - 1, x - 1), eggDrop(n, k - x)): 
                 x in {1, 2, ..., k}}

*/

//DYNAMIC PROGRAMMING - BOTTOM UP APPROACH

public int minEggDrops(int eggs, int floors){

    if(floors==0 || floors==1 || eggs==1) return floors;
    
    int drops[][] = new int[eggs+1][floors+1];
    
    //when we have only one floor --> #eggs does not matter --> only one drop rqd
    for(int i=1 ; i <= eggs ; i++) drops[i][1] = 1;
    
    //when we have only one egg --> we need to drop it from floor1 then floor2 then floor3...--> In worst case #drops = #floors 
    for(int j=1 ; j <= floors ; j++) drops[1][j] = j;
    
    for(int i=2 ; i <= eggs ; i++){
    
        for(int j=2 ; j <= floors ; j++){
        
            drops[i][j] = Integer.MAX_VALUE;
            
            //simulating the drop from each floor in the range [1...j]
            for(int k=1 ; k <= j ; k++){
            
                /*
                considering the WORST CASE by choosing the maximum number of drops from each floor
                drops[i-1][k-1] --> egg breaks, #floors under consideration reduces by 1 --> all floors below the current floor
                drops[i][j-k] --> egg does not break, #floors under consideration --> all floors above the current floor
                */
                
                int maxDropsForWorstCase = 1 + Math.max(drops[i-1][k-1], drops[i][j-k]);
                
                /*
                After looking through all the floors ie. the maximum number of drops required from each floor,
                we chose the floor which gives us the minimum number of drops in the worst case.
                ie. We look through the maximum number of drops required for each floor and chose the floor which
                results in minimum of all the worst cases
                */
                
                if(drops[i][j] > maxDropsForWorstCase){
                    drops[i][j] = maxDropsForWorstCase;
                }
            }
        }
    }
    
    return drops[eggs][floors];
}

/*
Time Complexity - O(eggs * floors^2)
Space Complexity - O(eggs * floors)
*/
