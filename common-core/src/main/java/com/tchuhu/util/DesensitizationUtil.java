package com.tchuhu.util;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName Desensitization
 * @Description 脱敏
 * @Author tchuhu
 * @Date 2021/7/26 17:40
 * @Version 1.0
 */
public class DesensitizationUtil {
    /**
     * 手机号脱敏
     * @param cellphoneNo
     * @return
     */
    public static String desenCellphone(String cellphoneNo) {
        if ((cellphoneNo == null) || (cellphoneNo.trim().length() != 11)) {
            return cellphoneNo;
        }
        return cellphoneNo.substring(0, 3) + "****" + cellphoneNo.substring(cellphoneNo.length() - 4);
    }
    /**
     * 邮箱脱敏
     * @param email
     * @return
     */
    public static String desenEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return "";
        }
        int index = StringUtils.indexOf(email, "@");
        if (index <= 1) {
            return email;
        }
        return StringUtils.rightPad(StringUtils.left(email, 1), index, "*")
                .concat(StringUtils.mid(email, index, email.length()));
    }
    /**
     * 身份证脱敏
     * @param idCardNo
     * @return
     */
    public static String desenIDCardNo(String idCardNo) {
        return desenCardNo(idCardNo);
    }

    /**
     * 银行卡脱敏
     * @param bankCardNo
     * @return
     */
    public static String desenBankCardNo(String bankCardNo) {
        return desenCardNo(bankCardNo);
    }

    /**
     * 卡号脱敏
     * @param cardNo
     * @return
     */
    private static String desenCardNo(String cardNo) {
        int minLenth = 8;
        if ((cardNo == null) || (cardNo.trim().length() <= minLenth)) {
            return cardNo;
        }
        cardNo = cardNo.trim();
        int length = cardNo.length();
        String firstFourNo = cardNo.substring(0, 4);
        String lastFourNo = cardNo.substring(length - 4);
        String mask = "";
        for (int i = 0; i < length - minLenth; i++) {
            mask = mask + "*";
        }
        return firstFourNo + mask + lastFourNo;
    }

}
