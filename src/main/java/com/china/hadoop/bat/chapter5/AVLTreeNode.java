package com.china.hadoop.bat.chapter5;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: AVLTreeNode
 * @Package com.china.hadoop.bat.chapter5
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2017/10/23
 */
public class AVLTreeNode<T extends Comparable<T>> extends TreeNode<T> {

    protected int height = 1;

    public AVLTreeNode(TreeNode<T> parent, T value) {
        super(parent, value);
    }

    public AVLTreeNode(TreeNode<T> left, TreeNode<T> right, T value) {
        super(left, right, value);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
