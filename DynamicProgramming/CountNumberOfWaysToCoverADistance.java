/*
https://www.geeksforgeeks.org/count-number-of-ways-to-cover-a-distance/
https://www.geeksforgeeks.org/count-number-of-ways-to-cover-a-distance-set-2/

Given a distance 'dist', count total number of ways to cover the distance with 1, 2 and 3 steps.

Examples :

Input:  n = 3
Output: 4
Below are the four ways
 1 step + 1 step + 1 step
 1 step + 2 step
 2 step + 1 step
 3 step

Input:  n = 4
Output: 7

In the given problem we have 3 choices to cover EACH STEP of a distance X 
1. Take a step of length = 1, remaining dist = X-1 ==> recur on (X-1)
2. Take a step of length = 2, remaining dist = X-2 ==> recur on (X-2)
3. Take a step of length = 3, remaining dist = X-3 ==> recur on (X-3)
*/

public int countPaths(int dist){

  //if (dist == 0) then the steps taken are valid return 1 to increment the number of valid paths
  if(dist == 0) return 1;
  
  /*
  if (dist < 0) then the steps taken are invalid and don't sum to the original distance. This path will not contribute to the
  total number of valid paths
  */
  else if(dist<0) return 0;
  
  //explore the 3 choices to find all possible paths
  else return (countPaths(dist-1) + countPaths(dist-2) + countPaths(dist-3));
}

/*
Time Complexity - O(3^distance)
For each value of distance we explore all the 3 choices to find all possible paths
    1. distance-1
    2. distance-2
    3. distance-3
*/

/*
DYNAMIC PROGRAMMING - BOTTOM UP APPROACH
The main problem can be divided into subproblems through recursion.
These subproblems are overlapping ==> we can apply dynamic programming
*/

public int countPaths(int dist){
    //paths[i] indicates the number of possible ways to reach the distance 'i', given we are allowed to take steps
    //of length 1, 2 and 3.
    int paths[] = new int[dist+1];
    
    //Base Cases: becuase each subproblem depends on three smaller subproblems
    paths[0] = 1;
    paths[1] = 1;
    paths[2] = 2;
    
    for(int d=3 ; d<=dist ; d++){
        //solving subproblem paths[d] with smaller subproblems
        paths[d] = paths[d-1] + paths[d-2] + paths[d-3];
    }
    
    //final ans - total number of paths possible
    return paths[dist];
}

/*
Time Complexity & Space Complexity - O(distance)
*/


/*
DYNAMIC PROGRAMMING - SPACE OPTIMIZED
to calculate the number of steps to cover the distance i, only the last three states are required (i – 1, i – 2, i – 3).
So, keep track of last 3 states only.
*/

public int countPaths(int dist){
    if(dist==0 || dist==1) return 1;
    else if( dist==2 ) return 2;
    
    int subProb0 = 1;
    int subProb1 = 1;
    int subProb2 = 2;
    int numOfPaths = 0;
    
    for(int d=3;d<=dist;d++){
        numOfPaths = subProb0 + subProb1 + subProb3;
        subProb0 = subProb1;
        subProb1 = subProb2;
        subProb3 = numOfPaths;
    }
    
    return numOfPaths;
    
}

/*
Time Complexity - O(distance)
Space Complexity - O(1)
*/
