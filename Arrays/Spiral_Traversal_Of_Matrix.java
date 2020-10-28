import java.util.*;
import java.lang.*;
import java.io.*;
class GFG{
	public static void main (String[] args){
	 //code
	    Scanner s = new Scanner(System.in);
	    int t = s.nextInt();
	    
	    while(t-- > 0){
	        int rows = s.nextInt();
	        int cols = s.nextInt();
	        
	        int mat[][] = new int[rows][cols];
	        
	        for(int i = 0 ; i < rows ; i++){
	            for(int j = 0 ; j < cols ; j++){
	                mat[i][j] = s.nextInt();
	            }
	        }
	        
	        int startRow = 0, startCol = 0, endRow = rows-1, endCol = cols-1;
	        while(startRow <= endRow && startCol <= endCol){
	            for(int i = startCol ; i <= endCol ; i++){
	                System.out.print(mat[startRow][i] + " ");
	            }
	            startRow++;
	            
	            for(int i = startRow ; i <= endRow ; i++){
	                System.out.print(mat[i][endCol] + " ");
	            }
	            endCol--;
	            
	            if(startRow <= endRow){
	                for(int i = endCol ; i >= startCol ; i--){
	                    System.out.print(mat[endRow][i] + " ");
	                }
	                endRow--;
	            }
	            if(startCol <= endCol){
	                for(int i = endRow ; i >= startRow ; i--){
	                    System.out.print(mat[i][startCol] + " ");
	                }
	                startCol++;
	            }
	        }
	        
	        System.out.println();
	    }
	 }
}
