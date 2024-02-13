package com.roadjava.psi.service;/*
 *ClassName:SaleOrderService
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-10 20:01
 */

import com.roadjava.psi.bean.request.saleOrder.SaleOrderAddReq;
import com.roadjava.psi.bean.request.saleOrder.SaleOrderSearchReq;
import com.roadjava.psi.bean.vo.SaleOrderVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;

public interface SaleOrderService {
    
    SaleOrderVO detailByOrderNo(String orderNo);

    void add(SaleOrderAddReq req);

    ResultVO<TableResult<SaleOrderVO>> loadTable(SaleOrderSearchReq req);
}
