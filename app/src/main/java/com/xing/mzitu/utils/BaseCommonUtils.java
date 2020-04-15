package com.xing.mzitu.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * created by xinghe
 * <p>
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 * <p>
 * date : 2020/4/7
 * <p>
 * version :
 * <p>
 * desc :
 */
public class BaseCommonUtils {

    public static boolean checkCollectionSize(Collection<?> collection, int size) {
        return null != collection && collection.size() >= size;
    }

    public static boolean checkCollectionIndex(Collection<?> collection , int index){
        return null != collection && collection.size() > index && index >= 0;

    }

    public static boolean checkCollection(Collection<?> collection) {
        return null != collection && !collection.isEmpty();
    }

    public static <T> List<T> getSafeArrayList(List<T> list) {
        return null == list ? new ArrayList<T>() : list;
    }

    public static int safeParseInt(String strValue, int defaultValue){
        try {
            return Integer.parseInt(strValue);
        }catch (NumberFormatException ignore){
            return defaultValue;
        }
    }

    public static int safeParseInt(String strValue){
        return safeParseInt(strValue,0);
    }


}
