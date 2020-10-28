import java.util.*;
import java.lang.*;
import java.io.*;
class GFG{
	public static void main (String[] args){
	 //code
	    Scanner s = new Scanner(System.in);
	    int t = s.nextInt();
	    
	    while(t-- > 0){
	        int n = s.nextInt();
	        int arr[] = new int[n];
	        
	        for(int i = 0 ; i < n ; i++){
	            arr[i] = s.nextInt();
	        }
	        
	        List<Integer> buy = new ArrayList<>();
	        List<Integer> sell = new ArrayList<>();
	        
	        int stocks = 0;
	        int i = 0;
	        while(i <= n-2){ //we 
	            while(i < n-1 && arr[i] >= arr[i+1]){
	                i++;
	            }
	            
	            if(i == n-1) break;
	            
	            buy.add(i++);
	            
	            while(i < n && arr[i] >= arr[i-1]){
	                i++;
	            }
	            
	            sell.add(i-1);
	            
	            stocks++;
	        }
	        
	        if(stocks == 0) System.out.println("No Profit");
	        else{
	            for(i = 0 ; i < buy.size() ; i++){
	                System.out.print("(" + buy.get(i) + " " + sell.get(i) + ") ");
	            }
	            System.out.println();
	        }
	    }
	 }
}
