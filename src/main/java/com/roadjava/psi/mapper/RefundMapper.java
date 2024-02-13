package com.roadjava.psi.mapper;/*
 *ClassName:RefundMapper
 *Description: refund mapper class
 *@Author:deanzhou
 *@Date:2024-02-10 20:01
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.entity.RefundDO;
import com.roadjava.psi.bean.request.refund.RefundSearchReq;
import com.roadjava.psi.bean.vo.RefundVO;

import java.util.List;

public interface RefundMapper extends BaseMapper<RefundDO> {

    List<RefundVO> selectRefundList(RefundSearchReq req);
}
