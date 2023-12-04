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

    return "%s%s%s%s".formatted(
        getThousands(thousands),
        getHundreds(hundreds),
        getTens(tens),
        getUnits(units)
    );
  }

  public static String getThousands(Integer n) {
    return switch (n) {
      case 1 -> "M";
      case 2 -> "MM";
      case 3 -> "MMM";
      default -> "";
    };
  }

  public static String getHundreds(Integer n) {
    return switch (n) {
      case 1 -> "C";
      case 2 -> "CC";
      case 3 -> "CCC";
      case 4 -> "CD";
      case 5 -> "D";
      case 6 -> "DC";
      case 7 -> "DCC";
      case 8 -> "DCCC";
      case 9 -> "CM";
      default -> "";
    };
  }

  public static String getTens(Integer n) {
    return switch (n) {
      case 1 -> "X";
      case 2 -> "XX";
      case 3 -> "XXX";
      case 4 -> "XL";
      case 5 -> "L";
      case 6 -> "LX";
      case 7 -> "LXX";
      case 8 -> "LXXX";
      case 9 -> "XC";
      default -> "";
    };
  }

  public static String getUnits(Integer n) {
    return switch (n) {
      case 1 -> "I";
      case 2 -> "II";
      case 3 -> "III";
      case 4 -> "IV";
      case 5 -> "V";
      case 6 -> "VI";
      case 7 -> "VII";
      case 8 -> "VIII";
      case 9 -> "IX";
      default -> "";
    };
  }

}
