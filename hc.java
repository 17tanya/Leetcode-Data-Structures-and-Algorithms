import java.util.*;

class library{
    int id;
    int books;
    int signup;
    int booksPerDay;
    ArrayList<Integer> bookIDs;
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
    // Used for sorting in ascending order of 
    // roll number 
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
        int totalDays = s.nextInt();
        int cost[] = new int[totalBooks];
        for(int i=0;i<totalBooks;i++) cost[i] = s.nextInt();
        
        HashMap<Integer,library> map = new HashMap<Integer,library>();
        for(int i=0;i<numOfLib;i++){
            int books = s.nextInt();
            int signup = s.nextInt();
            int bpd = s.nextInt();
            ArrayList<Integer> bid = new ArrayList<Integer>();
            for(int j=0;j<books;j++){
                bid.add(s.nextInt());
            }
            library lib = new library(i,books, signup, bpd, bid);
            map.put(i,lib);
        }
        ArrayList<library> sortedBySignup = new ArrayList(map.values());
        Collections.sort(sortedBySignup, new SortbySignup());
        HashMap<Integer,Integer> map2 = new HashMap<Integer,Integer>();
        int val = totalDays;
        int i = 0;
        
        while(i<numOfLib && val>0){
            library ll = sortedBySignup.get(i);
            int remDays = val - ll.signup;
            int shipment = remDays*ll.booksPerDay;
            map2.put(ll.id,shipment);
            val-=ll.signup;
            i++;
        }
        
        ArrayList<Integer> sol = new ArrayList<Integer>();
        int cntLibs = 0;
        for(Map.Entry<Integer,Integer> entry : map2.entrySet()){
            int ID = entry.getKey();
            int ship = entry.getValue();
            if(ship>0){
                cntLibs++;
                sol.add(ID);
            }
        }
        
        System.out.println(cntLibs);
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
