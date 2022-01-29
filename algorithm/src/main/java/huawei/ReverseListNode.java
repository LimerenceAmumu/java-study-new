package huawei;

import java.util.Stack;

/**
 * @author Amumu
 * @create 2021/7/31 10:55
 * 反转链表
 */
class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

public class ReverseListNode {
    public ListNode ReverseList(ListNode head) {


        Stack<ListNode> listNodes = new Stack<>();

        while (head != null) {
            listNodes.push(head);
            head = head.next;
        }

        if(listNodes.isEmpty()){
            return null;
        }
        ListNode node = listNodes.pop();
        ListNode newHead = node;
        while (!listNodes.isEmpty()){
            ListNode tempNode = listNodes.pop();
            node.next=tempNode;
            node = node.next;

        }
        node.next = null;

        return newHead;
    }
}
