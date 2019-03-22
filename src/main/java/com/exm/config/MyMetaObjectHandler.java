package com.exm.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * mybatisplus 2.x的版本MetaObjectHandler是个类，在配置文件里面指定。3.x变成了一个接口,使用x使用Bean指定
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     *   https://www.jianshu.com/p/a4d5d310daf8
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        setFieldValByName("createTime",new Date(),metaObject);

        /* 其他默认字段的处理示例
        Object fieldValue = getFieldValByName("name",metaObject);//获取需要填充的字段
        if(fieldValue == null){ //如果该字段没有设置值
            setFieldValByName("name","DefaultName",metaObject);  //那就将其设置为"DefaultName"
        }
         */


    }

    @Override
    public void updateFill(MetaObject metaObject) {
        setFieldValByName("updateTime",new Date(),metaObject);
    }
}
