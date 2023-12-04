package com.github.curenosm;


interface Converter<F, T> {
  T convert(F value);
}

public class RomanDecimalConverter implements Converter<Integer, String> {

  @Override
  public String convert(Integer value) {
    var units = value % 10;
    value /= 10;

    var tens = value % 100;
    value /= 10;

    var hundreds = value % 100;
    value /= 10;

    var thousands = value % 1000;

    return "I";
  }

}
