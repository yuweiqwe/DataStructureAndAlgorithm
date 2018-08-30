package DataStructureAndAlgorithm.util;

/**
 * @author <a href=mailto:yuwei07@meituan.com>余巍</a>
 * @version V1.0
 * @Title: BizTypeEnum
 * @Package com.sankuai.kaeru.yuqing.api.constant
 * @Description: 业务枚举：太平洋前端的业务枚举
 * @date 2018/3/17
 */
public enum BizTypeEnum {

    TRAFFIC(1, "TRAFFIC", "大交通"),
    WAIMAI_USER(2, "WAIMAI_USER", "外卖用户"),
    JINRONG(3, "JINRONG", "金融"),
    WAIMAI_MERCHANT(4, "WAIMAI_MERCHANT", "外卖商家"),
    WAIMAI_HORSEMAN(5, "WAIMAI_HORSEMAN", "外卖骑手"),
    TAXI_USER(6, "TAXI_USER", "打车用户"),
    TAXI_DRIVER(7, "TAXI_DRIVER", "打车司机"),
    PAOTUI_USER(8, "PAOTUI_USER", "跑腿用户"),
    PAOTUI_HORSEMAN(9, "PAOTUI_HORSEMAN", "跑腿骑手"),
    PAOTUI_MERCHANT(10, "PAOTUI_MERCHANT", "跑腿商家"),
    TRAVEL_USER_DOMESTIC(11, "TRAVEL_USER_DOMESTIC", "境内游用户"),
    HOTEL_USER(12, "HOTEL_USER", "酒店用户"),
    TRAVEL_USER_ABOARD(13, "TRAVEL_USER_ABOARD", "境外游用户"),
    SMART_PAY(14, "SMART_PAY", "智能支付"),
    DAOCAN_USER_BIZ(15, "DAOCAN_USER_BIZ", "餐饮用户"),
    DAOCAN_MERCHANT_BIZ(16, "DAOCAN_MERCHANT_BIZ", "餐饮商家"),
    DAOZONG_USER(17, "DAOZONG_USER", "到综用户"),
    MAOYAN_USER_BIZ(18, "MAOYAN_USER_BIZ", "冒烟用户服务"),
    CONSULT_ORDER(19, "CONSULT_ORDER", "综合咨询"),
    HOTEL_MERCHANT(20, "HOTEL_MERCHANT", "酒旅商服"),
    DAOZONG_MERCHANT(21, "DAOZONG_MERCHANT", "到综商服"),
    TRAVEL_SERVICE_BIZ(22, "TRAVEL_SERVICE_BIZ", "旅游商服"),
    PAYMENT_PLATFORM(23, "PAYMENT_PLATFORM", "支付平台"),
    DIANPING_PLATFORM(24, "DIANPING_PLATFORM", "点评平台用户服务"),
    MAOYAN_MERCHENT(25, "MAOYAN_MERCHENT", "猫眼商家"),


    ;

    private int id;

    private String code;

    private String name;

    BizTypeEnum(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
