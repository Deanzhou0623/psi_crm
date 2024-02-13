package com.roadjava.psi.handler;/*
 *ClassName:SaleOrderHandler
 *Description: sale order controller 层
 *@Author:deanzhou
 *@Date:2024-02-10 21:44
 */

import com.roadjava.psi.bean.constants.Constants;
import com.roadjava.psi.bean.request.saleOrder.SaleOrderAddReq;
import com.roadjava.psi.bean.request.saleOrder.SaleOrderSearchReq;
import com.roadjava.psi.bean.vo.SaleOrderVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.service.SaleOrderService;
import com.roadjava.psi.utils.NoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/saleOrder")
public class SaleOrderHandler {
    @Resource
    private SaleOrderService saleOrderService;
    /**
     * 新增
     */
    @PostMapping("/add")
    public ResultVO<String> add(@RequestBody @Validated SaleOrderAddReq req) {
        saleOrderService.add(req);
        return ResultVO.buildSuccess("添加成功");
    }
    /**
     * 查询表格数据
     */
    @PostMapping("/loadTable")
    public ResultVO<TableResult<SaleOrderVO>> loadTable(@RequestBody @Validated SaleOrderSearchReq req){
        return saleOrderService.loadTable(req);
    }
    /**
     * 得到单号
     */
    @GetMapping("/getNo")
    public ResultVO<String> getNO(){
        return ResultVO.buildSuccess(NoUtil.getNo(Constants.PRE_SALE));
    }
}
