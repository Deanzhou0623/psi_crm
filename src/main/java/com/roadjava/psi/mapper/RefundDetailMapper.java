package com.roadjava.psi.mapper;/*
 *ClassName:RefundDetailMapper
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-02-10 20:02
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roadjava.psi.bean.entity.RefundDetailDO;
import com.roadjava.psi.bean.vo.RefundDetailVO;

import java.util.List;

public interface RefundDetailMapper extends BaseMapper<RefundDetailDO> {
    List<RefundDetailVO> selectByRefundIds(List<Long> refundIds);
}
