/*
https://www.techiedelight.com/increasing-subsequence-with-maximum-sum/

*/

public void printMaxSumIncreasingSubseq(int A[]){
    int n = A.length;
    
    int sum[] = new int[n];
    sum[0] = A[0];
    
    //to store the subseq
    List<List<Integer>> msis = new ArrayList<Integer>();
    
    for(int i=0;i<n;i++){
        msis.add(new ArrayList<Integer>());
    }
    
    //Initialization
    msis.get(0).add(A[0]);
    
    
    for(int i=1 ; i < n ; i++){
        for(int j=0 ; j < i ; j++){
            //update the value of sum and the corresponding subseq
            if(A[j] < A[i] && sum[i] < sum[j]){
                sum[i] = sum[j];
                msis.set(i, new ArrayList<Integer>(msis.get(j)));
            }
        }
        //add the last value to the sum
        sum[i] += A[i];
        msis.get(i).add(A[i]);
    }
    
    int index = 0;
    for(int i=1;i<n;i++){
        if(sum[index] < sum[i]) index = i;
    }
    
    System.out.println("Maximum Sum : "+sum[index]);
    System.out.println("Maximum Sum Increasing Subsequence : "+msis.get(index));
    
}

/*
Time & Space Complexity - O(n^2)
*/

