package com.exm.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2018-12-13 9:50
 * @description
 */

public class PageUtil {

    public static <T> Page<T> buildPage(Query<T> query){
        Page<T> page= new Page<>(query.getCurrentPage(),query.getPageSize());
        /* 不要这种参数转换的，会转不成
        T param = query.getParam();
        Map<String,Object> map = null;
        try {
            map = ReflectionUtil.bean2Map(param);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("参数转换错误");
        }
        page.setCondition(map);*/
        return page;
    }

}
