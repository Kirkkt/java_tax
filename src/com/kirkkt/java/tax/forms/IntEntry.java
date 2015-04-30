package com.kirkkt.java.tax.forms;

import com.kirkkt.java.tax.Parser;

public class IntEntry extends Entry<Integer> {
  @Override
  public void readFromLine(String line, String prefix) throws IllegalArgumentException, NumberFormatException {
    super.readFromLine(line, prefix);
    setValue(Parser.parseAndRound(line.split(prefix, 2)[1], line));
  }
}
