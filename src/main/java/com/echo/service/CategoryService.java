package com.echo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.echo.common.Result;
import com.echo.entity.Category;

import java.util.List;

public interface CategoryService extends IService<Category> {

    /**
     * 新增分类
     *
     * @param category
     * @return
     */
    public Result saveCategory(Category category);


    /**
     * 分页查询所有分类
     *
     * @param page
     * @param pageSize
     * @return
     */
    Result getAllPageCategories(Integer page, Integer pageSize);


    /**
     * 根据分类id获取分类
     *
     * @param categoryId
     * @return
     */
    Result getCategoryById(Long categoryId);


    /**
     * 根据分类id删除分类
     *
     * @param categoryId
     * @return
     */
    Result deleteCategoryById(Long categoryId);

    /**
     * 根据类型查询分类数据
     *
     * @param type
     * @return
     */
    Result<List<Category>> getCategoryByType(Integer type);

    /**
     * 修改分类信息
     *
     * @param category
     * @return
     */
    Result updateCategory(Category category);


}
