package com.exm.business.user;


import com.alibaba.druid.support.json.JSONUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exm.common.R;
import com.exm.common.REnum;
import com.exm.common.RedisKey;
import com.exm.config.annotation.DS;
import com.exm.config.datasource.DSType;
import com.exm.config.exception.BusinessException;
import com.exm.config.header.MyHeader;
import com.exm.config.header.RequestHeaderContext;
import com.exm.business.dto.LoginDto;
import com.exm.business.student.Student;
import com.exm.business.mapper.UserMapper;
import com.exm.util.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author yyx
 * @version 1.0
 * @createDate 2018-12-12 10:28
 * @description
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService{

    @Autowired
//    private RedisTemplate<String,Object> redisTemplate;
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 分页查询
     * @param query
     * @return
     */
    @Override
    public IPage<User> pageSelect(Query<User> query) {
        User user = query.getParam();

        //以前的写法
        //EntityWrapper<User> ew = new EntityWrapper<>(user);
        //Page<User> page = PageUtil.buildPage(query);
        //return selectPage(page,ew);
        Page<User> page = new Page<>(query.getCurrentPage(),query.getPageSize());
        return page(page, Wrappers.<User>query().setEntity(user));
    }

    /**
     * 新增
     * @param user
     */
    @Override
    @DS(value = DSType.Master)
    @Transactional(rollbackFor = Exception.class)
    public boolean save(User user) {
        return super.save(user);
    }

    @Override
    @DS(value = DSType.Slave)
    @Transactional(rollbackFor = Exception.class)
    public R updateUser(User user) {
//        Boolean ifAbsent = redisTemplate.opsForValue().setIfAbsent("user_" + user.getId(), user, 10, TimeUnit.MINUTES);
        Boolean ifAbsent = stringRedisTemplate.opsForValue().setIfAbsent("user_" + user.getId(), JSONUtils.toJSONString(user), 10, TimeUnit.MINUTES);
        if (!ifAbsent){
            return R.failureWithDetail(REnum.System_Error,"10分钟内已修改过一次,过时后再修改");
        }
        boolean r = super.updateById(user);
        if (!r){
            return R.failureWithDetail(REnum.System_Error,"数据更新错误！");
        }
        return R.successWithData("数据更新成功！");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public R updateUserAndStudentById(User user, Student student) {
        boolean a = super.updateById(user);
        boolean b = student.updateById();
        if (!a){
            throw new BusinessException("更新User失败");
        }
        if (!b){
            throw new BusinessException("更新Student失败");
        }
        return R.success();
    }

    /**
     * 测试事务嵌套
     * @param user
     * @return
     */
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean updateTransacion(User user) {
            if (user.getId() ==2){
                "".substring(1);
            }
            return updateById(user);
    }

    /**
     * 登录
     */
    @Override
    public R login(LoginDto loginDto){
        User user = this.lambdaQuery().eq(User::getPhone, loginDto.getPhone()).one();
        if (null == user){
            return R.failure(REnum.User_Not_Exist);
        }
        if (StringUtils.isEmpty(user.getPwd()) || !user.getPwd().equals(loginDto.getPwd())){
            return R.failure(REnum.PWD_Error);
        }
        String token = UUID.randomUUID().toString().replaceAll("-","");
        stringRedisTemplate.opsForValue().set(RedisKey.LOGIN_TOKEN_KEY_USER+token,loginDto.getPhone(),3,TimeUnit.DAYS);
        stringRedisTemplate.opsForValue().set(RedisKey.LOGIN_USER_KEY_TOKEN+loginDto.getPhone(),token,3,TimeUnit.DAYS);
        MyHeader myHeader = RequestHeaderContext.get();
        String divice = myHeader.getDivice();
        log.info("这个是logn方法的Header ThreadLocal里取出来的divice:{}",divice);
        return R.successWithData(token);
    }
}
