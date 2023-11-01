package com.echo.modules.bus.service.impl;

import com.echo.modules.bus.model.BusOrder;
import com.echo.modules.bus.mapper.BusOrderMapper;
import com.echo.modules.bus.service.BusOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
@Service
public class BusOrderServiceImpl extends ServiceImpl<BusOrderMapper, BusOrder> implements BusOrderService {

}
