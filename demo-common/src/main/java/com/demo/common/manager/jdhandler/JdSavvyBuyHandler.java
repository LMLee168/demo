package com.demo.common.manager.jdhandler;

import com.demo.common.model.request.SavvyBuyRequest;
import com.demo.common.model.response.jd.JdResultResponse;
import com.demo.common.utils.ResultWrapper;

public interface JdSavvyBuyHandler<Request extends SavvyBuyRequest, Response extends JdResultResponse> {

    Response sendRequestWrapper(Request request);
}
