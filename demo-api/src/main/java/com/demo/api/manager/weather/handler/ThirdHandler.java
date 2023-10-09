package com.demo.api.manager.weather.handler;

import com.demo.api.model.request.ThirdRequest;
import com.demo.api.model.response.ThirdResponse;

public interface ThirdHandler<Request extends ThirdRequest, Response extends ThirdResponse> {

    Response sendRequest(Request request);
}
