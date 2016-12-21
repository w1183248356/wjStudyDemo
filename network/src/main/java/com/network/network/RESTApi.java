package com.network.network;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import rx.Observable;

public interface RESTApi {
    /**
     * -------------------------------系统通用接口开始---------------------------------
     */

    //获取省市区
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> commonGetarea(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取工作人员的名称及地理位置
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> commonGetlocation(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取支付方式
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> commonGetSettlement(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取省级数据
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> commonGetprovince(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取市级数据
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> commonGetcity(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取县区级数据
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> commonGetcounty(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //查询app最新版本
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> commonGetappversion(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //根据坐标信息获取区域ID
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> commonGetappareaid(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //清除全部缓存
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> commonClearcache(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);


    //根据名称获取区域id
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> commonName2areaid(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取自提点信息列表
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> commonGetSelfcollect(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //我要开店初始化数据
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> commonIniteopennetshop(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取首页需展示的数据
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> commonIndex(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //抢购频道数据
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> snappingIndex(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //抢购频道数据
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> getDonationTask(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    /**
     * ---------------------------------系统通用接口结束-------------------------------------
     * */


    /**
     * ---------------------------------用户API接口开始-------------------------------------
     */


    //保存、修改微店店铺信息
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> userOpenshop(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取店铺设置信息（我要开店）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> userShopdetail(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //用户密码找回
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> userFindpassword(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取店铺列表
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> userShoplist(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取店铺信息（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> userShopinfo(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //B2C用户登录
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> userLoginByB2C(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取当前用户的下级用户集(移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> userGettaskpriority(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取用户积分余额列表
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> userGetAmountPoints(@Field("oper") String oper, @Field("type") String type, @Field("pagination") String pagination, @Field("para") String para);

    //根据用户ID修改密码
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> userModifyPasswordByUserNo(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //用户注册
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> userRegister(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取用户信息
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> userUserInfo(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //用户密码修改
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> userModifypassword(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //用户登录
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> userLogin(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * ---------------------------------用户API接口结束-------------------------------------
     * */


    /**
     * ---------------------------------地址管理API接口开始-------------------------------------
     */
    //添加、修改收货人地址（P2C）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> addressSaveOrUpdate(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //设置默认收货地址
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> addressSetStates(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //地址变更状态修改
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> addressUpdateflg(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //申请变更收货地址
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> addressSaveAddrAreaChgReq(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取地址变更申请列表
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> addressGetAddrAreaChgReqList(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //删除收货人地址
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> addressRemove(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //新增、修改收货人地址
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> addressSave(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取用户的收货地址列表
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> addressGetList(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * ---------------------------------地址管理API接口结束-------------------------------------
     * */


    /**
     * ---------------------------------购物车API(B2B/移动办公)接口开始-------------------------------------
     */

    //获取购物车列表(B2B/移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> cartGetlist(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取订单购物车信息(B2B/移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> cartgetListB(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取购物车列表(B2B/移动办公)(新)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> cartGetlistNew(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //删除购物车(B2B/移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> cartRemove(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //添加或者修改购物车(B2B/移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> cartSave(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * ---------------------------------购物车API(B2B/移动办公)结束-------------------------------------
     * */


    /**
     * ---------------------------------优惠券API开始-------------------------------------
     */
    //获取订单可用优惠劵
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> couponGetListB(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取优惠劵列表(B2B、移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> couponGetListA(@Field("oper") String oper, @Field("type") String type, @Field("pagination") String pagination, @Field("para") String para);

    /**
     * ---------------------------------优惠券API结束-------------------------------------
     * */

    /**
     * ---------------------------------商品API开始-------------------------------------
     */
    //获取商品详情
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> productGetdetail(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取商品的所有类别集合（b2b/移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> productGetCates(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取分类一级界面（b2b/移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> productGetRootCates(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取分类二级界面（b2b/移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> productGetSubCates(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);


    //获取商品的所有品牌集合（b2b/移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> productGetBrand(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //根据关键词搜索商品
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> productGetSearch(@Field("oper") String oper, @Field("type") String type, @Field("pagination") String pagination, @Field("para") String para);

    //查询已登录用户的常订商品
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> productGetStkUsual(@Field("oper") String oper, @Field("type") String type, @Field("pagination") String pagination, @Field("para") String para);
    //提现
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> takeCash(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    //获取与此商品相关商品
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> productRelateds(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //根据条形码获取商品详情
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> productGetdetailpluc(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //保存商品到临时表
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> productSaveProduct(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取新增商品
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> productFindStkNews(@Field("oper") String oper, @Field("type") String type, @Field("pagination") String pagination, @Field("para") String para);

    //获取套餐商品
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> productFindCombo(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    //验证银行卡有效性
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> validBankCard(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    //获取类别对应下类别
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> productGetCateByCatId(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    //绑定银行卡
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> bindBankCard(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    //获取 每日特价（限时促销）商品
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> productFindWebPromItem(@Field("oper") String oper, @Field("type") String type, @Field("pagination") String pagination, @Field("para") String para);
    //获取银行卡所在支行
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> findBankCodes(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    //获取类别对应下的所有商品
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> productGetList(@Field("oper") String oper, @Field("type") String type, @Field("pagination") String pagination, @Field("para") String para);
    //选择提现银行卡接口
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> updateDefFlg(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    /**
     * ---------------------------------商品API结束-------------------------------------
     * */

    /**
     * ---------------------------------订单API开始-------------------------------------
     */
    //获取订单详情(B2B、移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderGetDetail(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //添加物流评价
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderSavelogisticsreview(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //修改订单状态
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderSetStates(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //通过接口类型代码查询一段时间内的订单日志
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderOrderaction(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //订单确认
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderConfirm(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取套餐详情接口(移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderGetcombo(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //设置配送员(移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderSetcourieruse(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //确认订单(移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderSetorderstate(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //订单差异(移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderSetdiffremark(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //根据用户获取订单列表
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderGetlist2(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取差异类型(移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderGetdiffreason(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //订单支付提交
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderPaymentsubmit(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    //获取银联支付流水号
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> getUnionPaystr(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    //银联支付验签
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> verifyPaymentsuccess(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //订单支付宝支付提交
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderGetalipaystr(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //订单微信支付提交
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderGetwxpaystr(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取用户积分、余额
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderGetsubaccount(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    //获取银行卡列表
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> findBankCardLists(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    //查询子账户余额
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> getSubamount(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    //银行卡解绑
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> unbindCard(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    //添加商品评价
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderSavereview(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //查询单个商品全部评论
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderGetreviewstkc(@Field("oper") String oper, @Field("type") String type, @Field("pagination") String pagination, @Field("para") String para);

    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderGetreview(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //根据订单id获取订单详情
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderGetdetailslist(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //支付状态更新
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderPaymentsuccess(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //查询店配送员所有店铺列表
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderGetlogisticorder(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //查询配送员店铺订单列表(订单详情)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderGetlogisticitem(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //批量确认订单(移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderBatchorderstate(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //根据条件查询订单(移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderQuery(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //提交订单（B2B、移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderSubmit(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取用户订单列表(B2B、移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> orderGetList(@Field("oper") String oper, @Field("type") String type, @Field("pagination") String pagination, @Field("para") String para);

    /**
     * ---------------------------------订单API结束-------------------------------------
     * */


    /**
     * ---------------------------------考勤模块API (移动办公)开始-------------------------------------
     */
    //查询当天是否签到签退 (移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> attendanceChecksign(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取用户考勤列表信息 (移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> attendanceGetlist(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取具体日期的考勤详情 (移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> attendanceGetdetail(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取定期上报的位置信息 (移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> attendanceGetlocate(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取上报数据类型 (移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> attendanceLocatecode(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //保存定期上报的位置信息 (移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> attendanceLocate(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //保存用户考勤信息接口 (移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> attendanceSign(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * ---------------------------------考勤模块API (移动办公)结束-------------------------------------
     * */


    /**
     * ---------------------------------公告通知API (移动办公)开始-------------------------------------
     */
    //获取公告通知的列表信息 (移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> bulletinGetlist(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取公告详情 (移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> bulletinGetdetail(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);


    /**
     * ---------------------------------公告通知API (移动办公)结束-------------------------------------
     * */


    /**
     * ---------------------------------物流API (移动办公)结束-------------------------------------
     */

    //查看订单物流信息
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> logisticsGetList(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * ---------------------------------物流API (移动办公)结束-------------------------------------
     * */


    /**
     * ---------------------------------仓库拣货(移动办公)结束-------------------------------------
     */
    //拣货订单的数量 (移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> pickOrderCount(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //拣货商品(移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> pickStkMas(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //拣货订单(移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> pickOrder(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //绑定或取消拣货员
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> pickingUpdatepicking(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //拣货员仓库查询
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> pickingGetwhclist(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //查询仓库线路
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> pickingGetWhcRoute(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //拣货订单查询
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> pickingGetpickinglist(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //提交捡货结果
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> pickingUpOrderMasPickFlg(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * ---------------------------------仓库拣货(移动办公)结束-------------------------------------
     * */


    /**
     * ---------------------------------价格采集API(移动办公)开始-------------------------------------
     */

    //获取价格采集记录列表（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> priceGetlist(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取具体某个价格采集记录详情（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> priceGetdetail(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //保存、修改某个价格采集记录（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> priceSaveprice(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //删除某个价格采集记录详情（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> priceDelprice(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * ---------------------------------价格采集API(移动办公)结束-------------------------------------
     * */

    /**
     * ---------------------------------促销、买赠、套装接口开始-------------------------------------
     */
    //根据商品编码获取促销列表
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> webpromGetstkcprom(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //促销频道首页数据
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> webpromPrommasindex(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //用户绑定优惠券
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> webpromSavecouponcam(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * ---------------------------------促销、买赠、套装接口结束-------------------------------------
     * */

    /**
     * ---------------------------------360业务员，物流人员个人中心API开始-------------------------------------
     */
    //获取用户是否O2O店铺
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> legworkGetUserO2OFlg(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取客户物恋外勤信息
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> legworkGetwldetail(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //业务员的业绩
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> legworkServiceRank(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //物流人员成就
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> legworkLogisticsRank(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //修改业务员的个人信息
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> legworkSaveUserInfo(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //得到用户的月订单排行
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> legworkFindUserRank(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * ---------------------------------360业务员，物流人员个人中心API结束-------------------------------------
     * */

    /**
     * ---------------------------------新店登记API(移动办公)开始-------------------------------------
     */
    //获取店铺类别（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopregGetShopCatList(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //删除配送点申请
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopregDelB2cappLmReq(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //新店升级为会员店铺（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopregShopupgrade(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //店铺升级（查询系统参数）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopregQuerySetting(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //根据当前坐标获取地标列表
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopregSearchLmMas(@Field("oper") String oper, @Field("type") String type, @Field("pagination") String pagination, @Field("para") String para);

    //获取配送点店铺列表
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopregSearchLmShop(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //禁用启用地标店铺
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopregUpdateLmShop(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //附近新店列表（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopregGetnearbystores(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //增加配送点申请
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopregAddB2cappLmReq(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取配送点申请列表
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopregGetB2cappLmReqList(@Field("oper") String oper, @Field("type") String type, @Field("pagination") String pagination, @Field("para") String para);

    //更新用户位置信息
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopregUpUserPosition(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //新店登记（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopregSave(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * ---------------------------------新店登记API(移动办公)结束-------------------------------------
     * */

    /**
     * ---------------------------------店铺拜访API（移动办公）结束-------------------------------------
     */
    //店铺列表（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopvisitGetlist(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //店铺的详细信息（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopvisitDetail(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //保存位置信息（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopvisitSavelocation(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //保存店铺拜访记录（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopvisitSavevisit(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //查询店铺记录详情（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopvisitVisitdetail(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取店铺拜访记录列表（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopvisitVisitlist(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //根据当前位置获取店铺列表（移动办公）由近到远
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopvisitGetnearbyshop(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获得未升级（新注册）店铺的详细信息
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopvisitGetnewshop(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //根据当前位置、距离获取店铺列表（移动办公）由近到远（包含新登记店铺）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> shopvisitGetnearbyshoplist(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * ---------------------------------店铺拜访API（移动办公）结束-------------------------------------
     * */

    /**
     * ---------------------------------短信API(B2B/移动办公)开始-------------------------------------
     */
    //发送短信验证码前校验(新店升级)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> smsValidUser(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //发送短信验证码(B2B、移动办公)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> smsSendMobileValidCode(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //验证手机验证码(B2C登录)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> smsBundingMobileByAPP(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //绑定手机号
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> bundingMobile(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //验证短信验证码(App)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> smsValidAppMobileValidCode(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //发送短信验证码(App)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> sendAppMobileValidCode(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    //发送短信验证码(App)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> sendAppMobileValidCodeByType(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //根据手机号获取验证码(APP找回密码)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> smsSendMobileValidateCode(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //验证手机验证码(APP找回密码)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> smsCheckMobileValidateCode(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //验证手机验证码(B2C登录)
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> smsValidB2CMobileValidateCode(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * ---------------------------------短信API(B2B/移动办公)结束-------------------------------------
     * */

    /**
     * ---------------------------------任务交办API（移动办公）开始-------------------------------------
     */
    //获取任务的详细内容（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> taskDetail(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取任务类别（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> taskGettaskcat(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取任务优先级主数据（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> taskGettaskprioritys(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取当前用户的任务列表（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> taskList(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //保存任务（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> taskSave(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);


    /**
     * ---------------------------------任务交办API（移动办公）结束-------------------------------------
     * */

    /**
     * ---------------------------------巡店API（移动办公）开始-------------------------------------
     */
    //获取巡店计划列表（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> visitplanGetvisitall(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //获取某个巡店计划内容（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> visitplanGetvisitbyid(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //保存任务执行结果（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> visitplanUpdatevpt(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    //保存巡店计划开始和结束动作信息（移动办公）
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> visitplanUpdatevisitstartorend(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * ---------------------------------巡店API（移动办公）结束-------------------------------------
     * */

    /**
     * ---------------------------------文件下载 开始-------------------------------------
     */

    @Streaming
    @GET("ottsoft/{filename}")
    Call<ResponseBody> downFile(@Path("filename") String fileName);

    @GET("qpwa-app/app/versionUpdate.jhtml")
    Observable<String> getUpdateInfo(@Query("verNum") int verNum, @Query("appCode") String packageName, @Query("verSource") int verSource);
    /**
     * ---------------------------------文件下载 结束-------------------------------------
     * */

    /**
     * 获取活动详情
     * */
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> getActivityDet(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * ---------------------------------获取七牛token 开始-------------------------------------
     */

    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> getUpToken(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    /**
     * ---------------------------------获取七牛token 结束-------------------------------------
     */

    /**
     * 检查是否注册环信
     */
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> checkIsRegisteredIm(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * 上传头像
     */
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> saveHeadImg(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

    /**
     * 根据环信ID获取用户的昵称和头像地址
     */
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> getHXInfoById(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);
    /**
     *获取供应商客服
     */
    @FormUrlEncoded
    @POST("handle/execute.jhtml")
    Observable<String> findsalesmen(@Field("oper") String oper, @Field("type") String type, @Field("para") String para);

}
