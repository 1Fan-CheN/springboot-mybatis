package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.sqldo.BaseProductDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BaseProductMapper extends BaseMapper<BaseProductDo> {
}
