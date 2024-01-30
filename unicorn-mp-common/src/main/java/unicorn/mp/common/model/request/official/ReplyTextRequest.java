package unicorn.mp.common.model.request.official;

import unicorn.mp.common.enumation.OfficialMsgTypeEnum;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XStreamAlias("xml")
public class ReplyTextRequest extends AbstractReplyRequest {

    private String Content;

    @Override
    public String SetMsgType() {
        return OfficialMsgTypeEnum.TEXT.getType();
    }


}
