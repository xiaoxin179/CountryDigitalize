package com.xiaoxin.controller;
import com.xiaoxin.req.SysUserLoginReq;
import com.xiaoxin.req.SysUserSaveReq;
import com.xiaoxin.resp.CommonResp;
import com.xiaoxin.resp.SysUserLoginResp;
import com.xiaoxin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sys-user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @PostMapping("/register")
    public CommonResp register(@RequestBody SysUserSaveReq req){
       req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        sysUserService.register(req);
        return resp;
    }

    @PostMapping("/login")
    public CommonResp login(@RequestBody SysUserLoginReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        SysUserLoginResp loginResp = sysUserService.login(req);
        resp.setContent(loginResp);
        return resp;
    }

}
