package com.roadjava.psi.bean.vo.result;/*
 *ClassName:TableResult
 *Description: table result return the front_end
 *@Author:deanzhou
 *@Date:2024-01-15 20:58
 */

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TableResult<T> {
    /**
     * 查询出的记录
     */
    private List<T> rows = new ArrayList<>();
    /**
     * 总条数
     */
    private long total;
}
