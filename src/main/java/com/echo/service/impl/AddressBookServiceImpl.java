package com.echo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.common.BaseContext;
import com.echo.common.Result;
import com.echo.entity.AddressBook;
import com.echo.mapper.AddressBookMapper;
import com.echo.service.AddressBookService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.echo.common.CommonConstant.ONE;
import static com.echo.common.CommonConstant.ZERO;
import static com.echo.common.ResultCode.*;

@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

    @Autowired
    private AddressBookMapper addressBookMapper;

    /**
     * 类路径：com.echo.service.impl
     * 类名称：AddressBookServiceImpl
     * 方法名称：getDefaultAddress
     * 方法描述：{ 查询默认地址 }
     * param：[]
     * return：com.echo.common.Result<com.echo.entity.AddressBook>
     * 创建人：@author Echo
     * 创建时间：2023/10/9 23:19
     * version：1.0
     */
    @Override
    public Result<AddressBook> getDefaultAddress() {
        AddressBook addressBook = addressBookMapper.selectOne(new LambdaQueryWrapper<AddressBook>()
                .eq(AddressBook::getUserId, BaseContext.getCurrentId())
                .eq(AddressBook::getIsDefault, 1)
        );
        return ObjectUtils.isEmpty(addressBook) ? Result.failed(RETURN_VALUE_IS_NULL) : Result.success(addressBook);
    }


    /**
     * 类路径：com.echo.service.impl
     * 类名称：AddressBookServiceImpl
     * 方法名称：getAllAddressByUserId
     * 方法描述：{ 查询指定用户的全部地址 }
     * param：[userId]
     * return：com.echo.common.Result<java.util.List<com.echo.entity.AddressBook>>
     * 创建人：@author Echo
     * 创建时间：2023/10/9 23:26
     * version：1.0
     */
    @Override
    public Result<List<AddressBook>> getAllAddressByUserId(Long userId) {
        List<AddressBook> addressBookList = addressBookMapper.selectList(new LambdaQueryWrapper<AddressBook>()
                .eq(AddressBook::getUserId, userId)
                .orderByDesc(AddressBook::getUpdateTime)
        );
        return CollectionUtils.isEmpty(addressBookList) ? Result.failed(RETURN_VALUE_IS_NULL) : Result.success(addressBookList);
    }

    /**
     * 类路径：com.echo.service.impl
     * 类名称：AddressBookServiceImpl
     * 方法名称：setDefaultAddress
     * 方法描述：{ 设置默认地址 }
     * param：[addressId]
     * return：com.echo.common.Result<com.echo.entity.AddressBook>
     * 创建人：@author Echo
     * 创建时间：2023/10/9 23:48
     * version：1.0
     */
    @Override
    public Result<AddressBook> setDefaultAddress(Long addressId) {
        AddressBook addressBook = addressBookMapper.selectOne(new LambdaQueryWrapper<AddressBook>()
                .eq(AddressBook::getUserId, BaseContext.getCurrentId())
                .eq(AddressBook::getId, addressId)
                .eq(AddressBook::getIsDefault, ZERO)
        );
        if (ObjectUtils.isEmpty(addressBook)) {
            return Result.failed(THE_DATA_IS_NOT_EXIST);
        }
        addressBook.setIsDefault(ONE);
        return addressBookMapper.updateById(addressBook) > ZERO ? Result.success() : Result.failed(THE_DEFAULT_ADDRESS_UPDATE_FAILED);
    }

}
