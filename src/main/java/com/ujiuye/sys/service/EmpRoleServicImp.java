package com.ujiuye.sys.service;

import com.ujiuye.sys.mapper.EmpRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class EmpRoleServicImp  implements EmpRoleServic{
    @Resource
    private EmpRoleMapper empRoleMapper;

    @Override
    public void inerst(int empid, String[] roleids) {
        for (String roleid : roleids) {
            empRoleMapper.insert(empid,Integer.parseInt(roleid));
        }

    }
}
