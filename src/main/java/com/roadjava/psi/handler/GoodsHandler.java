package com.roadjava.psi.handler;/*
 *ClassName:GoodsHandler
 *Description: goods controller
 *@Author:deanzhou
 *@Date:2024-01-21 22:17
 */

import com.roadjava.psi.bean.request.goods.GoodsAddReq;
import com.roadjava.psi.bean.request.goods.GoodsSearchReq;
import com.roadjava.psi.bean.vo.result.ResultVO;
import com.roadjava.psi.bean.vo.result.TableResult;
import com.roadjava.psi.bean.vo.statistics.GoodsVO;
import com.roadjava.psi.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Priority;
import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsHandler {
    @Resource
    private GoodsService goodsService;

    /*
    * add
    * */
    @PostMapping("/add")
    public ResultVO<String> add(@RequestBody @Validated GoodsAddReq req){
        goodsService.add(req);
        return ResultVO.buildSuccess("添加成功");
    }

    /*
    * delete
    * */
    @GetMapping("/deleteById")
     public ResultVO<String> deleteById(Long id){
        goodsService.deleteById(id);
        return ResultVO.buildSuccess("删除成功");
    }

    /*
    * select list
    * */
    @PostMapping("/selectList")
    public ResultVO<List<GoodsVO>> selectList(@RequestBody GoodsSearchReq req){
          return goodsService.selectList(req);
    }

    /*
    * 搜索图表
    * */
    @PostMapping("/loadTable")
    public ResultVO<TableResult<GoodsVO>> loadTable(@RequestBody @Validated GoodsSearchReq req) {
        return goodsService.loadTable(req);
    }

}
