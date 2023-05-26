package com.sangang.authorization.web.service.impl;

import com.sangang.authorization.web.entity.SysUser;
import com.sangang.authorization.web.mapper.SysUserDynamicSqlSupport;
import com.sangang.authorization.web.mapper.SysUserMapper;
import com.sangang.authorization.web.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author zxg
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<SysUser> user = sysUserMapper.selectOne(s -> s.where(SysUserDynamicSqlSupport.userName, isEqualTo(username)));
        if(!user.isPresent()){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        return new User(username, user.get().getPassword(), new ArrayList<>());
    }
}
