package com.chaozhuo.parentmanager.test.algorithm;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;
import java.util.Stack;

public class FTree {

    static {
        TreeNode root = new TreeNode(1);
        TreeNode node21 = new TreeNode(21);
        TreeNode node22 = new TreeNode(22);
        TreeNode node31 = new TreeNode(31);
        TreeNode node32 = new TreeNode(32);
        TreeNode node33 = new TreeNode(33);
        TreeNode node34 = new TreeNode(34);
        root.left = node21;
        root.right = node22;
        node21.left = node31;
        node21.right = node32;
        node22.left = node33;
        node22.right = node34;
    }

    /**
     * 非递归，借助栈来计算深度(层数)
     * 比如                root，先放入栈中
     * 5          当前栈的元素数量为1，len=1，取出栈中此时所有的元素，即5，然后将其子节点3和7放入栈中
     * 3       7      当前栈的元素数量为2，len=2，所以连续从栈中pop两次，使栈中不在含有该层元素，同时将下层节点2和4放入栈中
     * 2         4          当前栈的元素数量为2，len=2，所以连续从栈中pop两次
     * 记录深度，所以每次pop出栈中所有元素(某层的所有节点)只需深度+1，即depth++
     *
     * @param root
     * @return
     */
    public static int treeDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 初始化深度
        int depth = 0;
        // 存放每层树节点的栈
        Stack<TreeNode> stack = new Stack<>();
        // 将树的根(即第一层)放入栈中
        stack.push(root);
        while (!stack.isEmpty()) {
            // 当栈不为空时，层数+1，
            // 因为每次都会pop出当前层的所有节点，并将该层所有节点的子节点放入栈中
            depth++;
            // 当前栈中元素的数量
            int length = stack.size();
            while (length-- > 0) {
                // 取出栈中所有的节点，并将对应节点的子节点放入栈中
                TreeNode node = stack.pop();
                if (node.left != null) {
                    stack.push(node.left);
                }
                if (node.right != null) {
                    stack.push(node.right);
                }
            }
        }
        return depth;
    }

    public void mirror(TreeNode root) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            return;
        }
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        mirror(root.left);
        mirror(root.right);
    }

    public int maxDepth(TreeNode root) {
        int max = 0;//存储子树的深度
        int leftMax = 0;
        int rightMax = 0;
        if (root != null) {//如果当前子树不为空,非空树
            max++;//深度加1
            leftMax = maxDepth(root.left);//左子树深度
            rightMax = maxDepth(root.right);//右子树深度
            max += leftMax >= rightMax ? leftMax : rightMax;//当前子树的深度
        }
        return max;//返回当前子树的深度

    }

    //是否正确？
    int depth(TreeNode root) {
        if (root == null) return 0;
        //此时输出前序遍历
        int left = depth(root.left) + 1;
        //中序
        int right = depth(root.right) + 1;
        //后序
        return Math.max(left, right);
    }

    public int TreeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = TreeDepth(root.left);
        int right = TreeDepth(root.right);
        return left > right ? left + 1 : right + 1;
    }

    int deep(View view) {
        if (!(view instanceof ViewGroup)) {
            return 0;
        }
        ViewGroup group = (ViewGroup) view;
        if (group.getChildCount() == 0) return 0;
        int result = 0;
        for (int i = 0; i < group.getChildCount(); i++) {
            View v = group.getChildAt(i);
            int deep = deep(v) + 1;
            result = Math.max(deep, result);
        }
        return result;
    }

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        public int val;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private void findPath(TreeNode node, List<String> list, String s) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            list.add(s + node.val);
            return;
        }
        if (node.left != null) {
            findPath(node.left, list, s + node.val + "-");
        }
        if (node.right != null) {
            findPath(node.right, list, s + node.val + "-");
        }
    }
//    fun recursionPrint(root:View) {
//        printView(root)
//        if (root is ViewGroup) {
//            for (childIndex in 0 until root.childCount) {
//                val childView = root.getChildAt(childIndex)
//                recursionPrint(childView)
//            }
//        }
//    }
//
//    fun breadthFirst(root :View){
//        val viewDeque = LinkedList<View>()
//        var view = root
//        viewDeque.push(view)
//        while (!viewDeque.isEmpty()){
//            view = viewDeque.poll()
//            printView(view)
//            if(view is ViewGroup){
//                for(childIndex in 0 until view.childCount){
//                    val childView = view.getChildAt(childIndex)
//                    viewDeque.addLast(childView)
//                }
//            }
//        }
//    }
//
//    fun depthFirst(root :View){
//        val viewDeque = LinkedList<View>()
//        var view = root
//        viewDeque.push(view)
//        while (!viewDeque.isEmpty()){
//            view = viewDeque.pop()
//            printView(view)
//            if(view is ViewGroup){
//                for(childIndex in 0 until view.childCount){
//                    val childView = view.getChildAt(childIndex)
//                    viewDeque.push(childView)
//                }
//            }
//        }
//    }
}
