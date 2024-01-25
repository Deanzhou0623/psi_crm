package com.roadjava.psi.handler;/*
 *ClassName:SupplierHandler
 *Description: supplier controller
 *@Author:deanzhou
 *@Date:2024-01-21 22:18
 */

import com.roadjava.psi.bean.enums.RoleEnum;
import com.roadjava.psi.bean.request.supplier.SupplierAddReq;
import com.roadjava.psi.bean.request.supplier.SupplierSearchReq;
import com.roadjava.psi.bean.request.supplier.SupplierUpdateReq;
import com.roadjava.psi.bean.vo.RoleVO;
import com.roadjava.psi.bean.vo.SupplierVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.service.SupplierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/supplier")
public class SupplierHandler {
    @Resource
    private SupplierService supplierService;

    /*
    * add
    * */
    @PostMapping("/add")
    public ResultVO<String> add(@RequestBody @Validated SupplierAddReq req){
         supplierService.add(req);
         return ResultVO.buildFailure("添加成功");
     }
    /*
    * 查询表格数据
    * */
    @PostMapping("/loadTable")
    public ResultVO<TableResult<SupplierVO>> loadTable(@RequestBody @Validated SupplierSearchReq req){
         return  supplierService.loadTable(req);
    }

    /**
     * 按id查询
     */
    @GetMapping("/getById")
    public ResultVO<SupplierVO> getById(Long id){
         return supplierService.getById(id);
    }

    /**
     * 按id更新
     */
    @PostMapping("/updateById")
    public ResultVO<String> updateById(@RequestBody @Validated SupplierUpdateReq req){
        supplierService.updateById(req);
        return ResultVO.buildSuccess("更新成功");
    }

    /**
     * 按id删除
     */
    @GetMapping("/deleteById")
    public ResultVO<String> deleteById(Long id) {
        supplierService.deleteById(id);
        return ResultVO.buildSuccess("删除成功");
    }
    /**
     * 查询供应商列表
     */
    @GetMapping("/list")
    public ResultVO<List<SupplierVO>> list(){
        List<RoleVO> list = new ArrayList<>();
        return  supplierService.list();
    }
}
