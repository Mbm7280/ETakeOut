package com.echo.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echo.modules.ums.model.UmsResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 资源表 Mapper 接口
 * </p>
 *
 * @author Echo
 * @since 2023-10-21
 */
public interface UmsResourceMapper extends BaseMapper<UmsResource> {

    /**
     * 获取用户所有可访问资源
     */
    List<UmsResource> getResourceList(@Param("adminId") Long adminId);


    /**
     * 根据角色ID获取资源
     */
    List<UmsResource> getResourcesByRoleId(@Param("roleId") Long roleId);

}
