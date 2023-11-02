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
 * 菜品及套餐分类
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
@Getter
@Setter
@TableName("bus_category")
@ApiModel(value = "BusCategory对象", description = "菜品及套餐分类")
public class BusCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("类型   1 菜品分类 2 套餐分类")
    private Integer type;

    @ApiModelProperty("分类名称")
    private String name;

    @ApiModelProperty("顺序")
    private Integer sort;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("创建人")
    private Long createUser;

    @ApiModelProperty("修改人")
    private Long updateUser;

    @ApiModelProperty("1 -> 删除 0 -> 未删除")
    private Integer isDeleted;


}
