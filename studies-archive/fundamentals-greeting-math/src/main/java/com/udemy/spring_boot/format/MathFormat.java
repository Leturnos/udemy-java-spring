package com.udemy.spring_boot.format;

import com.udemy.spring_boot.exception.UnsupportedMathOperationException;
import org.springframework.stereotype.Component;

@Component
public class MathFormat {

    public static Double convertToDouble(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) throw new UnsupportedMathOperationException("Por favor, insira um valor numérico");
        String number = strNumber.replace(",",".");
        return Double.parseDouble(number);
    }

    public static boolean isNumeric(String strNumber) {
        if (strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replace(",",".");
        return number.matches("[-+]?[0-9]*\\.?[0-9]+");
    }
}
