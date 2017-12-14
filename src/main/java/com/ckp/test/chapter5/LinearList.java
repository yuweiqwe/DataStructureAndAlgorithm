package com.ckp.test.chapter5;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: LinearList
 * @Package com.ckp.test.chapter5
 * @Description: 线性列表接口描述
 * @date 2017/7/31
 */
public interface LinearList<T> {

    boolean empty();
    int size();
    T get(int index);
    int indexOf(T e);
    void erase(int index);
    void insert(int index, T e);

}
