package com.china.hadoop.bat.chapter5;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: TreeNode
 * @Package com.china.hadoop.bat.chapter5
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2017/10/19
 */
public class TreeNode<T extends Comparable<T>> {

    protected TreeNode<T> parent;
    protected TreeNode<T> left;
    protected TreeNode<T> right;
    protected T value;

    public TreeNode(TreeNode<T> parent, T value) {
        this.parent = parent;
        this.value = value;
    }

    public TreeNode(TreeNode<T> left, TreeNode<T> right, T value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TreeNode)) return false;

        TreeNode<?> treeNode = (TreeNode<?>) o;

        if (getLeft() != treeNode.getLeft()) return false;
        if (getRight() != treeNode.getRight()) return false;
        return getValue().equals(treeNode.getValue());
    }

    @Override
    public int hashCode() {
        return getValue().hashCode();
    }

    public static void main(String[] args) {
        TreeNode<Integer> c = new TreeNode<Integer>(null, null, 10);

        TreeNode<Integer> a = new TreeNode<Integer>(c, null, 10);
        TreeNode<Integer> b = new TreeNode<Integer>(null, c, 10);


        System.out.println(a.equals(b));
    }

    @Override
    public String toString() {
        /*return "TreeNode{" +
                "value=" + value +
                '}';*/
        return String.valueOf(value);
    }

}
