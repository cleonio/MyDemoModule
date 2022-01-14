package com.cleo.easyexcel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @className: com.cleo.easyexcel-> ShopDailyOperations
 * @description:
 * @author: cleo
 * @createDate: 2022-01-12 9:17
 * @version: 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShopDailyOperations {


    /**
     * 品名：即商品名称
     */
    private String name;

    /**
     * 销售数量：即该商品在指定时间内销售总数量
     */
    private Integer sellCount;

    /**
     * 销售单价：即该商品的当日销售价
     */
    private BigDecimal price;

    /**
     * 含税销售收入：商品销售数量*销售单价
     */
    private BigDecimal incomeTax;
    /**
     * 不含税销售收入：含税销售收入/（100%+销项税率）
     * 保留两位小数，末位进行四舍五入
     */
    private BigDecimal incomeNotTax;
    /**
     * 销项税率
     */
    private Double sellTaxRate;
    /**
     * 销项税  含税销售收入—不含税销售收入
     */
    private BigDecimal sellTax;
    /**
     * 成本含税单价
     */
    private BigDecimal costTaxPrice;
    /**
     * 含税成本 成本含税单价*销售数量
     */
    private BigDecimal costTax;
    /**
     * 不含税成本：含税成本/（100%+进项税率）
     * 保留两位小数，末位进行四舍五入
     */
    private BigDecimal costNotTax;
    /**
     * 进项税率
     */
    private Double buyTaxRate;
    /**
     * 含进项税：含税成本—不含税成本
     */
    private BigDecimal buyTax;
    /**
     * 优惠金额：即该商品在当日全部订单中享受到的抵扣金额总和
     */
    private BigDecimal reduceAmount;
}
