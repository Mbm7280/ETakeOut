package com.echo.modules.bus.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.config.api.Result;
import com.echo.modules.bus.dto.req.AddDishRequestDTO;
import com.echo.modules.bus.dto.req.UpdateDishByDishIdRequestDTO;
import com.echo.modules.bus.dto.res.GetDishWithFlavorByDishIdResponceDTO;
import com.echo.modules.bus.dto.res.GetPageDishListResponceDTO;
import com.echo.modules.bus.mapper.BusCategoryMapper;
import com.echo.modules.bus.mapper.BusDishFlavorMapper;
import com.echo.modules.bus.model.BusCategory;
import com.echo.modules.bus.model.BusDish;
import com.echo.modules.bus.mapper.BusDishMapper;
import com.echo.modules.bus.model.BusDishFlavor;
import com.echo.modules.bus.service.BusDishService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.echo.common.constant.CommonConstant.*;
import static com.echo.config.api.ResultCode.*;

/**
 * <p>
 * 菜品管理 服务实现类
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
@Service
public class BusDishServiceImpl extends ServiceImpl<BusDishMapper, BusDish> implements BusDishService {

    @Autowired
    private BusDishMapper dishMapper;

    @Autowired
    private BusCategoryMapper categoryMapper;

    @Autowired
    private BusDishFlavorMapper dishFlavorMapper;


    /**
     * 类路径：com.echo.modules.bus.service.impl
     * 类名称：BusDishServiceImpl
     * 方法名称：addDish
     * 方法描述：{ 新增菜品 }
     * param：[dishDto]
     * return：com.echo.config.api.Result
     * 创建人：@author Echo
     * 创建时间：2023/11/11 15:32
     * version：1.0
     */
    @Override
    public Result addDish(AddDishRequestDTO dishDto) {
        if (dishDto.getIsDeleted().equals(ONE)) {
            dishDto.setIsDeleted(ZERO);
        }
        return save(dishDto) ? Result.success() : Result.failed(THE_DISH_ADD_FAILED);
    }

    /**
     * 类路径：com.echo.modules.bus.service.impl
     * 类名称：BusDishServiceImpl
     * 方法名称：getPageDishList
     * 方法描述：{ 菜品信息分页查询 }
     * param：[pageNum, pageSize, dishName]
     * return：com.echo.config.api.Result<com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.echo.modules.bus.dto.res.GetPageDishListResponceDTO>>
     * 创建人：@author Echo
     * 创建时间：2023/11/11 16:01
     * version：1.0
     */
    @Override
    public Result<Page<GetPageDishListResponceDTO>> getPageDishList(int pageNum, int pageSize, String dishName) {
        Page<BusDish> pageInfo = new Page<>(pageNum, pageSize);
        Page<GetPageDishListResponceDTO> dishPageDTO = new Page<>();

        LambdaQueryWrapper<BusDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BusDish::getIsDeleted, ZERO);
        queryWrapper.like(StrUtil.isNotBlank(dishName), BusDish::getName, dishName);
        queryWrapper.orderByDesc(BusDish::getUpdateTime);

        Page<BusDish> dishPage = page(pageInfo, queryWrapper);

        BeanUtil.copyProperties(pageInfo, dishPageDTO, "records");
        List<BusDish> records = pageInfo.getRecords();
        if (CollUtil.isNotEmpty(records)) {
            List<GetPageDishListResponceDTO> getPageDishListResponceDTOS = records.stream().map((r) -> {
                GetPageDishListResponceDTO getPageDishListResponceDTO = new GetPageDishListResponceDTO();
                BeanUtil.copyProperties(r, getPageDishListResponceDTO);

                // 分类ID
                Long categoryId = r.getCategoryId();
                BusCategory busCategory = categoryMapper.selectById(categoryId);
                if (ObjectUtil.isNotNull(busCategory)) {
                    String categoryName = busCategory.getName();
                    getPageDishListResponceDTO.setCategoryName(categoryName);
                }
                return getPageDishListResponceDTO;
            }).collect(Collectors.toList());
            dishPageDTO.setRecords(getPageDishListResponceDTOS);
        }
        return Result.success(dishPageDTO);
    }

    /**
     * 类路径：com.echo.modules.bus.service.impl
     * 类名称：BusDishServiceImpl
     * 方法名称：getDishWithFlavorByDishId
     * 方法描述：{ 根据id查询菜品信息和对应的口味信息 }
     * param：[dishId]
     * return：com.echo.config.api.Result<com.echo.modules.bus.dto.res.GetDishWithFlavorByDishIdResponceDTO>
     * 创建人：@author Echo
     * 创建时间：2023/11/11 16:21
     * version：1.0
     */
    @Override
    public Result<GetDishWithFlavorByDishIdResponceDTO> getDishWithFlavorByDishId(Long dishId) {
        GetDishWithFlavorByDishIdResponceDTO dishResDTO = new GetDishWithFlavorByDishIdResponceDTO();
        BusDish busDish = dishMapper.selectOne(new LambdaQueryWrapper<BusDish>()
                .eq(BusDish::getIsDeleted, ZERO)
                .eq(BusDish::getId, dishId)
        );

        if (ObjectUtil.isEmpty(busDish)) {
            return Result.failed(THE_DISH_QUERY_FAILED);
        }

        BeanUtil.copyProperties(busDish, dishResDTO);

        List<BusDishFlavor> busDishFlavorList = dishFlavorMapper.selectList(new LambdaQueryWrapper<BusDishFlavor>()
                .eq(BusDishFlavor::getDishId, busDish.getId())
        );

        if (CollUtil.isNotEmpty(busDishFlavorList)) {
            dishResDTO.setFlavors(busDishFlavorList);
        }

        return Result.success(dishResDTO);
    }

    /**
     * 类路径：com.echo.modules.bus.service.impl
     * 类名称：BusDishServiceImpl
     * 方法名称：updateDish
     * 方法描述：{ 修改菜品 }
     * param：[updateDishByDishIdRequestDTO]
     * return：com.echo.config.api.Result
     * 创建人：@author Echo
     * 创建时间：2023/11/11 16:50
     * version：1.0
     */
    @Override
    public Result updateDish(UpdateDishByDishIdRequestDTO updateDishByDishIdRequestDTO) {
        BusDish busDish = dishMapper.selectOne(new LambdaQueryWrapper<BusDish>()
                .eq(BusDish::getIsDeleted, ZERO)
                .eq(BusDish::getId, updateDishByDishIdRequestDTO.getId())
        );

        if (ObjectUtil.isEmpty(busDish)) {
            return Result.failed(THE_DISH_QUERY_FAILED);
        }

        if (busDish.getIsDeleted().equals(ONE)) {
            busDish.setIsDeleted(ZERO);
        }

        int updatedResult = dishMapper.updateById(busDish);

        if (updatedResult > ZERO) {
            List<BusDishFlavor> busDishFlavorList = dishFlavorMapper.selectList(new LambdaQueryWrapper<BusDishFlavor>()
                    .eq(BusDishFlavor::getDishId, updateDishByDishIdRequestDTO.getId())
            );

            if (CollUtil.isNotEmpty(busDishFlavorList)) {
                int deleteResult = dishFlavorMapper.delete(new LambdaQueryWrapper<BusDishFlavor>()
                        .in(BusDishFlavor::getDishId, updateDishByDishIdRequestDTO.getId())
                );
                if (deleteResult > ZERO) {
                    List<BusDishFlavor> flavors = updateDishByDishIdRequestDTO.getFlavors();
                    for (BusDishFlavor dishFlavor : flavors) {
                        dishFlavorMapper.insert(dishFlavor);
                    }
                }
            }
        }
        return Result.success();
    }

}
