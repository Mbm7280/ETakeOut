package com.echo.modules.bus.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.echo.config.api.Result;
import com.echo.modules.bus.dto.req.AddressBookRequestDTO;
import com.echo.modules.bus.dto.req.SetDefaultAddressReqDTO;
import com.echo.modules.bus.model.BusAddressBook;
import com.echo.modules.bus.mapper.BusAddressBookMapper;
import com.echo.modules.bus.service.BusAddressBookService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.modules.ums.model.UmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.echo.common.constant.CommonConstant.*;
import static com.echo.config.api.ResultCode.*;

/**
 * <p>
 * 地址管理 服务实现类
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
@Service
public class BusAddressBookServiceImpl extends ServiceImpl<BusAddressBookMapper, BusAddressBook> implements BusAddressBookService {

    @Autowired
    private BusAddressBookMapper addressBookMapper;


    /**
     * 类路径：com.echo.modules.bus.service.impl
     * 类名称：BusAddressBookServiceImpl
     * 方法名称：addAddressBook
     * 方法描述：{ 新增地址 }
     * param：[requestDTO]
     * return：com.echo.config.api.Result<com.echo.modules.bus.model.BusAddressBook>
     * 创建人：@author Echo
     * 创建时间：2023/11/1 20:50
     * version：1.0
     */
    @Override
    public Result addAddressBook(AddressBookRequestDTO requestDTO) {
        if (ObjectUtil.equals(requestDTO.getIsDeleted(), ONE)) {
            requestDTO.setIsDeleted(ZERO);
        }
        return save(requestDTO) ? Result.success() : Result.failed(THE_ADDRESS_ADD_FAILED);
    }

    /**
     * 类路径：com.echo.modules.bus.service.impl
     * 类名称：BusAddressBookServiceImpl
     * 方法名称：setDefaultAddress
     * 方法描述：{ 设置默认地址 }
     * param：[requestDTO]
     * return：com.echo.config.api.Result
     * 创建人：@author Echo
     * 创建时间：2023/11/1 21:00
     * version：1.0
     */
    @Override
    public Result setDefaultAddress(SetDefaultAddressReqDTO requestDTO) {
        BusAddressBook addressBook = getOne(new LambdaQueryWrapper<BusAddressBook>()
                .eq(BusAddressBook::getUserId, requestDTO.getUserId())
                .eq(BusAddressBook::getId, requestDTO.getId())
        );
        if (ObjectUtil.isEmpty(addressBook)) {
            return Result.failed(THE_ADDRESS_QUERY_FAILED);
        }
        requestDTO.setIsDefault(ONE);
        return updateById(requestDTO) ? Result.success() : Result.failed(THE_ADDRESS_UPDATE_FAILED);
    }

    /**
     * 类路径：com.echo.modules.bus.service.impl
     * 类名称：BusAddressBookServiceImpl
     * 方法名称：getAddressById
     * 方法描述：{ 根据id查询地址 }
     * param：[id]
     * return：com.echo.config.api.Result<com.echo.modules.bus.model.BusAddressBook>
     * 创建人：@author Echo
     * 创建时间：2023/11/1 21:17
     * version：1.0
     */
    @Override
    public Result<BusAddressBook> getAddressById(Long id) {
        BusAddressBook addressBook = getOne(new LambdaQueryWrapper<BusAddressBook>()
                .eq(BusAddressBook::getId, id)
                .eq(BusAddressBook::getIsDeleted, ZERO)

        );
        return ObjectUtil.isNotEmpty(addressBook) ? Result.success(addressBook) : Result.failed(THE_ADDRESS_QUERY_FAILED);
    }

    /**
     * 类路径：com.echo.modules.bus.service.impl
     * 类名称：BusAddressBookServiceImpl
     * 方法名称：getDefaultAddress
     * 方法描述：{ 查询默认地址 }
     * param：[]
     * return：com.echo.config.api.Result<com.echo.modules.bus.model.BusAddressBook>
     * 创建人：@author Echo
     * 创建时间：2023/11/1 21:22
     * version：1.0
     */
    @Override
    public Result<BusAddressBook> getDefaultAddress() {
        Long userId = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UmsUser umsUser = (UmsUser) authentication.getPrincipal();
            userId = umsUser.getId();
        } else {
            return Result.failed(THE_USER_IS_NOT_EXIST);
        }
        BusAddressBook addressBook = getOne(new LambdaQueryWrapper<BusAddressBook>()
                .eq(BusAddressBook::getUserId, userId)
                .eq(BusAddressBook::getIsDeleted, ZERO)
                .eq(BusAddressBook::getIsDefault, ONE)
        );
        return ObjectUtil.isNotEmpty(addressBook) ? Result.success(addressBook) : Result.failed(THE_ADDRESS_QUERY_FAILED);
    }

    /**
     * 类路径：com.echo.modules.bus.service.impl
     * 类名称：BusAddressBookServiceImpl
     * 方法名称：getAddressListById
     * 方法描述：{ 查询指定用户的全部地址 }
     * param：[]
     * return：com.echo.config.api.Result<java.util.List<com.echo.modules.bus.model.BusAddressBook>>
     * 创建人：@author Echo
     * 创建时间：2023/11/1 21:25
     * version：1.0
     */
    @Override
    public Result<List<BusAddressBook>> getAddressListById() {
        Long userId = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            UmsUser umsUser = (UmsUser) authentication.getPrincipal();
            userId = umsUser.getId();
        } else {
            return Result.failed(THE_USER_IS_NOT_EXIST);
        }
        List<BusAddressBook> addressBookList = list(new LambdaQueryWrapper<BusAddressBook>()
                .eq(BusAddressBook::getUserId, userId)
                .eq(BusAddressBook::getIsDeleted, ZERO)
        );
        return CollUtil.isNotEmpty(addressBookList) ? Result.success(addressBookList) : Result.failed(THE_ADDRESS_QUERY_FAILED);
    }


}
