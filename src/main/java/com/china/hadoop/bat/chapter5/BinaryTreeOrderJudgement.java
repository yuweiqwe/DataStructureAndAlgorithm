package com.china.hadoop.bat.chapter5;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: BinaryTreeOrderJudgement
 * @Package com.china.hadoop.bat.chapter5
 * @Description: 二叉排序树序列判断：判断序列是否是前序序列、中序序列、后续序列
 * @date 2017/10/21
 */
public class BinaryTreeOrderJudgement {

    /**
     * @Title: isPostOrder
     * @Description: 判断一个输出是否：一颗二叉排序树按照后序遍历的输出
     * 后序遍历：s1 s2 s3 b1 b2 b3 b4 r
     * @param postOrder
     * @return boolean
     * @throws
     * */
    public static boolean isPostOrder(char[] postOrder){
        if(postOrder.length <= 1){
            return true;
        }

        char root = postOrder[postOrder.length - 1];

        //整棵树 左子树的root索引  左子树所有节点比root小
        //整棵树 右子树的root索引  右子树所有节点比root大
        int leftIndex = 0;
        int rightIndex = postOrder.length - 1 - 1;//整棵树 最后一个是root，root的index=length - 1，所以root前一个=length - 1 - 1

        while(leftIndex < postOrder.length - 1){
            if(postOrder[leftIndex] >= root){
                break;
            }
            leftIndex++;
        }

        while(rightIndex >= 0){
            if(postOrder[rightIndex] <= root){
                break;
            }
            rightIndex--;
        }

        if(rightIndex != leftIndex - 1){
            return false;
        }

        char[] left = new char[leftIndex - 1];
        char[] right = new char[postOrder.length - rightIndex - 1];

        System.arraycopy(postOrder, 0, left, 0, left.length);
        System.arraycopy(postOrder, rightIndex, right, 0, right.length);

        return isPostOrder(left) && isPostOrder(right);
    }

    public static void main(String[] args) {
        char[] postOrder = new char[]{1,4,2,3};
        char[] postOrder1 = new char[]{1,2,3,4};

        System.out.println(isPostOrder(postOrder));
    }

}
