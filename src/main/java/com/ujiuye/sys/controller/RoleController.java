package com.ujiuye.sys.controller;


import com.ujiuye.common.ResultEntity;
import com.ujiuye.sys.bean.Position;
import com.ujiuye.sys.bean.Role;
import com.ujiuye.sys.service.RoleService;
import com.ujiuye.sys.service.RoleSourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private RoleSourcesService roleSourcesService;

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    @ResponseBody
    public ResultEntity saveInfo(Role role, String ids){

        //1.想角色表中添加数据，而且必须返回该角色在数据表中的id
        int roleid=roleService.saveInfo(role);
        //2.将角色和权限的对应关系添加到中间表中
        roleSourcesService.saveInfo(roleid,ids);


        return ResultEntity.success();
    }
    @ResponseBody
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    public List<Role> getRoleList(){
        return roleService.getRoleList();
    }

}
