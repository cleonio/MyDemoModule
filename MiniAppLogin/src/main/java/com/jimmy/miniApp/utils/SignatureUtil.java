package com.jimmy.miniApp.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.base.utils.MD5Util;
import com.jimmy.miniApp.constant.RtnInfo;
import com.jimmy.miniApp.model.vo.TokenVO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignatureUtil {
    public static final String signatureKey = "signature";

    /**
     * 加密用的token
     */
    public static final String token = "4C05F6866713455AA720F1DD558F7EB3";
    public static void main(String[] args) {
        String str = MD5Util.encode("accessId=9999&acl=0&bucketId=abc&time=13614314714C05F6866713455AA720F1DD558F7EB3");
        System.out.println(str);
        System.out.println(JSON.toJSONString(RtnInfo.success(TokenVO.builder().token("abc").bindMobile(1).build())));

//        WxLoginDTO param = WxLoginDTO.builder().code("abc").build();
//        System.out.println(md5Signatrue(param));
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","abc");
//        System.out.println(md5Signatrue(map));
    }

    /**
     * md5加密
     * @param param
     * @param
     * @return
     */
    public static  String md5Signatrue(Object param){
        String ascString = getAscString(param);
        ascString+=token;
        System.out.println(ascString);
        return MD5Util.encode(ascString);
    }


    /**
     * 排序输出对象非空的字符串
     * @param param
     * @return
     */
    public static  String getAscString(Object param) {
        String jsonStr = JSON.toJSONString(param);
        StringBuilder sb = new StringBuilder();
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        List<String> keys = new ArrayList<>(jsonObject.keySet());
        Collections.sort(keys);
        for(String key:keys){
            if(StringUtils.isNotEmpty(key) && jsonObject.get(key)!=null){
                if(signatureKey.equals(key)){
                    continue;
                }
                if(sb.length() > 0){
                    sb.append("&");
                }
                sb.append(key).append("=").append(jsonObject.get(key));
            }
        }
        return sb.toString();
    }


}
