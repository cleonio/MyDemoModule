package com.base.http;


import com.alibaba.fastjson.JSONObject;
import com.base.utils.StringUtils;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Cleo
 * @description
 * @className OKHttpClientUtil
 * @date 2020/11/5 9:32
 */

public class OKHttpClientUtil {

    private static final Logger logger = LoggerFactory.getLogger(OKHttpClientUtil.class);

    private static OkHttpClient client;

    private static final String DEFAULT_MEDIA_TYPE = "application/json; charset=utf-8";

    private static final int CONNECT_TIMEOUT = 5;

    private static final int READ_TIMEOUT = 7;

    private static final String USER_MESSAGE = "userMessage";

    private static final String DEVELOPER_MESSAGE = "developerMessage";

    private static final String GET = "GET";

    private static final String POST = "POST";

    /**
     * 单例模式  获取类实例
     *
     * @return client
     */
    private static OkHttpClient getInstance() {
        if (client == null) {
            synchronized (OkHttpClient.class) {
                if (client == null) {
                    client = new OkHttpClient.Builder()
                            .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                            .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return client;
    }

    /**
     *
     * @param url
     * @param callMethod
     * @return
     * @author Cleo
     * @throws
     * @date 2020/11/5 10:01
     */
    public static String doGet(String url, String callMethod) throws HttpStatusException {

        try {
            long startTime = System.currentTimeMillis();
            addRequestLog(GET, callMethod, url, null, null);

            Request request = new Request.Builder().url(url).build();
            // 创建一个请求
            Response response = getInstance().newCall(request).execute();
            int httpCode = response.code();
            String result;
            if (response.body() != null) {
                result = response.body().string();
                addResponseLog(httpCode, result, startTime);
            } else {
                throw new RuntimeException("exception in OkHttpUtil,response body is null");
            }
            return handleHttpResponse(httpCode, result, response);
        } catch (Exception ex) {
            handleHttpThrowable(ex, url);
            return StringUtils.EMPTY;
        }
    }


    public static String doPost(String url, String postBody, String mediaType, String callMethod) throws HttpStatusException {
        try {
            long startTime = System.currentTimeMillis();
            addRequestLog(POST, callMethod, url, postBody, null);

            MediaType createMediaType = MediaType.parse(mediaType == null ? DEFAULT_MEDIA_TYPE : mediaType);
            Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(createMediaType, postBody))
                    .build();

            Response response = getInstance().newCall(request).execute();
            int httpCode = response.code();
            String result;
            if (response.body() != null) {
                result = response.body().string();
                addResponseLog(httpCode, result, startTime);
            } else {
                throw new IOException("exception in OkHttpUtil,response body is null");
            }
            return handleHttpResponse(httpCode, result, response);
        } catch (Exception ex) {
            handleHttpThrowable(ex, url);
            return StringUtils.EMPTY;
        }
    }

    public static String doPost(String url, Map<String, String> parameterMap, String callMethod) throws HttpStatusException {
        try {
            long startTime = System.currentTimeMillis();
            List<String> parameterList = new ArrayList<>();
            FormBody.Builder builder = new FormBody.Builder();
            if (parameterMap.size() > 0) {
                parameterMap.keySet().forEach(parameterName -> {
                    String value = parameterMap.get(parameterName);
                    builder.add(parameterName, value);
                    parameterList.add(parameterName + ":" + value);
                });
            }

            addRequestLog(POST, callMethod, url, null, Arrays.toString(parameterList.toArray()));

            FormBody formBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            Response response = getInstance().newCall(request).execute();
            String result;
            int httpCode = response.code();
            if (response.body() != null) {
                result = response.body().string();
                addResponseLog(httpCode, result, startTime);
            } else {
                throw new IOException("exception in OkHttpUtil,response body is null");
            }
            return handleHttpResponse(httpCode, result, response);

        } catch (Exception ex) {
            handleHttpThrowable(ex, url);
            return StringUtils.EMPTY;
        }
    }


    private static void addRequestLog(String method, String callMethod, String url, String body, String formParam) {
        logger.info("===========================request begin================================================");
        logger.info("URI          : {}", url);
        logger.info("Method       : {}", method);
        if (StringUtils.isNotBlank(body)) {
            logger.info("Request body : {}", body);
        }
        if (StringUtils.isNotBlank(formParam)) {
            logger.info("Request param: {}", formParam);
        }
        logger.info("---------------------------request end--------------------------------------------------");
    }

    private static void addResponseLog(int httpCode, String result, long startTime) {
        long endTime = System.currentTimeMillis();
        logger.info("Status       : {}", httpCode);
        logger.info("Response     : {}", result);
        logger.info("Time         : {} ms", endTime - startTime);
        logger.info("===========================response end================================================");
    }

    private static String handleHttpResponse(int httpCode, String result, Response response) throws HttpStatusException {
        if (response.isSuccessful()) {
            return result;
        }
        HttpStatus httpStatus = HttpStatus.valueOf(httpCode);
        if (result.contains(USER_MESSAGE) && result.contains(DEVELOPER_MESSAGE)) {
            JSONObject jsonObject = JSONObject.parseObject(result);
            String userMessage = (String) jsonObject.get(USER_MESSAGE);
            String developerMessage = (String) jsonObject.get(DEVELOPER_MESSAGE);
            throw new HttpStatusException(httpStatus, userMessage,developerMessage);
        } else {
            throw new HttpStatusException(httpStatus);
        }

    }

    private static void handleHttpThrowable(Exception ex, String url) throws HttpStatusException {
        if (ex instanceof HttpStatusException) {
            throw (HttpStatusException) ex;
        }
        logger.error("OkHttp error url: " + url, ex);
        if (ex instanceof SocketTimeoutException) {
            throw new RuntimeException("request time out of OkHttp when do get:" + url);
        }
        throw new RuntimeException(ex);
    }


    public static void main(String[] args) {
        String url = "https://gis.cx9z.com/map-server/common/queryBuildingFloor?buildingId=00270007";
        try {
            String test = doGet(url, "test");
            System.out.println(test);
        } catch (HttpStatusException e) {
            e.printStackTrace();
        }
    }

}
