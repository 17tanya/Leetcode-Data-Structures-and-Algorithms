//Leetcode 153. Find Minimum in Rotated Sorted Array
//Question - https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/

class Solution {
    public int findMin(int[] nums) {
        if(nums.length==0 || nums==null) return -1;
        if(nums.length==1) return nums[0];
        
        int low = 0;
        int high = nums.length-1;
        
        // left and right both converge to the minimum index;
        // DO NOT use left <= right because that would loop forever
        
        while(low < high){
        
            // find the middle value between the left and right bounds (their average);
			      // can equivalently do: mid = left + (right - left) // 2,
			      // if we are concerned left + right would cause overflow (which would occur
			      // if we are searching a massive array using a language like Java or C that has
			      // fixed size integer types)
            
            int mid = low + (high-low)/2;
            
            // the main idea for our checks is to converge the left and right bounds on the start
            // of the pivot, and never disqualify the index for a possible minimum value.

            // in normal binary search, we have a target to match exactly,
            // and would have a specific branch for if nums[mid] == target.
            // we do not have a specific target here, so we just have simple if/else.
            
            if(nums[mid] > nums[high]) 
            
                // we KNOW the pivot must be to the right of the middle:
                // if nums[mid] > nums[right], we KNOW that the
                // pivot/minimum value must have occurred somewhere to the right
                // of mid, which is why the values wrapped around and became smaller.

                // example:  [3,4,5,6,7,8,9,1,2] 
                // in the first iteration, when we start with mid index = 4, right index = 9.
                // if nums[mid] > nums[right], we know that at some point to the right of mid,
                // the pivot must have occurred, which is why the values wrapped around
                // so that nums[right] is less then nums[mid]

                // we know that the number at mid is greater than at least
                // one number to the right, so we can use mid + 1 and
                // never consider mid again; we know there is at least
                // one value smaller than it on the right
                
                low = mid + 1;
            
            else 
            
                // here, nums[mid] <= nums[right]:
                // we KNOW the pivot must be the middle or to the left of the middle:
                // if nums[mid] <= nums[right], we KNOW that the pivot was not encountered
                // to the right of middle, because that means the values would wrap around
                // and become smaller (which is caught in the above if statement).
                // this leaves the possible pivot point to be at index <= mid.
                            
                // example: [8,9,1,2,3,4,5,6,7]
                // in the first iteration, when we start with mid index = 4, right index = 9.
                // if nums[mid] <= nums[right], we know the numbers continued increasing to
                // the right of mid, so they never reached the pivot and wrapped around.
                // therefore, we know the pivot must at index <= mid.

                // we know that nums[mid] <= nums[right].
                // therefore, we know it is possible for the mid index to store a smaller
                // value than at least one other index in the list (at right), so we do
                // not discard it by doing right = mid - 1. it still might have the minimum value.
                
                high = mid;
        }
        return nums[low];
    }
}
