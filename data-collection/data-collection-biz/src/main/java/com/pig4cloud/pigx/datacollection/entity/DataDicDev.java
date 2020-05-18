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
 * 字典表
 *
 * @author pigx code generator
 * @date 2020-05-18 16:24:19
 */
@Data
@TableName("data_dic_dev")
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "字典表")
public class DataDicDev extends Model<DataDicDev> {
private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    @ApiModelProperty(value="主键")
    private String id;
    /**
     * 设备ID
     */
    @ApiModelProperty(value="设备ID")
    private Long devId;
    /**
     * 参数编号（指标）
     */
    @ApiModelProperty(value="参数编号（指标）")
    private String paramnum;
    /**
     * 参数说明
     */
    @ApiModelProperty(value="参数说明")
    private String remark;
    /**
     * 定义说明
     */
    @ApiModelProperty(value="定义说明")
    private String defined;
    /**
     * 设定类型
     */
    @ApiModelProperty(value="设定类型")
    private String devtype;
    }
