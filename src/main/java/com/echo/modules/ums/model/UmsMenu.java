package com.echo.modules.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author Echo
 * @since 2023-10-21
 */
@Getter
@Setter
@TableName("ums_menu")
@ApiModel(value = "UmsMenu对象", description = "菜单表")
public class UmsMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("父级ID")
    private Long parentId;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("菜单名称")
    private String title;

    @ApiModelProperty("菜单级数")
    private Integer level;

    @ApiModelProperty("菜单排序")
    private Integer sort;

    @ApiModelProperty("前端名称")
    private String name;

    @ApiModelProperty("前端图标")
    private String icon;

    @ApiModelProperty("前端隐藏")
    private Integer hidden;

    @ApiModelProperty("0 有效 1 无效")
    private Integer status;


}
