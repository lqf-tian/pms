package com.ujiuye.pro.service;

import com.ujiuye.pro.bean.Attachment;
import com.ujiuye.pro.mapper.AttachmentMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AttachmentServiceImp implements AttachmentService{

    @Resource
    private AttachmentMapper attachmentMapper;
    @Override
    public void saveInfo(Attachment attachment) {
        attachmentMapper.insert(attachment);
    }
}

