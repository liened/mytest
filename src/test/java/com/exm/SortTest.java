package com.exm;

import org.junit.After;
import org.junit.Test;

import java.util.Arrays;

public class SortTest {

    int[] arr = {5,3,4,9,7,8,6,2,1};

    @Test
    public void 普通(){
        int len = arr.length;
        for (int i=0;i<len-1;i++){
            for (int j=i+1;j<len;j++){
                if (arr[i]>arr[j]){
                    int t = arr[i];
                    arr[i] = arr[j];
                    arr[j] = t;
                }
            }
        }
    }

    @Test
    public void 冒泡(){
        int len = arr.length;
        for (int i=0;i<len-1;i++){
            for (int j=1;j<len-i;j++){
                if (arr[j-1]>arr[j]){
                    int t =  arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = t;
                }
            }
        }
    }

    @Test
    public void 插入(){
        int len = arr.length;
        for (int i = 1;i<len;i++){
            for (int j=i;j>0;j--){
                if (arr[j-1]>arr[j]){
                    int t =  arr[j-1];
                    arr[j-1] = arr[j];
                    arr[j] = t;
                }
            }
        }
    }

    @After
    public void print(){
        System.out.println(Arrays.toString(arr));
    }
}
