package com.echo.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.common.Result;
import com.echo.dto.DishDto;
import com.echo.entity.Category;
import com.echo.entity.Dish;
import com.echo.entity.DishFlavor;
import com.echo.service.CategoryService;
import com.echo.service.DishFlavorService;
import com.echo.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 菜品管理
 */
@RestController
@RequestMapping("/dish")
@Api(tags = "DishController")
@Tag(name = "DishController", description = "菜品管理模块")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 新增菜品
     *
     * @param dishDto
     * @return
     */
    @PostMapping("/saveDishWithFlavors")
    @ApiOperation(value = "新增菜品")
    public Result saveDishWithFlavors(@RequestBody DishDto dishDto) {
        return dishService.saveDishWithFlavors(dishDto);
    }

    @GetMapping("/getAllPageDishInfo")
    @ApiOperation(value = "菜品信息分页查询")
    public Result getAllPageDishInfo(Integer pageNum, Integer pageSize, String dishName) {
        return dishService.getAllPageDishInfo(pageNum, pageSize, dishName);
    }


    @GetMapping("/getDishAndFlavorsById/{dishId}")
    @ApiOperation(value = "根据id查询菜品信息和对应的口味信息")
    public Result<DishDto> getDishAndFlavorsById(@PathVariable Long dishId) {
        return dishService.getDishAndFlavorsById(dishId);
    }

    @PutMapping("/updateDishInfo")
    @ApiOperation(value = "修改菜品")
    public Result updateDishInfo(@RequestBody DishDto dishDto) {
        return dishService.updateDishInfo(dishDto);
    }


//    @GetMapping("/list")
//    public R<List<DishDto>> list(Dish dish) {
//        List<DishDto> dishDtoList = null;
//
//        //动态构造key
//        String key = "dish_" + dish.getCategoryId() + "_" + dish.getStatus();//dish_1397844391040167938_1
//
//        //先从redis中获取缓存数据
//        dishDtoList = (List<DishDto>) redisTemplate.opsForValue().get(key);
//
//        if (dishDtoList != null) {
//            //如果存在，直接返回，无需查询数据库
//            return R.success(dishDtoList);
//        }
//
//        //构造查询条件
//        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
//        queryWrapper.eq(dish.getCategoryId() != null, Dish::getCategoryId, dish.getCategoryId());
//        //添加条件，查询状态为1（起售状态）的菜品
//        queryWrapper.eq(Dish::getStatus, 1);
//
//        //添加排序条件
//        queryWrapper.orderByAsc(Dish::getSort).orderByDesc(Dish::getUpdateTime);
//
//        List<Dish> list = dishService.list(queryWrapper);
//
//        dishDtoList = list.stream().map((item) -> {
//            DishDto dishDto = new DishDto();
//
//            BeanUtils.copyProperties(item, dishDto);
//
//            Long categoryId = item.getCategoryId();//分类id
//            //根据id查询分类对象
//            Category category = categoryService.getById(categoryId);
//
//            if (category != null) {
//                String categoryName = category.getName();
//                dishDto.setCategoryName(categoryName);
//            }
//
//            //当前菜品的id
//            Long dishId = item.getId();
//            LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
//            lambdaQueryWrapper.eq(DishFlavor::getDishId, dishId);
//            //SQL:select * from dish_flavor where dish_id = ?
//            List<DishFlavor> dishFlavorList = dishFlavorService.list(lambdaQueryWrapper);
//            dishDto.setFlavors(dishFlavorList);
//            return dishDto;
//        }).collect(Collectors.toList());
//
//        //如果不存在，需要查询数据库，将查询到的菜品数据缓存到Redis
//        redisTemplate.opsForValue().set(key, dishDtoList, 60, TimeUnit.MINUTES);
//
//        return R.success(dishDtoList);
//    }

}
