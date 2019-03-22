package com.exm.business.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exm.business.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
 * @author yyx
 * @version 1.0
 * @description
 * @createDate 2019-03-20 17:04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{

}
