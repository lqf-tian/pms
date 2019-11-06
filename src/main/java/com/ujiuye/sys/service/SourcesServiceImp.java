package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Sources;
import com.ujiuye.sys.bean.SourcesExample;
import com.ujiuye.sys.mapper.SourcesMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SourcesServiceImp implements SourcesService{

    @Resource
    private SourcesMapper sourcesMapper;

    @Override
    public List<Sources> getSourcesByPid(int i) {
        SourcesExample example=new SourcesExample();
        SourcesExample.Criteria criteria = example.createCriteria();
        criteria.andPidEqualTo(i);
        List<Sources> sources = sourcesMapper.selectByExample(example);
        return sources;
    }

    @Override
    public void saveInfo(Sources sources) {
        sourcesMapper.insert(sources);

    }

    @Override
    public Sources getSourcesInfo(Integer sid) {
        return sourcesMapper.selectByPrimaryKey(sid);
    }

    @Override
    public void updateInfo(Sources sources) {
        sourcesMapper.updateByPrimaryKeySelective(sources);
    }

    @Override
    public void delete(Integer id) {
        sourcesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Sources> getSourcesByEid(Integer eid) {
        List<Sources> sources=sourcesMapper.getSourcesListByEid(eid,1);

        for (Sources source : sources) {
            Integer id =source.getId();
            List<Sources> list = sourcesMapper.getSourcesListByEid(eid, id);
            source.setChildren(list);
        }
        return sources;
    }
}
