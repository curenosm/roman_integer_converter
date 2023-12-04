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

  public static String getRoman(
      Integer n,
      String s,
      String mid,
      String next) {

    return switch (n) {
      case 1 -> s;
      case 2 -> s.repeat(2);
      case 3 -> s.repeat(3);
      case 4 -> s + mid;
      case 5 -> mid;
      case 6 -> mid + s;
      case 7 -> mid + s.repeat(2);
      case 8 -> mid + s.repeat(3);
      case 9 -> s + next;
      default -> "";
    };
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
    return getRoman(n, "C", "D", "M");
  }

  public static String getTens(Integer n) {
    return getRoman(n, "X", "L", "C");
  }

  public static String getUnits(Integer n) {
    return getRoman(n, "I", "V", "X");
  }

}
