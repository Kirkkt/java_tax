package com.kirkkt.java.tax.forms;

public class StringEntry extends Entry<String> {
  @Override
  void init() {
    super.init();
    value = "";
  }

  @Override
  public void readFromLine(String line, String prefix) throws IllegalArgumentException, NumberFormatException {
    super.readFromLine(line, prefix);
    setValue(line.split(prefix, 2)[1]);
  }
}
