package com.echo.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.echo.common.Result;
import com.echo.dto.DishDto;
import com.echo.entity.Category;
import com.echo.entity.Dish;
import com.echo.entity.DishFlavor;
import com.echo.mapper.CategoryMapper;
import com.echo.mapper.DishFlavorMapper;
import com.echo.mapper.DishMapper;
import com.echo.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.echo.common.CommonConstant.ZERO;
import static com.echo.common.ResultCode.*;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 类路径：com.echo.service.impl
     * 类名称：DishServiceImpl
     * 方法名称：saveDishWithFlavors
     * 方法描述：{ 新增菜品 }
     * param：[dishDto]
     * return：com.echo.common.Result
     * 创建人：@author Echo
     * 创建时间：2023/10/14 14:28
     * version：1.0
     */
    @Override
    public Result saveDishWithFlavors(DishDto dishDto) {
        // 保存菜品的基本信息到菜品表dish
        if (dishMapper.insert(dishDto) > ZERO) {
            return Result.failed(THE_DISH_SAVE_FAILED);
        }

        Long dishId = dishDto.getId();

        // 菜品口味
        if (CollectionUtil.isNotEmpty(dishDto.getFlavors())) {
            List<DishFlavor> dishFlaveors = dishDto.getFlavors().stream().map((item) -> {
                item.setDishId(dishId);
                return item;
            }).collect(Collectors.toList());

            for (DishFlavor dishFlavor : dishFlaveors) {
                dishFlavorMapper.insert(dishFlavor);
            }
        }

        return Result.success();
    }

    /**
     * 类路径：com.echo.service.impl
     * 类名称：DishServiceImpl
     * 方法名称：getAllPageDishInfo
     * 方法描述：{ 菜品信息分页查询 }
     * param：[pageNum, pageSize, dishName]
     * return：com.echo.common.Result
     * 创建人：@author Echo
     * 创建时间：2023/10/14 14:28
     * version：1.0
     */
    @Override
    public Result getAllPageDishInfo(Integer pageNum, Integer pageSize, String dishName) {
        Page<Dish> pageInfo = new Page<>(pageNum, pageSize);
        Page<DishDto> dishDtoPage = new Page<>();

        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StrUtil.isNotBlank(dishName), Dish::getName, dishName);
        queryWrapper.orderByDesc(Dish::getSort);
        Page<Dish> dishPage = page(pageInfo, queryWrapper);

        //对象拷贝
        BeanUtils.copyProperties(pageInfo, dishDtoPage, "records");
        List<Dish> records = pageInfo.getRecords();

        List<DishDto> list = records.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(item, dishDto);
            Long categoryId = item.getCategoryId();//分类id
            //根据id查询分类对象
            Category category = categoryMapper.selectById(categoryId);
            if (ObjectUtil.isNotNull(category)) {
                String categoryName = category.getName();
                dishDto.setCategoryName(categoryName);
            }
            return dishDto;
        }).collect(Collectors.toList());
        dishDtoPage.setRecords(list);

        return Result.success(dishDtoPage);
    }

    /**
     * 类路径：com.echo.service.impl
     * 类名称：DishServiceImpl
     * 方法名称：getDishAndFlavorsById
     * 方法描述：{ 根据id查询菜品信息和对应的口味信息 }
     * param：[dishId]
     * return：com.echo.common.Result<com.echo.dto.DishDto>
     * 创建人：@author Echo
     * 创建时间：2023/10/14 15:03
     * version：1.0
     */
    @Override
    public Result<DishDto> getDishAndFlavorsById(Long dishId) {
        DishDto dishDto = new DishDto();
        Dish dish = dishMapper.selectById(dishId);
        if (ObjectUtil.isEmpty(dish)) {
            return Result.failed(THE_DISH_QUERY_FAILED);
        }

        List<DishFlavor> dishFlavors = dishFlavorMapper.selectList(new LambdaQueryWrapper<DishFlavor>().eq(DishFlavor::getDishId, dishId));
        if (CollectionUtil.isNotEmpty(dishFlavors)) {
            dishDto.setFlavors(dishFlavors);
        }
        BeanUtils.copyProperties(dish, dishDto);
        return Result.success(dishDto);
    }

    /**
     * 类路径：com.echo.service.impl
     * 类名称：DishServiceImpl
     * 方法名称：updateDishInfo
     * 方法描述：{ 修改菜品 }
     * param：[dishDto]
     * return：com.echo.common.Result
     * 创建人：@author Echo
     * 创建时间：2023/10/14 15:27
     * version：1.0
     */
    @Override
    public Result updateDishInfo(DishDto dishDto) {
        //更新dish表基本信息
        int updatedDish = dishMapper.updateById(dishDto);
        if (updatedDish > ZERO) {
            return Result.failed(THE_DISH_UPDATE_FAILED);
        }

        //清理当前菜品对应口味数据
        dishFlavorMapper.delete(new LambdaQueryWrapper<DishFlavor>().eq(DishFlavor::getDishId, dishDto.getId()));

        //添加当前提交过来的口味数据
        List<DishFlavor> dishFlaveors = dishDto.getFlavors();
        if (CollectionUtil.isNotEmpty(dishDto.getFlavors())) {
            dishFlaveors = dishFlaveors.stream().map((item) -> {
                item.setDishId(dishDto.getId());
                return item;
            }).collect(Collectors.toList());
            for (DishFlavor dishFlavor : dishFlaveors) {
                dishFlavorMapper.insert(dishFlavor);
            }
        }
        return Result.success();
    }

}
