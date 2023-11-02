package com.echo.modules.bus.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusCategory;
import com.echo.modules.bus.mapper.BusCategoryMapper;
import com.echo.modules.bus.service.BusCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.echo.common.constant.CommonConstant.*;
import static com.echo.config.api.ResultCode.*;

/**
 * <p>
 * 菜品及套餐分类 服务实现类
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
@Service
public class BusCategoryServiceImpl extends ServiceImpl<BusCategoryMapper, BusCategory> implements BusCategoryService {

    @Autowired
    private BusCategoryMapper categoryMapper;


    /**
     * 类路径：com.echo.modules.bus.service.impl
     * 类名称：BusCategoryServiceImpl
     * 方法名称：addCategory
     * 方法描述：{ 新增分类 }
     * param：[category]
     * return：com.echo.config.api.Result
     * 创建人：@author Echo
     * 创建时间：2023/11/2 20:44
     * version：1.0
     */
    @Override
    public Result addCategory(BusCategory category) {
        if (category.getIsDeleted().equals(ONE)) {
            category.setIsDeleted(ZERO);
        }
        return save(category) ? Result.success() : Result.failed(THE_CATEGORY_ADD_FAILED);
    }

    /**
     * 类路径：com.echo.modules.bus.service.impl
     * 类名称：BusCategoryServiceImpl
     * 方法名称：getPageCategoryList
     * 方法描述：{ 分页查询分类 }
     * param：[pageNum, pageSize]
     * return：com.echo.config.api.Result<com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.echo.modules.bus.model.BusCategory>>
     * 创建人：@author Echo
     * 创建时间：2023/11/2 20:50
     * version：1.0
     */
    @Override
    public Result<Page<BusCategory>> getPageCategoryList(Integer pageNum, Integer pageSize) {
        Page<BusCategory> pageInfo = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<BusCategory> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BusCategory::getIsDeleted, ZERO);
        queryWrapper.orderByAsc(BusCategory::getSort);
        return Result.success(page(pageInfo, queryWrapper));
    }

    /**
     * 类路径：com.echo.modules.bus.service.impl
     * 类名称：BusCategoryServiceImpl
     * 方法名称：delCategoryById
     * 方法描述：{ 根据id删除分类 }
     * param：[id]
     * return：com.echo.config.api.Result
     * 创建人：@author Echo
     * 创建时间：2023/11/2 20:58
     * version：1.0
     */
    @Override
    public Result delCategoryById(Long id) {
        BusCategory busCategory = getOne(new LambdaQueryWrapper<BusCategory>()
                .eq(BusCategory::getIsDeleted, ZERO)
                .eq(BusCategory::getId, id)
        );
        if (ObjectUtil.isEmpty(busCategory)) {
            return Result.failed(THE_CATEGORY_QUERY_FAILED);
        }
        if (busCategory.getIsDeleted().equals(ONE)) {
            return Result.success();
        } else {
            busCategory.setIsDeleted(ONE);
            return updateById(busCategory) ? Result.success() : Result.failed(THE_CATEGORY_DELETE_FAILED);
        }
    }

    /**
     * 类路径：com.echo.modules.bus.service.impl
     * 类名称：BusCategoryServiceImpl
     * 方法名称：updateCategoryById
     * 方法描述：{ THE_CATEGORY_UPDATE_FAILED }
     * param：[category]
     * return：com.echo.config.api.Result
     * 创建人：@author Echo
     * 创建时间：2023/11/2 21:03
     * version：1.0
     */
    @Override
    public Result updateCategoryById(BusCategory category) {
        BusCategory busCategory = getOne(new LambdaQueryWrapper<BusCategory>()
                .eq(BusCategory::getIsDeleted, ZERO)
                .eq(BusCategory::getId, category.getId())
        );
        if (ObjectUtil.isEmpty(busCategory)) {
            return Result.failed(THE_CATEGORY_QUERY_FAILED);
        }
        if (category.getIsDeleted().equals(ONE)) {
            category.setIsDeleted(ZERO);
        }
        return updateById(category) ? Result.success() : Result.failed(THE_CATEGORY_UPDATE_FAILED);
    }

    /**
     * 类路径：com.echo.modules.bus.service.impl
     * 类名称：BusCategoryServiceImpl
     * 方法名称：getCategoryByKeyword
     * 方法描述：{ 根据条件查询分类数据 }
     * param：[category]
     * return：com.echo.config.api.Result<java.util.List<com.echo.modules.bus.model.BusCategory>>
     * 创建人：@author Echo
     * 创建时间：2023/11/2 21:10
     * version：1.0
     */
    @Override
    public Result<List<BusCategory>> getCategoryByKeyword(BusCategory category) {
        List<BusCategory> busCategoryList = list(new LambdaQueryWrapper<BusCategory>()
                .eq(BusCategory::getIsDeleted, ZERO)
                .eq(category.getType() != null, BusCategory::getType, category.getType())
                .orderByAsc(BusCategory::getSort)
                .orderByDesc(BusCategory::getUpdateTime)
        );
        return CollUtil.isNotEmpty(busCategoryList) ? Result.success() : Result.failed(THE_CATEGORY_QUERY_FAILED);
    }


}
