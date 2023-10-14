package com.echo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.echo.common.Result;
import com.echo.dto.DishDto;
import com.echo.entity.Dish;

public interface DishService extends IService<Dish> {


    /**
     * 新增菜品
     *
     * @param dishDto
     * @return
     */
    Result saveDishWithFlavors(DishDto dishDto);


    /**
     * 菜品信息分页查询
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result getAllPageDishInfo(Integer pageNum, Integer pageSize, String dishName);

    /**
     * 根据id查询菜品信息和对应的口味信息
     *
     * @param dishId
     * @return
     */
    Result<DishDto> getDishAndFlavorsById(Long dishId);


    /**
     * 修改菜品
     *
     * @param dishDto
     * @return
     */
    Result updateDishInfo(DishDto dishDto);

}
