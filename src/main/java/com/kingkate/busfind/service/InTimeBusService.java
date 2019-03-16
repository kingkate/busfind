package com.kingkate.busfind.service;

import com.kingkate.busfind.dto.request.QueryInTimeBusReq;
import com.kingkate.busfind.dto.response.InTimeBusInfoRes;

public interface InTimeBusService {
	public InTimeBusInfoRes getInTimeBusInfo(QueryInTimeBusReq queryInTimeBusReq);
	public void saveBusLine(QueryInTimeBusReq queryInTimeBusReq);
}
