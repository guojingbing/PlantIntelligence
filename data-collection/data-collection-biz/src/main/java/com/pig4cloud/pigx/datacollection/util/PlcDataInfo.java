package com.pig4cloud.pigx.datacollection.util;

import com.pig4cloud.pigx.datacollection.entity.DTO.CustDevAsk;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author 周宇
 * @create 2020-04-14 13:13
 * GMS=50100285423 PASS=lihui585856
 */
@Slf4j
public class PlcDataInfo {

	private static OkHttpUtils okHttpUtils = new OkHttpUtils();
	private CustDevAsk singleton = null;

	//提供一个无参构造
	public PlcDataInfo() {
	}

	public PlcDataInfo getInstance(String GRM, String PASS) {
		if (singleton == null) {
			synchronized (PlcDataInfo.class) {
				if (singleton == null) {
					//登录 设置CustDevAsk属性
					singleton = login(GRM, PASS);
				}
			}
		}
		return this;
	}

	/**
	 * 登录
	 * @param GRM  设备的序号(11 位数字)
	 * @param PASS GRM 设备的密码
	 * @return Ask
	 */
	public CustDevAsk login(String GRM, String PASS) {
		Map<String, String> parameters = new HashMap<String, String>();
		parameters.put("GRM", GRM);
		parameters.put("PASS", PASS);
		String result = okHttpUtils.doPostForm("http://www.yunplc.com:7080/exlog", parameters);
		String[] split = result.split("\r\n");

		log.info("====>  发起登录login: {}", split[0].equals("OK") ? "success" : "error");
		if (split[0].equals("OK")) {
			//登录成功后保存ASK 全局
			return new CustDevAsk("OK", split[1].substring(5), split[2].substring(4));
		} else {
			return new CustDevAsk("ERROR", Integer.valueOf(split[1]), split[2]);
		}
	}

	/**
	 * 读取设备缓存消息
	 * @return 返回文本
	 */
	public String info() {
		if (this.singleton == null) {
			log.info("====>  请先登录");
			return "";
		}
		if (this.singleton.getMES().equals("ERROR")) {
			return this.singleton.getE_MES();
		}
		String cache = okHttpUtils.doPostForm("http://" + this.singleton.getADDR() + "/exdata?SID=" + this.singleton.getSID() + "&OP=R", null);
		if (cache.equals("")) {
			log.info("====>  该设备暂无无缓存消息");
		}
		return cache;
	}

	/**
	 * 读取设备信息
	 * @param str 传入文本
	 * @return 返回文本
	 */
	public String info(String str) {
		if (this.singleton == null) {
			return "====>  请先登录";
		}
		if (this.singleton.getMES().equals("ERROR")) {
			return this.singleton.getE_MES();
		}
		if (str.equals("")) {
			log.info("====>  参数textstr为空");
			return this.info();
		}
		return okHttpUtils.doPostJson("http://" + this.singleton.getADDR() + "/exdata?SID=" + this.singleton.getSID() + "&OP=R", str);
	}

	/**
	 * 读取设备信息
	 * @param list 传入list集合
	 * @return 返回文本
	 */
	public String info(List<String> list) {
		if (this.singleton == null) {
			return "===>  请先登录";
		}
		if (this.singleton.getMES().equals("ERROR")) {
			return this.singleton.getE_MES();
		}
		if (list.size() == 0) {
			log.info("====>  参数textstr为空");
			return this.info();
		}
		StringBuilder sb = new StringBuilder();
		sb.append(list.size() + "\r\n");
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i) + "\r\n");
		}
		return okHttpUtils.doPostJson("http://"+this.singleton.getADDR()+"/exdata?SID="+this.singleton.getSID()+"&OP=R", sb.toString());
	}

	/**
	 * 枚举指定设备的所有变量信息
	 * @param content 请求内容 可以任意组合
	 * ⚫ N 是变量名，返回值为字符串
	 * ⚫ T 是变量类型，返回值为 B/I/F，分别代表 开关量/整数/浮点数
	 * ⚫ R 是变量读写属性，返回值为 R/W，分别代表 只读/可读写
	 * ⚫ P 是网络权限，返回值为 0/1/2，分别代表 低/中/高
	 * ⚫ G 是变量组名，返回值为字符串。如果有两级变量组，中间是.分隔。
	 * ⚫ C 是 Web 变量描述，返回值为字符串
	 * @return 设备信息
	 */
	public String variate(String content){
		return okHttpUtils.doPostJson("http://"+this.singleton.getADDR()+"/exdata?SID="+this.singleton.getSID()+"&OP=E", content);
	}

	/**
	 * 只枚举变量名
	 * @return
	 */
	public String variate(){
		return okHttpUtils.doPostJson("http://"+this.singleton.getADDR()+"/exdata?SID="+this.singleton.getSID()+"&OP=E", "N");
	}

	/**
	 * 根据枚举变量获得值
	 * @return
	 */
	public String variateinfo(){
		String variate = variate();
		variate = variate.substring(variate.indexOf("\r\n"));
		return info(Arrays.asList(variate.split("\r\n")));
	}

	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		PlcDataInfo plcDataInfo = new PlcDataInfo();
		PlcDataInfo instance = plcDataInfo.getInstance("50100285423", "lihui585856");
		System.err.println(instance.variate());
	}
}
