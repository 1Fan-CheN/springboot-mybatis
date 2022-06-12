package com.example.service.impl;

import com.example.dao.BaseProductDao;
import com.example.entity.sqldo.BaseProductDo;
import com.example.entity.vo.ProductMultiIdVo;
import com.example.entity.vo.ProductOneIdVo;
import com.example.entity.vo.BaseProductResp;
import com.example.service.ProductBaseService;
import com.example.utils.FormatUtil;
import com.example.utils.PermissionUtil;
import com.example.utils.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class ProductBaseServiceImpl implements ProductBaseService {

    @Resource
    private BaseProductDao baseProductDao;

    //TODO jia jianquan
    @Override
    public boolean checkProductPermission(String sessionId, int productId) {
        String operator = PermissionUtil.getOperatorBySessionID(sessionId);
        return baseProductDao.listAdminAndOwner(productId).contains(operator);
    }

    @Override
    public boolean productNameRepeat(String name) {
        return baseProductDao.countName(name) != 0;
    }

    @Override
    public int countUndeletedModules(int productId) {
        return baseProductDao.countUndeletedModule(productId);
    }

    @Override
    public int getProductStatus(int productId) {
        return baseProductDao.getProductStatus(productId);
    }

    public void updateProductStatus(int productId, int status) {
        BaseProductDo baseProductDo = new BaseProductDo();
        baseProductDo.setId(productId);
        baseProductDo.setStatus(status);
        baseProductDao.updateProductStatus(baseProductDo);
    }

    @Override
    public BaseProductResp listIds(ProductMultiIdVo productMultiIdVo) {
        try {
            List<Object> idList = baseProductDao.listIds(productMultiIdVo.getName(), productMultiIdVo.getStatus());
            return new BaseProductResp().success(idList);
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "get show product id failed", e.toString());
        }
    }

    @Override
    public BaseProductResp getProductInfoById(ProductMultiIdVo productMultiIdVo) {
        try {
            List<Map<String, Object>> productInfoList = baseProductDao.getProductInfoById(productMultiIdVo.getId());
            // 将原map中的id抽离，作为剩下map的key
            List<Map<String, Map<String, Object>>> formattedList = FormatUtil.formatInfoById(productInfoList);
            return new BaseProductResp().success(formattedList);
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "get show product id failed", e.toString());
        }
    }

    @Override
    public BaseProductResp createProduct(ProductOneIdVo productOneIdVo) {
        // 重名校验
        if (productNameRepeat(productOneIdVo.getName())) {
            return new BaseProductResp().fail(3, "product name is not unique", null);
        }

        BaseProductDo baseProductDo = new BaseProductDo();
        baseProductDo.setName(productOneIdVo.getName());
        baseProductDo.setDescription(productOneIdVo.getDesc());

        baseProductDo.setCreateTime(TimeUtil.getNowTs());
        baseProductDo.setUpdateTime(TimeUtil.getNowTs());
        baseProductDo.setStatus(4); // 默认待审核

        // 获取创建人
        String sessionID = productOneIdVo.getSessionId();
        String operator = PermissionUtil.getOperatorBySessionID(sessionID);

        baseProductDo.setCreator(operator);
        baseProductDo.setOwner(Collections.singletonList(operator)); // Owner 默认为创建人，admin 为空

        try {
            baseProductDao.createProduct(baseProductDo);
            return new BaseProductResp().success(null);
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "db insert error", e.toString());
        }
    }

    @Override
    public BaseProductResp updateProduct(ProductOneIdVo productOneIdVo) {
        if (!checkProductPermission(productOneIdVo.getSessionId(), productOneIdVo.getId())){
            return new BaseProductResp().fail(2, "no permission to operate", null);
        }
        
        BaseProductDo baseProductDo = new BaseProductDo();
        // field-strategy 默认为 not_null 判断，即只更新和插入非NULL值（不包括非空值）
        baseProductDo.setUpdateTime(new Date());
        baseProductDo.setId(productOneIdVo.getId());
        baseProductDo.setName(productOneIdVo.getName());
        baseProductDo.setDescription(productOneIdVo.getDesc());
        baseProductDo.setStatus(productOneIdVo.getStatus());
        baseProductDo.setOwner(productOneIdVo.getOwner());
        baseProductDo.setAdministrator(productOneIdVo.getAdmin());

        try {
            baseProductDao.updateProduct(baseProductDo);
            return new BaseProductResp().success(null);
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "db update error", e.toString());
        }
    }

    @Override
    public BaseProductResp offlineProduct(ProductOneIdVo productOneIdVo) {
        if (!checkProductPermission(productOneIdVo.getSessionId(), productOneIdVo.getId())){
            return new BaseProductResp().fail(2, "no permission to operate", null);
        }
        try {
            // TODO status enum
            if (countUndeletedModules(productOneIdVo.getId()) != 0) {
                return new BaseProductResp().fail(1, "has undeleted modules", null);
            }
            int status = 3;
            updateProductStatus(productOneIdVo.getId(), status);
            return new BaseProductResp().success(null);
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "get show product id failed", e.toString());
        }
    }

    @Override
    public BaseProductResp deleteProduct(ProductOneIdVo productOneIdVo) {
        if (!checkProductPermission(productOneIdVo.getSessionId(), productOneIdVo.getId())){
            return new BaseProductResp().fail(2, "no permission to operate", null);
        }
        try {
            if (getProductStatus(productOneIdVo.getId()) != 3) {
                // TODO jia yige error code
                return new BaseProductResp().fail(1, "product doesn't offline", null);
            }
            int status = 2;
            updateProductStatus(productOneIdVo.getId(), status);
            return new BaseProductResp().success(null);
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "get show product id failed", e.toString());
        }
    }

}
