package com.ujiuye.pro.controller;


import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.service.ProjecetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pro")
public class ProjectController {

    @Autowired
    private ProjecetService projecetService;

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST )
    public String saveInfo(Project project){
        projecetService.saveInfo(project);
        return "redirect:/pro/list";
    }

    //查询
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView getProjectList(){
        List<Project> list=projecetService.getProjectList();
        ModelAndView mv=new ModelAndView("project-base");
        mv.addObject("list",list);
        return mv;
    }
        //详情页面
    @RequestMapping(value = "/detail/{pid}",method = RequestMethod.GET)
    public String detail(@PathVariable("pid") Integer pid, Map<String,Object> map){
        Project project=projecetService.getProjectDetail(pid);
        map.put("project",project);
        return "project-base-look";
    }

    //删除
    @ResponseBody
    @RequestMapping(value = "/del",method = RequestMethod.DELETE)
    public Map<String,Object> batchDelete(@RequestParam("ids[]") Integer[] ids){
        boolean result = projecetService.batchDelete(ids);
        Map<String, Object> map = new HashMap<>();
        if (result){
            map.put("statusCode",200);
            map.put("message","删除成功");
        }else {
            map.put("statusCode",200);
            map.put("message","删除成功");
        }
        return map;
    }

    //编辑的数据回填
    @RequestMapping(value = "/edit/{pid}",method = RequestMethod.GET)
    public String edit(@PathVariable("pid") Integer pid, Map<String,Object> map){
        Project project=projecetService.getCustomerDetail(pid);
        map.put("project",project);
        return "project-base-edit";
    }

    //修改
    @RequestMapping(value = "/update",method = RequestMethod.PUT)
    public String update(Project project){
        projecetService.updateProject(project);
        return "redirect:/pro/list";
    }

    @ResponseBody
    @RequestMapping(value = "/jsonList",method = RequestMethod.GET)
    public List<Project> projecetJsonList(){
        return projecetService.projecetJsonList();
    }
    @ResponseBody
    @RequestMapping(value = "/withAnalyseJsonList",method = RequestMethod.GET)
    public List<Project> projecetWithAnalyseJsonList(){
       return projecetService.projecetWithAnalyseJsonList();
    }

}
