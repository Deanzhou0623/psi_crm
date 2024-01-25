package com.roadjava.psi.service;/*
 *ClassName:SupplierService
 *Description: supplier service interface
 *@Author:deanzhou
 *@Date:2024-01-25 00:02
 */

import com.roadjava.psi.bean.request.supplier.SupplierAddReq;
import com.roadjava.psi.bean.request.supplier.SupplierSearchReq;
import com.roadjava.psi.bean.request.supplier.SupplierUpdateReq;
import com.roadjava.psi.bean.vo.SupplierVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;

import java.util.List;

public interface SupplierService {
    /*
    * add
    * */
    void add(SupplierAddReq req);

    /*
    * load table
    * */
    ResultVO<TableResult<SupplierVO>> loadTable(SupplierSearchReq req);

    /*
    * 根据id查询供应商
    * */
    ResultVO<SupplierVO> getById(Long id);

    /*
    * 根据id更细供应商信息
    * */
    void updateById(SupplierUpdateReq req);

    /*
    * 根据id删除供应商信息
    * */
    void deleteById(Long id);

    /*
    * 查询供应商列表
    * */
    ResultVO<List<SupplierVO>> list();
}
