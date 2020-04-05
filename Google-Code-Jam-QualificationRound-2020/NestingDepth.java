import java.util.*;

class Solution{
    public static void main(String args[]){
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for(int i=0;i<t;i++){
            String str = s.next();
            StringBuffer sb = new StringBuffer("");
            int pN = str.charAt(0) - '0';
            for(int j=0;j<pN;j++) sb.append("(");
            sb.append(str.charAt(0));
            
            for(int j=1;j<str.length();j++){
                int cN = str.charAt(j) - '0';
                
                if(cN > pN){
                    for(int k=0;k<(cN-pN);k++){
                        sb.append("(");
                    }
                    sb.append(str.charAt(j));
                }
                else if(cN < pN){
                    for(int k=0;k<(pN-cN);k++){
                        sb.append(")");
                    }
                    sb.append(str.charAt(j));
                }
                else if(cN == 0 || pN == cN) sb.append(str.charAt(j));
                
                pN = cN;
            }
            
            pN = str.charAt(str.length()-1) - '0';
            for(int j=0;j<pN;j++) sb.append(")");
            System.out.println("Case #"+(i+1)+": "+sb);
            
        }
    }
}
