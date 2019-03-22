package com.exm;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.exm.business.order.Order;
import com.exm.business.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingDemoApplicationTests {

	@Autowired
	private OrderMapper orderMapper;

	@Test
	public void contextLoads() {
		LambdaQueryWrapper<Order> wrapper = Wrappers.<Order>lambdaQuery().eq(Order::getDelFlag, 0);
		List<Order> list = orderMapper.selectList(wrapper);
		System.out.println("======================");
		System.out.println(list);
	}

}
