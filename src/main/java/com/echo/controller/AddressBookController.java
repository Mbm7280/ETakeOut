package com.echo.controller;

import com.echo.common.BaseContext;
import com.echo.common.Result;
import com.echo.entity.AddressBook;
import com.echo.service.AddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.echo.common.ResultCode.RETURN_VALUE_IS_NULL;
import static com.echo.common.ResultCode.THE_ADDRESS_SAVE_FAILED;

/****************************************************
 * 创建人：@author ECHO
 * 创建时间: 2023/10/9 23:04
 * 项目名称: {EBlog}
 * 文件名称: AddressBookController
 * 文件描述: [Description]: 地址管理控制层
 * version：1.0
 * All rights Reserved, Designed By ECHO
 *
 ********************************************************/
@Slf4j
@RestController
@RequestMapping("/addressBook")
@Api(tags = "AddressBookController")
@Tag(name = "AddressBookController", description = "地址管理模块")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 查询默认地址
     */
    @GetMapping("getDefaultAddress")
    public Result<AddressBook> getDefaultAddress() {
        return addressBookService.getDefaultAddress();
    }

    @ApiOperation(value = "根据id查询地址")
    @GetMapping("/{id}")
    public Result getAddressById(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.getById(id);
        return addressBook != null ? Result.success(addressBook) : Result.failed(RETURN_VALUE_IS_NULL);
    }

    @ApiOperation(value = "查询指定用户的全部地址")
    @GetMapping("/getAllAddressByUserId")
    public Result<List<AddressBook>> getAllAddressByUserId(@RequestParam Long userId) {
        return addressBookService.getAllAddressByUserId(userId);
    }


    @ApiOperation(value = "保存地址")
    @PostMapping("/saveAddress")
    public Result<AddressBook> saveAddress(@RequestBody AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        return addressBookService.save(addressBook) ? Result.success(addressBook) : Result.failed(THE_ADDRESS_SAVE_FAILED);
    }


    @ApiOperation(value = "设置默认地址")
    @PutMapping("/setDefaultAddress")
    public Result<AddressBook> setDefaultAddress(@RequestParam Long addressId) {
        return addressBookService.setDefaultAddress(addressId);
    }

}
