package com.github.curenosm;


interface Converter<F, T> {
  T convert(F value);
}

public class RomanDecimalConverter implements Converter<Integer, String> {

  @Override
  public String convert(Integer value) {
    return "I";
  }
}
