package com.exm.business.user;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exm.business.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{

}
