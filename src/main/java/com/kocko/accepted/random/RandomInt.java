package com.kocko.accepted.random;

import java.util.Random;

public class RandomInt {

  public int next(int min, int max) {
    validateRange(min, max);
    int offset = max - min;
    return new Random().nextInt(offset + 1) + min;
  }

  private void validateRange(int min, int max) {
    if (min > max) {
      throw new IllegalArgumentException("Invalid range given: [" + min + ", " + max + "]");
    }
  }

}
