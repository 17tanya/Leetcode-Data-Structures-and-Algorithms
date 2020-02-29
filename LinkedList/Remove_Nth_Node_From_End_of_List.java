// Leetcode 19. Remove Nth Node From End of List
//Question - https://leetcode.com/problems/remove-nth-node-from-end-of-list/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode dummy = new ListNode(0); //to avoid corner cases like list with one/two nodes
        dummy.next = head;
        ListNode fastPointer = dummy;
        ListNode slowPointer = dummy;
        
        for(int i=0;i<=n;i++){ //runs n times
            //fastPointer has a head-start of n nodes 
            fastPointer = fastPointer.next;
        }
        
        while(fastPointer!=null){ //runs (N+1-n) times
            //when fastPointer will become null ==> it has reached end of list ==> slowPointer must be n nodes behind ==> slowPointer.next is the node to be deleted
            fastPointer = fastPointer.next;
            slowPointer = slowPointer.next;
        }
        
        slowPointer.next = slowPointer.next.next;
        return dummy.next;
        
        //It is a one-pass algorithm ==> n+(N+1)-n
        
    }
}
