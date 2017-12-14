package com.china.hadoop.bat.chapter5;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: ITree
 * @Package com.china.hadoop.bat.chapter5
 * @Description: ${todo}(用一句话描述该文件做什么)
 * @date 2017/10/23
 */
public interface ITree<T> {

    public boolean add(T value);

    public T remove(T value);

    public void clear();

    public boolean contains(T value);

    public int size();

    public boolean validate();

    public java.util.Collection<T> toCollection();

}
