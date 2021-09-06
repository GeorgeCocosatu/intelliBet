package com.intellibet.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BetForm {

  private String value;
  private String option;
  private String eventId;
  private String userBalance;

  public BetForm(String value) {
    this.value = value;
  }

    public void setUserBalance(Double balance) {
      userBalance = balance == null ? "0" : balance.toString();
    }
}
