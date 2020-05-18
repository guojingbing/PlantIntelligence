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
import com.pig4cloud.pigx.datacollection.entity.DataDicDev;
import com.pig4cloud.pigx.datacollection.service.DataDicDevService;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 字典表
 *
 * @author pigx code generator
 * @date 2020-05-18 16:24:19
 */
@RestController
@AllArgsConstructor
@RequestMapping("/datadicdev" )
@Api(value = "datadicdev", tags = "字典表管理")
public class DataDicDevController {

    private final  DataDicDevService dataDicDevService;

    /**
     * 分页查询
     * @param page 分页对象
     * @param dataDicDev 字典表
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("/page" )
    public R getDataDicDevPage(Page page, DataDicDev dataDicDev) {
        return R.ok(dataDicDevService.page(page, Wrappers.query(dataDicDev)));
    }


    /**
     * 通过id查询字典表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id查询", notes = "通过id查询")
    @GetMapping("/{id}" )
    public R getById(@PathVariable("id" ) String id) {
        return R.ok(dataDicDevService.getById(id));
    }

    /**
     * 新增字典表
     * @param dataDicDev 字典表
     * @return R
     */
    @ApiOperation(value = "新增字典表", notes = "新增字典表")
    @SysLog("新增字典表" )
    @PostMapping
    @PreAuthorize("@pms.hasPermission('datacollection_datadicdev_add')" )
    public R save(@RequestBody DataDicDev dataDicDev) {
        return R.ok(dataDicDevService.save(dataDicDev));
    }

    /**
     * 修改字典表
     * @param dataDicDev 字典表
     * @return R
     */
    @ApiOperation(value = "修改字典表", notes = "修改字典表")
    @SysLog("修改字典表" )
    @PutMapping
    @PreAuthorize("@pms.hasPermission('datacollection_datadicdev_edit')" )
    public R updateById(@RequestBody DataDicDev dataDicDev) {
        return R.ok(dataDicDevService.updateById(dataDicDev));
    }

    /**
     * 通过id删除字典表
     * @param id id
     * @return R
     */
    @ApiOperation(value = "通过id删除字典表", notes = "通过id删除字典表")
    @SysLog("通过id删除字典表" )
    @DeleteMapping("/{id}" )
    @PreAuthorize("@pms.hasPermission('datacollection_datadicdev_del')" )
    public R removeById(@PathVariable String id) {
        return R.ok(dataDicDevService.removeById(id));
    }

}
