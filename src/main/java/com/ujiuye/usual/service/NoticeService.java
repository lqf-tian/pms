package com.ujiuye.usual.service;

import com.ujiuye.usual.bean.Notice;

import java.util.List;

public interface NoticeService {
    List<Notice> getLatestNoticeList();

    Notice getNoticeInfo(Integer nid);
}
