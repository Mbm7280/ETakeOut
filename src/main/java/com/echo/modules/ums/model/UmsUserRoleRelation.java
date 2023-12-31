package com.echo.modules.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 用户和角色关系表
 * </p>
 *
 * @author Echo
 * @since 2023-10-21
 */
@Getter
@Setter
@TableName("ums_user_role_relation")
@ApiModel(value = "UmsUserRoleRelation对象", description = "用户和角色关系表")
public class UmsUserRoleRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long userId;

    private Long roleId;


}
