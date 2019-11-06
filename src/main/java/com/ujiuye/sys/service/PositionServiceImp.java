package com.ujiuye.sys.service;

import com.ujiuye.sys.bean.Position;
import com.ujiuye.sys.bean.PositionExample;
import com.ujiuye.sys.mapper.PositionMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PositionServiceImp implements PositionService{

    @Resource
    private PositionMapper positionMapper;

    @Override
    public List<Position> getPositionList() {

        PositionExample example = new PositionExample();
        List<Position> positions = positionMapper.selectByExample(example);
        return positions;
    }
}
