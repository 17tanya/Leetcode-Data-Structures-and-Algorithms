//Leetcode 35. Search Insert Position
//Question - https://leetcode.com/problems/search-insert-position/

/*Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.
Example 1:

Input: [1,3,5,6], 5
Output: 2

Example 2:

Input: [1,3,5,6], 2
Output: 1

Example 3:

Input: [1,3,5,6], 7
Output: 4

Example 4:

Input: [1,3,5,6], 0
Output: 0
*/

class Solution {
    public int searchInsert(int[] nums, int target) {
        int index = -1;
        int low = 0; int high = nums.length-1;
        int mid = -1;
        int result = -1;
        
        while(low<=high){
            mid = low + (high-low)/2;
            
            if(nums[mid]==target) return mid; //if target is present
            
            else if(nums[mid]>target){ 
                result = mid; //store the latest mid ==> this could be the first occurrence of least element greater than target
                high = mid - 1; //push algorithm to left in hopes to find first occurrence of least element greater than target OR to find the target itself
            }
            else low = mid+1;
        }
        
        //if the control is here the target value must not be in nums
        if(result==-1) return nums.length; //No element in nums was greater than target ==> the target is the greatest ==> needs to be added at the end
        else return result;//correct position for target found
        
    }
}
