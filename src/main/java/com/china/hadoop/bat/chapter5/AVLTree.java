package com.china.hadoop.bat.chapter5;

import java.util.Random;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: AVLTree
 * @Package com.china.hadoop.bat.chapter5
 * @Description: 平衡二叉树-balanced binary tree
 * 1962年由G.M.Adelson Velsky、E.M.Landis发明，又称AVL树
 * 定义（definition）: 每个结点的左右子树的高度之差不超过1
 * 1、如果插入或者删除结点后高差大于1，则进行结 点旋转，重新维护平衡状态。
 * 2、解决了二叉查找树退化成链表的问题，插入、 查找、删除的时间复杂度最坏情况是O(logN)。最好情况也是O(logN)
 * @date 2017/10/22
 */
public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T>{

    /**
     * Default constructor.
     */
    public AVLTree() {
        this.creator = new BinarySearchTree.INodeCreator<T>() {
            /**
             * {@inheritDoc}
             */
            @Override
            public TreeNode<T> createNewNode(TreeNode<T> parent, T id) {
                return (new AVLTreeNode<T>(parent, id));
            }
        };
    }

    /**
     * Constructor with external Node creator.
     */
    public AVLTree(INodeCreator<T> creator) {
        super(creator);
    }

    /**
     * @Title: singleRotateLeft
     * @Description: 左左旋转
     * 左左-根节点的左子树比右子树高度大，左子树的左子树比左子树的右子树高度大
     *         root                            left
     *        /   \                           /     \
     *      left  right      左旋          left2      root
     *     /    \            -->         /     \    /    \
     *    left2  right1               left3 right2 right1 right
     *   /    \
     *  left3 right2
     * @param node 需要进行平衡的树的根节点
     * @return AVLTreeNode<T> 新的root节点
     * @throws
     * */
    public void singleRotateLeft(TreeNode<T> node){
        TreeNode<T> parent = node.getParent();
        TreeNode<T> left = node.getLeft();
        TreeNode<T> right = left.getRight();

        left.setRight(node);
        node.setLeft(right);

        node.setParent(left);

        if (right != null)
            right.parent = node;

        if (parent!=null) {
            if (node == parent.left) {
                parent.left = left;
            } else if (node == parent.right) {
                parent.right = left;
            } else {
                throw new RuntimeException("Yikes! I'm not related to my parent. " + node.toString());
            }
            left.parent = parent;
        } else {
            root = left;
            root.parent = null;
        }

        //调整left左子树高度
        adjustHeight(root);
    }

    /**
     * @Title: singleRotateLeft
     * @Description: 右右旋转
     * 右右-根节点的右子树比左子树高度大，右子树的右子树比右子树的左子树高度大
     *          root                             right
     *         /    \             右旋          /     \
     *      left    right         -->        root     right2
     *              /   \                   /   \       /  \
     *            left2  right2           left  left2 left3 right3
     *                   /  \
     *                left3   right3
     * @param node 需要进行平衡的树的根节点
     * @return void
     * @throws
     * */
    public void singleRotateRight(TreeNode<T> node){
        TreeNode<T> parent = node.getParent();
        TreeNode<T> right = node.getRight();
        TreeNode<T> left = right.getLeft();

        node.setRight(left);
        right.setLeft(node);

        if (left != null)
            left.parent = node;

        if (parent!=null) {
            if (node == parent.left) {
                parent.left = right;
            } else if (node == parent.right) {
                parent.right = right;
            } else {
                throw new RuntimeException("Yikes! I'm not related to my parent. " + node.toString());
            }
            right.parent = parent;
        } else {
            root = right;
            root.parent = null;
        }

        adjustHeight(root);
    }

    /**
     * @Title: doubleRotateLeftRight
     * @Description: 左右旋转
     * 左右-根节点的左子树比右子树高度大，左子树的右子树比左子树的左子树高度大
     *         root                           root                              right1
     *        /   \                          /    \                           /       \
     *      left  right     右旋            right1   right   左旋           left        root
     *     /    \            -->           /   \             -->         /    \      /    \
     *   left2  right1                 left   right2                left2   left3 right2  right
     *           /   \                 /   \
     *        left3  right2          left2 left3
     * @param node 需要进行平衡的树的根节点
     * @return TreeNode<Integer> 新的root节点
     * @throws
     * */
    public void doubleRotateLeftRight(TreeNode<T> node){
        singleRotateRight(node.getLeft());
        singleRotateLeft(node);
    }

    /**
     * @Title: doubleRotateRightLeft
     * @Description: 右左旋转
     * 右左-根节点的右子树比左子树高度大，右子树的左子树比左子树的右子树高度大
     *         root                           root                              left2
     *        /   \                          /    \                            /      \
     *      left  right            左旋     left  left2         右旋         root     right
     *           /    \            -->           /   \          -->        /   \     /    \
     *        left2  right1                   left3  right             left  left3 right2 right1
     *        /   \                                  /   \
     *    left3  right2                          right2 right1
     * @param node 需要进行平衡的树的根节点
     * @return TreeNode<Integer> 新的root节点
     * @throws
     * */
    public void doubleRotateRightLeft(TreeNode<T> node){
        singleRotateLeft(node.getRight());
        singleRotateRight(node);
    }

    /**
     * @Title: insert
     * @Description: 平衡二叉树的插入(可能会调整root节点)
     * 插入的方法和二叉查找树基本一样，区别是， 插入完成后需要从插入的结点开始维护一个 到根结点的路径，每经过一个结点都要维持 树的平衡。
     * 维持树的平衡要根据高度差的特 点选择不同的旋转算法
     * @param value 插入值
     * @return TreeNode<T>  树的根节点
     * @throws
     * */
    public TreeNode<T> insert1(T value) {
        if(value == null){
            return null;
        }

        TreeNode<T> node = super.insert1(value);
        if(node == null){
            return node;
        }

        if(value.compareTo(node.getValue()) > 0){//如果 value大于node，插入右边
            insert(value);

            if(2 == getHeight(node.getRight()) - getHeight(node.getLeft())){
                if(value.compareTo(node.getRight().getValue()) > 0){
                    singleRotateRight(node);
                }else{
                    doubleRotateRightLeft(node);
                }
            }

        }else if(value.compareTo(node.getValue()) < 0){//如果 value小于node，插入左边
            insert(value);

            if(2 == getHeight(node.getLeft()) - getHeight(node.getRight())){
                if(value.compareTo(node.getLeft().getValue()) < 0){
                    singleRotateLeft(node);
                }else{
                    doubleRotateLeftRight(node);
                }
            }

        }

        adjustHeight(node);

        return node;
    }

    /**
     * @Title: delete
     * @Description: 平衡二叉树删除某节点
     * 删除的方法也和二叉查找树的一致，区别是， 删除完成后，需要从删除结点的父亲开始向 上维护树的平衡一直到根结点
     * @param node  节点
     * @param value 删除的值
     * @return TreeNode<Integer> 树的根节点
     * @throws
     * */
    public TreeNode<T> delete(TreeNode<T> node, T value) {
        if(value == null || node == null){
            return null;
        }

        if(value.compareTo(node.getValue()) > 0){//删除 node的右子树的节点
            delete(node.getRight(), value);
            if(2 == getHeight(node.getLeft()) - getHeight(node.getRight())){
                if(node.getLeft().getRight() != null
                        && getHeight(node.getLeft().getRight()) > getHeight(node.getLeft().getLeft())){
                    doubleRotateLeftRight(node);
                }else{
                    singleRotateLeft(node);
                }
            }
        }else if(value.compareTo(node.getValue()) < 0){//删除 node的左子树的节点
            delete(node.getLeft(), value);
            if(2 == getHeight(node.getRight()) - getHeight(node.getLeft())) {
                if(node.getRight().getLeft() != null
                        && getHeight(node.getRight().getLeft()) > getHeight(node.getRight().getRight())){
                    doubleRotateRightLeft(node);
                }else{
                    singleRotateRight(node);
                }
            }

        }else{//删除node节点本身
            //node 1、没有子节点 2、有两个子节点 3、有一个子节点
            if(node.getLeft() == null && node.getRight() == null){
                return null;
            }else if(node.getLeft() != null && node.getRight() != null){
                TreeNode<T> rleft = node.getRight();
                while(rleft != null){
                    rleft = rleft.getLeft();
                }
                delete(node.getRight(), rleft.getValue());
                if(2 == getHeight(node.getLeft()) - getHeight(node.getRight())){
                    if(node.getLeft().getRight() != null
                            && getHeight(node.getLeft().getRight()) > getHeight(node.getLeft().getLeft())){
                        doubleRotateLeftRight(node);
                    }else{
                        singleRotateLeft(node);
                    }
                }
                return node;
            }else{
                if(node.getLeft() != null){
                    return node.getLeft();
                }else if(node.getRight() != null){
                    return node.getRight();
                }
            }

        }

        return node;
    }


    /**
     * @Title: getHeight
     * @Description: 获取root为根节点的AVL平衡二叉树的高度
     * @param node
     * @return int 树的高度
     * @throws
     * */
    public int getHeight(TreeNode<T> node){
        if(node == null){
            return 0;
        }

        int leftH = getHeight(node.getLeft()) + 1;
        int rightH = getHeight(node.getRight()) + 1;

        return Math.max(leftH, rightH);
    }

    /**
     * @Title: adjustHeight
     * @Description: 修正root为根节点的AVL平衡二叉树的高度
     * @param node
     * @return int 修正后树的高度
     * @throws
     * */
    public int adjustHeight(TreeNode<T> node){
        if(node == null){
            return 0;
        }
        AVLTreeNode<T> avlNode = (AVLTreeNode<T>) node;
        int leftH = adjustHeight(avlNode.getLeft()) + 1;
        int rightH = adjustHeight(avlNode.getRight()) + 1;

        avlNode.setHeight(Math.max(leftH, rightH));
        return avlNode.getHeight();
    }

    public static void main(String[] args) {

    }

}
