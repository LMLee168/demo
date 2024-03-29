package unicorn.mp.common.manager;

import unicorn.mp.common.manager.tbhandler.*;
import unicorn.mp.common.model.request.tb.*;
import unicorn.mp.common.manager.tbhandler.*;
import unicorn.mp.common.model.response.PromotionAgg;
import unicorn.mp.common.model.response.jd.*;
import unicorn.mp.common.model.response.tb.*;
import unicorn.mp.common.utils.ResultWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import unicorn.mp.common.model.request.tb.*;
import unicorn.mp.common.model.response.jd.PromotionCodeAggResp;
import unicorn.mp.common.model.response.jd.PromotionCodeResp;
import unicorn.mp.common.model.response.tb.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * 1、解析淘口令获取商品id
 * 2、根据商品ID获取商品信息
 * 3、将商品转为专属的淘口令
 * 4、用户订单绑定
 */
@Slf4j
@Component
public class CommonTbManager {
    @Resource
    private TbkDgMaterialOptionalHandler tbkDgMaterialOptionalHandler;
    @Resource
    private TbkItemConvertHandler tbkItemConvertHandler;
    @Resource
    private TbkItemInfoGetHandler tbkItemInfoGetHandler;
    @Resource
    private TbkTpwdConvertHandler tbkTpwdConvertHandler;
    @Resource
    private WirelessShareTpwdQueryHandler wirelessShareTpwdQueryHandler;
    @Resource
    private TbkItemClickExtractHandler tbkItemClickExtractHandler;
    @Resource
    private TbkTpwdConvertAggZtkHandler tbkTpwdConvertAggZtkHandler;
    @Resource
    private ZtkItemOrderQueryHandler ztkItemOrderQueryHandler;

    /**
     * 单个商品 q 和 adzone_id
     */
    public TbkDgMaterialOptionalResponse materialOptional() {
        TbkDgMaterialOptionalRequest request = new TbkDgMaterialOptionalRequest();
        request.setAdzone_id(111482500415L);
        ResultWrapper<TbkDgMaterialOptionalResponse> result = tbkDgMaterialOptionalHandler.sendRequestWrapper(request);
        if (result.isFailure()) {
            log.error(result.getMessage());
            return null;
        }
        return result.getData();
    }
    //商品id取详情查询（简版）
    public TbkItemInfoGetResponse tbkItemInfoGet(TbkItemInfoGetRequest request) {

        ResultWrapper<TbkItemInfoGetResponse> result = tbkItemInfoGetHandler.sendRequestWrapper(request);
        if (result.isFailure()) {
            log.error(result.getMessage());
            return null;
        }
        return result.getData();
    }

    //长/段链 -》取商品id
    public TbkItemConvertResponse itemConvert() {
        TbkItemConvertRequest request = new TbkItemConvertRequest();
        request.setAdzone_id(111482500415L);
        ResultWrapper<TbkItemConvertResponse> result = tbkItemConvertHandler.sendRequestWrapper(request);
        if (result.isFailure()) {
            log.error(result.getMessage());
            return null;
        }
        return result.getData();
    }

    //tkl解析-得商品id
    public TbkTpwdConvertResponse tpwdConvert(String content) {
        TbkTpwdConvertRequest request = new TbkTpwdConvertRequest();
        request.setAdzone_id(111482500415L);
        request.setPwd_content(content);
        ResultWrapper<TbkTpwdConvertResponse> result = tbkTpwdConvertHandler.sendRequestWrapper(request);
        if (result.isFailure()) {
            log.error(result.getMessage());
            return null;
        }
        return result.getData();
    }

    //tkl  解析--无商品id
    public WirelessShareTpwdQueryResponse shareTpwdQueryt(String content) {
        WirelessShareTpwdQueryRequest request = new WirelessShareTpwdQueryRequest();
        request.setPassword_content(content);
        ResultWrapper<WirelessShareTpwdQueryResponse> result = wirelessShareTpwdQueryHandler.sendRequestWrapper(request);
        if (result.isFailure()) {
            log.error(result.getMessage());
            return null;
        }
        return result.getData();
    }

    //从tbk推广长链接或短链接中解析出open_iid(即识别商品的id)。
    public TbkItemClickExtractResponse itemClickExtract(String url) {
        TbkItemClickExtractRequest request = new TbkItemClickExtractRequest();
        request.setClick_url(url);
        ResultWrapper<TbkItemClickExtractResponse> result = tbkItemClickExtractHandler.sendRequestWrapper(request);
        if (result.isFailure()) {
            log.error(result.getMessage());
            return null;
        }
        return result.getData();
    }

    //zetaoke 根据tkl获取聚合信息
    public PromotionCodeAggResp tpwdConvertAggByZtk(String content) {
        TbkTpwdConvertAggZtkRequest request = new TbkTpwdConvertAggZtkRequest();
        request.setTkl(content);

        ResultWrapper<ZheTaoKeTbAggPromotionGetResponse> result = tbkTpwdConvertAggZtkHandler.sendRequestWrapper(request);
        if (result.isFailure()) {
            log.error(result.getMessage());
            return null;
        }
        PromotionAgg aggInfo = result.getData().getContent().get(0);
        PromotionCodeResp resp = new PromotionCodeResp();
        resp.setShortURL(aggInfo.getShorturl());
        resp.setClickURL(aggInfo.getCoupon_click_url());

        PromotionCodeAggResp aggResp = new PromotionCodeAggResp();
        aggResp.setPromotionCodeResp(resp);
        aggResp.setItem_url(aggInfo.getItem_url());
        aggResp.setTkfee3(aggInfo.getTkfee3());
        aggResp.setCoupon_id(aggInfo.getCoupon_id());
        aggResp.setQuanhou_jiage(aggInfo.getQuanhou_jiage());
        aggResp.setCoupon_info_money(aggInfo.getCoupon_info_money());
        aggResp.setSkuId(aggInfo.getTao_id());
        aggResp.setGoodsName(aggInfo.getTao_title());
        aggResp.setUnitPrice(new BigDecimal(aggInfo.getSize()));

        return aggResp;
    }

    //ztk 订单查询
    public List<TbkItemOrderZtkQueryResponse.ContentBO>  queryOrder(String orderId) {
        TbkItemOrderZtkQueryRequest request = new TbkItemOrderZtkQueryRequest();
        request.setOrderid(orderId);
        request.setSan_pingtai_id("4");
        ResultWrapper<TbkItemOrderZtkQueryResponse> result =ztkItemOrderQueryHandler.sendRequestWrapper(request);
        if (result.isFailure()) {
            log.error(result.getMessage());
            return null;
        }
        return result.getData().getContent();
    }


}
