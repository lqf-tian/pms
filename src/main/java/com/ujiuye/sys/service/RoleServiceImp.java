package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Position;
import com.ujiuye.sys.bean.Role;
import com.ujiuye.sys.bean.RoleExample;
import com.ujiuye.sys.mapper.RoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImp implements RoleService{

    @Resource
    private RoleMapper roleMapper;

    @Override
    public int saveInfo(Role role) {
        roleMapper.saveInfo(role);
        return role.getRoleid();
    }

    @Override
    public List<Role> getRoleList() {
        RoleExample example= new RoleExample();
        List<Role> roles = roleMapper.selectByExample(example);
        return roles;
    }
}
