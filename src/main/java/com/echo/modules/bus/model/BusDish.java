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
 * 菜品管理
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
@Getter
@Setter
@TableName("bus_dish")
@ApiModel(value = "BusDish对象", description = "菜品管理")
public class BusDish implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("菜品名称")
    private String name;

    @ApiModelProperty("菜品分类id")
    private Long categoryId;

    @ApiModelProperty("菜品价格")
    private BigDecimal price;

    @ApiModelProperty("商品码")
    private String code;

    @ApiModelProperty("图片")
    private String image;

    @ApiModelProperty("描述信息")
    private String description;

    @ApiModelProperty("0 停售 1 起售")
    private Integer status;

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

    @ApiModelProperty("是否删除 0 否 1是")
    private Integer isDeleted;


}
