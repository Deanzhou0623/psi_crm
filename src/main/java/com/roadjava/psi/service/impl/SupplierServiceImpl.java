package com.roadjava.psi.service.impl;/*
 *ClassName:GoodsServiceImpl
 *Description: 供应商接口实现层
 *@Author:deanzhou
 *@Date:2024-01-21 22:22
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.github.pagehelper.Page;
import com.roadjava.psi.bean.entity.SupplierDO;
import com.roadjava.psi.bean.request.supplier.SupplierAddReq;
import com.roadjava.psi.bean.request.supplier.SupplierSearchReq;
import com.roadjava.psi.bean.request.supplier.SupplierUpdateReq;
import com.roadjava.psi.bean.vo.SupplierVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.convert.SupplierConvert;
import com.roadjava.psi.mapper.SupplierMapper;
import com.roadjava.psi.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SupplierServiceImpl implements SupplierService {
    @Resource
    private SupplierMapper supplierMapper;

   @Resource
   private SupplierConvert supplierConvert;

   /*
   *  添加对象
   * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SupplierAddReq req) {
        //1. 转化对象
        SupplierDO supplierDO = supplierConvert.addReq2DO(req);
        supplierMapper.insert(supplierDO);
    }
    /*
    * 查询图表
    * */
    @Override
    public ResultVO<TableResult<SupplierVO>> loadTable(SupplierSearchReq req) {
        IPage<SupplierDO> page = (IPage<SupplierDO>) new Page(req.getPageNow(),req.getPageSize());
        //lambda
        LambdaQueryWrapper<SupplierDO> queryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(req.getName())){
            queryWrapper.like(SupplierDO::getName,req.getName());
        }
        queryWrapper.orderByDesc(SupplierDO::getId);
        IPage<SupplierDO> pageResult = supplierMapper.selectPage(page, queryWrapper);
        List<SupplierDO> records = pageResult.getRecords();
        if (CollectionUtils.isEmpty(records)){
            return ResultVO.buildEmptySuccess();
        }
        List<SupplierVO> voList=records.stream()
                .map(entity->supplierConvert.entity2VO(entity))
                .collect(Collectors.toList());
        // 设置表格结果并返回
        TableResult<SupplierVO> tableResult = new TableResult<>();
        tableResult.setTotal(pageResult.getTotal());
        tableResult.setRows(voList);
        return ResultVO.buildSuccess(tableResult);
    }
    /*
    * 根据id查询对象
    * */
    @Override
    public ResultVO<SupplierVO> getById(Long id) {
        SupplierDO supplierDO = supplierMapper.selectById(id);
        if (supplierDO ==null){
            return ResultVO.buildFailure("id:"+id+"不存在");
        }
        return ResultVO.buildSuccess(supplierConvert.entity2VO(supplierDO));
    }

    /*
    * 根据id更新对象
    * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateById(SupplierUpdateReq req) {
        SupplierDO supplierDO = supplierConvert.updateReq2DO(req);
        supplierMapper.updateById(supplierDO);
    }
    /*
    * 根据id删除对象
    * */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        supplierMapper.deleteById(id);
    }
    /*
    * 查询列表
    * */
    @Override
    public ResultVO<List<SupplierVO>> list() {
        LambdaQueryWrapper<SupplierDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(SupplierDO::getId);
        List<SupplierDO> records = supplierMapper.selectList(queryWrapper);
        /*
         是否为null
        * */
        if (CollectionUtils.isEmpty(records)) {
            return ResultVO.buildEmptySuccess();
        }
        List<SupplierVO> voList =records.stream().map(
                supplierDO -> supplierConvert.entity2VO(supplierDO))
                .collect(Collectors.toList());

        return ResultVO.buildSuccess(voList);
    }
}
