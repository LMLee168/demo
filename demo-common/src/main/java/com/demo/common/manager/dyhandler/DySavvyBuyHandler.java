package com.demo.common.manager.dyhandler;

import com.demo.common.model.request.SavvyBuyRequest;
import com.demo.common.model.response.dy.DyResultResponse;

public interface DySavvyBuyHandler <Request extends SavvyBuyRequest, Response extends DyResultResponse> {

    Response sendRequestWrapper(Request request);
}