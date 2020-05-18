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
import com.pig4cloud.pigx.datacollection.entity.DataCustData;
import com.pig4cloud.pigx.datacollection.service.DataCustDataService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 设备数据表
 *
 * @author pigx code generator
 * @date 2020-05-18 16:24:19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/datacustdata" )
@Api(value = "datacustdata", tags = "设备数据表管理")
public class DataCustDataController {

    private final  DataCustDataService dataCustDataService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param dataCustData 设备数据表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getDataCustDataPage(Page page, DataCustData dataCustData) {
        return R.ok(dataCustDataService.page(page, Wrappers.query(dataCustData)));
    }


    /**
     * 通过id查询设备数据表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return R.ok(dataCustDataService.getById(id));
    }

    /**
     * 新增设备数据表
     * @param dataCustData 设备数据表
     * @return R
     */
    @ApiOperation(value = "新增设备数据表", notes = "新增设备数据表")
    @SysLog("新增设备数据表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('datacollection_datacustdata_add')" )
    public R save(@RequestBody DataCustData dataCustData) {
        return R.ok(dataCustDataService.save(dataCustData));
    }

    /**
     * 修改设备数据表
     * @param dataCustData 设备数据表
     * @return R
     */
    @ApiOperation(value = "修改设备数据表", notes = "修改设备数据表")
    @SysLog("修改设备数据表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('datacollection_datacustdata_edit')" )
    public R updateById(@RequestBody DataCustData dataCustData) {
        return R.ok(dataCustDataService.updateById(dataCustData));
    }

    /**
     * 通过id删除设备数据表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除设备数据表", notes = "通过id删除设备数据表")
    @SysLog("通过id删除设备数据表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('datacollection_datacustdata_del')" )
    public R removeById(@PathVariable String id) {
        return R.ok(dataCustDataService.removeById(id));
    }

}
