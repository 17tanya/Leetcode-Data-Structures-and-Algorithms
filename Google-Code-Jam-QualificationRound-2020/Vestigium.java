/*
Problem Link - https://codingcompetitions.withgoogle.com/codejam/round/000000000019fd27/000000000020993c
*/

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
            
            //calculating trace and checking if each row contains distinct elements
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    arr[j][k] = s.nextInt();
                    //trace
                    if(j == k) trace += arr[j][k];
                    //adding all elements of the row to the set
                    set.add(arr[j][k]);
                }
                //below condition is true when row contains duplicates
                if(set.size() != n) row++; 
                set.clear();
            }
            
            set.clear();
            for(int j=0;j<n;j++){
                for(int k=0;k<n;k++){
                    //adding all elements of the column to the set
                    set.add(arr[k][j]);
                }
                //below condition is true when column contains duplicates
                if(set.size() != n) col++; 
                set.clear();
            }
            
            System.out.println("Case #"+(i+1)+": "+trace+" "+row+" "+col);
        }
    }
    
}
