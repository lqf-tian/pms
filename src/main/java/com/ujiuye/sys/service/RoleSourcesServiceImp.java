package com.ujiuye.sys.service;

import com.ujiuye.sys.mapper.RoleSourecsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleSourcesServiceImp implements RoleSourcesService{

    @Resource
    private RoleSourecsMapper roleSourecsMapper;

    public void saveInfo(int roleid, String ids) {
        String[] idArr= ids.split(",");
        for (String s:idArr){
            roleSourecsMapper.insert(roleid,Integer.parseInt(s));
        }
    }
}
