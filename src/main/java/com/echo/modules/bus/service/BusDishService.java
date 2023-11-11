package com.echo.modules.bus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.config.api.Result;
import com.echo.modules.bus.dto.req.AddDishRequestDTO;
import com.echo.modules.bus.dto.req.UpdateDishByDishIdRequestDTO;
import com.echo.modules.bus.dto.res.GetDishWithFlavorByDishIdResponceDTO;
import com.echo.modules.bus.dto.res.GetPageDishListResponceDTO;
import com.echo.modules.bus.model.BusDish;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 菜品管理 服务类
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
public interface BusDishService extends IService<BusDish> {


    /**
     * 新增菜品
     *
     * @param dishDto
     * @return
     */
    Result addDish(AddDishRequestDTO dishDto);


    /**
     * 菜品信息分页查询
     *
     * @param pageNum
     * @param pageSize
     * @param dishName
     * @return
     */
    Result<Page<GetPageDishListResponceDTO>> getPageDishList(int pageNum, int pageSize, String dishName);


    /**
     * 根据id查询菜品信息和对应的口味信息
     *
     * @param dishId
     * @return
     */
    Result<GetDishWithFlavorByDishIdResponceDTO> getDishWithFlavorByDishId(Long dishId);


    /**
     * 修改菜品
     *
     * @param updateDishByDishIdRequestDTO
     * @return
     */
    Result updateDish(UpdateDishByDishIdRequestDTO updateDishByDishIdRequestDTO);


}
