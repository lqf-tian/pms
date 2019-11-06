package com.ujiuye.pro.controller;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.bean.Project;
import com.ujiuye.pro.service.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;

@Controller
@RequestMapping("/att")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;
    //文件上传
    @RequestMapping(value = "/info",method = RequestMethod.POST)
    public String saveInfo(Attachment attachment, MultipartFile attchmentfile, HttpSession session){
        ServletContext context = session.getServletContext();
        String realPath = context.getRealPath("/upload");
        File file=new File(realPath);
        if (!file.exists()){
            file.mkdirs();
        }
        String originalFilename = attchmentfile.getOriginalFilename();
        String realName = UUID.randomUUID().toString().replaceAll("-", "")+originalFilename;
        try{
        //有三种方法
        attchmentfile.transferTo(new File(realPath+"/"+realName));
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        attachment.setPath(realPath+"/"+realName);
        attachmentService.saveInfo(attachment);
        return "redirect:/project-file.jsp";
    }
}
