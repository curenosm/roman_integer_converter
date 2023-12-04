package com.github.curenosm;


import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.logging.Logger;

class ConverterTests {

  private static final Converter<String, Integer> romanToDecimal = new RomanToDecimalConverter();
  private static final Converter<Integer, String> decimalToRoman = new DecimalToRomanConverter();
  private static final Logger logger = Logger.getLogger(ConverterTests.class.getName());

  @Test
  void testIntegerToRoman_value_1() {
    assert decimalToRoman
        .convert(1)
        .equals("I");
  }

  @Test
  void testIntegerToRoman_value_4() {
    assert decimalToRoman
        .convert(4)
        .equals("IV");
  }

  @Test
  void testIntegerToRoman_value_13() {
    assert decimalToRoman
        .convert(13)
        .equals("XIII");
  }

  @Test
  void testIntegerToRoman_value_45() {
    assert decimalToRoman
        .convert(45)
        .equals("XLV");
  }

  @Test
  void testIntegerToRoman_value_99() {
    assert decimalToRoman
        .convert(99)
        .equals("XCIX");
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
    var res = decimalToRoman.convert(value);
    logger.info("converting %d to roman = %s".formatted(value, res));
    assert res.equals(expected);
  }

  @Tag("param")
  @ParameterizedTest(name = "converting {0} from roman to decimal")
  @CsvFileSource(resources = "/roman_to_decimal.csv")
  void testIntegerToRoman(String value, Integer expected) {
    var res = romanToDecimal.convert(value);
    logger.info("converting %s from roman to decimal = %d".formatted(value, res));
    logger.info("we expected %d".formatted(expected));
    logger.info("we received %d".formatted(res));
    assert res.equals(expected);
  }

}
