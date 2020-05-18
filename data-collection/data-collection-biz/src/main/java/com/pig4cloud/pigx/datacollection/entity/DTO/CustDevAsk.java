package com.pig4cloud.pigx.datacollection.entity.DTO;

import lombok.Data;

/**
 * @author 周宇
 * @create 2020-04-14 13:13
 * 用于判断是否登录请求成功
 */
@Data public class CustDevAsk {
	private String MES;
	//这里是后面操作命令要用的数据服务器地址！
	private String ADDR;
	private String SID;
	private Integer E_CODE;
	private String E_MES;

	public CustDevAsk(String MES, String ADDR, String SID) {
		this.MES = MES;
		this.ADDR = ADDR;
		this.SID = SID;
	}
	
	public CustDevAsk(String MES, int e_CODE, String e_MES) {
		this.MES = MES;
		E_CODE = e_CODE;
		E_MES = e_MES;
	}
}
