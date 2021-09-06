package com.intellibet.dto;

import lombok.Data;

@Data
public class PlacedBetForm {

  private String option;
  private String wagedValue;
  private String gainedValue;
  private String odd;
  private String playerA;
  private String playerB;
  private String eventDate;
  private String betDateAndTime;
  private String betWon;
}
