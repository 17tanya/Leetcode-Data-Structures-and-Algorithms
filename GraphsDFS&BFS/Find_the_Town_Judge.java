//Leetcode 997. Find the Town Judge
//Question - https://leetcode.com/problems/find-the-town-judge/

class Solution {
    public int findJudge(int N, int[][] trust) {
        if(N==1) return 1;
        int trusted[] = new int[N+1];
        
        for(int i=0 ; i<trust.length ; i++){
            trusted[trust[i][1]]++;
        }
        
        int possibleJudge = -1;
        
        for(int i=0;i<=N;i++){
            if(trusted[i]==N-1){
                if(possibleJudge==-1){
                    possibleJudge = i;
                }
                else return -1; //only the judge can have indegree=N-1
                //if more than one people satisfy this condition then their is NO judge
            }
        }
        
        //check that possibleJudge doesn't trust anyone
        for(int i=0;i<trust.length;i++){
            if(trust[i][0]==possibleJudge) return -1;
        }
        
        return possibleJudge;
    }
}
