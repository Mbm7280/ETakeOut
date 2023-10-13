package com.echo.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.echo.common.Result;
import com.echo.entity.Category;
import com.echo.entity.Dish;
import com.echo.entity.Setmeal;
import com.echo.mapper.CategoryMapper;
import com.echo.mapper.DishMapper;
import com.echo.mapper.SetmealMapper;
import com.echo.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.echo.common.CommonConstant.ONE;
import static com.echo.common.CommonConstant.ZERO;
import static com.echo.common.ResultCode.*;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {


    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 类路径：com.echo.service.impl
     * 类名称：CategoryServiceImpl
     * 方法名称：categoryService
     * 方法描述：{ 新增分类 }
     * param：[category]
     * return：com.echo.common.Result
     * 创建人：@author Echo
     * 创建时间：2023/10/13 20:42
     * version：1.0
     */
    @Override
    public Result saveCategory(Category category) {
        return categoryMapper.insert(category) > ZERO ? Result.success() : Result.failed(THE_CATEGORY_SAVE_FAILED);
    }

    /**
     * 类路径：com.echo.service.impl
     * 类名称：CategoryServiceImpl
     * 方法名称：getAllPageCategories
     * 方法描述：{ 分页查询所有分类 }
     * param：[pageNum, pageSize]
     * return：com.echo.common.Result
     * 创建人：@author Echo
     * 创建时间：2023/10/13 20:55
     * version：1.0
     */
    @Override
    public Result getAllPageCategories(Integer pageNum, Integer pageSize) {
        Page<Category> pageInfo = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.orderByDesc(Category::getSort);
        return Result.success(page(pageInfo, lambdaQueryWrapper));
    }

    /**
     * 类路径：com.echo.service.impl
     * 类名称：CategoryServiceImpl
     * 方法名称：getCategoryById
     * 方法描述：{ 根据分类id获取分类 }
     * param：[categoryId]
     * return：com.echo.common.Result
     * 创建人：@author Echo
     * 创建时间：2023/10/13 21:58
     * version：1.0
     */
    @Override
    public Result getCategoryById(Long categoryId) {
        return ObjectUtil.isEmpty(this.getCategoryById(categoryId)) ? Result.failed(THE_CATEGORY_QUERY_FAILED) : Result.success();
    }

    /**
     * 类路径：com.echo.service.impl
     * 类名称：CategoryServiceImpl
     * 方法名称：deleteCategoryById
     * 方法描述：{ 根据分类id删除分类 }
     * param：[categoryId]
     * return：com.echo.common.Result
     * 创建人：@author Echo
     * 创建时间：2023/10/13 23:22
     * version：1.0
     */
    @Override
    public Result deleteCategoryById(Long categoryId) {
        Category category = this.getCategory(categoryId);
        if (ObjectUtil.isEmpty(category)) {
            return Result.failed(THE_CATEGORY_QUERY_FAILED);
        }
        Integer dishCount = dishMapper.selectCount(new LambdaQueryWrapper<Dish>().eq(Dish::getCategoryId, categoryId));
        if (dishCount > 0) {
            //已经关联菜品，抛出一个业务异常
            return Result.failed(THE_CATEGORY_HAS_DISHES);
        }
        //查询当前分类是否关联了套餐，如果已经关联，抛出一个业务异常
        Integer setmealCount = setmealMapper.selectCount(new LambdaQueryWrapper<Setmeal>()
                .eq(Setmeal::getCategoryId, categoryId));
        if (setmealCount > 0) {
            //已经关联菜品，抛出一个业务异常
            return Result.failed(THE_CATEGORY_HAS_MEALS);
        }
        category.setIsDeleted(ONE);
        return categoryMapper.updateById(category) > ZERO ? Result.success() : Result.failed(THE_CATEGORY_DELETE_FAILED);
    }

    private Category getCategory(Long categoryId) {
        return categoryMapper.selectById(new LambdaQueryWrapper<Category>().eq(Category::getId, categoryId)
                .eq(Category::getIsDeleted, ZERO)
        );
    }


    /**
     * 类路径：com.echo.service.impl
     * 类名称：CategoryServiceImpl
     * 方法名称：getCategoryByType
     * 方法描述：{ 根据条件查询分类数据 }
     * param：[type]
     * return：com.echo.common.Result<java.util.List<com.echo.entity.Category>>
     * 创建人：@author Echo
     * 创建时间：2023/10/13 21:45
     * version：1.0
     */
    @Override
    public Result<List<Category>> getCategoryByType(Integer type) {
        List<Category> categories = categoryMapper.selectList(new LambdaQueryWrapper<Category>()
                .eq(Category::getType, type)
                .orderByDesc(Category::getUpdateTime)
        );
        return CollectionUtils.isEmpty(categories) ? Result.failed(THE_CATEGORY_QUERY_FAILED) : Result.success();
    }

    /**
     * 类路径：com.echo.service.impl
     * 类名称：CategoryServiceImpl
     * 方法名称：updateCategory
     * 方法描述：{ 修改分类信息 }
     * param：[category]
     * return：com.echo.common.Result
     * 创建人：@author Echo
     * 创建时间：2023/10/13 21:50
     * version：1.0
     */
    @Override
    public Result updateCategory(Category category) {
        return categoryMapper.updateById(category) > ZERO ? Result.success() : Result.failed(THE_CATEGORY_UPDATE_FAILED);
    }

}
