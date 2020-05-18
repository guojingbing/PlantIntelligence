/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.pig4cloud.pigx.datacollection.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.math.BigDecimal;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 设备数据表
 *
 * @author pigx code generator
 * @date 2020-05-18 16:24:19
 */
@Data
@TableName("data_cust_data")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "设备数据表")
public class DataCustData extends Model<DataCustData> {
private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value = "Id",type = IdType.UUID)
	@ApiModelProperty(value="主键")
    private String id;
    /**
     * 参数编号
     */
    @ApiModelProperty(value="参数编号")
    private Long param;
    /**
     * 指标数据
     */
    @ApiModelProperty(value="指标数据")
    private BigDecimal pvalue;
    /**
     * 采集时间
     */
    @ApiModelProperty(value="采集时间")
    private LocalDateTime cdatetime;
    }
