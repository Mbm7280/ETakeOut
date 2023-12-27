package com.echo.modules.bus.dto.res;


import com.echo.modules.ums.model.UmsMenu;
import com.echo.modules.ums.model.UmsRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserInfoResDTO {

    private String userName;

    private String icon;

    private List<UmsMenu> menus;

    private List<UmsRole> roles;

}
