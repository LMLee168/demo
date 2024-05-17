package unicorn.mp.common.manager.pddhandler;

import unicorn.mp.common.model.request.SavvyBuyRequest;
import unicorn.mp.common.model.request.pdd.PddSavvyBuyRequest;
import unicorn.mp.common.model.response.jd.JdResultResponse;
import unicorn.mp.common.model.response.pdd.PddResultResponse;

public interface PddSavvyBuyHandler<Request extends PddSavvyBuyRequest, Response extends PddResultResponse> {

    Response sendRequestWrapper(Request request);
}
