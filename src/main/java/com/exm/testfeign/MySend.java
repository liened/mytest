package com.exm.testfeign;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2018-12-28 15:33
 * @description
 */
public class MySend {

    public void testAnn(@FeignForm Hxd hxd){
        System.out.println(hxd);
    }

    public void testClassAnn(MyParaClass myParaClass){
        System.out.println(myParaClass);
    }

}
