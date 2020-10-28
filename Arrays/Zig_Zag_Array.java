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
	        
	        for(int i = 1 ; i < n ; i++){
	            if(i%2 != 0){
	                if(a[i] < a[i-1]){
	                    int temp = a[i];
	                    a[i] = a[i-1];
	                    a[i-1] = temp;
	                }
	            }
	            else{
	                if(a[i] > a[i-1]){
	                    int temp = a[i];
	                    a[i] = a[i-1];
	                    a[i-1] = temp;
	                }
	            }
	        }
	        
	        for(int i = 0 ; i < n ; i++){
	            System.out.print(a[i] + " ");
	        }
	        System.out.println();
	    }
	 }
}
