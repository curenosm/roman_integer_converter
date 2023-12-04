package com.github.curenosm;


import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

class ConverterTests {

  private static final Converter<Integer, String> romanToDecimal = new RomanDecimalConverter();

  @Test
  void testIntegerToRoman_value_i() {
    var res = romanToDecimal.convert(1);
    Logger logger = Logger.getLogger(ConverterTests.class.getName());
    logger.info(res);
    assert res.equals("I");
  }

}
