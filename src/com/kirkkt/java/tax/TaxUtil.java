package com.kirkkt.java.tax;

import com.google.common.base.Preconditions;

import com.kirkkt.java.tax.forms.Form;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public final class TaxUtil {

  public static final String DATA_FOLDER = System.getenv("GITHUB_DIR")
      + "/../vim_notes/java_programs/data/com/kirkkt/java/tax";

  // TODO(kirktdev): get rid of these global consts
  private String checkingAccountRoutingNumber;
  private String checkingAccountAccountNumber;
  private String checkingAccountAccountType;

  /**
   * Return a standard error message string describing that additional logic is
   * needed form the given form.
   * </p>If this happens, java_tax might not be right for you.
   *
   * @param form the form currently being processed
   */
  public static String additionalLogicNeededString(Form form) {
    return "Additional logic needed for form "
        + form.getFormType() + " of tax year "
        + form.getTaxYear() + ".";
  }

  /**
   * Return a standard error message string describing that additional logic is
   * needed on a specific line in the given form.
   * </p>If this happens, java_tax might not be right for you.
   *
   * @param form the form currently being processed
   * @param line the line name where additional logic is needed
   */
  public static String additionalLogicNeededString(Form form, String line) {
    return "Additional logic needed for form "
        + form.getFormType() + " of tax year "
        + form.getTaxYear() + " on line "
        + line + ".";
  }

  public TaxUtil() {
    BufferedReader br;
    String line;
    String fileName = DATA_FOLDER + "/common.txt";
    try {
      br = new BufferedReader(new FileReader(fileName));
      while ((line = br.readLine()) != null) {
        if (line.equals("common")) {
          // heading
        } else if (line.startsWith("checking account routing number ")) {
          checkingAccountRoutingNumber = line.split(" ", 5)[4];
        } else if (line.startsWith("checking account account number ")) {
          checkingAccountAccountNumber = line.split(" ", 5)[4];
        } else if (line.startsWith("checking account account type ")) {
          checkingAccountAccountType = line.split(" ", 5)[4];
        } else {
          br.close();
          throw new IllegalArgumentException("Invalid input line: " + line);
        }
      }
      br.close();
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to read file " + fileName + " due to error " + e);
    }
  }

  public String getCheckingAccountAccountType() {
    return checkingAccountAccountType;
  }

  public String getCheckingAccountRoutingNumber() {
    return checkingAccountRoutingNumber;
  }

  public String getCheckingAccountAccountNumber() {
    return checkingAccountAccountNumber;
  }
}
