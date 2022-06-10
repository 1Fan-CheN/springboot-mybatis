package com.example.service.impl;

import com.example.dao.BaseProductDao;
import com.example.entity.sqldo.BaseProductDo;
import com.example.entity.vo.BaseProductInfoVo;
import com.example.entity.vo.BaseProductResp;
import com.example.entity.vo.BaseProductVo;
import com.example.service.ProductBaseService;
import com.example.utils.FormatUtil;
import com.example.utils.TimeUtil;
import com.example.utils.PermissionUtil;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProductBaseServiceImpl implements ProductBaseService {

    @Resource
    private BaseProductDao baseProductDao;

    public boolean checkProductPermission(BaseProductVo baseProductVo) {
        String operator = PermissionUtil.getOperatorBySessionID(baseProductVo.getSessionId());
        return PermissionUtil.checkOperatorPermission(operator, baseProductVo.getId());
    }

    private boolean productNameRepeat(String name) {
        return baseProductDao.countName(name) != 0;
    }

    private boolean hasUndeletedModules(int productId) {
        return baseProductDao.countUndeletedModule(productId) != 0;
    }

    @Override
    public BaseProductResp getIdList(BaseProductInfoVo baseProductInfoVo) {
        try {
            List<Object> idList = baseProductDao.getIdList(baseProductInfoVo.getName(), baseProductInfoVo.getStatus());
            return new BaseProductResp().success(idList);
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "get show product id failed", e.toString());
        }
    }

    @Override
    public BaseProductResp getProductInfoById(BaseProductInfoVo baseProductInfoVo) {
        try {
            List<Map<String, Object>> productInfoList = baseProductDao.getProductInfoById(baseProductInfoVo.getId());
            // 将原map中的id抽离，作为剩下map的key
            List<Map<String, Map<String, Object>>> formattedList = FormatUtil.formatInfoById(productInfoList);
            return new BaseProductResp().success(formattedList);
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "get show product id failed", e.toString());
        }
    }

    @Override
    public BaseProductResp createProduct(BaseProductVo baseProductVo) {
        // 重名校验
        if (productNameRepeat(baseProductVo.getName())) {
            return new BaseProductResp().fail(3, "product name is not unique", null);
        }

        BaseProductDo baseProductDo = new BaseProductDo();
        baseProductDo.setName(baseProductVo.getName());
        baseProductDo.setDescription(baseProductVo.getDesc());

        baseProductDo.setCreateTime(TimeUtil.getNowTs());
        baseProductDo.setUpdateTime(TimeUtil.getNowTs());
        baseProductDo.setStatus(4); // 默认待审核

        // 获取创建人
        String sessionID = baseProductVo.getSessionId();
        String operator = PermissionUtil.getOperatorBySessionID(sessionID);

        baseProductDo.setCreator(operator);
        baseProductDo.setOwner(Collections.singletonList(operator)); // Owner 默认为创建人，admin 为空

        try {
            if (baseProductDao.createProduct(baseProductDo) == 1) {
                return new BaseProductResp().success(null);
            } else {
                throw new Exception("failed insert data to database");
            }
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "db insert error", e.toString());
        }
    }

    @Override
    public BaseProductResp updateProduct(BaseProductVo baseProductVo) {
        BaseProductDo baseProductDo = new BaseProductDo();

        // field-strategy 默认为 not_null 判断，即只更新和插入非NULL值（不包括非空值）
        baseProductDo.setUpdateTime(new Date());
        baseProductDo.setId(baseProductVo.getId());
        baseProductDo.setName(baseProductVo.getName());
        baseProductDo.setDescription(baseProductVo.getDesc());
        baseProductDo.setStatus(baseProductVo.getStatus());
        baseProductDo.setOwner(baseProductVo.getOwner());
        baseProductDo.setAdministrator(baseProductVo.getAdmin());

        try {
            if (baseProductDao.updateProduct(baseProductDo) == 1) {
                return new BaseProductResp().success(null);
            } else {
                throw new Exception("failed update data in database");
            }
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "db update error", e.toString());
        }
    }

}
