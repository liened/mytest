package com.exm.business.order;

import com.baomidou.mybatisplus.extension.service.IService;
import com.exm.common.R;

public interface OrderService extends IService<Order>{

    R insert(OrderDto orderDto);

}
