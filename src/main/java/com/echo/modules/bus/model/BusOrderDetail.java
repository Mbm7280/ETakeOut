package com.echo.modules.bus.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单明细表
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
@Getter
@Setter
@TableName("bus_order_detail")
@ApiModel(value = "BusOrderDetail对象", description = "订单明细表")
public class BusOrderDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("名字")
    private String name;

    @ApiModelProperty("图片")
    private String image;

    @ApiModelProperty("订单id")
    private Long orderId;

    @ApiModelProperty("菜品id")
    private Long dishId;

    @ApiModelProperty("套餐id")
    private Long setmealId;

    @ApiModelProperty("口味")
    private String dishFlavor;

    @ApiModelProperty("数量")
    private Integer number;

    @ApiModelProperty("金额")
    private BigDecimal amount;

    @ApiModelProperty("是否删除 0 否 1是")
    private Integer isDeleted;


}
