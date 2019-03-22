package com.exm.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.exm.common.R;
import com.exm.business.student.Student;
import com.exm.business.user.User;
import com.exm.business.user.UserService;
import com.exm.util.Query;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @notice 在mybatis-plus 3.0下ActiveRecord模式自动回滚的问题，加了事务就好了
 * @link https://www.jianshu.com/p/a4d5d310daf8
 *  AR 之旅:
 *      Active Record(活动记录)，是一种领域模型模式，特点是一个模型类对应关系型数据库中的一个表，而模型类的一个实例对应表中的一行记录。
 *      ActiveRecord 一直广受动态语言（ PHP 、 Ruby 等）的喜爱，而 Java 作为准静态语言，对于 ActiveRecord 往往只能感叹其优雅，
 *      所以 MP 也在 AR 道路上进行了一定的探索，仅仅需要让实体类继承 Model 类且实现主键指定方法，即可开启 AR 之旅。
 */
@Slf4j
@Api(value = "维护学生信息",tags = "学生信息维护")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询学生信息",notes = "不用Service通过对象操作数据库")
    @GetMapping("getStudent")
    public R getStudent(){
        Student s = new Student();
        List<Student> list = s.selectAll();
        return R.successWithData(list);
    }

    @ApiOperation(value = "学生的分页",notes = "通过AR直接查询")
    @PostMapping("pageSelect")
    public R pageSelect(@RequestBody(required = false) Query<Student> query){
        Student s = new Student();
        IPage<Student> page = s.selectPage(new Page<Student>(query.getCurrentPage(),query.getPageSize()), Wrappers.<Student>query().between("score",30,60));
        return R.successWithData(page);
    }

    @ApiOperation("新增学生信息")
    @PostMapping("insert")
    public R insert(@RequestBody Student student){
        boolean b = student.insert();
        return R.successWithData(b);
    }

    @ApiOperation("修改学生信息")
    @PostMapping("update")
    @Transactional
    public R update(@RequestBody Student student){
        boolean b = student.updateById();
        return R.successWithData(b);
    }

    @ApiOperation(value = "删除",notes = "AR操作")
    @PostMapping("/delete/{id}")
    public R delete(@PathVariable Integer id){
        Student s = new Student();
        //s.setId(id);
        boolean b = false;
        try {
            b = s.deleteById(id);
        }catch (Exception e){
            log.error("删除错误",e);
        }
        return R.successWithData(b);
    }

    @ApiOperation(value = "删除所有",notes = "AR操作，看看执行分析插件(SqlExplainInterceptor)是否生效。结果：因为是逻辑删除，所有没有生效")
    @PostMapping("deleteAll")
    public R deleteAll(){
        Student s = new Student();
        boolean b = s.delete(null);
        return R.successWithData(b);
    }


    @ApiOperation("测试事务更新")
    @ApiOperationSort(1)
    @PostMapping("testTransaction")
    @Transactional
    public R testUpdate(@RequestParam Integer id,@RequestParam String name,@RequestParam Integer score){
        Student s = new Student();
        s.setId(id);
        s.setScore(score);
        boolean b = s.updateById();
        User user = new User();
        user.setId(id);
        user.setUserName(name);
        boolean b1 = userService.updateTransacion(user);
        log.info("=============== 更新结果:b={},b1={}",b,b1);
        return R.success();
    }
}
