package com.github.curenosm;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @version 1.0.0
 */
public class RomanToDecimalConverter implements Converter<String, Integer> {

  private static final Logger logger = Logger.getLogger(RomanToDecimalConverter.class.getName());

  /**
   * Converts roman numbers in the form of a given String
   * with only valid characters, to decimal numbers in
   * the form of Integer.
   *
   * @param value String to be converted.
   */
  @Override
  public Integer convert(String value) {
    var acc = new ArrayList<String>();
    var matches = matchNext(value, acc);
    var formedString = matchesToString(matches);
    return Integer.parseInt(formedString);
  }

  /**
   * Converts a list of matches to a String.
   *
   * @param matches List of matches.
   * @return String of matches.
   */
  public String matchesToString(List<String> matches) {
    return matches.stream()
        .map(this::matchToDecimal)
        .reduce("", String::concat);
  }

  /**
   * Converts a match to a decimal number.
   * @param c Match in the form of roman to be converted.
   * @return String representation of the decimal number.
   */
  public String matchToDecimal(String c) {
    return switch (c) {
      case "I", "X", "C", "M" -> "1";
      case "II", "XX", "CC", "MM" -> "2";
      case "III", "XXX", "CCC", "MMM" -> "3";
      case "IV", "XL", "CD" -> "4";
      case "V", "L", "D" -> "5";
      case "VI", "LX", "DC" -> "6";
      case "VII", "LXX", "DCC" -> "7";
      case "VIII", "LXXX", "DCCC" -> "8";
      case "IX", "XC", "CM" -> "9";
      default -> "";
    };
  }

  /**
   * Maps a character to a Roman enum.
   * @param c Character to be mapped.
   *          It must be a valid character.
   *          Otherwise, it will return Roman.NONE.
   * @return Roman enum.
   */
  public static Roman getRomanEnumFromChar(char c) {
    return switch (c) {
      case 'I' -> Roman.I;
      case 'V' -> Roman.V;
      case 'X' -> Roman.X;
      case 'L' -> Roman.L;
      case 'C' -> Roman.C;
      case 'D' -> Roman.D;
      case 'M' -> Roman.M;
      default -> Roman.NONE;
    };
  }

  /**
   * Recursive function that iterates over the string to
   * from left to right and goes accumulating the next
   * match.
   *
   * @param currentValue String to be analyzed.
   * @param matches List of matches until now.
   * @return List of matches.
   */
  public List<String> matchNext(
      String currentValue,
      List<String> matches) {

    // Caso base
    if (currentValue.isEmpty()) {
      return matches;
    } else {
      var acc = new StringBuilder();
      var cur = 0;
      var next = 1;

      // Por poner un ejemplo, analizando
      // MMDCCCLVIII

      // Primero vas acumulando, guardas la M y te queda MDCCCLVIII
      // Sigues acumulando la M porque sigue siendo mayor que la D
      // Te queda hasta el momento MM, DCCCLVIII

      while (cur < currentValue.length()
          && next < currentValue.length()
          && (getRomanEnumFromChar(currentValue.charAt(cur)).getValue()
          >= getRomanEnumFromChar(currentValue.charAt(next)).getValue())) {
        acc.append(currentValue.charAt(cur));
        cur++;
        next++;
        if (next >= currentValue.length()) {
          break;
        }
      }

      // Luego la D tiene un valor asociado menor que la M,
      // por lo que el string acumulado pasa a insertarse en la lista
      // de matches

      matches.add(acc.toString());

      // Después se reinicia el proceso con el string DCCCLVIII
      // MM_DCC_C_L_VIII

      return matchNext(
          currentValue.substring(1),
          matches
      );
    }
  }
}
