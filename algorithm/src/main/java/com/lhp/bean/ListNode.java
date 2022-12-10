package com.lhp.bean;

import lombok.Data;

/**
 * @Description: 链表节点
 * @author: lihp
 * @date: 2022/6/22 10:16
 */
@Data
public class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
