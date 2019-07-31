package com.kocko.accepted.random;

import java.util.Random;

public class RandomString {

  public String next(int size, int asciiStart, int asciiEnd) {
    ensureThatSizeIsPositive(size);
    ensureLetterRangeIsValid(asciiStart, asciiEnd);

    StringBuilder result = new StringBuilder();
    int offset = asciiEnd - asciiStart;
    Random r = new Random();
    while (size-- > 0) {
      char ch = (char) (r.nextInt(offset + 1) + asciiStart);
      result.append(ch);
    }
    return result.toString();
  }

  private void ensureThatSizeIsPositive(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException("Invalid string size given: " + size);
    }
  }

  private void ensureLetterRangeIsValid(int start, int end) {
    if (start > end) {
      throw new IllegalArgumentException("Invalid letter range given");
    }
  }

}
