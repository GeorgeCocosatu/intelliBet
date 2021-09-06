package com.intellibet.model;

import java.util.Random;

public enum BettingOption {
  _1, X, _2;

  public static BettingOption getOptionFrom(String option) {
    switch (option) {
      case "1": {
        return _1;
      }
      case "X": {
        return X;
      }
      case "2": {
        return _2;
      }
      default:
        throw new IllegalArgumentException("Option not valid");
    }
  }

    public static BettingOption getRandomOption() {
        Random random = new Random();
        int randomInt = random.nextInt(3);
        BettingOption[] bettingOptions = BettingOption.values();
        return bettingOptions[randomInt];
    }

  @Override
  public String toString() {
    switch (this) {
      case _1:
        return "1";
      case _2:
        return "2";
      case X:
        return "X";
    }
    return super.toString();
  }
}

