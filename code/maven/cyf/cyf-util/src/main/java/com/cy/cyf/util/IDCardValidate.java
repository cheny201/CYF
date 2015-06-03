package com.cy.cyf.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

/**
 * 身份证校验
 * @author ChenY201
 * @version 1.0
 * @created 2015-5-23 下午6:58:01
 */
public class IDCardValidate {
    /*********************************** 身份证验证开始 ****************************************/
    /**
     * 身份证号码验证 1、号码的结构 公民身份号码是特征组合码，由十七位数字本体码和一位校验码组成。排列顺序从左至右依次为：六位数字地址码， 八位数字出生日期码，三位数字顺序码和一位数字校验码。 2、地址码(前六位数）
     * 表示编码对象常住户口所在县(市、旗、区)的行政区划代码，按GB/T2260的规定执行。 3、出生日期码（第七位至十四位） 表示编码对象出生的年、月、日，按GB/T7408的规定执行，年、月、日代码之间不用分隔符。
     * 4、顺序码（第十五位至十七位） 表示在同一地址码所标识的区域范围内，对同年、同月、同日出生的人编定的顺序号， 顺序码的奇数分配给男性，偶数分配给女性。 5、校验码（第十八位数） （1）十七位数字本体码加权求和公式 S =
     * Sum(Ai * Wi), i = 0, ... , 16 ，先对前17位数字的权求和 Ai:表示第i位置上的身份证号码数字值 Wi:表示第i位置上的加权因子 Wi: 7 9 10 5 8 4 2 1 6 3 7 9 10 5
     * 8 4 2 （2）计算模 Y = mod(S, 11) （3）通过模得到对应的校验码 Y: 0 1 2 3 4 5 6 7 8 9 10 校验码: 1 0 X 9 8 7 6 5 4 3 2
     */

    /**
     * 身份证的有效验证
     * 
     * @param IDStr 身份证号
     * @return 校验结果
     * 			status 0-失败;1-成功
     *  		year 身份证上的年
     *   		month 身份证上的月
     *    		day 身份证上的日
     *    		sex 身份证上的性别（0-女；1-男）
     *     		error 失败信息
     * @throws ParseException
     */
    public static HashMap < String, String > validate(String IDStr) {
        
        // ================= 将最后一位的字母 小写x 转换成 大写X ==================
        IDStr = IDStr.toUpperCase();
        
        HashMap < String, String > result = new HashMap < String, String >();
        result.put("status", "0");
        
        String[] ValCodeArr = { "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" };
        String[] Wi = { "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2" };
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            result.put("error", "身份证号码长度应该为15位或18位。");
            return result;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);
        }
        else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (ValidateUtil.isInteger(Ai) == false) {
            result.put("error", "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。");
            return result;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年
        String strMonth = Ai.substring(10, 12);// 月
        String strDay = Ai.substring(12, 14);// 日
        if (ValidateUtil.isDate(strYear + "-" + strMonth + "-" + strDay, "yyyy-MM-dd") == false) {
            result.put("error", "身份证生日无效。");
            return result;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                result.put("error", "身份证生日不在有效范围。");
                return result;
            }
        }
        catch (Exception e) {
        	result.put("error", "身份证生日不在有效范围。");
            return result;
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            result.put("error", "身份证月份无效。");
            return result;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            result.put("error", "身份证日期无效。");
            return result;
        }
        // =====================(end)=====================

        // ================ 地区码时候有效 ================
        HashMap < String, String > hashMap = GetAreaCode();
        if (hashMap.get(Ai.substring(0, 2)) == null) {
            result.put("error", "身份证地区编码错误。");
            return result;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                result.put("error", "身份证无效，不是合法的身份证号码。");
                return result;
            }
        }
        
        result.put("status", "1");
        result.put("year", strYear);
        result.put("month", strMonth);
        result.put("day", strDay);
        result.put("sex", (Integer.parseInt(Ai.substring(16, 17)) % 2 == 0 ? 0 : 1)+"");
        return result;
        // =====================(end)=====================
    }

    /**
     * 设置地区编码
     * 
     * @return HashMap 对象
     */
    private static HashMap < String, String > GetAreaCode() {
        HashMap < String, String > hashMap = new HashMap < String, String >();
        hashMap.put("11", "北京");
        hashMap.put("12", "天津");
        hashMap.put("13", "河北");
        hashMap.put("14", "山西");
        hashMap.put("15", "内蒙古");
        hashMap.put("21", "辽宁");
        hashMap.put("22", "吉林");
        hashMap.put("23", "黑龙江");
        hashMap.put("31", "上海");
        hashMap.put("32", "江苏");
        hashMap.put("33", "浙江");
        hashMap.put("34", "安徽");
        hashMap.put("35", "福建");
        hashMap.put("36", "江西");
        hashMap.put("37", "山东");
        hashMap.put("41", "河南");
        hashMap.put("42", "湖北");
        hashMap.put("43", "湖南");
        hashMap.put("44", "广东");
        hashMap.put("45", "广西");
        hashMap.put("46", "海南");
        hashMap.put("50", "重庆");
        hashMap.put("51", "四川");
        hashMap.put("52", "贵州");
        hashMap.put("53", "云南");
        hashMap.put("54", "西藏");
        hashMap.put("61", "陕西");
        hashMap.put("62", "甘肃");
        hashMap.put("63", "青海");
        hashMap.put("64", "宁夏");
        hashMap.put("65", "新疆");
        hashMap.put("71", "台湾");
        hashMap.put("81", "香港");
        hashMap.put("82", "澳门");
        hashMap.put("91", "国外");
        return hashMap;
    }

    /*********************************** 身份证验证结束 ****************************************/
 
}
