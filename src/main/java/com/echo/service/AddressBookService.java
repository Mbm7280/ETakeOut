package com.echo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.echo.common.Result;
import com.echo.entity.AddressBook;

import java.util.List;

public interface AddressBookService extends IService<AddressBook> {

    /**
     * 查询默认地址
     *
     * @return
     */
    Result<AddressBook> getDefaultAddress();


    /**
     * 查询指定用户的全部地址
     *
     * @param userId
     * @return
     */
    Result<List<AddressBook>> getAllAddressByUserId(Long userId);


    /**
     * 设置默认地址
     *
     * @param addressId
     * @return
     */
    Result<AddressBook> setDefaultAddress(Long addressId);

}
