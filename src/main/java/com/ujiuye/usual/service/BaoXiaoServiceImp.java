package com.ujiuye.usual.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ujiuye.usual.bean.BaoXiao;
import com.ujiuye.usual.bean.BaoXiaoExample;
import com.ujiuye.usual.mapper.BaoXiaoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class BaoXiaoServiceImp implements BaoXiaoService{

    @Resource
    private BaoXiaoMapper baoXiaoMapper;

    @Override
    public void saveInfo(BaoXiao baoXiao) {
        String bxid = UUID.randomUUID().toString().replaceAll("-", "");
        baoXiao.setBxid(bxid);
        baoXiao.setBxstatus(0);
        baoXiaoMapper.insert(baoXiao);
    }

    @Override
    public PageInfo<BaoXiao> getMyBaoXiaoList(Integer eid, Integer pageNum, Map<String, Object> map) {
        BaoXiaoExample example = new BaoXiaoExample();
        BaoXiaoExample.Criteria criteria = example.createCriteria();
        criteria.andEmpFkEqualTo(eid);

        Map<String,String> mybatisMap=mapToMyBatisMap(map);
        String status = mybatisMap.get("status");
        String keyword = mybatisMap.get("keyword");
        if (status != null && status != ""){
            criteria.andBxstatusEqualTo(Integer.parseInt(status));
        }
       if (keyword != null && keyword != ""){
           criteria.andBxremarkLike(keyword);
       }
        PageHelper.startPage(pageNum,3);
        List<BaoXiao> baoXiaos=baoXiaoMapper.selectByExample(example);
        PageInfo<BaoXiao> page=new PageInfo<BaoXiao>(baoXiaos,5);


        return page;
    }

    private Map<String, String> mapToMyBatisMap(Map<String, Object> map) {

        Map<String,String> resultMap =new HashMap<String, String>();
        Set<Map.Entry<String,Object>> entries= map.entrySet();
        for (Map.Entry<String,Object> entry :entries){
            String key =entry.getKey();
            String value=(String)entry.getValue();
            if (key.contains("like")) {
                key=key.substring(key.indexOf("_")+1);
                value="%"+value+"%";
            }
            resultMap.put(key,value);
        }
        return resultMap;
    }
}
