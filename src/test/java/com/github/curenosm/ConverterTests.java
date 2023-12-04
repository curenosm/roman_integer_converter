package com.github.curenosm;


import org.junit.jupiter.api.Test;

class ConverterTests {

  private static final Converter<Integer, String> romanToDecimal = new RomanDecimalConverter();

  @Test
  void testIntegerToRoman_value_i() {
    assert romanToDecimal.convert(1).equals("I");
  }
}
