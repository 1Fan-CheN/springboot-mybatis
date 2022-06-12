package com.example.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class ProductMultiIdVo extends BaseProductVo {
    private List<Integer> id;
}
