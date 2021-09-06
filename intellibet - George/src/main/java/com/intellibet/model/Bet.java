package com.intellibet.model;

import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Bet {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(cascade = CascadeType.ALL)
  private User user;

  @ManyToOne(cascade = CascadeType.ALL)
  private Event event;

  private BettingOption bettingOption;

  private Double value;

  private LocalDateTime dateTime;


  public Optional<Boolean> isWon() {
    if(event.getOutcome() == null){
      return Optional.empty();
    }
    return Optional.of(bettingOption.equals(event.getOutcome()));
  }

  public Float getOdd() {
    switch (bettingOption) {
      case _1: {
        return event.getOddA();
      }
      case _2: {
        return event.getOddB();
      }
      case X: {
        return event.getOddX();
      }
    }
    throw new IllegalArgumentException("not implemented");
  }
}
