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
 * 套餐菜品关系
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
@Getter
@Setter
@TableName("bus_setmeal_dish_relation")
@ApiModel(value = "BusSetmealDishRelation对象", description = "套餐菜品关系")
public class BusSetmealDishRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("套餐id ")
    private String setmealId;

    @ApiModelProperty("菜品id")
    private String dishId;

    @ApiModelProperty("菜品名称 （冗余字段）")
    private String name;

    @ApiModelProperty("菜品原价（冗余字段）")
    private BigDecimal price;

    @ApiModelProperty("份数")
    private Integer copies;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("创建人")
    private Long createUser;

    @ApiModelProperty("修改人")
    private Long updateUser;

    @ApiModelProperty("是否删除")
    private Integer isDeleted;


}
