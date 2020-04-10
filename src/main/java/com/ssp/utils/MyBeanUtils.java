package com.ssp.utils;

import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;


public class MyBeanUtils {
    /**
     * 获取所有的属性值位空属性名的数组
     * @param source
     * @return
     */
    public static String[] getNullPropertyNames(Object source){
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
        List<Object> nullPropertyNames = new ArrayList<>();
        for (PropertyDescriptor pd: pds) {
            String propertyName = pd.getName();
            if (beanWrapper.getPropertyValue(propertyName) == null){
                nullPropertyNames.add(propertyName);
            }
        }
        return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
    }
}
