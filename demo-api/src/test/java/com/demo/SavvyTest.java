package com.demo;

import com.alibaba.fastjson.JSON;
import com.demo.common.manager.CommonJdManager;
import com.demo.common.manager.CommonTbManager;
import com.demo.common.model.request.jd.PromotionCodeReq;
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

//        PromotionCodeReq req = new PromotionCodeReq();
//        req.setMaterialId("10070745581287999");
//        System.out.println(JSON.toJSONString(commonJdManager.getPromoteAggByZhetaok(req)));

        System.out.println(JSON.toJSONString(commonTbManager.tpwdConvertAggByZtk("【手机天喵】https://m.tb.cn/h.5bD3X1I?tk=FiDAdGSLQ5i CZ3457 「爆沫2.0雨屏361男鞋运动鞋2023春季新款防泼水跑鞋减震防滑跑步鞋」" +
                "点击链接直接打开")));

//        System.out.println(JSON.toJSONString(commonJdManager.jdPromoteQueryWithSku("10070745581287")));

    }

}
