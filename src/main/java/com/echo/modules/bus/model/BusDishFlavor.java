package com.echo.modules.bus.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜品口味关系表
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
@Getter
@Setter
@TableName("bus_dish_flavor")
@ApiModel(value = "BusDishFlavor对象", description = "菜品口味关系表")
public class BusDishFlavor implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("菜品")
    private Long dishId;

    @ApiModelProperty("口味名称")
    private String name;

    @ApiModelProperty("口味数据list")
    private String value;

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
