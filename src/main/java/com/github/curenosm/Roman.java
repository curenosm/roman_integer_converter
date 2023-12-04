package com.github.curenosm;

/**
 * We create an enum to be able to compare the values
 * of the roman characters.
 */
public enum Roman {
  NONE(0),
  I(1),
  V(5),
  X(10),
  L(50),
  C(100),
  D(500),
  M(1000);

  private final int value;

  Roman(int value) {
    this.value = value;
  }

  public int getValue() {
    return value;
  }
}
