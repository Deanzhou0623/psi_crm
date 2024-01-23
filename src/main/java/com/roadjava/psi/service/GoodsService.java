package com.roadjava.psi.service;/*
 *ClassName:GoodsService
 *Description: goods interface
 *@Author:deanzhou
 *@Date:2024-01-21 22:21
 */

import com.roadjava.psi.bean.request.goods.GoodsAddReq;
import com.roadjava.psi.bean.request.goods.GoodsSearchReq;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.bean.vo.statistics.GoodsVO;

import java.util.List;

public interface GoodsService {
    /*
    * 商品添加
    * */
    void add(GoodsAddReq req);

    /*
    * 搜索：加载表格数据
    * */
    ResultVO<TableResult<GoodsVO>> loadTable(GoodsSearchReq req);

    /*
    * 根据id删除
    * */
    void deleteById(Long id);

    /*
    * 搜索列表数据
    * */
    ResultVO<List<GoodsVO>> selectList(GoodsSearchReq req);
}
