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
	        int a[] = new int[n];
	        
	        int rightSum = 0;
	        for(int i = 0 ; i < n ; i++){
	            a[i] = s.nextInt();
	            rightSum += a[i];
	        }
	        
	        if(n == 1){
	            System.out.println(1);
	            continue;
	        }
	        
	        int leftSum = 0;
	        boolean flag = false;
	        
	        for(int i = 0 ; i < n ; i++){
	            rightSum -= a[i];
	            
	            if(leftSum == rightSum){
	                System.out.println(i+1);
	                flag = true;
	                break;
	            }
	            
	            leftSum += a[i];
	        }
	        
	        if(!flag) System.out.println(-1);
	        
	    }
	 }
}
