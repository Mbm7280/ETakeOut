package com.echo.modules.bus.service;

import com.echo.config.api.Result;
import com.echo.modules.bus.dto.req.AddressBookRequestDTO;
import com.echo.modules.bus.dto.req.SetDefaultAddressReqDTO;
import com.echo.modules.bus.model.BusAddressBook;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 地址管理 服务类
 * </p>
 *
 * @author Echo
 * @since 2023-11-01
 */
public interface BusAddressBookService extends IService<BusAddressBook> {


    /**
     * 新增地址
     * @param requestDTO
     * @return
     */
    Result addAddressBook(AddressBookRequestDTO requestDTO);


    /**
     * 设置默认地址
     * @param requestDTO
     * @return
     */
    Result setDefaultAddress(SetDefaultAddressReqDTO requestDTO);


    /**
     * 根据id查询地址
     * @param id
     * @return
     */
    Result<BusAddressBook> getAddressById(Long id);


    /**
     * 查询默认地址
     * @return
     */
    Result<BusAddressBook> getDefaultAddress();


    /**
     * 查询指定用户的全部地址
     * @return
     */
    Result<List<BusAddressBook>> getAddressListById();


}
