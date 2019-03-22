package com.exm.business.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exm.common.R;
import com.exm.business.order.Order;
import com.exm.business.order.OrderDto;

/**
 * @author yyx
 * @version 1.0
 * @description
 * @createDate 2019-03-12 16:20
 */
public interface OrderService extends IService<Order>{

    R insert(OrderDto orderDto);

}
