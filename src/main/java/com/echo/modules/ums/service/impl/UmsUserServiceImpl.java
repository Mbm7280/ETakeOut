package com.echo.modules.ums.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.config.api.Result;
import com.echo.config.exception.Asserts;
import com.echo.dto.AdminUserDetails;
import com.echo.modules.bus.dto.req.AllowUserRoleReqDTO;
import com.echo.modules.bus.dto.req.UpdateUserInfoByUserIdReqDTO;
import com.echo.modules.bus.dto.res.GetUserInfoResDTO;
import com.echo.modules.ums.dto.req.LoginReqDTO;
import com.echo.modules.ums.dto.req.RegisterReqDTO;
import com.echo.modules.ums.dto.req.UpdateUserPasswordReqDTO;
import com.echo.modules.ums.dto.res.LoginResDTO;
import com.echo.modules.ums.dto.res.RefreshTokenResDTO;
import com.echo.modules.ums.mapper.UmsResourceMapper;
import com.echo.modules.ums.mapper.UmsRoleMapper;
import com.echo.modules.ums.mapper.UmsUserMapper;
import com.echo.modules.ums.model.*;
import com.echo.modules.ums.service.UmsRoleService;
import com.echo.modules.ums.service.UmsUserCacheService;
import com.echo.modules.ums.service.UmsUserRoleRelationService;
import com.echo.modules.ums.service.UmsUserService;
import com.echo.utils.JwtTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static com.echo.common.constant.CommonConstant.ONE;
import static com.echo.common.constant.CommonConstant.ZERO;
import static com.echo.config.api.ResultCode.*;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Echo
 * @since 2023-10-21
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements UmsUserService {

    @Autowired
    private UmsRoleService roleService;

    @Autowired
    private UmsResourceMapper resourceMapper;

    @Autowired
    private UmsRoleMapper roleMapper;

    @Autowired
    private UmsUserRoleRelationService userRoleRelationService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;


    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：register
     * 方法描述：{ 用户注册 }
     * param：[registerReqDTO]
     * return：com.echo.config.api.Result<com.echo.modules.ums.model.UmsUser>
     * 创建人：@author Echo
     * 创建时间：2023/10/21 17:56
     * version：1.0
     */
    @Override
    public Result<UmsUser> register(RegisterReqDTO registerReqDTO) {
        UmsUser umsUser = new UmsUser();

        BeanUtils.copyProperties(registerReqDTO, umsUser);
        umsUser.setCreateTime(new Date());
        // 帐号启用状态：0->禁用；1->启用
        umsUser.setStatus(ONE);

        // 查询是否有相同用户名的用户
        List<UmsUser> umsUserList = list(new LambdaQueryWrapper<UmsUser>()
                .eq(UmsUser::getUsername, umsUser.getUsername())
        );
        if (umsUserList.size() > ZERO) {
            return Result.success(THE_USER_HAS_REGISTERED);
        }

        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsUser.getPassword());
        umsUser.setPassword(encodePassword);

        return save(umsUser) ? Result.success() : Result.failed();
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：login
     * 方法描述：{ 登录 }
     * param：[loginReqDTO]
     * return：com.echo.config.api.Result<com.echo.modules.ums.dto.res.LoginResDTO>
     * 创建人：@author Echo
     * 创建时间：2023/10/21 18:21
     * version：1.0
     */
    @Override
    public Result<LoginResDTO> login(LoginReqDTO loginReqDTO) {
        LoginResDTO loginResDTO = new LoginResDTO();

        // 获取用户信息
        UserDetails userDetails = loadUserByUsername(loginReqDTO.getUsername());

        // 校验用户账号是否被禁用
        if (!userDetails.isEnabled()) {
            Asserts.fail("帐号已被禁用");
        }

        // 密码校验
        if (!passwordEncoder.matches(loginReqDTO.getPassword(), userDetails.getPassword())) {
            Asserts.fail("密码不正确");
        }

        // 将认证信息放到上下文中
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 生成Token
        String token = jwtTokenUtil.generateToken(userDetails);

        // 校验Token生成
        if (StrUtil.isBlank(token)) {
            return Result.failed("用户名或密码错误");
        }

        // 返回
        loginResDTO.setToken(token);
        loginResDTO.setTokenHead(tokenHead);

        return Result.success(loginResDTO);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：getUserInfo
     * 方法描述：{ 获取当前登录用户信息 }
     * param：[userName]
     * return：com.echo.config.api.Result<com.echo.modules.bus.dto.res.GetUserInfoResDTO>
     * 创建人：@author Echo
     * 创建时间：2023/12/26 15:40
     * version：1.0
     */
    @Override
    public Result<GetUserInfoResDTO> getUserInfo(String userName) {
        // 获取用户基本信息
        UmsUser umsUser = this.getUserInfoByUserName(userName);
        if (ObjectUtil.isEmpty(umsUser)) {
            return Result.failed(THE_USER_QUERY_FAILED);
        }

        // 获取用户菜单信息
        List<UmsMenu> umsMenuList = roleService.getMenuListByUserId(umsUser.getId());
        if (CollUtil.isEmpty(umsMenuList)) {
            return Result.failed(THE_MENU_QUERY_FAILED);
        }

        // 获取用户角色信息
        List<UmsRole> roleList = this.getRoleListByUserId(umsUser.getId());
        if (CollUtil.isEmpty(roleList)) {
            return Result.failed(THE_ROLE_QUERY_FAILED);
        }

        GetUserInfoResDTO getUserInfoResDTO = new GetUserInfoResDTO();
        getUserInfoResDTO.setUserName(userName);
        getUserInfoResDTO.setIcon(umsUser.getIcon());
        getUserInfoResDTO.setMenus(umsMenuList);
        // data.put("roles", roleList.stream().map(UmsRole::getRoleName).collect(Collectors.toList()));
        getUserInfoResDTO.setRoles(roleList);

        return Result.success(getUserInfoResDTO);
    }


    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：refreshToken
     * 方法描述：{ 刷新token }
     * param：[request]
     * return：com.echo.config.api.Result<com.echo.modules.ums.dto.res.RefreshTokenResDTO>
     * 创建人：@author Echo
     * 创建时间：2023/10/21 18:28
     * version：1.0
     */
    @Override
    public Result<RefreshTokenResDTO> refreshToken(HttpServletRequest request) {
        RefreshTokenResDTO refreshTokenResDTO = new RefreshTokenResDTO();
        // 获取token
        String oldToken = request.getHeader(tokenHeader);
        // 刷新token
        String newToken = jwtTokenUtil.refreshHeadToken(oldToken);

        if (StrUtil.isBlank(newToken)) {
            return Result.failed("token已经过期！");
        }

        refreshTokenResDTO.setToken(newToken);
        refreshTokenResDTO.setTokenHead(tokenHead);

        return Result.success(refreshTokenResDTO);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：loadUserByUsername
     * 方法描述：{ 获取用户信息 }
     * param：[username]
     * return：org.springframework.security.core.userdetails.UserDetails
     * 创建人：@author Echo
     * 创建时间：2023/10/28 15:41
     * version：1.0
     */
    @Override
    public UserDetails loadUserByUsername(String userName) {
        // 获取用户信息
        UmsUser admin = getUserInfoByUserName(userName);

        if (admin != null) {
            // 获取该用户的资源信息
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin, resourceList);
        }

        throw new UsernameNotFoundException("用户名或密码错误");
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：getUserInfoByUserId
     * 方法描述：{ 获取指定用户信息 }
     * param：[userId]
     * return：com.echo.config.api.Result<com.echo.modules.ums.model.UmsUser>
     * 创建人：@author Echo
     * 创建时间：2023/12/27 15:17
     * version：1.0
     */
    @Override
    public Result<UmsUser> getUserInfoByUserId(Long userId) {
        UmsUser umsUser = getOne(new LambdaQueryWrapper<UmsUser>()
                .eq(UmsUser::getId, userId)
        );

        if (ObjectUtil.isEmpty(umsUser)) {
            return Result.failed(THE_USER_IS_NOT_EXIST);
        }
        if (umsUser.getStatus().equals(ZERO)) {
            return Result.failed(THE_USER_IS_NOT_EXIST);
        }

        return Result.success(umsUser);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：getResourceList
     * 方法描述：{ 获取指定用户的可访问资源 }
     * param：[adminId]
     * return：java.util.List<com.echo.modules.ums.model.UmsResource>
     * 创建人：@author Echo
     * 创建时间：2023/10/28 15:41
     * version：1.0
     */
    @Override
    public List<UmsResource> getResourceList(Long adminId) {
        List<UmsResource> resourceList = getCacheService().getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }
        resourceList = resourceMapper.getResourceList(adminId);
        if (CollUtil.isNotEmpty(resourceList)) {
            getCacheService().setResourceList(adminId, resourceList);
        }
        return resourceList;
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：getUserInfoByUserName
     * 方法描述：{ 根据用户名获取用户信息 }
     * param：[userName]
     * return：com.echo.modules.ums.model.UmsUser
     * 创建人：@author Echo
     * 创建时间：2023/10/28 15:41
     * version：1.0
     */
    @Override
    public UmsUser getUserInfoByUserName(String userName) {
        UmsUser admin = getCacheService().getAdmin(userName);
        if (admin != null) {
            return admin;
        }
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsUser::getUsername, userName);
        List<UmsUser> adminList = list(wrapper);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            getCacheService().setAdmin(admin);
            return admin;
        }
        return null;
    }


    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：getRoleListByUserId
     * 方法描述：{ 根据用户获取角色 }
     * param：[userId]
     * return：java.util.List<com.echo.modules.ums.model.UmsRole>
     * 创建人：@author Echo
     * 创建时间：2023/10/28 15:43
     * version：1.0
     */

    @Override
    public List<UmsRole> getRoleListByUserId(Long userId) {
        return roleMapper.getRoleListByUserId(userId);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：getPageUserListByKeyword
     * 方法描述：{ 根据用户名或昵称分页查询用户 }
     * param：[keyword, pageSize, pageNum]
     * return：com.echo.config.api.Result<com.baomidou.mybatisplus.extension.plugins.pagination.Page<com.echo.modules.ums.model.UmsUser>>
     * 创建人：@author Echo
     * 创建时间：2023/10/28 15:43
     * version：1.0
     */
    @Override
    public Result<Page<UmsUser>> getPageUserListByUserName(String userName, Integer pageSize, Integer pageNum) {
        Page<UmsUser> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<UmsUser> umsUserLambdaQueryWrapper = new LambdaQueryWrapper<>();
        umsUserLambdaQueryWrapper.eq(UmsUser::getStatus, ONE);
        if (StrUtil.isNotEmpty(userName)) {
            umsUserLambdaQueryWrapper.like(UmsUser::getUsername, userName);
            umsUserLambdaQueryWrapper.or().like(UmsUser::getNickName, userName);
        }
        return Result.success(page(page, umsUserLambdaQueryWrapper));
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：updateUserInfoByUserId
     * 方法描述：{ 修改指定用户信息 }
     * param：[userId, userInfo]
     * return：com.echo.config.api.Result
     * 创建人：@author Echo
     * 创建时间：2023/10/28 15:43
     * version：1.0
     */
    @Override
    public Result updateUserInfo(UpdateUserInfoByUserIdReqDTO reqDTO) {
        Long userId = reqDTO.getId();


        UmsUser rawUser = getById(userId);
        if (ObjectUtil.isEmpty(rawUser)) {
            return Result.failed(THE_USER_QUERY_FAILED);
        }

        // 用户名校验
        List<UmsUser> umsUserList = list(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getUsername, reqDTO.getUsername()));
        if (umsUserList.size() >= ONE) {
            return Result.failed(THE_USERNAME_HAS_EXISTED);
        }

        // 密码修改
        if (StrUtil.isNotEmpty(reqDTO.getPassword())) {
            reqDTO.setPassword(passwordEncoder.encode(reqDTO.getPassword()));
        }

        UmsUser umsUser = new UmsUser();
        BeanUtil.copyProperties(reqDTO, umsUser);

        if (updateById(umsUser)) {
            getCacheService().delAdmin(userId);
            return Result.success();
        }
        return Result.failed();
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：updateUserPassword
     * 方法描述：{ 修改用户密码 }
     * param：[updateUserPasswordReqDTO]
     * return：com.echo.config.api.Result
     * 创建人：@author Echo
     * 创建时间：2023/10/28 15:44
     * version：1.0
     */
    @Override
    public Result updateUserPassword(UpdateUserPasswordReqDTO updateUserPasswordReqDTO) {
        List<UmsUser> umsUserList = list(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getUsername, updateUserPasswordReqDTO.getUsername()));
        if (CollUtil.isEmpty(umsUserList) || umsUserList.size() > ONE) {
            return Result.failed(THE_USER_IS_NOT_EXIST);
        }
        UmsUser umsUser = umsUserList.get(0);
        if (!passwordEncoder.matches(updateUserPasswordReqDTO.getOldPassword(), umsUser.getPassword())) {
            return Result.failed(THE_OLD_PASSWORD_IS_WRONG);
        }
        umsUser.setPassword(passwordEncoder.encode(updateUserPasswordReqDTO.getNewPassword()));
        if (updateById(umsUser)) {
            getCacheService().delAdmin(umsUser.getId());
            return Result.success();
        }
        return Result.failed(THE_PASSWORD_UPDATE_FAILED);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：delUserByUserId
     * 方法描述：{ 删除指定用户信息 }
     * param：[userId]
     * return：com.echo.config.api.Result
     * 创建人：@author Echo
     * 创建时间：2023/10/28 15:44
     * version：1.0
     */
    @Override
    public Result delUserByUserId(Long userId) {
        UmsUser umsUser = getOne(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getId, userId).eq(UmsUser::getStatus, ONE));
        if (ObjectUtil.isEmpty(umsUser)) {
            return Result.failed(THE_USER_QUERY_FAILED);
        }
        umsUser.setStatus(ZERO);
        if (updateById(umsUser)) {
            getCacheService().delResourceList(userId);
            getCacheService().delAdmin(userId);
            return Result.success();
        }
        return Result.failed(THE_USER_DELETE_FAILED);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：allowUserRole
     * 方法描述：{ 修改用户角色关系 }
     * param：[userId, roleIds]
     * return：com.echo.config.api.Result
     * 创建人：@author Echo
     * 创建时间：2023/10/28 15:44
     * version：1.0
     */
    @Override
    public Result allowUserRole(AllowUserRoleReqDTO allowUserRoleReqDTO) {
        Long userId = allowUserRoleReqDTO.getUserId();
        List<Long> roleIds = allowUserRoleReqDTO.getRoleIds();

        // 先删除原来的关系
        QueryWrapper<UmsUserRoleRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsUserRoleRelation::getUserId, userId);
        userRoleRelationService.remove(wrapper);

        // 建立新关系
        List<UmsUserRoleRelation> umsUserRoleRelations = new ArrayList<>();
        for (Long roleId : roleIds) {
            UmsUserRoleRelation roleRelation = new UmsUserRoleRelation();
            roleRelation.setUserId(userId);
            roleRelation.setRoleId(roleId);
            umsUserRoleRelations.add(roleRelation);
        }

        userRoleRelationService.saveBatch(umsUserRoleRelations);

        getCacheService().delResourceList(userId);

        return Result.success();
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：getCacheService
     * 方法描述：{ 获取缓存服务 }
     * param：[]
     * return：com.echo.modules.ums.service.UmsUserCacheService
     * 创建人：@author Echo
     * 创建时间：2023/10/28 15:42
     * version：1.0
     */
    @Override
    public UmsUserCacheService getCacheService() {
        return SpringUtil.getBean(UmsUserCacheService.class);
    }

}
