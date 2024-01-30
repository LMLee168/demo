package unicorn.mp.common.manager.dyhandler;

import unicorn.mp.common.model.request.SavvyBuyRequest;
import unicorn.mp.common.model.response.dy.DyResultResponse;

public interface DySavvyBuyHandler <Request extends SavvyBuyRequest, Response extends DyResultResponse> {

    Response sendRequestWrapper(Request request);
}