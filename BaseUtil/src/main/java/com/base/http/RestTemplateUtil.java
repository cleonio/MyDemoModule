package com.base.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Cleo
 * @description
 * @className RestTemplateUtil
 * @date 2020/11/5 9:53
 */
@Component
public class RestTemplateUtil {

    @Autowired
    private RestTemplate restTemplate = new RestTemplate();;

    public String doGetForObject(String url,Map<String,Object> param){
        String result ;
        if (param==null||param.isEmpty()){
            result = restTemplate.getForObject(url, String.class);
        }else {
            result = restTemplate.getForObject(url, String.class, param);
        }
        return result;
    }

    public String doGetForEntity(String url,Map<String,Object> param){
        ResponseEntity<String> forEntity ;
        if (param==null||param.isEmpty()){
            forEntity = restTemplate.getForEntity(url, String.class);
        }else {
            forEntity = restTemplate.getForEntity(url, String.class, param);
        }
        String body = forEntity.getBody();
        return body;
    }

    public String doPostForObject(String url,Map<String,Object> param){
        String result;
        if (param==null||param.isEmpty()){
            result = restTemplate.postForObject(url, null, String.class);
        }else {
            //HashMap来作为body传递 解析转换不了，换成 MultiValueMap就ok了
            MultiValueMap<String,Object> params = new LinkedMultiValueMap<>();
            params.setAll(param);
            result = restTemplate.postForObject(url,params,String.class);
        }
        return result;
    }

    public String doPostForEntity(String url,Map<String,Object> param){
        ResponseEntity<String> entity;
        if (param==null||param.isEmpty()){
            entity = restTemplate.postForEntity(url, null, String.class);
        }else {
            //HashMap来作为body传递 解析转换不了，换成 MultiValueMap就ok了
            MultiValueMap<String,Object> params = new LinkedMultiValueMap<>();
            params.setAll(param);
            entity = restTemplate.postForEntity(url,params,String.class);
        }
        String body = entity.getBody();
        return body;
    }

    public static void main(String[] args) {
        String url = "https://gis.cx9z.com/map-server/common/queryBuildingFloor?buildingId=00270007";
        String url1 = "https://gis.cx9z.com/map-server/common/queryBuildingFloor?buildingId={buildingId}";
        String urlPost= "https://gis.cx9z.com/map-server/common/queryBuildingFloor";
        String url2 = "https://www.baidu.com";

        Map<String,Object> param = new HashMap();
        param.put("buildingId","00270007");

        RestTemplateUtil restTemplateUtil = new RestTemplateUtil();

       /* String s = restTemplateUtil.doGetForObject(url, null);
        System.out.println(s);*/

        /*
        String map1 = restTemplateUtil.doGetForEntity(url, null);
        System.out.println(map1);
        String map2 = restTemplateUtil.doGetForEntity(url1, param);
        System.out.println(map2);*/


        //String s = restTemplateUtil.doPostForObject(url2, null);
        //System.out.println(s);
        //String s1 = restTemplateUtil.doPostForObject(urlPost, param);
        //System.out.println(s1);

        String s3 = restTemplateUtil.doPostForEntity(urlPost, param);
        System.out.println(s3);
    }
}
