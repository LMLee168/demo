package unicorn.mp.api.manager.weather.handler;

import unicorn.mp.api.model.request.ThirdRequest;
import unicorn.mp.api.model.response.ThirdResponse;

public interface ThirdHandler<Request extends ThirdRequest, Response extends ThirdResponse> {

    Response sendRequest(Request request);
}
