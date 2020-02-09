package com.cjr.securitytest.web.security.dal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjr.securitytest.web.security.dal.dao.UserMapper;
import com.cjr.securitytest.web.security.dal.entity.Authority;
import com.cjr.securitytest.web.security.dal.entity.User;
import com.cjr.securitytest.web.security.dal.service.UserService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author cjr
 * @since 2019-09-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public Set<Authority> findAuthority(Long userId) {
        // 需要根据我们系统的业务去查用户的所有权限
        Set<Authority> authorities = new HashSet<>();
        authorities.add(new Authority("ROLE_USER"));        // 使用 auth2 用户必须有一个 ROLE_USER 角色，其他的权限根据 userId 去获取
        return authorities;
    }

    @Override
    public User getByUsername(String username) {
        return getOne(new QueryWrapper<User>().eq("username", username));
    }

    @Override
    public User getByPhone(String phone) {
        return getOne(new QueryWrapper<User>().eq("phone", phone));
    }

}
