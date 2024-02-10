package com.roadjava.psi.handler;/*
 *ClassName:PurchaseHandler
 *Description: 进货处理
 *@Author:deanzhou
 *@Date:2024-01-27 21:17
 */

import com.roadjava.psi.bean.constants.Constants;
import com.roadjava.psi.bean.request.purchase.PurchaseAddReq;
import com.roadjava.psi.bean.request.purchase.PurchaseSearchReq;
import com.roadjava.psi.bean.request.purchase.PurchaseUpdateStatusReq;
import com.roadjava.psi.bean.vo.PurchaseVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.service.PurchaseService;
import com.roadjava.psi.utils.NoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/purchase")
public class PurchaseHandler {
    @Resource
    private PurchaseService purchaseService;

    /*
    * add 添加货源
    * */
    @PostMapping("/add")
    public ResultVO<String> add(@RequestBody @Validated PurchaseAddReq req){
        purchaseService.insert(req);
        return ResultVO.buildSuccess("添加成功");
    }
    /*
    * delete by id   
    * */
    @GetMapping("/deleteById")
    public ResultVO<String> deleteById(Long id){
          purchaseService.deleteById(id);
          return ResultVO.buildSuccess("删除成功");
    }
    /*
    * update
    * */
    @PostMapping("/updateStatus")
    public ResultVO<String> updateStatus(@RequestBody @Validated PurchaseUpdateStatusReq req){
        purchaseService.updateStatus(req);
        return ResultVO.buildSuccess("更新成功");
    }
    /*
    * get No (单号)
    * */
    @GetMapping("getNo")
    public ResultVO<String> getNo(){
        return ResultVO.buildSuccess(NoUtil.getNo(Constants.PRE_PURCHASE));
    }
    /*
    * search table
    * */
    @PostMapping("/loadTable")
    public ResultVO<TableResult<PurchaseVO>> loadTable(@RequestBody @Validated PurchaseSearchReq req){
        return purchaseService.loadTable(req);
    }
}
