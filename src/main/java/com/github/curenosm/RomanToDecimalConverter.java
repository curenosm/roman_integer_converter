package com.github.curenosm;

import java.util.ArrayList;
import java.util.List;


public class RomanToDecimalConverter implements Converter<String, Integer> {

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
    return Integer.parseInt(
        String.join("", formedString)
    );
  }

  /**
   * Converts a list of matches to a String.
   *
   * @param matches List of matches.
   * @return String of matches.
   */
  public String matchesToString(List<String> matches) {
    return String.join("", matches);
  }

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
