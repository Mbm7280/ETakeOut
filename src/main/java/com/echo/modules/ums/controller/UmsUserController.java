package com.echo.modules.ums.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.config.api.Result;
import com.echo.modules.bus.dto.req.AllowUserRoleReqDTO;
import com.echo.modules.bus.dto.req.UpdateUserInfoByUserIdReqDTO;
import com.echo.modules.bus.dto.res.GetUserInfoResDTO;
import com.echo.modules.ums.dto.req.LoginReqDTO;
import com.echo.modules.ums.dto.req.RegisterReqDTO;
import com.echo.modules.ums.dto.req.UpdateUserPasswordReqDTO;
import com.echo.modules.ums.dto.res.LoginResDTO;
import com.echo.modules.ums.dto.res.RefreshTokenResDTO;
import com.echo.modules.ums.model.UmsUser;
import com.echo.modules.ums.service.UmsUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static com.echo.config.api.ResultCode.THE_AUTHORIZED_FAILED;


/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Echo
 * @since 2023-10-21
 */
@RestController()
@RequestMapping("/umsUser")
@Api(tags = "UmsUserController")
@Tag(name = "UmsUserController", description = "用户管理")
public class UmsUserController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsUserService userService;


    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public Result<UmsUser> register(@Validated @RequestBody RegisterReqDTO registerReqDTO) {
        return userService.register(registerReqDTO);
    }

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public Result<LoginResDTO> login(@Validated @RequestBody LoginReqDTO loginReqDTO) {
        return userService.login(loginReqDTO);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping(value = "/getUserInfo")
    public Result<GetUserInfoResDTO> getUserInfo(Principal principal) {
        if (principal == null) {
            return Result.failed(THE_AUTHORIZED_FAILED);
        }
        return userService.getUserInfo(principal.getName());
    }

    @ApiOperation(value = "刷新token")
    @GetMapping(value = "/refreshToken")
    public Result<RefreshTokenResDTO> refreshToken(HttpServletRequest request) {
        return userService.refreshToken(request);
    }

    @ApiOperation(value = "登出功能")
    @PostMapping(value = "/logout")
    public Result logout() {
        return Result.success();
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping(value = "/getPageUserListByUserName")
    public Result<Page<UmsUser>> getPageUserListByUserName(@RequestParam(value = "userName", required = false) String userName,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        return userService.getPageUserListByUserName(userName, pageSize, pageNum);
    }

    @ApiOperation("获取指定用户信息")
    @GetMapping(value = "/getUserInfoByUserId/{userId}")
    public Result<UmsUser> getUserInfoByUserId(@PathVariable Long userId) {
        return userService.getUserInfoByUserId(userId);
    }

    @ApiOperation("修改指定用户信息")
    @PutMapping(value = "/updateUserInfo")
    public Result updateUserInfo(@RequestBody UpdateUserInfoByUserIdReqDTO reqDTO) {
        return userService.updateUserInfo(reqDTO);
    }

    @ApiOperation("修改指定用户密码")
    @PutMapping(value = "/updateUserPassword")
    public Result updateUserPassword(@Validated @RequestBody UpdateUserPasswordReqDTO updateUserPasswordReqDTO) {
        return userService.updateUserPassword(updateUserPasswordReqDTO);
    }

    @ApiOperation("删除指定用户信息")
    @DeleteMapping(value = "/delUserByUserId/{userId}")
    public Result delUserByUserId(@PathVariable Long userId) {
        return userService.delUserByUserId(userId);
    }

    @ApiOperation("给用户分配角色")
    @PostMapping(value = "/allowUserRole")
    public Result allowUserRole(@RequestBody AllowUserRoleReqDTO allowUserRoleReqDTO) {
        return userService.allowUserRole(allowUserRoleReqDTO);
    }

}

