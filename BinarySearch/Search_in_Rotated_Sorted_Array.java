//Leetcode 33. Search in Rotated Sorted Array
//Question - https://leetcode.com/problems/search-in-rotated-sorted-array/

/*Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4

Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

*/

class Solution {
    public int search(int[] nums, int target) {
        int len = nums.length;
        int low = 0;
        int high = len-1;
        
        //finding index of pivot
        while(low < high){
            int mid = low + (high-low)/2;
            if(nums[mid] > nums[high]) low = mid + 1;
            else high = mid;
        }
        
        int pivotIndex = low;
        low = 0;
        high = len-1;
        while(low <= high){
            //calculating mid according to orignal array sorted in ascending order
            int mid = low + (high-low)/2;
            
            //calculating mid in accordance to rotated array
            //each element is displaced by "pivot" ==> add pivot ==> modulo becuase the rotation is circular
            int midOfRotatedArray = (mid + pivotIndex) % len;
            
            if(nums[midOfRotatedArray]==target) return midOfRotatedArray;
            else if(nums[midOfRotatedArray] < target) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }
}
