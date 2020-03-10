package com.chaozhuo.parentmanager.test.algorithm;

import com.chaozhuo.parentmanager.bean.LinkNode;
import com.orhanobut.logger.Logger;

import java.util.HashSet;
import java.util.Set;
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

    // 单链表反转
    private void reverseLink(Node head) {
        Node next = null;
        Node pre = null;
        // 每次循环head都在改变，一次是链表的node，第一次node1，第二次node2 ...
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
    }


    //判断链表是否有环
    private boolean isNodeCycle(Node node) {
        Set<Node> set = new HashSet<>();
        while (node != null) {
            if (set.contains(node)) {
                return true;
            } else {
                set.add(node);
                node = node.next;
            }
        }
        return false;
    }

    //判断链表是否有环
    private boolean isNodeCycle2(Node node) {
        Node quick = node;
        Node slow = node;
        // 为什么是quick的循环，因为只要有环必定永不为空，while会一直循环，必能能找到快慢相等的点
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
            if (quick == slow) return true;
        }
        return false;
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
        LinkNode p = head;
        while ((p.next) != null) {
            p = p.next;
        }
        p.next = node;
    }

    class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }
    }

    class Queue {
        private Stack stack1;
        private Stack stack2;

        public void push(Object obj) {
            stack1.push(obj);
        }

        public Object pop() {
            if (!stack2.isEmpty()) return stack2.pop();
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
