package com.echo.modules.bus.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusCategory;
import com.echo.modules.bus.service.BusCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 菜品分类管理 前端控制器
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
@RestController
@RequestMapping("/bus/busCategory")
@Api(tags = "BusCategoryController")
@Tag(name = "BusCategoryController", description = "菜品分类管理")
public class BusCategoryController {

    @Autowired
    private BusCategoryService categoryService;

    @ApiOperation(value = "新增分类")
    @PostMapping("/addCategory")
    public Result addCategory(@RequestBody BusCategory category) {
        return categoryService.addCategory(category);
    }

    @ApiOperation(value = "分页查询分类")
    @GetMapping("/getPageCategoryList")
    public Result<Page<BusCategory>> getPageCategoryList(Integer pageNum, Integer pageSize) {
        return categoryService.getPageCategoryList(pageNum, pageSize);
    }


    @ApiOperation(value = "根据id删除分类")
    @DeleteMapping("/delCategoryById")
    public Result delCategoryById(Long id) {
        return categoryService.delCategoryById(id);
    }


    @ApiOperation(value = "根据id修改分类信息")
    @PutMapping("/updateCategoryById")
    public Result updateCategoryById(@RequestBody BusCategory category) {
        return categoryService.updateCategoryById(category);
    }


    @ApiOperation(value = "根据条件查询分类数据")
    @GetMapping("/getCategoryByKeyword")
    public Result<List<BusCategory>> getCategoryByKeyword(BusCategory category) {
        return categoryService.getCategoryByKeyword(category);
    }


}

