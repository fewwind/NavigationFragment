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
//        003-从尾到头打印链表
//        014-链表中倒数第k个结点
//        015-反转链表
//        016-合并两个或k个有序链表
//        025-复杂链表的复制
//        036-两个链表的第一个公共结点
//        055-链表中环的入口结点
//        056-删除链表中重复的结点
        node1 = new LinkNode(1);
        node2 = new LinkNode(2);
        addLink(node1, new LinkNode(3));
        addLink(node1, new LinkNode(5));
        addLink(node2, new LinkNode(4));
        addLink(node2, new LinkNode(6));
    }


    // 单链表反转
    private void reverseLink(LinkNode head) {
        LinkNode next = null;
        LinkNode pre = null;
        // 每次循环head都在改变，一次是链表的node，第一次node1，第二次node2 ...
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
    }

    public void printLinkRevarse(LinkNode node){
        Stack<LinkNode> stack = new Stack<>();
        while (node != null){
            stack.push(node);
            node = node.next;
        }
        while (!stack.isEmpty()) stack.pop();
    }
    public void printLinkCycle(LinkNode node){
        if (node != null){
            printLinkCycle(node.next);
            System.out.println(node.val);
        } else return;
    }

    //判断链表是否有环
    private boolean isNodeCycle(LinkNode node) {
        Set<LinkNode> set = new HashSet<>();
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


    private LinkNode findMid(LinkNode node) {
        LinkNode quick = node;
        LinkNode slow = node;
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
        }
        return slow;
    }


    private void findPublic(LinkNode node1, LinkNode node2) {
        while (node1 != null && node2 != null) {
            if (node1.val < node2.val) {
                node1 = node1.next;
            } else if (node1.val < node2.val) {
                node2 = node2.next;
            } else {
                node1 = node1.next;
                node2 = node2.next;
            }
        }
    }


    //判断链表是否有环
    public static boolean isNodeCycle2(LinkNode node) {
        LinkNode quick = node;
        LinkNode slow = node;
        // 为什么是quick的循环，因为只要有环必定永不为空，while会一直循环，必能能找到快慢相等的点
        while (quick != null && quick.next != null) {
            quick = quick.next.next;
            slow = slow.next;
            if (quick.val == slow.val) {
                printLn(slow);
                return true;
            }
        }
        return false;
    }


    public LinkNode merge(LinkNode node1, LinkNode node2) {
        if (node1 == null) return node2;
        if (node2 == null) return node1;
        LinkNode head = null;
        if (node1.val <= node2.val) {
            head = node1;
            printLink(head);
            head.next = merge(node1.next, node2);
        } else {
            head = node2;
            printLink(head);
            head.next = merge(node1, node2.next);
        }
        printLink(head);
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

    public void backwardsK(int k,LinkNode node){
        LinkNode quick = node;
        LinkNode slow = node;
        while (k -->0){
            quick = quick.next;
        }
        while (quick != null){
            quick = quick.next;
            slow = slow.next;
        }
    }
    public void delBackwardsK(int k,LinkNode node){
        LinkNode quick = node;
        LinkNode slow = node;
        LinkNode pre = node;
        while (k -->0){
            quick = quick.next;
        }
        while (quick != null){
            quick = quick.next;
            pre = slow;
            slow = slow.next;
        }
        pre.next = slow.next;
        printLink(node);
    }
    public static void printLink(LinkNode node) {
        String s = "";
        while (node != null) {
            s = s + node.val + "-";
            node = node.next;
        }
        Logger.e("Node = " + s);
    }
    public static void printLn(LinkNode node) {
        String s = "";
        while (node != null) {
            s = s + node.val + "-";
            node = node.next;
        }
        System.out.print("Node = " + s);
    }


    public static void deleteRepeat(LinkNode node){
        LinkNode head = node;
        while ( head != null && head.next != null){
            if (head.val == head.next.val){
                head.next = head.next.next;
                //head  = head.next;
            }else {
                head  = head.next;
            }
        }
        printLn(node);
    }
    public static void deleteVal(LinkNode node,int val){
        LinkNode head = new LinkNode(-1);
        LinkNode pre = head;
        while ( pre.next != null){
            if (pre.next.val ==val){
                pre = pre.next.next;
            } else {
                pre = pre.next;
            }
        }
        printLn(node);
    }

    static void addLink(LinkNode head, LinkNode node) {
        LinkNode p = head;
        while ((p.next) != null) {
            p = p.next;
        }
        p.next = node;
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
