package com.exm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exm.common.R;
import com.exm.dto.LoginDto;
import com.exm.entity.Student;
import com.exm.entity.User;
import com.exm.util.Query;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2018-12-12 10:28
 * @description
 */
public interface UserService extends IService<User> {

    /**
     * 分页查询
     * @param query
     * @return
     */
    IPage<User> pageSelect(Query<User> query);

    R updateUser(User user);

    R updateUserAndStudentById(User user ,Student student);

    /**
     * 测试事务嵌套
     * @param user
     * @return
     */
    boolean updateTransacion(User user);

    R login(LoginDto loginDto);
}
