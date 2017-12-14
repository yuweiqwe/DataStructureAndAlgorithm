package com.china.hadoop.bat.chapter5;

import java.util.Stack;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: BinaryTreeOrder
 * @Package com.china.hadoop.bat.chapter5
 * @Description: 二叉树的遍历：前序遍历、中序遍历、后续遍历
 * 前中后是针对父节点的遍历顺序来说的
 * 前序遍历：root-left-right
 * 中序遍历：left-root-right
 * 后续遍历：left-right-root
 * @date 2017/10/19
 */
public class BinaryTreeOrder {

    public static <T extends Comparable<T>> void preOrder(TreeNode<T> treeNode) {
        if (treeNode == null) {
            return;
        }

        System.out.print(treeNode.getValue());

        preOrder(treeNode.getLeft());
        preOrder(treeNode.getRight());
    }

    public static <T extends Comparable<T>> void preOrder1(TreeNode<T> treeNode) {
        if (treeNode == null) {
            return;
        }

        Stack<TreeNode<T>> stack = new Stack<>();
        stack.add(treeNode);

        while(!stack.isEmpty()) {
            TreeNode<T> node = stack.pop();
            System.out.print(node.getValue());

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }
        }
    }

    public static <T extends Comparable<T>> void inOrder(TreeNode<T> treeNode) {
        if (treeNode == null) {
            return;
        }

        inOrder(treeNode.getLeft());
        System.out.print(treeNode.getValue() + " ");
        inOrder(treeNode.getRight());
    }

    public static <T extends Comparable<T>> void inOrder1(TreeNode<T> treeNode) {
        if (treeNode == null) {
            return;
        }

        Stack<TreeNode<T>> stack = new Stack<>();
        TreeNode<T> node = treeNode;
        while(node != null || !stack.isEmpty()){

            while(node != null){
                stack.push(node);
                node = node.getLeft();
            }

            if(!stack.isEmpty()){
                node = stack.pop();
                System.out.print(node.getValue());

                node = node.getRight();
            }
        }
    }

    public static <T extends Comparable<T>> void inOrder2(TreeNode<T> treeNode) {
        if (treeNode == null) {
            return;
        }

        Stack<Pair<TreeNode<T>, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(treeNode, 0));

        Pair<TreeNode<T>, Integer> pair = null;
        TreeNode<T> node = null;
        while(!stack.isEmpty()){
            pair = stack.pop();
            node = pair.getK();

            if(pair.getV() == 0){
                if(node.getRight() != null){
                    stack.push(new Pair<>(node.getRight(), 0));
                }
                stack.push(new Pair<>(node, 1));
                if(node.getLeft() != null){
                    stack.push(new Pair<>(node.getLeft(), 0));
                }
            }else{
                System.out.print(node.getValue());
            }
        }
    }

    public static <T extends Comparable<T>> void postOrder(TreeNode<T> treeNode) {
        if (treeNode == null) {
            return;
        }

        postOrder(treeNode.getLeft());
        postOrder(treeNode.getRight());
        System.out.print(treeNode.getValue());
    }

    public static <T extends Comparable<T>> void postOrder1(TreeNode<T> treeNode) {
        if (treeNode == null) {
            return;
        }

        Stack<Pair<TreeNode<T>, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(treeNode, 0));

        Pair<TreeNode<T>, Integer> pair = null;
        TreeNode<T> node = null;
        while(!stack.isEmpty()){
            pair = stack.pop();
            node = pair.getK();

            if(pair.getV() == 0){
                stack.push(new Pair<>(node, 1));
                if(node.getRight() != null){
                    stack.push(new Pair<>(node.getRight(), 0));
                }
                if(node.getLeft() != null){
                    stack.push(new Pair<>(node.getLeft(), 0));
                }
            }else{
                System.out.print(node.getValue());
            }
        }
    }

    public static void main(String[] args) {
        TreeNode<String> right4 = new TreeNode<>(null, null, "right4");

        TreeNode<String> left1 = new TreeNode<>(null, right4, "left1");
        TreeNode<String> right1 = new TreeNode<>(null, null, "right1");

        TreeNode<String> left2 = new TreeNode<>(null, null, "left2");
        TreeNode<String> right2 = new TreeNode<>(null, null, "right2");

        TreeNode<String> left3 = new TreeNode<>(left1, right1, "left3");
        TreeNode<String> right3 = new TreeNode<>(left2, right2, "right3");

        TreeNode<String> root = new TreeNode<>(left3, right3, "root");

        System.out.println("------preOrder-------");
        preOrder(root);
        System.out.println("------preOrder1-------");
        preOrder1(root);
        System.out.println("------inOrder-------");
        inOrder(root);
        System.out.println("------inOrder1-------");
        inOrder1(root);
        System.out.println("------inOrder2-------");
        inOrder2(root);
        System.out.println("------postOrder-------");
        postOrder(root);
        System.out.println("------postOrder1-------");
        postOrder1(root);

    }

}
