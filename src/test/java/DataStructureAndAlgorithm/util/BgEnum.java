package DataStructureAndAlgorithm.util;

/**
 * @author lijianye Created on 16/11/10.
 * ID确保为两位数字.
 */
public enum BgEnum {
    酒店(11, "jiudian", "酒店", BizTypeEnum.HOTEL_USER),
    旅游(12, "lvyou", "旅游", BizTypeEnum.TRAVEL_USER_DOMESTIC),
    机票(13, "jipiao", "机票", BizTypeEnum.TRAFFIC),
    火车票(14, "huochepiao", "火车票", BizTypeEnum.TRAFFIC),
    //电影票(15, "dianyingpiao", "电影票", BizTypeEnum.MAOYAN_MERCHENT),
    外卖(16, "waimai", "外卖", BizTypeEnum.WAIMAI_USER),
    猫眼(17, "maoyan", "猫眼", BizTypeEnum.MAOYAN_USER_BIZ),
    到店餐饮(18, "daocan", "到店餐饮", BizTypeEnum.MAOYAN_USER_BIZ),
    到店综合(19, "daozong", "到店综合", BizTypeEnum.DAOZONG_USER),
    //@Deprecated
    //美食(20, "meishi", "美食", null),
    //@Deprecated
    //KTV(21, "ktv", "ktv", null),
    //@Deprecated
    //休闲娱乐(22, "xiuxianyule", "休闲娱乐", null),
    //@Deprecated
    //基础(23, "jichu", "基础", null),

    打车(24, "others", "打车", BizTypeEnum.TAXI_USER),
    船票(25, "others", "船票", BizTypeEnum.TRAFFIC),
    //    客车票(26, "others", "客车票", BizTypeEnum.TRAFFIC),
    汽车票(26, "others", "汽车票", BizTypeEnum.TRAFFIC),
    猫眼商服(27, "others", "猫眼商服", BizTypeEnum.MAOYAN_MERCHENT),
    外卖众包(28, "others", "外卖众包", BizTypeEnum.WAIMAI_HORSEMAN),
    外卖跑腿(29, "others", "外卖跑腿", BizTypeEnum.PAOTUI_USER),
    支付平台(30, "others", "支付平台", BizTypeEnum.PAYMENT_PLATFORM),
    美团平台(31, "others", "美团平台", null),
    到餐商服(32, "others", "到餐商服", BizTypeEnum.DAOCAN_MERCHANT_BIZ),
    外卖商服(33, "others", "外卖商服", BizTypeEnum.WAIMAI_MERCHANT),
    到综商服(34, "others", "到综商服", BizTypeEnum.DAOZONG_MERCHANT),
    //@Deprecated
    //酒旅商服(35, "others", "酒旅商服", null),
    小微信贷(36, "others", "小微信贷", BizTypeEnum.JINRONG),
    智能支付商服(37, "others", "智能支付商服", BizTypeEnum.SMART_PAY),

    //舆情接入太平洋第一期 新增
    //境外度假用户(38, "others", "境外度假用户", BizTypeEnum.TRAVEL_USER_ABOARD),
    境外游(38, "others", "境外游", BizTypeEnum.TRAVEL_USER_ABOARD),
    国际机票(39, "others", "国际机票", BizTypeEnum.TRAFFIC),
    演出票(40, "others", "演出票", BizTypeEnum.MAOYAN_USER_BIZ),
    跑腿众包(41, "others", "跑腿众包", BizTypeEnum.PAOTUI_HORSEMAN),
    打车司机(42, "others", "打车司机", BizTypeEnum.TAXI_DRIVER),
    跑腿商家(43, "others", "跑腿商家", BizTypeEnum.PAOTUI_MERCHANT),
    掌鱼生鲜(44, "others", "掌鱼生鲜", null),
    //餐饮生态商服(45, "others", "餐饮生态商服", null),
    榛果民宿(46, "others", "榛果民宿", null),
    酒店商服(47, "others", "酒店商服", BizTypeEnum.HOTEL_MERCHANT),
    旅游商服(48, "others", "旅游商服", BizTypeEnum.TRAVEL_SERVICE_BIZ),
    点评平台(49, "others", "点评平台", BizTypeEnum.DIANPING_PLATFORM),
    快驴(50, "others", "快驴", null),


    //舆情接入太平洋第一期 新增

    未分类(99, "others", "未分类", null)
    ;

    private int id;

    private String code;

    private String name;

    private BizTypeEnum bizTypeEnum;

    BgEnum(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }

    BgEnum(int id, String code, String name, BizTypeEnum bizTypeEnum) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.bizTypeEnum = bizTypeEnum;
    }

    public static BgEnum getBgByCode(String code) {
        for (BgEnum bg : BgEnum.values()) {
            if (bg.getCode().equals(code)) {
                return bg;
            }
        }
        return null;
    }

    public static BgEnum getBgById(Integer id) {
        if (id == null) {
            return null;
        }

        for (BgEnum bg : BgEnum.values()) {
            if (id.equals(bg.getId())) {
                return bg;
            }
        }
        return null;
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

    public BizTypeEnum getBizTypeEnum() {
        return bizTypeEnum;
    }
}
