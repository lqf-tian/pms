package com.ujiuye.sys.mapper;

import org.apache.ibatis.annotations.Param;

public interface RoleSourecsMapper {
    void insert(@Param("roleid")int roleid,@Param("sid") int parseInt);
}
