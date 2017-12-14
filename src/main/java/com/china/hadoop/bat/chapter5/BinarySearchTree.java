package com.china.hadoop.bat.chapter5;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: BinarySearchTree
 * @Package com.china.hadoop.bat.chapter5
 * @Description: 二叉树算法：新增、删除、查找
 * @date 2017/10/19
 */
public class BinarySearchTree<T extends Comparable<T>> {

    protected TreeNode<T> root = null;
    protected int size = 0;
    protected INodeCreator<T> creator = null;

    public BinarySearchTree() {
        this.creator = new INodeCreator<T>() {
            /**
             * {@inheritDoc}
             */
            @Override
            public TreeNode<T> createNewNode(TreeNode<T> parent, T id) {
                return (new TreeNode<T>(parent, id));
            }
        };
    }

    /**
     * Constructor with external Node creator.
     */
    public BinarySearchTree(INodeCreator<T> creator) {
        this.creator = creator;
    }

    /**
     * @Title: find
     * @Description: 非递归方法：根据值找到二叉树中值与之相等的节点
     * @param value
     * @return TreeNode
     * @throws
     * */
    public TreeNode<T> find(T value){
        if(root == null || value == null){
            return null;
        }

        TreeNode<T> node = root;
        while(node != null){
            if(node.getValue().equals(value)){
                return node;
            }else if(value.compareTo(node.getValue()) > 0){
                node = node.getRight();
            }else{
                node = node.getLeft();
            }
        }

        return node;
    }

    /**
     * @Title: insert
     * @Description: 非递归方法：在二叉树中新增一个节点，节点的值为value，如果value存在，则不插入，最后返回节点（新增 或 已存在的）
     * @param value
     * @return TreeNode
     * @throws
     * */
    public TreeNode<T> insert1(T value){
        TreeNode<T> newNode = this.creator.createNewNode(null, value);
        if(root == null){
            root = newNode;
            size++;
            return newNode;
        }

        TreeNode<T> node = root;
        TreeNode<T> cur = null;
        while(node != null){
            cur = node;
            if(value.compareTo(node.getValue()) == 0){
                return node;
            }else if(value.compareTo(node.getValue()) > 0){
                node = node.getRight();
            }else{
                node = node.getLeft();
            }
        }

        if(value.compareTo(cur.getValue()) < 0){
            cur.setLeft(newNode);
        }else{
            cur.setRight(newNode);
        }

        return newNode;
    }

    /**
     * @Title: insert1
     * @Description: 非递归方法：在二叉树中新增一个节点，节点的值为value，如果value存在，则不插入，最后返回节点（新增 或 已存在的）
     * @param value
     * @return TreeNode
     * @throws
     * */
    public boolean insert(T value){
        TreeNode<T> nodeAdded = this.insert1(value);
        return (nodeAdded != null);
    }

    /**
     * @Title: remove
     * @Description: 非递归方法：在二叉树中删除value值对应的节点
     * 记待删除的结点为p，分三种情况进行处理:
     * 1、p为叶子结点
     * 2、p为单支结点
     * 3、p的左子树和右子树均不空
     * @param value
     * @return TreeNode
     * @throws
     * */
    public TreeNode delete(T value){
        if(value == null){
            return null;
        }
        //找到要删除的节点
        TreeNode<T> node = root;
        TreeNode<T> parent = null;
        while(node != null){
            if(value.compareTo(node.getValue()) == 0){
                break;
            }else if(value.compareTo(node.getValue()) > 0){
                parent = node;
                node = node.getRight();
            }else{
                parent = node;
                node = node.getLeft();
            }
        }

        if(node == null){
            return null;
        }

        //判断节点类型
        //根据类型删除
        if(node.getLeft() == null && node.getRight() == null){
            node = deleteLeaf(parent, node);
        }else if(node.getLeft() != null && node.getRight() != null){
            node = deleteMultiChildrens1(parent, node);
        }else{
            node = deleteSingleChildrens(parent, node);
        }

        return node;
    }

    /**
     * @Title: deleteLeaf
     * @Description: 删除树的叶子节点：child为叶子叶节点，parent为child的父节点
     * @param parent
     * @param child
     * @return TreeNode
     * @throws
     * */
    private TreeNode deleteLeaf(TreeNode<T> parent, TreeNode<T> child){
        parent.setRight(null);
        parent.setLeft(null);

        return child;
    }

    /**
     * @Title: deleteSingleChildrens
     * @Description: 删除树的单孩子节点（只有一个孩子的节点）：child为叶子叶节点，parent为child的父节点
     * @param parent
     * @param child
     * @return TreeNode
     * @throws
     * */
    private TreeNode deleteSingleChildrens(TreeNode<T> parent, TreeNode<T> child){
        TreeNode<T> nextNode = null;
        if(child.getRight() != null){
            nextNode = child.getRight();
        }else if(child.getLeft() != null){
            nextNode = child.getLeft();
        }

        if(parent.getLeft() == child){
            parent.setLeft(nextNode);
        }else{
            parent.setRight(nextNode);
        }

        return child;
    }

    /**
     * @Title: deleteSingleChildrens
     * @Description: 删除树的多孩子节点（只有二个孩子的节点）：child为叶子叶节点，parent为child的父节点
     * 真实删除原节点，把要删除的原节点 替换成 原节点的左边最右子孙（最大子孙） 或者 右边最左子孙（最小子孙）
     * @param parent
     * @param child
     * @return TreeNode
     * @throws
     * */
    private TreeNode deleteMultiChildrens(TreeNode<T> parent, TreeNode<T> child){

        //获取到节点孩子中最大子孩子 或者 获取到节点孩子中最小子孩子
        //他们都是最多是单孩子节点（单孩子节点、叶子节点）
        TreeNode<T> parentOfReplaceNode = child;
        TreeNode<T> replaceNode = child.getLeft();
        while(replaceNode.getRight() != null){
            parentOfReplaceNode = replaceNode;
            replaceNode = replaceNode.getRight();
        }

        parentOfReplaceNode.setRight(replaceNode.getLeft());//删除replaceNode在原来的位置

        replaceNode.setLeft(child.getLeft());//替换节点 左孩子设置为原节点的左孩子
        replaceNode.setRight(child.getRight());//替换节点 右孩子设置为原节点的右孩子

        if(parent.getLeft() == child){
            parent.setLeft(replaceNode);
        }else{
            parent.setRight(replaceNode);
        }

        return child;
    }

    /**
     * @Title: deleteSingleChildrens
     * @Description: 删除树的多孩子节点（只有二个孩子的节点）：child为叶子叶节点，parent为child的父节点
     * 未真实删除原节点，只是把要删除的原节点值替换成 原节点的左边最右子孙（最大子孙） 或者 右边最左子孙（最小子孙）
     * @param parent
     * @param child
     * @return TreeNode
     * @throws
     * */
    private TreeNode deleteMultiChildrens1(TreeNode<T> parent, TreeNode<T> child){

        //获取到节点孩子中最大子孩子 或者 获取到节点孩子中最小子孩子
        //他们都是最多是单孩子节点（单孩子节点、叶子节点）
        TreeNode<T> parentOfReplaceNode = child;
        TreeNode<T> replaceNode = child.getLeft();
        while(replaceNode.getRight() != null){
            parentOfReplaceNode = replaceNode;
            replaceNode = replaceNode.getRight();
        }


        if(replaceNode.getLeft() == null){
            replaceNode = deleteLeaf(parentOfReplaceNode, replaceNode);
        }else{
            replaceNode = deleteSingleChildrens(parentOfReplaceNode, replaceNode);
        }

        T del = child.getValue();
        child.setValue(replaceNode.getValue());
        replaceNode.setValue(del);

        return replaceNode;//真实删除节点不是child而是replaceNode
    }

    @Override
    public String toString() {
        return TreePrinter.getString(this);
    }

    protected static interface INodeCreator<T extends Comparable<T>> {

        /**
         * Create a new Node with the following parameters.
         *
         * @param parent
         *            of this node.
         * @param id
         *            of this node.
         * @return new Node
         */
        public TreeNode<T> createNewNode(TreeNode<T> parent, T id);
    }

    protected static class TreePrinter {

        public static <T extends Comparable<T>> String getString(BinarySearchTree<T> tree) {
            if (tree.root == null)
                return "Tree has no nodes.";
            return getString(tree.root, "", true);
        }

        private static <T extends Comparable<T>> String getString(TreeNode<T> node, String prefix, boolean isTail) {
            StringBuilder builder = new StringBuilder();

            if (node.parent != null) {
                String side = "left";
                if (node.equals(node.parent.right))
                    side = "right";
                builder.append(prefix + (isTail ? "└── " : "├── ") + "(" + side + ") " + node.value + "\n");
            } else {
                builder.append(prefix + (isTail ? "└── " : "├── ") + node.value + "\n");
            }
            List<TreeNode<T>> children = null;
            if (node.left != null || node.right != null) {
                children = new ArrayList<TreeNode<T>>(2);
                if (node.left != null)
                    children.add(node.left);
                if (node.right != null)
                    children.add(node.right);
            }
            if (children != null) {
                for (int i = 0; i < children.size() - 1; i++) {
                    builder.append(getString(children.get(i), prefix + (isTail ? "    " : "│   "), false));
                }
                if (children.size() >= 1) {
                    builder.append(getString(children.get(children.size() - 1), prefix + (isTail ? "    " : "│   "), true));
                }
            }

            return builder.toString();
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(100);

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int num = random.nextInt(100);
            System.out.println(num);
            tree.insert(50 + num);
        }

        System.out.println(tree);

        for (int i = 0; i < 100; i++) {
            int num = random.nextInt(100);
            System.out.println(num);
            System.out.println(tree.find(50 + num));
        }

        Integer value = 0;
        TreeNode treeNode = tree.delete( value);
        System.out.println(treeNode);

        System.out.println(tree);

        BinaryTreeOrder.inOrder(tree.root);
    }

}
