package com.github.curenosm;


import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

class ConverterTests {

  private static final Converter<Integer, String> romanToDecimal = new RomanDecimalConverter();
  private static final Logger logger = Logger.getLogger(ConverterTests.class.getName());

  @Test
  void testIntegerToRoman_value_1() {
    var res = romanToDecimal.convert(1);
    assert res.equals("I");
  }

  @Test
  void testIntegerToRoman_value_4() {
    var res = romanToDecimal.convert(4);
    assert res.equals("IV");
  }

  @Test
  void testIntegerToRoman_value_13() {
    var res = romanToDecimal.convert(13);
    assert res.equals("XIII");
  }

  @Test
  void testIntegerToRoman_value_45() {
    var res = romanToDecimal.convert(45);
    assert res.equals("XLV");
  }

  @Test
  void testIntegerToRoman_value_99() {
    var res = romanToDecimal.convert(99);
    assert res.equals("XCIX");
  }

}
