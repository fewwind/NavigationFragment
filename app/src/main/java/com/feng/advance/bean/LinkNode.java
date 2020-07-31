package com.feng.advance.bean;

public class LinkNode {
    public int val;
    public LinkNode pre;
    public LinkNode next;

    public LinkNode(int val) {
        this.val = val;
    }


    public LinkNode(int val, LinkNode next) {
        this.val = val;
        this.next = next;
    }


    public void add(LinkNode LinkNode) {
        LinkNode curr = this;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = LinkNode;
        LinkNode.pre = curr;
    }

    public void add(int index, LinkNode LinkNode) {
        if (index < 0) return;
        LinkNode curr = this;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
            if (curr == null) return;
        }
        if (curr.pre != null) curr.pre.next = LinkNode;
        LinkNode.pre = curr.pre;
        LinkNode.next = curr;
        curr.pre = LinkNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        LinkNode LinkNode = this;
        while (LinkNode != null) {
            sb.append("," + LinkNode.val);
            LinkNode = LinkNode.next;
        }
        return sb.toString();
    }
}
