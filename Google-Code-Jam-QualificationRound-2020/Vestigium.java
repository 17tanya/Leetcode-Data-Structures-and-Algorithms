import java.util.*;

class Solution{
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for(int i=0;i<t;i++){
            int n = s.nextInt();
            int arr[][] = new int[n][n];
            
            HashSet<Integer> set = new HashSet<Integer>();
            int trace = 0;
            int row = 0;
            int col = 0;
            
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    arr[j][k] = s.nextInt();
                    if(j == k) trace += arr[j][k];
                    set.add(arr[j][k]);
                }
                if(set.size() != n) row++; 
                set.clear();
            }
            
            set.clear();
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    set.add(arr[k][j]);
                }
                if(set.size() != n) col++; 
                set.clear();
            }
            
            System.out.println("Case #"+(i+1)+": "+trace+" "+row+" "+col);
        }
    }
    
}