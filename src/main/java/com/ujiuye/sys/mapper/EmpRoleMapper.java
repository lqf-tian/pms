package com.ujiuye.sys.mapper;

import org.apache.ibatis.annotations.Param;

public interface EmpRoleMapper {
    void insert(@Param("eid") int empid,@Param("rid") int parseInt);
}
