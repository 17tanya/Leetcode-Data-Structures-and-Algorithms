//LeetCode 2. Add Two Numbers
//Question - https://leetcode.com/problems/add-two-numbers/

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node1 = l1;
        ListNode node2 = l2;
        int val1 = 0;
        int val2 = 0;
        
        ListNode dummy = new ListNode();
        ListNode curr = dummy;
        int carry = 0;
        
        while(node1 != null || node2 != null){
            val1 = (node1 == null) ? 0 : node1.val;
            val2 = (node2 == null) ? 0 : node2.val;
            
            int sum = val1 + val2 + carry;
            if(sum > 9){
                carry = 1;
                curr.next = new ListNode(sum % 10);
            }
            else{
                carry = 0;
                curr.next = new ListNode(sum);
            }
            
            curr = curr.next;
            node1 = (node1 == null) ? null : node1.next;
            node2 = (node2 == null) ? null : node2.next;
        }
        
        if(carry == 1) curr.next = new ListNode(carry);
        
        return dummy.next;
    }
}
