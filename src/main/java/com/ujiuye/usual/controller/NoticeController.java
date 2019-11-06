package com.ujiuye.usual.controller;


import com.ujiuye.common.ResultEntity;
import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("notice")
public class NoticeController {

    @Autowired
    private NoticeService noticeService;

    @ResponseBody
    @RequestMapping(value = "/latestNoticeList",method = RequestMethod.GET)
    public ResultEntity getLatestNoticeList(){
        List<Notice> notices=noticeService.getLatestNoticeList();
        return ResultEntity.success().put("notices",notices);
    }

    @ResponseBody
    @RequestMapping("/info/{id}")
    public Notice getNoticeInfo(@PathVariable("id") Integer nid){
        return  noticeService.getNoticeInfo(nid);
    }
}
