package com.xiaoxin.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaoxin.entity.SysUserEntity;
import com.xiaoxin.mapper.SysUserMapper;
import com.xiaoxin.req.SysUserSaveReq;
import com.xiaoxin.resp.SysUserLoginResp;
import com.xiaoxin.service.SysUserService;
import com.xiaoxin.utils.CopyUtil;
import com.xiaoxin.utils.SnowFlake;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import com.xiaoxin.req.*;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity> implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Autowired
    private SnowFlake snowFlake;


    @Override
    public void register(SysUserSaveReq req) {
        SysUserEntity user = CopyUtil.copy(req, SysUserEntity.class);
        if(ObjectUtils.isEmpty(req.getId())){
            SysUserEntity userDb = selectByLoginName(req.getName());
            if(ObjectUtils.isEmpty(userDb)){
                user.setId((int) snowFlake.nextId());
                sysUserMapper.insert(user);
            }
        }
    }

    @Override
    public SysUserLoginResp login(SysUserLoginReq req) {
        SysUserEntity userDb = selectByLoginName(req.getLoginName());
        if(ObjectUtils.isEmpty(userDb)){
            //用户不存在
            return null;
        }else {
            //登陆成功
            SysUserLoginResp userLoginResp = CopyUtil.copy(userDb, SysUserLoginResp.class);
            return userLoginResp;
        }
    }

    //查询loginName是否被注册
    public SysUserEntity selectByLoginName(String name){
        QueryWrapper<SysUserEntity> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(SysUserEntity::getName,name);
        List<SysUserEntity> userEntityList = sysUserMapper.selectList(wrapper);
        if(CollectionUtils.isEmpty(userEntityList)){
            return  null;
        }else {
            return userEntityList.get(0);
        }
    }
}
