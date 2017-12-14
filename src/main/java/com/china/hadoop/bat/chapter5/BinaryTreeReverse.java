package com.china.hadoop.bat.chapter5;

import java.util.Random;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: BinaryTreeReverse
 * @Package com.china.hadoop.bat.chapter5
 * @Description: 二叉树翻转
 * @date 2017/10/21
 */
public class BinaryTreeReverse {


    public static TreeNode<Integer> reverse(TreeNode<Integer> root){
        if(root == null){
            return root;
        }
        swap(root);
        reverse(root.getLeft());
        reverse(root.getRight());

        return root;
    }

    public static void swap(TreeNode<Integer> root){
        TreeNode<Integer> t = root.getLeft();
        root.setLeft(root.getRight());
        root.setRight(t);
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(100);

        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int num = random.nextInt(10);
            System.out.println(num);
            tree.insert(50 + num);
        }

        System.out.println(tree);


        reverse(tree.root);

        System.out.println(tree);
    }

}
