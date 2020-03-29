/*
https://www.techiedelight.com/longest-bitonic-subsequence/

*/

public void lengthOfLongestBitonicSubseq(int A[]){
    int n = A.length;
    
    //Instead of storing max length of subseq, we store the entire subseq
    List<List<Integer>> I = new ArrayList<>();
    List<List<Integer>> D = new ArrayList<>();
    
    for(int i = 0 ; i < n ; i++){
        I.add(new ArrayList<Integer>());
        D.add(new ArrayList<Integer>());
    }
    
    //Initialization
    I.get(0).add(A[0]);
    D.get(n-1).add(A[n-1]);
    
    //starting from the start of the array
    for(int i=1 ; i < n ; i++){
        
        //for all elements occuring before A[i], check if they can be included to form an increasing subseq ending at i
        for(int j=0 ; j < i ; j++){
            //comparing the array elements and the length of longest increasing subseq
            if(A[j] < A[i] && I.get(i).size() < I.get(j).size()){
                I.set(i, new ArrayList<>(I.get(j)));
            }
        }
        //add current element at the end of the subseq
        I.get(i).add(A[i]);
    }
    
    //starting from the end of the array
    //Increasing subseq from end --> decreasing subseq from start
    for(int i=n-2 ; i >= 0 ; i--){
        
        //for all elements occuring after A[i], check if they can be included to form an increasing subseq ending at i
        for(int j=n-1 ; j > i ; j--){
            if(A[i] > A[j] && D.get(j).size() > D.get(i).size()){
                D.set(i, new ArrayList<>(D.get(j)));
            }
        }
        //add current element to the start of the subseq
        D.get(i).add(0, A[i]);
    }
    
    //Find length of longest bitonic subseq
    int maxInd = 0;
    for(int i=1 ; i < n ; i++){
        //subtract 1 --> i is included twice, both in I and D
        if(I.get(maxInd).size() + D.get(maxInd).size() < I.get(i).size() + D.get(i).size()) maxInd = i;
    }
    
    //Print increasing subseq
    System.out.print(I.get(maxInd));
    //This element is repeated --> remove it
    D.get(maxInd).remove(0);
    //Print decreasing subseq
    System.out.print(D.get(maxInd));
    
}

