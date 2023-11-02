package com.echo.modules.bus.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusCategory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 菜品及套餐分类 服务类
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
public interface BusCategoryService extends IService<BusCategory> {


    /**
     * 新增分类
     *
     * @param category
     * @return
     */
    Result addCategory(BusCategory category);


    /**
     * 分页查询分类
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    Result<Page<BusCategory>> getPageCategoryList(Integer pageNum, Integer pageSize);


    /**
     * 根据id删除分类
     *
     * @param id
     * @return
     */
    Result delCategoryById(Long id);


    /**
     * 根据id修改分类信息
     *
     * @param category
     * @return
     */
    Result updateCategoryById(BusCategory category);


    /**
     * 根据条件查询分类数据
     *
     * @param category
     * @return
     */
    Result<List<BusCategory>> getCategoryByKeyword(BusCategory category);


}
