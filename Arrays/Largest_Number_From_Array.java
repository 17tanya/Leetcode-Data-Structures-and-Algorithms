class Solution {
    public String largestNumber(int[] nums) {
        if(nums.length == 0) return "";
        else if(nums.length == 1) return String.valueOf(nums[0]);
        
        String arr[] = new String[nums.length];
        
        for(int i = 0 ; i < nums.length ; i++){
            arr[i] = String.valueOf(nums[i]);
        }
        
        Comparator<String> comp = new Comparator<String>(){
            public int compare(String s1, String s2){
                String a = s1+s2;
                String b = s2+s1;
                
                //descending order
                return b.compareTo(a);
            }  
        };
        
        Arrays.sort(arr, comp);
        if(arr[0].charAt(0) == '0') return "0";
        
        StringBuffer sb = new StringBuffer("");
        for(String s : arr){
            sb.append(s);
        }
        
        return String.valueOf(sb);
    }
    
}
