/*
https://www.techiedelight.com/longest-common-subsequence-finding-lcs/
Start from bottom right corner and make your way up to the top left corner.
Logic to find one LCS - 
1. If the current characters are equal, append the character to the solution string and move up along the left diognal.
2. If the current characters are not equal, we go top or left depending on which cell holds the maximum value.
*/

class LCS{

  public String lcs(String a, String b, int n, int m, int matrix[][]){
        if(n==0 || m==0) return "";
    
        if(a.charAt(n-1)==b.charAt(m-1)){
                return ( lcs(a,b,n-1,m-1,matrix) + a.charAt(n-1) );
        }
        else if(matrix[n-1][m] > matrix[n][m-1]){
                return lcs(a,b,n-1,m,matrix);
        }
        else{
                return lcs(a,b,n,m-1,matrix);
        }
  }
  
  public int LCSlength(String a, String b, int n, int m, int lcs[][]){
    
        for(int i=0;i<=n;i++) lcs[i][0] = 0;
        for(int j=0;j<=m;j++) lcs[0][j] = 0;
    
        for(int i=1;i<=n;i++){
        for(int j=1;j<=m;j++){
        if(a.charAt(i-1)==b.charAt(j-1)){
                lcs[i][j] = lcs[i-1][j-1] + 1;
        }
        
        else{
                lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
        }
      }
    }
    return lcs[n][m];
    
  }

}


/*
Logic to find all LCSs - 
1. If the current characters are equal, append the character to the solution string and move up along the left diognal.
2. If the current characters are not equal, we go top or left depending on which cell holds the maximum value.
    2.1. If both the cells hold the same value we explore them both
*/

class LCS{

  public List<String> lcs(String a, String b, int n, int m, int matrix[][]){
        if(n==0 || m==0){
                List<String> list = new ArrayList<String>();
                list.append("");
                return list;
        }
    
        if(a.charAt(n-1)==b.charAt(m-1)){
                List<String> list = lcs(a,b,n-1,m-1,matrix);
      
                for(int i=0;i<list.size();i++){
                        list.set(i,list.get(i)+a.charAt(n-1));
                }
                return list;
        }
    
        else if(matrix[n-1][m] > matrix[n][m-1]){
                return lcs(a,b,n-1,m,matrix);
        }
        else if(matrix[n-1][m] < matrix[n][m-1]){
                return lcs(a,b,n,m-1,matrix);
        }
        else{
                List<String> top = lcs(a,b,n-1,m,matrix);
                List<String> left = lcs(a,b,n,m-1,matrix);
      
                top.addAll(left);
                return top;
        }
    
  }
  
  public int LCSlength(String a, String b, int n, int m, int lcs[][]){
    
    for(int i=0;i<=n;i++) lcs[i][0] = 0;
    for(int j=0;j<=m;j++) lcs[0][j] = 0;
    
    for(int i=1;i<=n;i++){
      for(int j=1;j<=m;j++){
        if(a.charAt(i-1)==b.charAt(j-1)){
          lcs[i][j] = lcs[i-1][j-1] + 1;
        }
        
        else{
          lcs[i][j] = Math.max(lcs[i-1][j], lcs[i][j-1]);
        }
      }
    }
    return lcs[n][m];
    
  }
  
  //to avoid duplicates
  public Set<String> LCSset(String a, String b, int matrix[][]){
        LCSlength(a,b,a.length(),b.length(),matrix);
          
        ArrayList<String> list = lcs(a,b,a.length(),b.length(),matrix);
          
        return new HashSet<>(list);
  }

}




