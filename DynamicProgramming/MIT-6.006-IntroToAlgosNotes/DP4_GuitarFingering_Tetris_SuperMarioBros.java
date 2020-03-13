/*
In the recurrence, we usually take the min of a bunch of options or the max of a bunch of options.
And those options correspond to a guessed feature. We don't know whether the go left or go right, so we try them both.
That's guessing. But there's another way to guess. So two kinds of guessing. 

In the previous problems we GUESSED which subproblems to use to solve the bigger problem.

In KNAPSACK problem, we added MORE SUBPROBLEMS to GUESS/REMEMBER more features of the solution.
  1. If we look at the suffix - we don't know the capacity we have used up - we don't remember details about the prefix
  2. That is why we add MORE SUBPROBLEMS - to REMEMBER how much capacity has been used in the prefix.
  3. We generated MORE SUBPROBLEMS by - MULTIPLYING every sub-problem by S different choices, which is how many units of knapsack   still remain.
  4. Simply put - we are REMEMBERING MORE about the prefix(past).
  
  
//PIANO OR GUITAR FINGERING - Single Hand, Single Note
Given sequence of n notes, find fingering for each note(single).
  1. Fingers are labelled as - 1,2,....,F
  2. Difficulty Measure - d(p,f,q,g)
     where, f and g denote finger labels
            p and q denote notes
            finger f plays the p note and finger g plays the q note
            
            
DP Approach 1(WRONG APPROACH)- 
1. Subproblems - How to play notes[i:] onwards(suffix)
2. Guess - Which finger to use for note[i]
3. Recurrence - 
      DP(i) = min{ DP(i+1) + d(i,f,i+1.??????) for each f in 1...F}
      d() is the difficulty measure, can be thought of as the transition cost
The above approach is WRONG beause the transition function requires us to know the finger used for the transition.
We need more information about what we are going to do next.

We need to add MORE SUBPROBLEMS.


DP approach 2(Correct) - 
1. Subproblems - How to play notes[i:], when we use finger f for notes[i]
2. Guess - finger g for notes[i+1]
3. Recurrence - 
      DP(i,f) = min{ DP(i+1,g) + d(i,f,i+1,g) for g in 1...F }
4. Topological Order - 
      Bottom up approach 
      for i in reversed(range(n)): //go from right to left
        for f in 1...F:
5. Original Problem - 
   Here the subproblems we are solving require us to provide to first finger label, but we don't know that.
   We don't know which finger to start with, we consider them all and take the min.
   min(DP(0,f) for f in 1...F)
  Time = (num of subproblems)*(time/subproblem) = theta(nF)*theta(F) = theta(n . F^2) 
          when F in small ~ linear time 
   
DAG - complete bipartite graph
               notes
      0 ..........i............. n-1
    1 x1 y1 z1 ....................
    2 x2 y2 z2 .................... 
f   . .............................
    . .............................
    F .............................
   
   
Each cell in the matrix is a node in the BIPARTITE GRAPH ==> 
  each x in column 0 is connected to each y in column 1
  x1 --> (y1,y2,.....yF)
  .
  .
  xF --> (y1,y2,.....yF)
  
  each y in column 1 is connected to each z in column 2
  y1 --> (z1,z2,.....zF)
  .
  .
  yF --> (z1,z2,.....zF)
  and so on...
  
We need to find the shortest path in this DAG, the current formulation is not a single source shortest path problem.
We are looking at all sources to find the shortest path.

This can be converted to single source shortest path by using a dummy node and connecting it with all column 0 nodes with weight 0. Now, the source is this dummy node.



//TETRIS TRAINING
  - given seq of n peices
  - must drp from top
  - full rows don't clear
  - can you SURVIVE? ==> THIS IS THE PROBLEM TO BE SOLVED
  - width w is small
  - board is initially empty
  
  
DP Approach - 
1. Subproblems - How to play? suffix[i:] with peices. Given the board skyline.
   number of subproblems = n.((h+1)^w)
   we have w columns and each column can have a min heigth=0 and max height=h
   total possibilties for height for each column = (h+1)
   num of columns = w
   total possibilties for height for all columns = (h+1)^w
2. Guess - How to play and where to drop the peice?
           We can rotate the peiece in 4 ways
           We can drop the peice at w locations
           number of choices = 4.w
3. Recurrence - survival is the problem. Use boolean or [0/1]
                when using [0/1] we can maximize the recurrence
Total Time = (num of subproblems)*(time/subproblem)
           = n.((h+1)^w).(4.w)
           = theta(n.w.((h+1)^w))
   
*/
