package com.example.service.impl;

import com.example.entity.sqldo.BaseProductDo;
import com.example.entity.vo.BaseProductVo;
import com.example.mapper.BaseProductMapper;
import com.example.service.ProductBaseService;
import com.example.utils.UserUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class ProductBaseServiceImpl implements ProductBaseService {

    @Resource
    private BaseProductMapper baseProductMapper;

    @Override
    public List<Integer> getIdList(String name, int status, String owner){
        return baseProductMapper.getIdList(name, status, owner);
    }


    @Override
    public String createProduct(BaseProductVo baseProductVo) {

        BaseProductDo baseProductDo = new BaseProductDo();
        baseProductDo.setName(baseProductVo.getName());
        baseProductDo.setDesc(baseProductVo.getDesc());
        baseProductDo.setCreateTime(new Date());
        baseProductDo.setUpdateTime(new Date());
        baseProductDo.setStatus(4);

        String sessionID = baseProductVo.getSessionId();
        String operator = UserUtil.getOperatorBySessionID(sessionID);

        baseProductDo.setCreator(operator);
        baseProductDo.setOwner(operator);
        baseProductDo.setAdmin(null);

        return String.valueOf(baseProductMapper.createProduct(baseProductDo));
    }

    @Override
    public String updateProduct(BaseProductVo baseProductVo){
        BaseProductDo baseProductDo = new BaseProductDo();

        baseProductDo.setUpdateTime(new Date());
        baseProductDo.setName(baseProductVo.getName());
        baseProductDo.setDesc(baseProductVo.getDesc());
        baseProductDo.setStatus(baseProductVo.getStatus());
        baseProductDo.setOwner(baseProductVo.getOwner());
        baseProductDo.setAdmin(null);

        return "asa";
    }

    @Override
    public boolean productNameRepeat(String name) {
        return baseProductMapper.countName(name) != 0;
    }

    @Override
    public boolean hasUndeletedModules(int productId) {
        return baseProductMapper.countUndeletedModule(productId) != 0;
    }
}
