/*
https://www.geeksforgeeks.org/box-stacking-problem-dp-22/

Question is on the same lines as the Longest Increasing Subsequence
*/

class Box implements Comparable<Box>{ 
      
        // h --> height, w --> width, d --> depth 
        int h, w, d, area; 
          
        // for simplicity of solution, 
        // always keep w <= d 
  
        /*Constructor to initialise object*/
        public Box(int h, int w, int d) { 
            this.h = h; 
            this.w = w; 
            this.d = d; 
        } 
          
        /*To sort the box array on the basis 
        of area in decreasing order of area */
        public int compareTo(Box o) { 
            return o.area-this.area; 
        } 
 } 
 
 //DYNAMIC PROGRAMMING - BOTTOM UP APPROACH
 class BoxStack{
  public int maxHeight(Box b[]){
    int n = b.length;
    Box allBoxes[] = new Box[n*3];
    
    //creating all rotations of a box
    for(int i=0 ; i < n ; i++){
        Box box = b[i];
        allBoxes[i*3] = new Box(box.h, Math.max(box.d, box.w), Math.min(box.d, box.w));
        allBoxes[i*3 + 1] = new Box(box.d, Math.max(box.h, box.w), Math.min(box.h, box.w));
        allBoxes[i*3 + 2] = new Box(box.w, Math.max(box.d, box.h), Math.min(box.d, box.h));
    }
   
    //calculating area of the boxes - we need this because each box must have smaller length and breadth than the box below it
    //this helps us to explore only valid subproblems - explore the boxes having greater dimensions than the current one 
    for(int i=0 ; i < n*3 ; i++) allBoxes[i].area = allBoxes[i].d * allBoxes[i].w;
    
    //sorting in descending order according to the area
    Arrays.sort(allBoxes);
    
    //msh[i] indicates the maximum possible height of a stack when box i is at the top
    int msh[] = new int[n*3];
    
    //storing the current height of each of the box
    for(int i=0 ; i < n*3 ; i++) msh[i] = allBoxes[i].h;
    
    //for each of the box, we compute the maximum stack height when it is at the top
    for(int i=0 ; i < n*3 ; i++){
    
        Box topBox = allBoxes[i];
        
        //initializing maximum stack height of the subproblem
        int maxH = 0;
        
        //SUBPROBLEMS - explore all boxes greater in dimension than the current one to find the one giving the maximum height
        for(int j=0 ; j < i ; j++){
        
            Box bottomBox = allBoxes[j];
            
            //Constraints - greater dimensions than the current box
            if(topBox.w < bottomBox.w && topBox.d < bottomBox.d){
                //Out of all the subproblems consider the one giving max height
                //This box comes below the current box
                maxH = Math.max(maxH, msh[j]);
            }
        }
        
        //Store the result - add the height of the box to the calculated max height from the subproblems
        msh[i] = maxH + topBox.h;
    }
    
    int maxstackHeight = -1;
    for(int i=0 ; i < n*3 ; i++){
        if(maxstackHeight < msh[i]) maxstackHeight = msh[i];
    }
    
    return maxstackHeight;
    
  }
 }
 
 
 /*
 Time Complexity - O(n^2)
 Space Complexity - O(n)
 */
 
 
