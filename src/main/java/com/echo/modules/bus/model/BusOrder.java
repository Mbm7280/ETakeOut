package com.echo.modules.bus.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 订单表
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
@Getter
@Setter
@TableName("bus_order")
@ApiModel(value = "BusOrder对象", description = "订单表")
public class BusOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("订单号")
    private String number;

    @ApiModelProperty("订单状态 1待付款，2待派送，3已派送，4已完成，5已取消")
    private Integer status;

    @ApiModelProperty("下单用户")
    private Long userId;

    @ApiModelProperty("地址id")
    private Long addressBookId;

    @ApiModelProperty("下单时间")
    private Date orderTime;

    @ApiModelProperty("结账时间")
    private Date checkoutTime;

    @ApiModelProperty("支付方式 1微信,2支付宝")
    private Integer payMethod;

    @ApiModelProperty("实收金额")
    private BigDecimal amount;

    @ApiModelProperty("备注")
    private String remark;

    private String phone;

    private String address;

    private String userName;

    private String consignee;

    @ApiModelProperty("是否删除 0 否 1是")
    private Integer isDeleted;


}
