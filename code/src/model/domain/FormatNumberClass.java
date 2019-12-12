package model.domain;

import java.text.DecimalFormat;

public class FormatNumberClass {
    public static String parseToStringTwoDecimals(double number){
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(number);
    }
}
