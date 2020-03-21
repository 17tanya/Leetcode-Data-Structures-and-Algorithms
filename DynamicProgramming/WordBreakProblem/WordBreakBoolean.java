/*
https://www.geeksforgeeks.org/word-break-problem-dp-32/
https://leetcode.com/problems/word-break/discuss/43790/Java-implementation-using-DP-in-two-ways

NOTE - NOT SURE ABOUT THE TIME COMPLEXITY
Comment from Leetcode - 
just want to add some comments for the time complexity:
First DP: [length of s][size of dict][avg length of words in dict]
Second DP: [length of s]^3

BTW, for this kind of problem, which time complexity is [length of s][size of dict][avg length of words in dict]. We can usually remove [size of dict] by using Tire, remove [avg length of words in dict] by KMP, and what's more, remove both [size of dict] and [avg length of words in dict] by AC-automata. And of course these are all doable for this problem.
This is just a insight for people who want to think deeper about this problem, hope it can help you :)


QUESTION - 
Given an input string and a dictionary of words, find out if the input string can be segmented into a space-separated sequence of dictionary words.
{ i, like, sam, sung, samsung, mobile, ice, cream, icecream, man, go, mango}
Input:  ilike
Output: Yes 
The string can be segmented as "i like".

Input:  ilikesamsung
Output: Yes
The string can be segmented as "i like samsung" 
or "i like sam sung".
*/

//DP - Method 1

public boolean wordBreak(String s, Set<String> dict){

    //T[i] indicates if it is possible to break the string s[0...i] into valid words from the dictionary
    boolean T[] = new boolean[s.length()+1];
    
    //i=0 indicates empty string, which is valid ==> true
    T[0] = true;
    
    //the string under consideration starts from length=1 and goes up to length=s.length()
    //This is a bottom up approach - we start from the base condition ie. length=1
    //During each iteration the string under consideration is s[0....i]
    for(int i=1 ; i <= s.length() ; i++){
    
        //j is used to create a partition
        //The problem here is to check if s[0...i] can be broken down into valid words from the dictionary
        //We need to find where to make a break to get a valid result ==> explore all breaking points using 'j'
        //'j' breaks the string s[0...i] into two parts ==> s[0...j) and s[j...i)
        //subproblem is s[0...j) which has already been solved and the result is stored in T[j]
        //In this loop we basically - 
        //1. choose a partition index - j
        //2. check if the suffix string is present in the dictionary
        //3. If it is then check if the prefix can be broken into valid words from the dictionary
        //4. If both 2 and 3 are true, we found another valid break of the string at index j, break as soon as 2 & 3 become true 
        
        //we start at j=0 because we want to check if the entire word s[0...i] is present in the dictionary or not
        for(int j=0 ; j < i ; j++){
            if(dict.contains(s.substring(j,i)) && T[j]){
                T[i] = true;
                break;
            }
        }
    }
    
    return T[s.length()];
}
//Time - O(n^2) OR O(n^3) ????

//DP-Method 2

public boolean wordBreak(String s, Set<String> dict){
    
    //T[i] indicates if it is possible to break the string s[0...i] into valid words from the dictionary
    boolean T[] = new boolean[s.length()+1];
    
    //i=0 indicates empty string, which is valid ==> true
    T[0] = true;
    
    //the string under consideration starts from length=1 and goes up to length=s.length()
    //This is a bottom up approach - we start from the base condition ie. length=1
    //During each iteration the string under consideration is s[0....i]
    for(int i=1 ; i<=s.length() ; i++){
    
        //look through all the strings in the dictionary and see if any of these words fits as a suffix in the current string
        for(String str : dict){
            //1. To check if a string is present as a suffix we must first see if current 'i' ie. length of string under
            //consideration is greater than the string from the dictionary
            //2. If 1 is true we check if the string from dictionary matches with the suffix of string
            //3. If 2 is true we solve the subproblem s[0......(i-str.length())] to see if this part of the string can be broken
            
            /*
            This code is exactly the same as the above - the inner for loop is used for partitioning in both the code snippets.
            Here we use the dictionary string's length to partition 
            In the previous code a separate index is used to partition
            */
            
            if(i >= str.length() && s.substring(i-str.length(),i).equals(str) && T[i-str.length()]){
                T[i] = true;
                break;
            }
        }
    }
}
//Time - O(s.length()*dict.size())
