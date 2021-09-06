package com.intellibet.model;

public enum EventCategory {
  FOOTBALL, BOX, TENNIS, MMA, RACING, POLITICS, BASKET;

  public static EventCategory getCategoryFromString(String category) {
    switch (category) {
      case "FOOTBALL":
        return FOOTBALL;

      case "BOX":
        return BOX;

      case "TENNIS":
        return TENNIS;

      case "MMA":
        return MMA;

      case "RACING":
        return RACING;

      case "POLITICS":
        return POLITICS;

      case "BASKET":
        return BASKET;

      default:
        throw new IllegalArgumentException("The option you chose is invalid!");
    }
  }

}
