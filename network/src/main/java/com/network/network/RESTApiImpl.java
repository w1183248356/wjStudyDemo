package com.network.network;


public class RESTApiImpl {
    /**
     * -------------------------------------------系统通用接口开始----------------------------
     */

    //获取省市区
//    public static Observable<CommonResult> commonGetarea(Map<String, String> map, ProgressDialog dialog) {
//        return RetrofitHelp.getApi().commonGetarea("getarea", "common", NetworkUtil.map2Json(map))
//                .compose(new RxResponTransform(dialog))
//                .compose(new RxCommonResultTransform());
//    }


    //获取省级数据
//    public static Observable<AreaInfo> commonGetprovince(Map<String, String> map, ProgressDialog dialog) {
//        return RetrofitHelp.getApi().commonGetprovince("getprovince", "common", NetworkUtil.map2Json(map))
//                .compose(new RxResponTransform(dialog))
//                .map(result -> {
//                    if (result != null) {
//                        AreaInfo areaInfo = new Gson().fromJson(result.toString(), AreaInfo.class);
//                        return areaInfo;
//                    }
//                    return null;
//                });
//    }

}
