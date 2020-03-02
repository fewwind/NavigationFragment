package com.chaozhuo.parentmanager.test.algorithm;

import com.chaozhuo.parentmanager.bean.LinkNode;
import com.orhanobut.logger.Logger;

import java.util.Stack;

public class Flink {

    static LinkNode node1;
    static LinkNode node2;

    static {
        node1 = new LinkNode(1);
        node2 = new LinkNode(2);
        addLink(node1, new LinkNode(3));
        addLink(node1, new LinkNode(5));
        addLink(node2, new LinkNode(4));
        addLink(node2, new LinkNode(6));
    }

    public LinkNode merge(LinkNode node1, LinkNode node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        LinkNode head = null;
        if (node1.val <= node2.val) {
            head = node1;
            logLinkV(head);
            head.next = merge(node1.next, node2);
        } else {
            head = node2;
            logLinkV(head);
            head.next = merge(node1, node2.next);
        }
        logLink(head);
        return head;
    }
    public LinkNode mergeTwoLists(LinkNode l1, LinkNode l2) {
        // 在返回节点之前维护对节点的不变引用。（？？？）
        LinkNode prehead = new LinkNode(-1);

        LinkNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // l1和l2中正好有一个在这一点上可以是非空的，所以连接
        //非空列表到合并列表的末尾。
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }

    void logLink(LinkNode node) {
        String s = "";
        while (node != null) {
            s = s + node.val + "-";
            node = node.next;
        }
        Logger.e("Node = " + s);
    }

    void logLinkV(LinkNode node) {
        String s = "";
        while (node != null) {
            s = s + node.val + "-";
            node = node.next;
        }
        Logger.v("Node = " + s);
    }

    static void addLink(LinkNode head, LinkNode node) {
        while (head.next != null) {
            head = head.next;
        }
        head.next = node;
    }
    class Queue {
        private Stack stack1;
        private Stack stack2;

        public void push(Object obj) {
            stack1.push(obj);
        }

        public Object pop() {
            if (stack2.isEmpty()) return stack2.pop();
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        }

        public Queue() {
            stack1 = new Stack();
            stack2 = new Stack();
        }
    }
}
