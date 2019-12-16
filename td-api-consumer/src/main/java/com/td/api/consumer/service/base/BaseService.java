package com.td.api.consumer.service.base;

import com.alibaba.fastjson.JSONObject;
import com.td.api.consumer.bean.Json;
import com.td.api.consumer.enums.ResultEnum;

public class BaseService {

	protected String returnHix() {
		Json json = new Json();
		json.setCode(ResultEnum.ERROR.getCode());
		json.setMsg(ResultEnum.ERROR.getMsg());
		return JSONObject.toJSONString(json);
	}
}
