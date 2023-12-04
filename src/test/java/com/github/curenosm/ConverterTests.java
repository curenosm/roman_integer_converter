package com.github.curenosm;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.logging.Logger;

class ConverterTests {

  private static final Converter<Integer, String> romanToDecimal = new RomanToDecimalConverter();
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

  @Tag("param")
  @ParameterizedTest(name = "converting {0} to roman")
  @CsvSource({
      "1, I",
      "4, IV",
      "13, XIII",
      "45, XLV",
      "99, XCIX",
      "100, C",
      "400, CD",
      "500, D",
      "900, CM",
      "2000, MM",
      "2300, MMCCC",
      "2344, MMCCCXLIV",
      "2858, MMDCCCLVIII",
      "3000, MMM",
  })
  void testIntegerToRoman(Integer value, String expected) {
    var res = romanToDecimal.convert(value);
    logger.info("converting %d to roman = %s".formatted(value, res));
    assert res.equals(expected);
  }

}
