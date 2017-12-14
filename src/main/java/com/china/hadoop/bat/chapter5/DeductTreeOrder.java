package com.china.hadoop.bat.chapter5;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: DeductTreeOrder
 * @Package com.china.hadoop.bat.chapter5
 * @Description: 根据二叉树的前序遍历、中序遍历、后序遍历其中两种，推导树的结构和另外一个种遍历顺序
 * @date 2017/10/20
 */
public class DeductTreeOrder {

    private static int index;

    public static void deductTreeStructByPreInOrder(char[] preOrder, char[] inOrder, int size, char[] postOrder) {
        if(size <= 0){
            return;
        }

        if(size == 1){
            postOrder[index++] = preOrder[0];
            return;
        }

        int i = 0;
        for (; i < size; i++) {
            if(inOrder[i] == preOrder[0]){
                break;
            }
        }

        char[] preLeft = new char[i];//前序遍历 左子树   Root left right
        char[] preRight = new char[size - i - 1];//前序遍历 左子树

        char[] inLeft = new char[i];//中序遍历 左子树     left Root right
        char[] inRight = new char[size - i - 1];//中序遍历 左子树

        System.arraycopy(preOrder, 1, preLeft, 0, preLeft.length);
        System.arraycopy(preOrder, i + 1, preRight, 0, preRight.length);

        System.arraycopy(inOrder, 0, inLeft, 0, inLeft.length);
        System.arraycopy(inOrder, i + 1, inRight, 0, inRight.length);

        deductTreeStructByPreInOrder(preLeft, inLeft, preLeft.length, postOrder);
        deductTreeStructByPreInOrder(preRight, inRight, inRight.length, postOrder);

        postOrder[index++] = preOrder[0];
    }

    public static TreeNode<Integer> rebuildTreeByPreInOrder(char[] pre, int startPre, int endPre, char[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn)
            return null;
        TreeNode<Integer> root = new TreeNode(null, null, pre[startPre]);

        for (int i = startIn; i <= endIn; i++)
            if (in[i] == pre[startPre]) {
                //pre前序遍历开始于startPre，其中startPre是本次的root节点在前序遍历串中位置
                //in中序遍历开始于startIn，其中i是本次root节点在中序遍历串中位置
                //根据中序遍历中root节点位置，分割中序遍历为左边-左子树、右边-右子树
                //其中左子树节点个数：中序遍历root节点位置 - 起点位置 = i - startIn    中序遍历root节点位置=i   起点位置=startIn
                //其中右子树节点个数：终点位置 - 中序遍历root节点位置 = endId - i    中序遍历root节点位置=i   终点位置=endIn

                //pre前序遍历串中左子树起点位置：前序遍历root节点位置 + 1 = startPre + 1 （startPre是root位置，故加1）
                //pre前序遍历串中左子树终点位置：前序遍历左子树起点位置 + 左子树节点个数 - 1 = startPre + 1 + i - startIn - 1 = startPre + i - startIn

                //pre前序遍历串中右子树起点位置：pre前序遍历串中左子树终点位置 + 1 = 前序遍历左子树起点位置 + 右子树节点个数 = startPre + i - startIn + 1 = startPre + 1 + endId - i
                //pre前序遍历串中右子树终点位置：enPre

                //in中序遍历串中左子树起点位置：startIn
                //in中序遍历串中左子树终点位置：中序遍历root节点位置 - 1 = i - 1

                //in中序遍历串中右子树起点位置：中序遍历root节点位置 + 1 = i + 1
                //in中序遍历串中右子树终点位置：endId

                TreeNode<Integer> left = rebuildTreeByPreInOrder(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                TreeNode<Integer> right = rebuildTreeByPreInOrder(pre, startPre + i - startIn + 1, endPre, in, i + 1, endIn);

                root.setLeft(left);
                root.setRight(right);
            }

        return root;
    }

    public static TreeNode<Integer> rebuildTreeByInPostOrder(char[] post, int startPost, int endPost, char[] in, int startIn, int endIn) {
        if(startPost > endPost || startIn > endIn){
            return null;
        }

        TreeNode<Integer> root = new TreeNode(null, null, post[endPost]);

        for (int i = startIn; i <= endIn; i++) {
            if(in[i] == post[endPost]){

                TreeNode<Integer> left = rebuildTreeByInPostOrder(post, startPost, startPost + i - startIn - 1, in, startIn, i - 1);
                TreeNode<Integer> right = rebuildTreeByInPostOrder(post, startPost + i - startIn, endPost - 1, in, i + 1, endIn);

                root.setLeft(left);
                root.setRight(right);
            }
        }

        return root;
    }


    public static void main(String[] args) {
//        String preOrder = "CABGHEDF";
//        String inOrder = "GHBACDEF";
//
//        TreeNode<Character> root = deductTreeStructByPreInOrder(preOrder.toCharArray(), inOrder.toCharArray(), null, preOrder.length());
//
//        BinaryTreeOrder.preOrder(root);

        String preOrder = "CABGHEDF";
        String inOrder = "GHBACDEF";
        String postOrder = "HGBADFEC";

        TreeNode<Integer> root = rebuildTreeByPreInOrder(preOrder.toCharArray(), 0, preOrder.length() - 1, inOrder.toCharArray(), 0, inOrder.length() - 1);

        System.out.println("------preOrder-------");
        System.out.println();
        BinaryTreeOrder.preOrder(root);
        System.out.println();
        System.out.println("------inOrder-------");
        System.out.println();
        BinaryTreeOrder.inOrder(root);
        System.out.println();
        System.out.println("------postOrder-------");
        System.out.println();
        BinaryTreeOrder.postOrder(root);
        System.out.println();

        System.out.println("---------------------");
        root = rebuildTreeByInPostOrder(postOrder.toCharArray(), 0, postOrder.length() -1, inOrder.toCharArray(), 0, inOrder.length() - 1);

        System.out.println("------preOrder-------");
        System.out.println();
        BinaryTreeOrder.preOrder(root);
        System.out.println();
        System.out.println("------inOrder-------");
        System.out.println();
        BinaryTreeOrder.inOrder(root);
        System.out.println();
        System.out.println("------postOrder-------");
        System.out.println();
        BinaryTreeOrder.postOrder(root);
        System.out.println();

        System.out.println("--------------------");
        char[] post = new char[preOrder.length()];
        deductTreeStructByPreInOrder(preOrder.toCharArray(), inOrder.toCharArray(), preOrder.length(), post);
        System.out.println(post);
    }


}
