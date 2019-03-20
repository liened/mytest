package com.exm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.exm.common.R;
import com.exm.common.REnum;
import com.exm.config.exception.BusinessException;
import com.exm.dto.UserStudentDto;
import com.exm.entity.Student;
import com.exm.entity.User;
import com.exm.service.UserService;
import com.exm.util.Query;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @ApiImplicitParam：用在@ApiImplicitParams注解中，指定一个请求参数的各个方面
 *  name：参数名
 *  value：参数的汉字说明、解释
 *  required：参数是否必须传
 *  paramType：参数放在哪个地方
 *  · header --> 请求参数的获取：@RequestHeader
 *  · query --> 请求参数的获取：@RequestParam
 *  · path（用于restful接口）--> 请求参数的获取：@PathVariable
 *  · body（不常用）
 *  · form（不常用）
 *  dataType：参数类型，默认String，其它值dataType="Integer"
 *  defaultValue：参数的默认值
 *
 */

/**
 *
 * @author yyx
 * @version 1.0
 * @createDate 2018-12-11 15:26
 * @description
 */
@Api(value = "维护用户信息",tags = "用户信息维护")
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @ApiOperation(value = "分页查询",notes = "主库")
    @PostMapping("pageSelect")
    public R pageSelect(@RequestBody(required = false)Query<User> query){
        IPage<User> page = userService.pageSelect(query);
        return R.successWithData(page);
    }

    @ApiOperation(value = "新增用户",notes = "从库")
    @ApiResponse(code = 200,message = "新增成功")
    @PostMapping("save")
    public R save(@RequestBody User user){
        userService.save(user);
        return R.success();
    }

    @ApiOperation(value = "修改用户信息",notes = "Service自带的更新方法")
    @PostMapping("update")
    public R update(@RequestBody User user){
        return userService.updateUser(user);
    }

    /**
     * TODO false
     * @param id
     * @return
     */
//    @ApiOperation(value = "修改用户信息2",notes = "使用UpdateWrapper")
//    @PostMapping("update2")
//    public R update2(@RequestBody User user){
//        Wrapper<Object> updateWrapper = Wrappers.update().set("age", user.getAge()).set("user_name", user.getUserName());
//        return R.success();
//    }

    @ApiOperation("删除用户信息")
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable Integer id){
        boolean b = userService.removeById(id);
        return R.successWithData(b);
    }

    @ApiOperation("查询所有用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "X-TOKEN",value = "用户token",defaultValue = "0",dataType = "String",paramType = "header")
    })
    @PostMapping("all")
    public R all(){
        //这个可以设置查询哪几个字段
        LambdaQueryWrapper<User> qw = Wrappers.<User>lambdaQuery().select(User::getId, User::getUserName, User::getPwd, User::getPhone, User::getCreateTime);
        List<User> list = userService.list(qw);
        return R.successWithData(list);
    }

    @ApiOperation("根据id查询用户")
    @PostMapping("getById/{id}")
    public R getById(@PathVariable Integer id){
        String userKey = "user_"+id;
        Boolean isHas = redisTemplate.hasKey(userKey);
        if (!isHas){
            synchronized (this){
                if (!redisTemplate.hasKey(userKey)){
                    log.info("Redis不存在该用户，从数据库查询...userId={}",id);
                    User user = userService.getById(id);
                    if (user == null){
                        log.info("不存在Id为{}的用户",id);
                        return R.failureWithDetail(REnum.User_Not_Exist,String.format("不存在Id为%d的用户",id));
                    }
                    boolean b = redisTemplate.opsForValue().setIfAbsent(userKey,user);
                    if (!b){
                        throw new BusinessException("缓存穿通!!");
                    }
                    return R.successWithData(user);
                }
            }
        }
        log.info("Redis存在该用户,userId={}",id);
        User user = (User) redisTemplate.opsForValue().get(userKey);
        return R.successWithData(user);
    }

    @ApiOperation("更新用户和学生")
    @PostMapping(value = "updateUserAndStudent",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public R updateUserAndStudent(@Valid @RequestBody UserStudentDto dto){
        User user = new User();
        user.setId(dto.getUserId());
        user.setUserName(dto.getUserName());

        Student student = new Student();
        student.setId(dto.getStudentId());
        student.setScore(dto.getScore());
        return userService.updateUserAndStudentById(user,student);
    }



    /**
     stringRedisTemplate.execute(connection->{
     RedisSerializer<String> serializer = stringRedisTemplate.getStringSerializer();
     if (StringUtils.isNotEmpty(oldHashName)){
     connection.rename(serializer.serialize(hashName),serializer.serialize(oldHashName));
     connection.rPush(serializer.serialize(ConstantSymbol.redis_monitor_old_hash_list),serializer.serialize(oldHashName));
     }
     connection.hSet(serializer.serialize(hashName), serializer.serialize(ConstantSymbol.redis_monior_hash_expire_str), serializer.serialize(String.valueOf(time)));
     if (StringUtils.isNotEmpty(monitorId)){
     connection.hSet(serializer.serialize(hashName), serializer.serialize(monitorId), serializer.serialize(count));
     }
     return true;
     },false,true);
     */

}
