/*
https://www.techiedelight.com/total-possible-solutions-linear-equation-k-variables/

Problem is similar to the problem - number of ways to change coin.
We either include the current coefficient or exlcude it.

Recurrence - 
count(coeff, k, rhs) = count(coeff, k, rhs-coeff[k]) + count(coeff, k-1, rhs)
where, count(coeff, k, rhs-coeff[k]) --> including coeff[k]
       count(coeff, k-1, rhs) --> excluding coeff[k]
*/

//Recursion

public int count(int coeff[], int index, int rhs){
    //Base Case: we have considered valid coeff and their summation equals rhs
    if(rhs == 0) return 1;
    
    //Base Case: we have considered invalid coeff and their summation does not equals rhs
    else if(rhs<0 || index< 0) return 0;
    
    //Include the current coeff
    int includeIndex = count(coeff, index, rhs-coeff[index]);
    
    //Exclude the current coeff
    int excludeIndex = count(coeff, index-1, rhs);
    
    return include + exclude;
}


//Memoized Recursion
public int count(int coeff[], int index, int rhs, HashMap<String, Integer> map){

    //Base Case: we have considered valid coeff and their summation equals rhs
    if(rhs == 0) return 1;
    
    //Base Case: we have considered invalid coeff and their summation does not equals rhs
    else if(rhs<0 || index< 0) return 0;
    
    String key = index + "|" + rhs;
    
    if(!map.containsKey(key)){
        //Include the current coeff
        int includeIndex = count(coeff, index, rhs-coeff[index]);
    
        //Exclude the current coeff
        int excludeIndex = count(coeff, index-1, rhs);
        
        map.put(key, include + exclude);
    }
    
    return map.get(key);
    
}

//Bottom up DP
public int count(int coeff[], int rhs){
       int numOfCoeff = coeff.length;
       
       //dp[i] indicates the number of ways to form a sum, sum = i
       int dp[] = new int[numOfCoeff+1];
       dp[0] = 1;
       
       /*
       we consider all coefficients in the range [0...i] when computing dp[]
       for first interation i = 0 --> only first coeff is considered
       for second iteration i = 1 --> first two coeficients are considered [0,1]
       for last interation i = numOfCoeff --> all coefficients are considered
       
       We basically answer the question - 
            #ways to sum up to j when we conisder only the first coefficient?
            #ways to sum up to j when we conisder the first two coefficients?
            .....
            #ways to sum up to RHS when we consider all coefficients
       */
       for(int i=0 ; i < numOfCoeff ; i++){
              for(int j=coeff[i] ; j <= rhs ; j++){
                    //since we are including coeff[i] in sum = j, we must add #ways to get a sum = (j-coeff[i])
                     dp[j] += dp[ j - coeff[i] ];
              }
       }
       
       return dp[rhs];
}


/*
Time Complexity - O(numberOfCoefficients * RHS)
Pseudo Polynomial
*/

