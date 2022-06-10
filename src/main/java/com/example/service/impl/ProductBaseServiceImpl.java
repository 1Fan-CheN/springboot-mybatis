package com.example.service.impl;

import com.example.dao.BaseProductDao;
import com.example.entity.sqldo.BaseProductDo;
import com.example.entity.vo.BaseProductMultiIdVo;
import com.example.entity.vo.BaseProductResp;
import com.example.entity.vo.BaseProductOneIdVo;
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

    public boolean checkProductPermission(BaseProductOneIdVo baseProductOneIdVo) {
        String operator = PermissionUtil.getOperatorBySessionID(baseProductOneIdVo.getSessionId());
        return PermissionUtil.checkOperatorPermission(operator, baseProductOneIdVo.getId());
    }

    private boolean productNameRepeat(String name) {
        return baseProductDao.countName(name) != 0;
    }

    private boolean hasUndeletedModules(int productId) {
        return baseProductDao.countUndeletedModule(productId) != 0;
    }

    private boolean isProductOffline(int productId) {
        return baseProductDao.getProductStatus(productId) == 3;
    }

    @Override
    public BaseProductResp getIdList(BaseProductMultiIdVo baseProductMultiIdVo) {
        try {
            List<Object> idList = baseProductDao.getIdList(baseProductMultiIdVo.getName(), baseProductMultiIdVo.getStatus());
            return new BaseProductResp().success(idList);
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "get show product id failed", e.toString());
        }
    }

    @Override
    public BaseProductResp getProductInfoById(BaseProductMultiIdVo baseProductMultiIdVo) {
        try {
            List<Map<String, Object>> productInfoList = baseProductDao.getProductInfoById(baseProductMultiIdVo.getId());
            // 将原map中的id抽离，作为剩下map的key
            List<Map<String, Map<String, Object>>> formattedList = FormatUtil.formatInfoById(productInfoList);
            return new BaseProductResp().success(formattedList);
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "get show product id failed", e.toString());
        }
    }

    @Override
    public BaseProductResp createProduct(BaseProductOneIdVo baseProductOneIdVo) {
        // 重名校验
        if (productNameRepeat(baseProductOneIdVo.getName())) {
            return new BaseProductResp().fail(3, "product name is not unique", null);
        }

        BaseProductDo baseProductDo = new BaseProductDo();
        baseProductDo.setName(baseProductOneIdVo.getName());
        baseProductDo.setDescription(baseProductOneIdVo.getDesc());

        baseProductDo.setCreateTime(TimeUtil.getNowTs());
        baseProductDo.setUpdateTime(TimeUtil.getNowTs());
        baseProductDo.setStatus(4); // 默认待审核

        // 获取创建人
        String sessionID = baseProductOneIdVo.getSessionId();
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
    public BaseProductResp updateProduct(BaseProductOneIdVo baseProductVo) {
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

    @Override
    public BaseProductResp offlineProduct(BaseProductOneIdVo baseProductOneIdVo) {
        try {
            // TODO status enum
            if (hasUndeletedModules(baseProductOneIdVo.getId())) {
                return new BaseProductResp().fail(1, "has undeleted modules", null);
            }
            BaseProductDo baseProductDo = new BaseProductDo();
            baseProductDo.setId(baseProductOneIdVo.getId());
            baseProductDo.setStatus(3);  // 已下线
            if (baseProductDao.updateProductStatus(baseProductDo) == 1) {
                return new BaseProductResp().success(null);
            } else {
                throw new Exception("fail.....");
            }
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "get show product id failed", e.toString());
        }
    }

    @Override
    public BaseProductResp deleteProduct(BaseProductOneIdVo baseProductOneIdVo) {
        try {
            if (!isProductOffline(baseProductOneIdVo.getId())) {
                // TODO jia yige error code
                return new BaseProductResp().fail(1, "product doesn't offline", null);
            }
            BaseProductDo baseProductDo = new BaseProductDo();
            baseProductDo.setId(baseProductOneIdVo.getId());
            baseProductDo.setStatus(2);
            if (baseProductDao.updateProductStatus(baseProductDo) == 1) {
                return new BaseProductResp().success(null);
            } else {
                throw new Exception("fail.....");
            }
        } catch (Exception e) {
            log.error(e.toString());
            return new BaseProductResp().fail(-1, "get show product id failed", e.toString());
        }
    }

}
