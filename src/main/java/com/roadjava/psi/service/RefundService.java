package com.roadjava.psi.service;/*
 *ClassName:RefundService
 *Description: 退款服务层
 *@Author:deanzhou
 *@Date:2024-02-10 15:53
 */

import com.roadjava.psi.bean.request.refund.RefundAddReq;
import com.roadjava.psi.bean.request.refund.RefundSearchReq;
import com.roadjava.psi.bean.vo.RefundVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;

public interface RefundService {
    void add(RefundAddReq req);

    ResultVO<TableResult<RefundVO>> loadTable(RefundSearchReq req);
}
