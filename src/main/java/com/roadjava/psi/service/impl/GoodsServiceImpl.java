package com.roadjava.psi.service.impl;/*
 *ClassName:GoodsServiceImpl
 *Description: 商品接口实现层
 *@Author:deanzhou
 *@Date:2024-01-21 22:22
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.roadjava.psi.bean.entity.GoodsDO;
import com.roadjava.psi.bean.request.goods.GoodsAddReq;
import com.roadjava.psi.bean.request.goods.GoodsSearchReq;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.bean.vo.statistics.GoodsVO;
import com.roadjava.psi.convert.GoodsConvert;
import com.roadjava.psi.mapper.GoodsMapper;
import com.roadjava.psi.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    @Resource
    private GoodsConvert goodsConvert;
    /*
    * 商品添加
    * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(GoodsAddReq req) {
        GoodsDO entity = goodsConvert.addReq2DO(req);
        goodsMapper.insert(entity);
    }

    /*
    * 商品查询
    * */
    @Override
    public ResultVO<TableResult<GoodsVO>> loadTable(GoodsSearchReq req) {
        PageHelper.startPage(req);
        List<GoodsVO> voList =goodsMapper.selectGoodsList(req);
        PageInfo<GoodsVO> pageInfo = new PageInfo<>(voList);
        // 设置表格结果并返回
        TableResult<GoodsVO> tableResult = new TableResult<>();
        tableResult.setTotal(pageInfo.getTotal());
        tableResult.setRows(pageInfo.getList());
        return ResultVO.buildSuccess(tableResult);
    }

    /*
    * 根据id删除
    * */
    @Override
    public void deleteById(Long id) {
         goodsMapper.deleteById(id);
    }

    /*
    * 返回查询结果
    * */
    @Override
    public ResultVO<List<GoodsVO>> selectList(GoodsSearchReq req) {
        List<GoodsVO> goodsVOS = goodsMapper.selectGoodsList(req);
        return ResultVO.buildSuccess(goodsVOS);
    }
}
