package com.example.library;

import android.text.TextUtils;
import android.util.Log;

import static com.example.library.BankCardInfo.bankBin;
import static com.example.library.BankCardInfo.bankName;

/**
 * Created by yuyuhang on 2018/1/23.
 */
public class BankCardUtils {


    public static String getNameOfBank(char[] charBin, int offset) {
        long longBin = 0;

        for (int i = 0; i < 6; i++) {
            longBin = (longBin * 10) + (charBin[i + offset] - 48);
        }
        Log.i("BankCardInfo", "bankBin: " + longBin);

        int index = binarySearch(bankBin, longBin);

        if (index == -1) {
            return "";
        }
        return bankName[index];

    }

    //二分查找方法
    public static int binarySearch(long[] srcArray, long des) {
        int low = 0;
        int high = srcArray.length - 1;
        while (low <= high) {
            int middle = (low + high) / 2;
            if (des == srcArray[middle]) {
                return middle;
            } else if (des < srcArray[middle]) {
                high = middle - 1;
            } else {
                low = middle + 1;
            }
        }
        return -1;
    }

    /**
     * 校验银行卡卡号
     */
    public static boolean checkBankCard(String cardId) {
        if (TextUtils.isEmpty(cardId)) return false;

        char bit = getBankCardCheckCode(cardId.substring(0, cardId.length() - 1));
        return bit != 'N' && cardId.charAt(cardId.length() - 1) == bit;
    }

    /**
     * 从不含校验位的银行卡卡号采用 Luhm 校验算法获得校验位
     */
    public static char getBankCardCheckCode(String nonCheckCodeCardId) {
        if (TextUtils.isEmpty(nonCheckCodeCardId)
                || !nonCheckCodeCardId.matches("\\d+")
                || nonCheckCodeCardId.length() < 16
                || nonCheckCodeCardId.length() > 19) {
            //如果传的不是数据返回N
            return 'N';
        }
        char[] chs = nonCheckCodeCardId.trim().toCharArray();
        int luhmSum = 0;
        for (int i = chs.length - 1, j = 0; i >= 0; i--, j++) {
            int k = chs[i] - '0';
            if (j % 2 == 0) {
                k *= 2;
                k = k / 10 + k % 10;
            }
            luhmSum += k;
        }
        return (luhmSum % 10 == 0) ? '0' : (char) ((10 - luhmSum % 10) + '0');
    }

}
