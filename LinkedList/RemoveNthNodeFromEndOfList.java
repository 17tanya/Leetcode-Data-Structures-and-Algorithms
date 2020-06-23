//LeetCode 19. Nth Node From End of List
//Question - https://leetcode.com/problems/remove-nth-node-from-end-of-list/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //dummy is needed when the node to be deleted is the first node
        //In this case, the fast pointer would point to null after completing the for loop
        //As a result the while loop is not activated and slow remains at the dummy node.
        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;
        
        //Make the fast pointer point to a location just after the nth node to be deleted
        for(int i = 1 ; i <= n + 1; i++){
            fast = fast.next;
        }
        
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        
        slow.next = slow.next.next;
        return dummy.next;
    }
}
