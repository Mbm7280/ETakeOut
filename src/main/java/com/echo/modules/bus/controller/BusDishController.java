package com.echo.modules.bus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.config.api.Result;
import com.echo.modules.bus.dto.req.AddDishRequestDTO;
import com.echo.modules.bus.dto.req.UpdateDishByDishIdRequestDTO;
import com.echo.modules.bus.dto.res.GetDishWithFlavorByDishIdResponceDTO;
import com.echo.modules.bus.dto.res.GetPageDishListResponceDTO;
import com.echo.modules.bus.service.BusDishService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <p>
 * 菜品管理 前端控制器
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
@RestController
@RequestMapping("/bus/busDish")
public class BusDishController {

    @Autowired
    private BusDishService dishService;

    @ApiOperation(value = "新增菜品")
    @PostMapping
    public Result addDish(@RequestBody AddDishRequestDTO dishDto) {
        return dishService.addDish(dishDto);
    }

    @ApiOperation(value = "菜品信息分页查询")
    @GetMapping("/getPageDishList")
    public Result<Page<GetPageDishListResponceDTO>> getPageDishList(int pageNum, int pageSize, String dishName) {
        return dishService.getPageDishList(pageNum, pageSize, dishName);
    }


    @ApiOperation(value = "根据id查询菜品信息和对应的口味信息")
    @GetMapping("/getDishWithFlavorById/{dishId}")
    public Result<GetDishWithFlavorByDishIdResponceDTO> getDishWithFlavorByDishId(@PathVariable Long dishId) {
        return dishService.getDishWithFlavorByDishId(dishId);
    }

    @ApiOperation(value = "修改菜品")
    @PutMapping("/updateDish")
    public Result updateDish(@RequestBody UpdateDishByDishIdRequestDTO updateDishIdRequestDTO) {
        return dishService.updateDish(updateDishIdRequestDTO);
    }
}

