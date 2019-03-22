package com.exm.business.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.exm.common.R;
import com.exm.business.dto.LoginDto;
import com.exm.business.student.Student;
import com.exm.util.Query;

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
