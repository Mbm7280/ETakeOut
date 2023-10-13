package com.echo.controller;

import com.echo.common.Result;
import com.echo.entity.Category;
import com.echo.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/category")
@Api(tags = "AddressBookController")
@Tag(name = "AddressBookController", description = "分类管理模块")

public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @PostMapping("/saveCategory")
    @ApiOperation(value = "新增分类")
    public Result saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @GetMapping("/getAllPageCategories")
    @ApiOperation(value = "分页查询所有分类")
    public Result getAllPageCategories(Integer pageNum, Integer pageSize) {
        return categoryService.getAllPageCategories(pageNum, pageSize);
    }

    @GetMapping("getCategoryById")
    @ApiOperation(value = "根据分类id获取分类")
    public Result getCategoryById(Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }


    @DeleteMapping("deleteCategoryById")
    @ApiOperation(value = "根据分类id删除分类")
    public Result deleteCategoryById(Long categoryId) {
        return categoryService.deleteCategoryById(categoryId);
    }


    @PutMapping("/updateCategory")
    @ApiOperation(value = "修改分类信息")
    public Result updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }


    @GetMapping("/getCategoryByType")
    @ApiOperation(value = "根据类型查询分类数据")
    public Result<List<Category>> getCategoryByType(Integer type) {
        return categoryService.getCategoryByType(type);
    }
}
