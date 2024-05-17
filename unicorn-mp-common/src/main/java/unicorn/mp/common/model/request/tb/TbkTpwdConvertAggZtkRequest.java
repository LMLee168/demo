package unicorn.mp.common.model.request.tb;

import unicorn.mp.common.model.request.SavvyBuyRequest;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class TbkTpwdConvertAggZtkRequest extends SavvyBuyRequest {

    /**
     * ￥2k12308DjviP￥	需要解析的淘口令
     * 淘口令文案或者二合一链接或者长链接或者短链接或者喵口令。请注意，该参数需要进行Urlencode编码后传入。
     * 淘口令文案：复制这条信息，￥ysuUbqEiTSt￥，打开【手机淘宝】即可。
     * 二合一链接：以https://uland.taobao.com/coupon/edetail?e=开头的链接
     * 长链接：以https://s.click.taobao.com/t?e=开头的链接
     * 短链接：类似https://s.click.taobao.com/R7rDLHw的链接
     * 喵口令：类似http://***.com/s/3X51L?tm=cd5add的链接
     */
    @NonNull
    private String tkl;
    //淘客PID : mm_631370037_2366600350_111482500415
    private String pid = "mm_631370037_2366600350_111482500415";
    /**
     * 对应ztk里授权的id
     */
    private String sid= "165691";
    //渠道关系ID，仅适用于渠道推广场景。
    private String relationId ;
    //会员运营ID
    private String specialId;
    /**
     * signurl=5，返回结果整合高佣转链API、解析商品编号API、全网商品详情API、淘口令创建API，已经自动判断和拼接使用全网G券还是全网S券。
     * signurl=4，返回结果整合高佣转链API、解析商品编号API、商品简版详情API、淘口令创建API，已经自动判断和拼接使用全网G券还是全网S券。
     * signurl=3，返回结果整合高佣转链API、解析商品编号API，已经自动判断和拼接使用全网G券还是全网S券。
     * signurl=0或1或2，返回官方高佣转链接口结果，需要自行判断和拼接使用全网G券或者全网S券。
     * signurl=0，表示直接返回最终结果。
     * signurl=1或2，表示返回淘宝联盟请求地址，大家拿到地址后再用自己的服务器二次请求即可获得最终结果，值为1返回http链接，值为2返回https安全链接。
     */
    private Integer signUrl = 5;



}
