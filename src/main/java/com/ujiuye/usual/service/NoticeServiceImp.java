package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Notice;
import com.ujiuye.usual.bean.NoticeExample;
import com.ujiuye.usual.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeServiceImp implements NoticeService{

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> getLatestNoticeList() {
        NoticeExample example = new NoticeExample();
        example.setOrderByClause("ndate desc limit 3");

        List<Notice> notices = noticeMapper.selectByExample(example);
        return notices;
    }

    @Override
    public Notice getNoticeInfo(Integer nid) {
        Notice notice = noticeMapper.selectByPrimaryKey(nid);
        return notice;
    }
}
