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
	        
	        for(int i = 0 ; i < n ; i++){
	            a[i] = s.nextInt();
	        }
	        
	        int low = 0;
	        int mid = 0;
	        int high = n-1;
	        int temp = -1;
	   
	        while(mid <= high){
	            switch(a[mid]){
	               case 0:
	                    temp = a[low];
	                    a[low] = a[mid];
	                    a[mid] = temp;
	                    low++;
	                    mid++;
	                    break;
	               case 1:
	                   mid++;
	                   break;
	               case 2:
	                   temp = a[high];
	                   a[high] = a[mid];
	                   a[mid] = temp;
	                   high--;
	                   break;
	            }
	        }
	        
	        for(int i=0;i<n;i++){
	            System.out.print(a[i] + " ");
	        }
	        System.out.println();
	    }
	 }
}
