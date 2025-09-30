package com.udemy.spring_boot.controllers;

import com.udemy.spring_boot.exception.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.udemy.spring_boot.format.MathFormat.convertToDouble;
import static com.udemy.spring_boot.format.MathFormat.isNumeric;

@RestController
@RequestMapping("/math")
public class MathController {

    @RequestMapping("/sum/{number}/{number1}")
    public Double sum(
            @PathVariable("number") String number,
            @PathVariable("number1") String number2
    ) {
        verificar(number);
        verificar(number2);
        return convertToDouble(number) + convertToDouble(number2);
    }

    @RequestMapping("/sub/{number}/{number1}")
    public Double sub(
            @PathVariable("number") String number,
            @PathVariable("number1") String number2
    ) {
        verificar(number);
        verificar(number2);
        return convertToDouble(number) - convertToDouble(number2);
    }

    @RequestMapping("/mul/{number}/{number1}")
    public Double mul(
            @PathVariable("number") String number,
            @PathVariable("number1") String number2
    ) {
        verificar(number);
        verificar(number2);
        return convertToDouble(number) * convertToDouble(number2);
    }

    @RequestMapping("/div/{number}/{number1}")
    public Double div(
            @PathVariable("number") String number,
            @PathVariable("number1") String number2
    ) {
        verificar(number);
        verificar(number2);
        if (number2.equals("0")) throw new UnsupportedMathOperationException("Impossível dividir por 0");
        return convertToDouble(number) / convertToDouble(number2);
    }

    @RequestMapping("/avg/{number}/{number1}")
    public Double avg(
            @PathVariable("number") String number,
            @PathVariable("number1") String number2
    ) {
        verificar(number);
        verificar(number2);
        return (convertToDouble(number) + convertToDouble(number2)) / 2;
    }

    @RequestMapping("/sqrt/{number}")
    public Double sqrt (@PathVariable("number") String number)  {
        verificar(number);
        return Math.sqrt(convertToDouble(number));
    }

    public void verificar (String number) {
        if (!isNumeric(number)) {
            throw new UnsupportedMathOperationException("Por favor, insira um valor numérico");
        }
    }
}


