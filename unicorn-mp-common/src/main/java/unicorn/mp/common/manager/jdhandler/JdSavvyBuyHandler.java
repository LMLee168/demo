package unicorn.mp.common.manager.jdhandler;

import unicorn.mp.common.model.request.SavvyBuyRequest;
import unicorn.mp.common.model.response.jd.JdResultResponse;

public interface JdSavvyBuyHandler<Request extends SavvyBuyRequest, Response extends JdResultResponse> {

    Response sendRequestWrapper(Request request);
}
