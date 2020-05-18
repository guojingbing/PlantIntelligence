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
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 客户设备账号
 *
 * @author pigx code generator
 * @date 2020-05-18 16:24:19
 */
@Data
@TableName("data_cust_dev")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "客户设备账号")
public class DataCustDev extends Model<DataCustDev> {
private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
	@TableId(value = "Id",type = IdType.UUID)
    @ApiModelProperty(value="主键")
    private String id;
    /**
     * 客户编号
     */
    @ApiModelProperty(value="客户编号")
    private String custId;
    /**
     * 设备采集号
     */
    @ApiModelProperty(value="设备采集号")
    private String devId;
    /**
     * 设备账号
     */
    @ApiModelProperty(value="设备账号")
    private Long pid;
    /**
     * 设备密码
     */
    @ApiModelProperty(value="设备密码")
    private String pwd;
    /**
     * 采集方式
     */
    @ApiModelProperty(value="采集方式")
    private String ctype;
    /**
     * 采集来源
     */
    @ApiModelProperty(value="采集来源")
    private String cfrom;
    }
