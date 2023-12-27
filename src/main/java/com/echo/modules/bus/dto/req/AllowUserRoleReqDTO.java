package com.echo.modules.bus.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllowUserRoleReqDTO {

    @NotBlank
    @ApiModelProperty("用户id")
    private Long userId;

    @NotBlank
    @ApiModelProperty("角色id")
    private List<Long> roleIds;
}
