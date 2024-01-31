package unicorn.mp.common.manager.tbhandler;

import unicorn.mp.common.model.request.SavvyBuyRequest;
import unicorn.mp.common.model.response.TbResultResponse;
import unicorn.mp.common.utils.ResultWrapper;

public interface TbSavvyBuyHandler<Request extends SavvyBuyRequest, Response extends TbResultResponse> {

    ResultWrapper<Response> sendRequestWrapper(Request request);
}
