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
 * 套餐
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
@Getter
@Setter
@TableName("bus_setmeal")
@ApiModel(value = "BusSetmeal对象", description = "套餐")
public class BusSetmeal implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("菜品分类id")
    private Long categoryId;

    @ApiModelProperty("套餐名称")
    private String name;

    @ApiModelProperty("套餐价格")
    private BigDecimal price;

    @ApiModelProperty("状态 0:停用 1:启用")
    private Integer status;

    @ApiModelProperty("编码")
    private String code;

    @ApiModelProperty("描述信息")
    private String description;

    @ApiModelProperty("图片")
    private String image;

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
