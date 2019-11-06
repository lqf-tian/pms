package com.ujiuye.cust.controller;

import com.ujiuye.cust.bean.Customer;
import com.ujiuye.cust.service.CustomerService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/cust")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/importExcel",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> importExcel(MultipartFile excel){
        Map<String,Object> map = new HashMap<String, Object>();
        List<Customer> customers = new ArrayList<Customer>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try{
            Workbook wb = WorkbookFactory.create(excel.getInputStream());
            for(int k=0;k<wb.getNumberOfSheets();k++){
                Sheet sheet = wb.getSheetAt(k);
                for (int i=sheet.getFirstRowNum()+1; i<=sheet.getLastRowNum();i++){
                    Row row = sheet.getRow(i);
                    Customer customer = new Customer();
                    if(row != null){

                        String linkMan = row.getCell(1).getStringCellValue();
                        customer.setCompanyperson(linkMan);
                        String companyName = row.getCell(2).getStringCellValue();
                        customer.setComname(companyName);
                        Date dateCellValue = row.getCell(3).getDateCellValue();
                        String format = sdf.format(dateCellValue);
                        Date addTime = sdf.parse(format);
                        customer.setAddtime(addTime);
                        double numericCellValue = row.getCell(4).getNumericCellValue();
                        BigDecimal decimal = new BigDecimal(String.valueOf(numericCellValue));
                        customer.setComphone(decimal.toPlainString());

                        System.out.println();
                    }
                    customers.add(customer);
                }
            }
            System.out.println(customers);
            /*customerService.batchInsert(customers);*/
            map.put("statusCode",200);
            map.put("message","上传成功");
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("出异常了");
            map.put("statusCode",500);
            map.put("message","上传失败");
        }


        return map;
    }

    //导出Excel表
    @ResponseBody
    @RequestMapping(value = "/exportExcel", method = RequestMethod.GET)
    public Map<String, Object> exportExcel() {
        Workbook wb = new HSSFWorkbook();
        Sheet sheet1 = wb.createSheet("customer");
        Row row = sheet1.createRow(0);
        Cell[] cell = new HSSFCell[5];
        for (int i = 0; i < 5; i++) {
            cell[i] = row.createCell(i);
        }
        cell[0].setCellValue("ID");
        cell[1].setCellValue("联系人");
        cell[2].setCellValue("公司名称");
        cell[3].setCellValue("添加时间");
        cell[4].setCellValue("联系电话");

        //将数据放入列表
        List<Customer> customerList = customerService.getCustomerList();

        //遍历数据
        for (int i = 0; i < customerList.size(); i++) {
            Customer customer = customerList.get(i);
            Row row1 = sheet1.createRow(i + 1);
            Cell[] cell1 = new HSSFCell[5];
            for (int j = 0; j < 5; j++) {
                cell1[j] = row1.createCell(j);
            }
            cell1[0].setCellValue(customer.getId());
            cell1[1].setCellValue(customer.getCompanyperson());
            cell1[2].setCellValue(customer.getComname());
            cell1[3].setCellValue(customer.getAddtime());
            cell1[4].setCellValue(customer.getComphone());
        }

        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(new File("d:\\Desktop\\customers1.xlsx"));


            wb.write(fos);
            fos.close();
        } catch (Exception ex) {

        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 200);
        map.put("message", "下载成功");
        return map;

    }

    @RequestMapping(value = "/info/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Customer info(@PathVariable("id") Integer cid) {
        Customer customer = customerService.getCustomerDetail(cid);
        return customer;
    }

    @RequestMapping(value = "/jsonList", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getCustomerJsonList() {
        return customerService.getCustomerList();
    }

    //条件查询
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView search(Integer cid, String keyword, Integer orderby) {
        List<Customer> list = customerService.search(cid, keyword, orderby);
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("list", list);
        return mv;
    }

    //批量删除
    @RequestMapping(value = "/del", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> batchDelete(@RequestParam("ids[]") Integer[] ids) {
        boolean result = customerService.batchDelete(ids);
        Map<String, Object> map = new HashMap<String, Object>();
        if (result) {
            map.put("statusCode", 200);
            map.put("message", "删除成功");
        } else {
            map.put("statusCode", 500);
            map.put("message", "删除失败");
        }

        return map;
    }

    //修改
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public String update(Customer customer) {
        customerService.updateCustomer(customer);
        return "redirect:/cust/list";
    }

    //编辑的数据回填
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") Integer cid, Map<String, Object> map) {
        Customer customer = customerService.getCustomerDetail(cid);
        map.put("customer", customer);
        return "customer-edit";
    }

    //查看详情
    @RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable("id") Integer cid, Map<String, Object> map) {
        Customer customer = customerService.getCustomerDetail(cid);
        map.put("customer", customer);
        return "customer-look";
    }

    //查询
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getCustomerList() {
        List<Customer> list = customerService.getCustomerList();
        ModelAndView mv = new ModelAndView("customer");
        mv.addObject("list", list);
        return mv;
    }

    //添加
    @RequestMapping(value = "/saveInfo", method = RequestMethod.POST)
    public String saveInfo(Customer customer) {
        customerService.saveInfo(customer);
        return "redirect:/cust/list";
    }
}
