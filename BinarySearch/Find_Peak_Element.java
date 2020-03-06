//Leetcode 162. Find Peak Element
//Question - https://leetcode.com/problems/find-peak-element/

/*A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.

Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.

Note:

Your solution should be in logarithmic complexity.
*/

class Solution {
//We can view any given sequence in numsnumsnums array as alternating ascending and descending sequences.
    public int findPeakElement(int[] nums) {
        if(nums.length==0 || nums==null) return -1;
        else if(nums.length==1) return 0;
        
        
        //We start off by finding the middle element, mid from the given nums array.
        //If this element happens to be lying in a descending sequence of numbers.
        //or a local falling slope(found by comparing nums[i]nums[i]nums[i] to its right neighbour),
        //it means that the peak will always lie towards the left of this element.
        //Thus, we reduce the search space to the left of midmidmid(including itself) and perform the same process on left subarray.

        //If the middle element, mid lies in an ascending sequence of numbers
        //or a rising slope(found by comparing nums[i]nums[i]nums[i] to its right neighbour),
        //it obviously implies that the peak lies towards the right of this element.
        //Thus, we reduce the search space to the right of midmidmid and perform the same process on the right subarray.

        //In this way, we keep on reducing the search space till we eventually reach a state where only one element is remaining in the search space.
        //This single element is the peak element.
        
        int low = 0;
        int high = nums.length-1;
        while(low < high){
            int mid = low + (high-low)/2;
            if(nums[mid] <= nums[mid+1]){
                low = mid + 1;
            }
            else high = mid;
        }
        
        return low;
    }
}
