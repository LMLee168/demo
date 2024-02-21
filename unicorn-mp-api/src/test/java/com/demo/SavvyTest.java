package com.demo;

import com.alibaba.fastjson.JSON;
import unicorn.mp.common.manager.CommonJdManager;
import unicorn.mp.common.manager.CommonTbManager;
import unicorn.mp.common.model.request.jd.PromotionCodeReq;
import org.junit.Test;

import javax.annotation.Resource;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = DemoApplication.class)
//@Slf4j
//@TestConfiguration
public class SavvyTest extends BaseTest{

//    public SavvyTest() {
//        System.setProperty("es.set.netty.runtime.available.processors", "false");
//    }


    @Resource
    private CommonJdManager commonJdManager;
    //mm_631370037_2366600350_111482500415
    @Resource
    private CommonTbManager commonTbManager;
    @Test
    public void tb() {

//        System.out.println(JSON.toJSONString(commonTbManager.shareTpwdQueryt("0HshdpTt27g CZ3457")));

//        System.out.println(JSON.toJSONString(commonTbManager.itemClickExtract("https://m.tb.cn/h.UwFjVpW?tk=0HshdpTt27g CZ3457")));

        PromotionCodeReq req = new PromotionCodeReq();
        req.setMaterialId("https://3.cn/1-V5Neva");
        System.out.println(JSON.toJSONString(commonJdManager.getPromoteAggByZhetaok(req)));

//        System.out.println(JSON.toJSONString(commonTbManager.tpwdConvertAggByZtk("JxNFWTcR8fy CZ0001 ")));

//        System.out.println(JSON.toJSONString(commonJdManager.jdPromoteQueryWithSku("10082064398107")));
//        System.out.println(JSON.toJSONString(commonTbManager.queryOrder("3596737647696987837")));

    }

}
