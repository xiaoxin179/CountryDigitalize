package com.xiaoxin.service;
import com.xiaoxin.req.SysUserLoginReq;
import com.xiaoxin.req.SysUserSaveReq;
import com.xiaoxin.resp.SysUserLoginResp;

public interface SysUserService {
    void register(SysUserSaveReq req);

    SysUserLoginResp login(SysUserLoginReq req);
}
