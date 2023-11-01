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
 * 地址管理
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
@Getter
@Setter
@TableName("bus_address_book")
@ApiModel(value = "BusAddressBook对象", description = "地址管理")
public class BusAddressBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("用户id")
    private Long userId;

    @ApiModelProperty("收货人")
    private String consignee;

    @ApiModelProperty("性别 0 女 1 男")
    private Integer sex;

    @ApiModelProperty("手机号")
    private String phone;

    @ApiModelProperty("省级区划编号")
    private String provinceCode;

    @ApiModelProperty("省级名称")
    private String provinceName;

    @ApiModelProperty("市级区划编号")
    private String cityCode;

    @ApiModelProperty("市级名称")
    private String cityName;

    @ApiModelProperty("区级区划编号")
    private String districtCode;

    @ApiModelProperty("区级名称")
    private String districtName;

    @ApiModelProperty("详细地址")
    private String detail;

    @ApiModelProperty("标签")
    private String label;

    @ApiModelProperty("默认 0 否 1是")
    private Boolean isDefault;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("更新时间")
    private Date updateTime;

    @ApiModelProperty("创建人")
    private Long createUser;

    @ApiModelProperty("修改人")
    private Long updateUser;

    @ApiModelProperty("是否删除 0 否 1是")
    private Boolean isDeleted;


}
