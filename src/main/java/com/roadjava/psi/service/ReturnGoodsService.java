package com.roadjava.psi.service;/*
 *ClassName:ReturnGoodsService
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-01-27 21:55
 *@Param: $ $
 *@Param: $ $
 *@Return: $
 */

import com.roadjava.psi.bean.request.ret.RetUpdateStatusReq;
import com.roadjava.psi.bean.request.ret.ReturnAddReq;
import com.roadjava.psi.bean.request.ret.ReturnSearchReq;
import com.roadjava.psi.bean.vo.ReturnGoodsVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;

public interface ReturnGoodsService {
    
    void add(ReturnAddReq req);

    void updateStatus(RetUpdateStatusReq req);

    ResultVO<TableResult<ReturnGoodsVO>> loadTable(ReturnSearchReq req);

    void deleteById(Long id);

    void deleteByPurchaseNo(String purchaseNo);
}
