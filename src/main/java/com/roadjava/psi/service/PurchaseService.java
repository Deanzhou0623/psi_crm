package com.roadjava.psi.service;/*
 *ClassName:PurchaseService
 *Description: 进货功能接口
 *@Author:deanzhou
 *@Date:2024-01-27 21:42
 */

import com.roadjava.psi.bean.request.purchase.PurchaseAddReq;
import com.roadjava.psi.bean.request.purchase.PurchaseSearchReq;
import com.roadjava.psi.bean.request.purchase.PurchaseUpdateStatusReq;
import com.roadjava.psi.bean.vo.PurchaseVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;

public interface PurchaseService {
    /*
    * add
    * */
    void insert(PurchaseAddReq req);

    /*
    * delete by id
    * */
    void deleteById(Long id);

    /*
    * update
    * */
    void updateStatus(PurchaseUpdateStatusReq req);

    /*
    * load table
    * */
    ResultVO<TableResult<PurchaseVO>> loadTable(PurchaseSearchReq req);
}
