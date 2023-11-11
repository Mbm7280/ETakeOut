package com.echo.modules.bus.dto.res;

import com.echo.modules.bus.model.BusDish;
import com.echo.modules.bus.model.BusDishFlavor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPageDishListResponceDTO extends BusDish {


    //菜品对应的口味数据
    private List<BusDishFlavor> flavors = new ArrayList<>();

    // 菜品分类名称
    private String categoryName;

}
