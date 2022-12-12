package com.lhp.linklist;

import com.lhp.bean.ListNode;
import org.junit.Test;

/**
 * @author : lihp
 * @Description : 在合并两个有序链表时让你合二为一，而这里需要分解让你把原链表一分为二。具体来说，我们可以把原链表分成两个小链表，
 * 一个链表中的元素大小都小于 x，另一个链表中的元素都大于等于 x，最后再把这两条链表接到一起，
 * @date : 2022/7/25 13:49
 */
public class partitionList {

    //反转前n个链表    1->2  ->3-4
    ListNode successor = null; // 后驱节点

    // 1 4 3 2 5 2     x=3
    // 1 2 2 4 3 5


    //对于递归算法，最重要的就是明确递归函数的定义。具体来说，我们的 reverse 函数定义是这样的：
    //输入一个节点 head，将「以 head 为起点」的链表反转，并返回反转之后的头结点。
    //递归反转链表
    ListNode reverse(ListNode head) {
        System.out.println(head.val);
        if (head == null || head.next == null) {
            System.out.println("=" + head.val);
            return head;
        }
        ListNode last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode last = reverseList(head.next); // 最深处的last会到达最后一个节点
        head.next.next = head; // 让下一个节点的next指向自己
        head.next = null; // 自己的next废弃掉
        return last; // 返回的是最深处的last，也就是原链表的最后一个节点
    }

    // 3->4
    public ListNode reverseListL(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //      last= 4            head=3  head.next=4
        ListNode last = reverseListL(head.next);


        head.next.next = head;  // 3->4->3
        head.next = null;//  3->null   4->3

        return last; // 4
    }

    // 3->4->5
    public ListNode reverseListL2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        //      last= 5            head=4  head.next=5       ||      last=4  head=3
        ListNode last = reverseListL2(head.next);//一直到链表最末尾


        head.next.next = head;  // 3->4->5->4      ||    3->4->3
        head.next = null;//  4->null   3->4->null  5->4    ||    3->null   4->3

        return last; // 4
    }

    @Test
    public void testReverse() {

        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);

        reverse(listNode);

    }

    @Test
    public void testReverseN() {
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        reverseN(listNode, 2);
    }

    // 需要一个节点记录后驱节点
    // 反转以 head 为起点的 n 个节点，返回新的头结点
    ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode last = reverseN(head.next, n - 1);

        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }


}
