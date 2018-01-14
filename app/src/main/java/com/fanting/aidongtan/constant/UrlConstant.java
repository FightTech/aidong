package com.fanting.aidongtan.constant;

/**
 * Created by djj on 2016/12/22.
 */

public class UrlConstant {
    public static String baseUrl = "http://123.56.202.232:8082/";

    /**
     * 安装和升级客户端
     **/
    public static String clientInstallUrl = baseUrl + "user/client/install";
    /**
     * 启动客户端
     **/
    public static String clientLaunchUrl = baseUrl + "user/client/start";
    /**
     * 用户注册
     **/
    public static String userRegisterUrl = baseUrl + "user/register";
    /**
     * 用户登录
     **/
    public static String userLoginUrl = baseUrl + "user/login";
    /**
     * 忘记密码
     **/
    public static String forgetPassworUrl = baseUrl + "user/forgetPassword";
    /**
     * 私信提醒设置
     **/
    public final static String setPvtLetterUrl = baseUrl + "user/center/pvtLetter";
    /**
     * 系统提醒设置
     **/
    public static String setSystemMsgUrl = baseUrl + "user/center/systemMsg";
    /**
     * 留言提醒设置
     **/
    public static String setTreasureMsgUrl = baseUrl + "user/center/treasureMsg";
    /**
     * 获取设置信息
     **/
    public static String getNotificationSettingUrl = baseUrl + "user/center/getSettingInfo";
    /**
     * 获取设置信息
     **/
    public static String getPersonalInfoUrl = baseUrl + "user/center/personalInfo";
    /**
     * 退出登陆
     **/
    public static String logoutUrl = baseUrl + "user/center/logout";
    /**
     * 修改昵称
     **/
    public static String alterNicknameUrl = baseUrl + "user/center/modifyNickname";
    /**
     * 修改性别
     **/
    public static String alterGenderUrl = baseUrl + "user/center/modifyGender";
    /**
     * 修改职业
     **/
    public static String alterCareerUrl = baseUrl + "user/center/modifyCareer";
    /**
     * 添加手机号
     **/
    public static String addPhoneNumberUrl = baseUrl + "user/center/addTelephone";
    /**
     * 修改密码
     **/
    public static String alterPasswordUrl = baseUrl + "user/center/modifyPassword";
    /**
     * 获取常用地址列表
     **/
    public static String getAddressListUrl = baseUrl + "user/center/getAddresses";
    /**
     * 添加常用地址
     **/
    public static String addAddressUrl = baseUrl + "user/center/addAddress";
    /**
     * 添加常用地址
     **/
    public static String editAddressUrl = baseUrl + "user/center/modifyAddress";

    /**
     * 删除常用地址
     **/
    public static String delAddressUrl = baseUrl + "user/center/delAddress";
    /**
     * 设置默认常用地址
     **/
    public static String setDefaultAddressUrl = baseUrl + "user/center/modifyDefaultAddress";
    /**
     * 首页（海外箱子）
     **/
    public static String goodsSpGetByMainUrl = baseUrl + "goods/goodsSpGetByMain";


    /**
     * 首页（广告）
     **/
    public static String goodsAdvertGetByMain = baseUrl + "goods/goodsAdvertGetByMain";

    /**
     * 推荐商品
     **/
    public static String goodsSpGetBySellerUrl = baseUrl + "goods/goodsSpGetBySeller";
    /**
     * 买家首页
     **/
    public static String goodsInfoGetByBuyerUrl = baseUrl + "goods/goodsInfoGetByBuyer";
    /**
     * 买家商品搜索
     **/
    public static String goodsInfoSearchByBuyerUrl = baseUrl + "goods/goodsInfoSearchByBuyer";
    /**
     * 卖家商品搜索
     **/
    public static String goodsInfoSearchBySellerUrl = baseUrl + "/goods/goodsInfoSearchBySeller";
    /**
     * 商品添加（买家发布商品）
     **/
    public static String goodsInfoAddByBuyerUrl = baseUrl + "goods/goodsInfoAddByBuyer";
    /**
     * 商品添加（卖家发布商品）
     **/
    public static String goodsInfoAddBySellerUrl = baseUrl + "goods/goodsInfoAddBySeller";
    /**
     * 商品修改（编辑商品）
     **/
    public static String goodsInfoEditUrl = baseUrl + "goods/goodsInfoUpdate";
    /**
     * 卖家首页（求购清单）
     **/
    public static String goodsInfoGetBySellerUrl = baseUrl + "goods/goodsInfoGetBySeller";
    /**
     * oss文件上传获取token
     **/
    public static String getToken = baseUrl + "oss/getToken";
    /**
     * oss文件上传回调地址
     **/
    public static String uploadCallbackUrl = baseUrl + "oss/ossCallback";
    /**
     * 微信获取accesstoken
     **/
    public static String getWXAccessTokenUrl ="https://api.weixin.qq.com/sns/oauth2/access_token";
    /**
     * 微信获取个人信息
     **/
    public static String getWXUserInfoUrl ="https://api.weixin.qq.com/sns/userinfo";
    /**
     * 第三方登录
     **/
    public static String thirdLoginUrl =baseUrl+"user/thirdLogin";
    /**
     * 修改头像
     **/
    public static String modifyHeadUrl =baseUrl+"user/center/modifyHead";
    /**
     * 添加意见反馈
     **/
    public static String addFeedBackUrl =baseUrl+"/user/center/feedback";
    /**
     * 支付宝支付
     **/
    public static String alipayUrl =baseUrl+"/pay/appPay";
}
