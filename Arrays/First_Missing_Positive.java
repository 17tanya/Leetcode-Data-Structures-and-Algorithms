//Leetcode 41. First Missing Positive
//Question - https://leetcode.com/problems/first-missing-positive/

/*Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3

Example 2:

Input: [3,4,-1,1]
Output: 2

Example 3:

Input: [7,8,9,11,12]
Output: 1

Note:

Your algorithm should run in O(n) time and uses constant extra space.
*/

class Solution {
    public int firstMissingPositive(int[] nums) {
        if(nums==null || nums.length==0) return 1;
        
        boolean oneFound = false;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==1) oneFound = true;
            else if(nums[i]<=0) nums[i] = Integer.MAX_VALUE;
        }
        
        if(!oneFound) return 1;
        
        for(int i=0 ; i<nums.length ; i++){
            int targetIndex = Math.abs(nums[i])-1;
             if(targetIndex>=0 && targetIndex<nums.length){
                 nums[targetIndex] = Math.abs(nums[targetIndex])*-1;
             }
        }
        
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0) return (i+1);
        }
        return nums.length+1;
       
    }
}
