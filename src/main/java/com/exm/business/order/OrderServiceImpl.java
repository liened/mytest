package com.exm.business.order;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.exm.business.mapper.OrderMapper;
import com.exm.common.R;
import com.exm.common.REnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author yyx
 * @version 1.0
 * @description
 * @createDate 2019-03-12 16:21
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService{

    @Override
    public R insert(OrderDto orderDto) {
        Order order = new Order();
        BeanUtils.copyProperties(orderDto,order);
        LocalDateTime now = LocalDateTime.now();
        order.setCreateTime(now);
        order.setUpdateTime(now);
        int b = this.baseMapper.insert(order);
        if (b >0){
            return R.success();
        }
        return R.fail(REnum.Operate_Fail);
    }
}
