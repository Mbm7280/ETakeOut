package com.echo.modules.bus.dto.req;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserInfoByUserIdReqDTO {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("userID")
    private Long id;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("登录密码")
    private String password;

    @ApiModelProperty("头像")
    private String icon;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("昵称")
    private String nickName;

    @ApiModelProperty("备注信息")
    private String note;

    @ApiModelProperty("最后登录时间")
    private Date loginTime;

}
