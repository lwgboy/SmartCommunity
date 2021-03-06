package com.wb.sc.task;

import java.util.List;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.common.net.volley.ParamsEncryptRequest;
import com.wb.sc.parser.BaseParser;
import com.wb.sc.parser.ResetPwdParser;
import com.wb.sc.bean.ResetPwd;
import com.wb.sc.config.RespCode;

public class ResetPwdRequest extends ParamsEncryptRequest<ResetPwd> {
	public ResetPwdRequest (String url, List<String> params, 
			Listener<ResetPwd> listenre, ErrorListener errorListener) {
		super(url, params, listenre, errorListener);
	}
	
	@Override
	protected Response<ResetPwd> parseNetworkResponse(NetworkResponse response) {
		String resultStr = new String(response.data);
		ResetPwd dataBean = new ResetPwd();	
		BaseParser.parse(dataBean, resultStr);
		if(dataBean.respCode.equals(RespCode.SUCCESS)) {
			new ResetPwdParser().parse(dataBean);
		}
		return Response.success(dataBean, getCacheEntry());
	}
}
