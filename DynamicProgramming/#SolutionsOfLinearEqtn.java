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


/*
Time Complexity - O(numberOfCoefficients * RHS)
Pseudo Polynomial
*/

