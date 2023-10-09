package com.demo.common.manager.tbhandler;

import com.demo.common.model.request.SavvyBuyRequest;
import com.demo.common.model.response.TbResultResponse;
import com.demo.common.utils.ResultWrapper;

public interface TbSavvyBuyHandler<Request extends SavvyBuyRequest, Response extends TbResultResponse> {

    ResultWrapper<Response> sendRequestWrapper(Request request);
}
