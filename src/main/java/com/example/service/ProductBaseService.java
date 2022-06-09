package com.example.service;

import com.example.entity.vo.BaseProductVo;

import java.util.List;

public interface ProductBaseService {

    List<Integer> getIdList(String name, int status, String owner);

    String createProduct(BaseProductVo baseProductVo);

    String updateProduct(BaseProductVo baseProductVo);

    boolean productNameRepeat(String name);

    boolean hasUndeletedModules(int productId);

}
