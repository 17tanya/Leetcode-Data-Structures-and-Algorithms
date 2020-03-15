/*
https://www.techiedelight.com/longest-increasing-subsequence-using-dynamic-programming/
REFER - Length of Longest Increasing Subsequence Code
*/

public void printLIS(int A[]){
    List<List<String>> LIS = new ArrayList<String>();
    
    for(int i=0 ; i<A.length ; i++) LIS.add(new ArrayList<String>());
    
    //LIS of A[0] is itself
    LIS.get(0).add(A[0]);
    
    for(int i=1 ; i<A.length ; i++){
        for(int j=0 ; j<i ; j++){
            if(A[j] < A[i] && LIS.get(i).size() < LIS.get(j).size()){
                LIS.set(i, new ArrayList<>(LIS.get(j)));
            }
        }
        LIS.get(i).add(A[i]);
    }
    
    int index = 0;
    for(int i=1 ; i<LIS.size() ; i++){
        if(LIS.get(index).size() < LIS.get(i).size()) index = i;
    }
    
    System.out.println(LIS.get(index));
    
}
