/*
https://www.techiedelight.com/word-break-problem/
https://www.geeksforgeeks.org/word-break-problem-using-backtracking/?ref=rp
*/

public void printWordBreak(String s, Set<String> dict, String res){
  if(s.length() == 0){
    System.out.println(res);
    return;
  }
  
  for(int i=1;i<=s.length();i++){
    String prefix = s.substring(0,i);
    
    if(dict.contains(prefix)){
        printWordBreak(s.substring(i), dict, res+" "+prefix);
    }
  }
}
