package com.ujiuye.sys.controller;

import com.ujiuye.sys.bean.Employee;
import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.service.EmpRoleServic;
import com.ujiuye.sys.service.EmployeeService;
import com.ujiuye.sys.service.SourcesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private SourcesService sourcesService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmpRoleServic empRoleServic;

    @RequestMapping(value = "/saveInfo",method = RequestMethod.POST)
    public String saveInfo(Employee employee,String[] roleids){
        int empid=employeeService.saveInfo(employee);
        empRoleServic.inerst(empid,roleids);
        return "redirect:/user.jsp";
    }

    @ResponseBody
    @RequestMapping(value = "/managers",method = RequestMethod.GET)
    public List<Employee> getManagers(){
        return employeeService.getManagers();

    }

    //退出当前用户
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/login.jsp";
    }

    /**
     * 如果重定向时想携带数据到页面
     * 1.在目标方法参数上使用RedirectAttributes类型
     * 2.不能直接重定向到页面，必须经过springmvc的映射
     */

    //登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(Employee employee, String code, HttpSession session, RedirectAttributes attributes){
        String validateCode =(String) session.getAttribute("validateCode");
        if (code.equalsIgnoreCase(validateCode)){
           employee= employeeService.login(employee);
           session.removeAttribute("validateCode");
           if (employee != null){

               Integer eid = employee.getEid();
               List<Sources> list=sourcesService.getSourcesByEid(eid);
                session.setAttribute("sourcesList",list);

               session.setAttribute("loginUser",employee);
               return "redirect:/index.jsp";
           }else {
               attributes.addFlashAttribute("errorMsg","用户名或密码错误");
               return "redirect:/login";
           }
        }
        attributes.addFlashAttribute("errorMsg","验证码错误");

        return "redirect:/login";
    }

    //自动填写验证码（要把验证码返回）
    @RequestMapping(value = "/yanz",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> yanZheng(HttpSession session){
        Map<String,String> map=new HashMap<String, String>();
        String validateCode = (String)session.getAttribute("validateCode");
        map.put("validateCode",validateCode);
        return map;
    }
}
