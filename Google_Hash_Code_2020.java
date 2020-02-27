//This is my submission for the Google Hash Code 2020 Online Qualification Round. In fetched me a score of 15,435,636
//and a Global Rank of 5364 out of 10724 teams.

//A few modifiactions can be done to improve the code and score further.

import java.util.*;

//class to store details of each library
class library{
    int id; //unique Library ID
    int books; //number of books in library
    int signup; //Sign up time
    int booksPerDay; //number of books that can be processed per day
    ArrayList<Integer> bookIDs; //IDs of books in the library
    library(int i,int b, int s, int bp, ArrayList<Integer> bIDs){
        id = i;
        books = b;
        signup = s;
        booksPerDay = bp;
        bookIDs = bIDs;
    }
}

class SortbySignup implements Comparator<library> 
{ 
    // Used for sorting in ascending order of library sign up time
    public int compare(library a, library b) 
    { 
        return a.signup - b.signup; 
    } 
} 



public class hc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);
        	int totalBooks = s.nextInt();
        	int numOfLib = s.nextInt();
        	int totalDays = s.nextInt(); //total number of days where libraries can sign-up and books can be proceesed
        	int cost[] = new int[totalBooks]; //to store cost of each book
		
        	for(int i=0;i<totalBooks;i++) cost[i] = s.nextInt();
        	
		//to store Library Info with it's Unique ID
        	HashMap<Integer,library> map = new HashMap<Integer,library>();
		
		//storing information of all libraries
        	for(int i=0;i<numOfLib;i++){
            		int books = s.nextInt(); //num of books in library
            		int signup = s.nextInt(); //Sign-up time of library
            		int bpd = s.nextInt(); //number of books that can be processed per day
			
			//to store book IDs of books in the library
            		ArrayList<Integer> bid = new ArrayList<Integer>();
			
            		for(int j=0;j<books;j++){
                		bid.add(s.nextInt());
            		}
			
            		library lib = new library(i,books, signup, bpd, bid);
            		map.put(i,lib);
        	}
		
		//sorting libraries by sign-up time
        	ArrayList<library> sortedBySignup = new ArrayList(map.values());
        	Collections.sort(sortedBySignup, new SortbySignup());
		
		//Map to store Library ID and the number of days when the books can be shipped
        	HashMap<Integer,Integer> map2 = new HashMap<Integer,Integer>();
        	int val = totalDays;
        	int i = 0;
        	
		//calculating shipment days for each library
        	while(i<numOfLib && val>0){
            		library ll = sortedBySignup.get(i);
            		int remDays = val - ll.signup;
            		int shipment = remDays*ll.booksPerDay;
            		map2.put(ll.id,shipment);
            		val-=ll.signup;
            		i++;
        	}
        
		//store library IDs to be displayed on console
        	ArrayList<Integer> sol = new ArrayList<Integer>();
        	int cntLibs = 0;
		
		
        	for(Map.Entry<Integer,Integer> entry : map2.entrySet()){
            		int ID = entry.getKey();
            		int ship = entry.getValue();
            		if(ship>0){ //to check for a valid value
                		cntLibs++;
                		sol.add(ID);
            		}
        	}
        	
		//Printing solution
       	 	System.out.println(cntLibs); //number of libraries
        	for(int j=0;j<cntLibs;j++){
            		int ID = sol.get(j);
            		int shipBooks = map2.get(ID);
           	 	library llll = map.get(ID);
            		ArrayList<Integer> bid = llll.bookIDs;
            		shipBooks = Math.min(bid.size(),shipBooks);
            		System.out.println(ID+" "+shipBooks);
            
            		for(int k=0;k<shipBooks;k++){
                		System.out.print(bid.get(k)+" ");
            		}
            		System.out.println();
        	}
	}

}
