package cn.elife.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Author：张凯  on 2016/5/25 18:27
 * Blog: bukevin@github.io
 *
 * 这是一个自定义排序的类，专门针对 List 中的数据进行排序；可按指定的方法进行
 */
public class SortCarMerch<E> {
    /**
     * 对列表中的数据按指定字段进行排序
     * @param list
     * @param method
     * @param reverseFlag
     */
    public void sortByMethod(List<E> list, final String method,
                             final boolean reverseFlag){
        Collections.sort(list, new Comparator<Object>() {
            @Override
            public int compare(Object arg1, Object arg2) {
                int result=0;
                try {
                    Method m1=((E)arg1).getClass().getMethod(method,null);
                    Method m2=((E)arg2).getClass().getMethod(method,null);
                    Object obj1=m1.invoke(((E)arg1),null);
                    Object obj2=m2.invoke(((E)arg2),null);
                    if(obj1 instanceof String){
                        //字符串
                        result=obj1.toString().compareTo(obj2.toString());
                    }else if(obj2 instanceof Integer){
                        // 整型（Method的返回参数可以是int的，因为JDK1.5之后，Integer与int可以自动转换了）
                        result = (Integer)obj1 - (Integer)obj2;
                    }else {
                        // 目前尚不支持的对象，直接转换为String，然后比较，后果未知
                        result = obj1.toString().compareTo(obj2.toString());
                        System.err.println("MySortList.sortByMethod方法接受到不可识别的对象类型，转换为字符串后比较返回...");
                    }
                    if (reverseFlag) {
                        // 倒序
                        result = -result;
                    }
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                return result;
            }
        });
    }
}
