package com.ujiuye.common;




import org.springframework.core.convert.converter.Converter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConverter implements Converter<String, Date> {

    public Date convert(String source) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try{
            Date parse = sdf.parse(source);
            return parse;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }
}
