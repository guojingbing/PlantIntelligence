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

package com.pig4cloud.pigx.datacollection.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pigx.common.core.util.R;
import com.pig4cloud.pigx.common.log.annotation.SysLog;
import com.pig4cloud.pigx.datacollection.entity.DataCustDev;
import com.pig4cloud.pigx.datacollection.service.DataCustDevService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 客户设备账号
 *
 * @author pigx code generator
 * @date 2020-05-18 16:24:19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/datacustdev" )
@Api(value = "datacustdev", tags = "客户设备账号管理")
public class DataCustDevController {

    private final  DataCustDevService dataCustDevService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param dataCustDev 客户设备账号
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getDataCustDevPage(Page page, DataCustDev dataCustDev) {
        return R.ok(dataCustDevService.page(page, Wrappers.query(dataCustDev)));
    }


    /**
     * 通过id查询客户设备账号
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return R.ok(dataCustDevService.getById(id));
    }

    /**
     * 新增客户设备账号
     * @param dataCustDev 客户设备账号
     * @return R
     */
    @ApiOperation(value = "新增客户设备账号", notes = "新增客户设备账号")
    @SysLog("新增客户设备账号" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('datacollection_datacustdev_add')" )
    public R save(@RequestBody DataCustDev dataCustDev) {
        return R.ok(dataCustDevService.save(dataCustDev));
    }

    /**
     * 修改客户设备账号
     * @param dataCustDev 客户设备账号
     * @return R
     */
    @ApiOperation(value = "修改客户设备账号", notes = "修改客户设备账号")
    @SysLog("修改客户设备账号" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('datacollection_datacustdev_edit')" )
    public R updateById(@RequestBody DataCustDev dataCustDev) {
        return R.ok(dataCustDevService.updateById(dataCustDev));
    }

    /**
     * 通过id删除客户设备账号
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除客户设备账号", notes = "通过id删除客户设备账号")
    @SysLog("通过id删除客户设备账号" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('datacollection_datacustdev_del')" )
    public R removeById(@PathVariable String id) {
        return R.ok(dataCustDevService.removeById(id));
    }

}
