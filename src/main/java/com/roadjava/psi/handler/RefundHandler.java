package com.roadjava.psi.handler;/*
 *ClassName:RefundHandler
 *Description: refunder 控制层
 *@Author:deanzhou
 *@Date:2024-02-10 15:51
 */

import com.roadjava.psi.bean.constants.Constants;
import com.roadjava.psi.bean.request.refund.RefundAddReq;
import com.roadjava.psi.bean.request.refund.RefundSearchReq;
import com.roadjava.psi.bean.vo.RefundVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.service.RefundService;
import com.roadjava.psi.utils.NoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/refund")
public class RefundHandler {
    @Resource
    private RefundService refundService;

    /*
    * add
    * */
    @PostMapping("/add")
    public ResultVO<String> add(@RequestBody @Validated RefundAddReq req){
        refundService.add(req);
        return ResultVO.buildSuccess("添加成功");
    }

    /*
    * 查询表格数据
    * */
    @PostMapping("loadTable")
    public ResultVO<TableResult<RefundVO>> loadTable(@RequestBody @Validated RefundSearchReq req){
       return refundService.loadTable(req);
    }

    /*
    * 得到单号
    * */
    @GetMapping("/getNo")
    public ResultVO<String> getNo(){
        return ResultVO.buildSuccess(NoUtil.getNo(Constants.PRE_REFUND));
    }
}
