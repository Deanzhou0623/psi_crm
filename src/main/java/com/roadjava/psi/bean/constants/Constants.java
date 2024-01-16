package com.roadjava.psi.bean.constants;/*
 *ClassName:Constants
 *Description: TODO
 *@Author:deanzhou
 *@Date:2024-01-15 21:21
 */

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Constants {
    public static final Integer DEFAULT_PAGE_NOW = 1;
    public static final Integer DEFAULT_PAGE_SIZE = 10;
    public static final String TOKEN_KEY = "user_token";
    /**
     * 进货单号前缀
     */
    public static final String PRE_PURCHASE = "PURCHASE_";
    /**
     * 退货单号前缀
     */
    public static final String PRE_RET = "RETURN_GOODS_";
    /**
     * 销售单号前缀
     */
    public static final String PRE_SALE = "SALE_";
    /**
     * 退款单号前缀
     */
    public static final String PRE_REFUND = "REFUND_";

    public static final Charset UTF_8 = StandardCharsets.UTF_8;
}
