package com.ckp.test.chapter5;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: ArrayList
 * @Package com.ckp.test.chapter5
 * @Description: 线性表实现类--数组线性表
 * @date 2017/7/31
 */
public class ArrayList<T extends Object> implements LinearList<T>, Serializable {

    protected T[] elements;//存储线性表元素的一维数组
    protected int listSize;//线性表的元素个数
    protected int arrayLength;//一维数组的容量

    private static final int DEFAULT_INITIAL_CAPACITY = 10;

    public ArrayList(Class<T> clazz) {
        arrayLength = DEFAULT_INITIAL_CAPACITY;
        elements = (T[]) Array.newInstance(clazz, arrayLength);
        listSize = 0;
    }

    public ArrayList(Class<T> clazz, int initialCapacity) {
        if(initialCapacity < 1){
            StringBuilder sb = new StringBuilder();
            sb.append("Initial capacity = ").append(initialCapacity).append(" Must be > 0");
            throw new IllegalArgumentException(sb.toString());
        }

        arrayLength = initialCapacity;
        elements = (T[]) Array.newInstance(clazz, arrayLength);
        listSize = 0;
    }

    public boolean empty() {
        return listSize == 0;
    }

    public int size() {
        return listSize;
    }

    public T get(int index) {
        this.checkIndex(index);
        return elements[index];
    }

    public int indexOf(T e) {
        return find(e);
    }

    public void erase(int index) {
        this.checkIndex(index);
        elements[listSize] = null;
    }

    public void insert(int index, T e) {
        this.checkIndex(index);
        if(listSize == arrayLength){
            changeLength1D(2*listSize);
            arrayLength *= 2;
        }
        elements[index] = e;
        listSize++;
    }

    private void checkIndex(int index){
        if(index < 0 || index > arrayLength){
            StringBuilder sb = new StringBuilder();
            sb.append("theIndex = ").append(index).append(" size = ").append(arrayLength);
            throw new IllegalArgumentException(sb.toString());
        }
    }

    private int find(T e){
        for (int i = 0; i < elements.length; i++) {
            T element = elements[i];
            if(element.equals(e)){
                return i;
            }
        }

        return -1;
    }

    private void changeLength1D (int newLength){
        if(newLength < 0){
            throw new IllegalArgumentException("new length must be > 0");
        }

        elements = Arrays.copyOf(elements, newLength);
    }

    public static void main(String[] args) {
        LinearList<Integer> list = new ArrayList<Integer>(Integer.class);

        System.out.println(list.size());
        System.out.println(list.empty());

        for (int i = 0; i < 11; i++) {
            list.insert(i, i);
        }

        System.out.println(list.size());
        System.out.println(list.empty());
    }

}
