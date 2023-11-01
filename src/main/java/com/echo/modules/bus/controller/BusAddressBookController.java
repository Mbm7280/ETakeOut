package com.echo.modules.bus.controller;


import com.echo.config.api.Result;
import com.echo.modules.bus.dto.req.AddressBookRequestDTO;
import com.echo.modules.bus.dto.req.SetDefaultAddressReqDTO;
import com.echo.modules.bus.model.BusAddressBook;
import com.echo.modules.bus.service.BusAddressBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 地址管理 前端控制器
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
@RestController
@RequestMapping("/bus/busBusAddressBook")
@Api(tags = "BusAddressBookController")
@Tag(name = "BusAddressBookController", description = "地址管理")
public class BusAddressBookController {

    @Autowired
    private BusAddressBookService addressBookService;

    @ApiOperation(value = "新增地址")
    @PostMapping("/addAddressBook")
    public Result addAddressBook(@RequestBody AddressBookRequestDTO requestDTO) {
        return addressBookService.addAddressBook(requestDTO);
    }

    @PutMapping("/setDefaultAddress")
    @ApiOperation(value = "设置默认地址")
    public Result setDefaultAddress(@RequestBody SetDefaultAddressReqDTO requestDTO) {
        return addressBookService.setDefaultAddress(requestDTO);
    }

    @GetMapping("/getAddressById/{id}")
    @ApiOperation(value = "根据id查询地址")
    public Result<BusAddressBook> getAddressById(@PathVariable Long id) {
        return addressBookService.getAddressById(id);
    }

    @GetMapping("/getDefaultAddress")
    @ApiOperation(value = "查询默认地址")
    public Result<BusAddressBook> getDefaultAddress() {
        return addressBookService.getDefaultAddress();
    }

    @GetMapping("/getAddressListById")
    @ApiOperation(value = "查询指定用户的全部地址")
    public Result<List<BusAddressBook>> getAddressListById() {
        return addressBookService.getAddressListById();
    }


}

