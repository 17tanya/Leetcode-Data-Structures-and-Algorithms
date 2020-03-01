/*How to approach this problem?
1. Need to analyse the entire string ==> iteration
2. Involves parantheses which can also be nested ==> solve the innermost parentheses first ==> Go deep in the string to find the innermost parentheses ==> similar to depth first approach
3. need to keep track of count for each set of parenthese ==> need storage, but we need to access the innermost first ==> stack
4. need to keep track of the string ==> can be maintained as two parts ==>
    prefix - the part which is to be repeated
    suffix - the part to which the repeated string needs to be attached.
5. store prefix in a variable
6. store suffix in a stack 
7. As we move through the string there are 4 types of characters we may encounter ==> digit(number), alphabet, [, ]
==> for each of these we write a separate else-if statement
*/
class Solution {
    public String decodeString(String s) {
        Stack<Integer> count = new Stack<>();
        Stack<String> suffixString = new Stack<>();
        String res=""; //this at all times must contain the string within the current set of parentheses ==> if the string were to be drwan as a tree, each nested parentheses would indicate a deeper level ==> res contains the string in the current level
        int index = 0;
        
        while(index<s.length()){
            if(Character.isDigit(s.charAt(index))){
                int cnt = 0;
                //loop to form the number 
                while(Character.isDigit(s.charAt(index))){
                    cnt = 10*cnt + (int)(s.charAt(index) - '0');
                    index++;
                }
                count.push(cnt);
            }
            else if(s.charAt(index)=='['){
                //push the latest suffix into the stack
                suffixString.push(res);
                res = ""; //clear previous value ==> need to store the string which starts with this [
                index++;
            }
            else if(s.charAt(index)==']'){
                //stop scanning the string. Pop suffix from stack, pop counter from stack. keep adding res to suffix till the counter becomes zero
                StringBuffer suffix = new StringBuffer(suffixString.pop());
                int repeat = count.pop();
                for(int i=0;i<repeat;i++){
                    suffix.append(res);
                }
                res = String.valueOf(suffix);
                index++;
            }
            else{
                //start storing the string which started with [
                res+=s.charAt(index);
                index++;
            }
        }
        return res;
        
    }
}