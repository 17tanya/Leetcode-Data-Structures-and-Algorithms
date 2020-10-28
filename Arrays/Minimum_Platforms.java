import java.util.*;
import java.lang.*;
import java.io.*;
class GFG
 {
	public static void main (String[] args)
	 {
	 //code
	    Scanner s = new Scanner(System.in);
	    int t = s.nextInt();
	    
	    while(t-- > 0){
	        int n = s.nextInt();
	        
	        int arr[] = new int[n];
	        for(int i = 0 ; i < n ; i++){
	            arr[i] = s.nextInt();
	        }
	        
	        int dept[] = new int[n];
	        for(int i = 0 ; i < n ; i++){
	            dept[i] = s.nextInt();
	        }
	        
	        Arrays.sort(arr);
	        Arrays.sort(dept);
	        
	        int i = 1;
	        int j = 0;
	        int plat = 1;
	        int res = 1;
	        
	        while(i < n && j < n){
	            //if current arrival time is smaller than previous departure 
	            //time then, move to the next arrival time to see if that
	            //too is smaller than previous departure time.
	            if(arr[i] <= dept[j]){
	                plat++;
	                i++;
	            }
	            //If current arrival time is greater than previous departure
	            //time then, move to next departure time to see if that
	            //arrival time is greater than next departure too or not
	            else{
	                plat--;
	                j++;
	            }
	            
	            res = Math.max(res, plat);
	        }
	        
	        System.out.println(res);
	    }
	 }
}
