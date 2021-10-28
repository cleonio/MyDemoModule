package com.base.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LanguageUtil {

    public static void main(String[] args) {
        String s = "uweruYha一十二fss三五";
        boolean containChinese = isContainChinese(s);
        System.out.println(containChinese);
        String s1 = covertChineseNumberToArabNum(s);
        System.out.println(s1);
    }

  /**
   *
   * @param str
   * @return
   * @author Cleo
   * @throws
   * @date 2020/11/4 10:01
   */

    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }




    /**
     *	将中文數字转化成阿拉伯数字
     * @param str 要转化成阿拉伯数字的字符
     * @return
     */
    public static String covertChineseNumberToArabNum(String str){
        //如果为空，直接返回空
        if(StringUtils.isBlank(str)){
            return null;
        }
        String regex = "[一二三四五六七八九十百千万亿]+";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        boolean find = matcher.find();
        if(find){
            //找到的数字
            String findNumbers = matcher.group();
            //转化成阿拉伯数字
            int arabiaNumber = chineseNumber2Int(findNumbers);
            //替换后的字符
            String convertNumber = matcher.replaceAll(arabiaNumber+"");
            return convertNumber;
        }else{
            return null;
        }
    }

    /**
     * 中文數字转阿拉伯数组【十万九千零六十  --> 109060】
     * @param chineseNumber 中文汉子
     * @return int
     */
    @SuppressWarnings("unused")
    private static int chineseNumber2Int(String chineseNumber){
        int result = 0;
        int temp = 1;//存放一个单位的数字如：十万
        int count = 0;//判断是否有chArr
        char[] cnArr = new char[]{'一','二','三','四','五','六','七','八','九'};
        char[] chArr = new char[]{'十','百','千','万','亿'};
        for (int i = 0; i < chineseNumber.length(); i++) {
            boolean b = true;//判断是否是chArr
            char c = chineseNumber.charAt(i);
            for (int j = 0; j < cnArr.length; j++) {//非单位，即数字
                if (c == cnArr[j]) {
                    if(0 != count){//添加下一个单位之前，先把上一个单位值添加到结果中
                        result += temp;
                        temp = 1;
                        count = 0;
                    }
                    // 下标+1，就是对应的值
                    temp = j + 1;
                    b = false;
                    break;
                }
            }
            if(b){//单位{'十','百','千','万','亿'}
                for (int j = 0; j < chArr.length; j++) {
                    if (c == chArr[j]) {
                        switch (j) {
                            case 0:
                                temp *= 10;
                                break;
                            case 1:
                                temp *= 100;
                                break;
                            case 2:
                                temp *= 1000;
                                break;
                            case 3:
                                temp *= 10000;
                                break;
                            case 4:
                                temp *= 100000000;
                                break;
                            default:
                                break;
                        }
                        count++;
                    }
                }
            }
            if (i == chineseNumber.length() - 1) {//遍历到最后一个字符
                result += temp;
            }
        }
        return result;
    }
}
