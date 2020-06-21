//LeetCode 11. Container with most water
//Question - https://leetcode.com/problems/container-with-most-water/

class Solution {
    public int maxArea(int[] height) {
        int start = 0;
        int end = height.length - 1;
        int maxWater = 0;
        
        /*
        Brute force solution would be to evaluate all containers. This will take
        O(n^2) time. This can be avoided by considering the fact that the 
        maximum water a container can hold is restricted by the minimum of the
        two sides. The minimum value acts as a limiting factor. 
        
        There is no point in pairing-up this minimum value with other greater values
        as the height is limited by this value.
        
        To gain maximum profit from this value, we need to pair it with the
        farthest value which is equal to or greater than this value. This is 
        the maximum water we can retain with this value and so it is safe to
        move the pointer pointing at this minimum value.
        */
        
        while(start < end){
            int water = Math.min(height[start], height[end]) * (end - start);
            maxWater = Math.max(maxWater, water);
            
            if(height[start] < height[end]) start++;
            else end--;
        }
        
        return maxWater;
    }
}