package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.sqldo.BaseModuleDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseModuleMapper extends BaseMapper<BaseModuleDo> {
}
