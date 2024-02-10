package com.roadjava.psi.mapper;/*
 *ClassName:PurchaseMapper
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-01-15 21:39
 *@Param: $ $
 *@Param: $ $
 *@Return: $
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.entity.PurchaseDO;
import com.roadjava.psi.bean.request.purchase.PurchaseSearchReq;
import com.roadjava.psi.bean.vo.PurchaseVO;

import java.util.List;

public interface PurchaseMapper extends BaseMapper<PurchaseDO> {
    List<PurchaseVO> selectPurchaseList(PurchaseSearchReq req);

    PurchaseVO selectWithDetail(PurchaseDO purchaseQuery);
}
