package com.intellibet.dto;

import lombok.Data;

@Data
public class EventForm {

  private String eventId;
  private String playerA;
  private String playerB;
  private String category;
  private String oddA;
  private String oddB;
  private String oddX;
  private String date;
  private String time;


}
