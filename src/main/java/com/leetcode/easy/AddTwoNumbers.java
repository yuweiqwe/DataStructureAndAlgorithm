package com.leetcode.easy;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: AddTwoNumbers
 * @Package com.leetcode
 * @Description: 两个链表对应位置数相加，附带进位--从低位到高位
 * @date 2017/11/14
 */
public class AddTwoNumbers {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode r = new ListNode(0);
        ListNode l3 = r;
        int carry = 0;

        while(l1 != null || l2 != null){
            l3.next = new ListNode(0);
            l3 = l3.next;

            if(l1 != null && l2 != null){
                l3.val = (l1.val + l2.val + carry)%10;
                carry = (l1.val + l2.val + carry)/10;
                l1 = l1.next;
                l2 = l2.next;
            }else if(l1 != null){
                l3.val = (l1.val + carry)%10;
                carry = (l1.val + carry)/10;
                l1 = l1.next;
            }else{
                l3.val = (l2.val + carry)%10;
                carry = (l2.val + carry)/10;
                l2 = l2.next;
            }

        }

        if(carry > 0){
            l3.next = new ListNode(carry);
        }

        return r.next;
    }


}
