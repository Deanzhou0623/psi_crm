package com.roadjava.psi.handler;/*
 *ClassName:ReturnGoodsHandler
 *Description: 退货
 *@Author:deanzhou
 *@Date:2024-01-27 21:17
 */

import com.roadjava.psi.bean.constants.Constants;
import com.roadjava.psi.bean.request.ret.RetUpdateStatusReq;
import com.roadjava.psi.bean.request.ret.ReturnAddReq;
import com.roadjava.psi.bean.request.ret.ReturnSearchReq;
import com.roadjava.psi.bean.vo.ReturnGoodsVO;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.service.ReturnGoodsService;
import com.roadjava.psi.utils.NoUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/returnGoods")
public class ReturnGoodsHandler {
    @Resource
    private ReturnGoodsService returnGoodsService;

    /*
    * add
    * */
    @PostMapping("/add")
    public ResultVO<String> add(@RequestBody @Validated ReturnAddReq req){
        returnGoodsService.add(req);
        return ResultVO.buildSuccess("退货已发起,等待审核通过");
    }

    /*
    *  更新
    * */
    @PostMapping("/updateStatus")
    public ResultVO<String> updateStatus(@RequestBody @Validated RetUpdateStatusReq req) {
        returnGoodsService.updateStatus(req);
        return ResultVO.buildSuccess("更新成功");
    }
    /**
     * 查询表格数据
     */
    @PostMapping("/loadTable")
    public ResultVO<TableResult<ReturnGoodsVO>> loadTable(@RequestBody @Validated ReturnSearchReq req) {
        return returnGoodsService.loadTable(req);
    }
    /**
     * 按id删除
     */
    @GetMapping("/deleteById")
    public ResultVO<String> deleteById(Long id) {
        returnGoodsService.deleteById(id);
        return ResultVO.buildSuccess("删除成功");
    }

    /**
     * 得到单号
     */
    @GetMapping("/getNo")
    public ResultVO<String> getNo() {
        return ResultVO.buildSuccess(NoUtil.getNo(Constants.PRE_RET));
    }
}
