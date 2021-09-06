package com.intellibet.mapper;

import com.intellibet.dto.BetForm;
import com.intellibet.dto.PlacedBetForm;
import com.intellibet.model.Bet;
import com.intellibet.model.BettingOption;
import com.intellibet.model.Event;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class BetMapper {

  private static DecimalFormat decimalFormat = new DecimalFormat("#.##");

  public Bet map(BetForm betForm) {
    Bet bet = new Bet();
    bet.setValue(Double.parseDouble(betForm.getValue()));
    bet.setBettingOption(BettingOption.getOptionFrom(betForm.getOption()));
    bet.setDateTime(LocalDateTime.now());
    return bet;
  }

  public PlacedBetForm toPlacedBetForm(Bet bet) {
    PlacedBetForm placedBetForm = new PlacedBetForm();
    Event event = bet.getEvent();

    placedBetForm.setPlayerA(event.getPlayerA());
    placedBetForm.setPlayerB(event.getPlayerB());
    placedBetForm.setEventDate(event.getDateTime().toLocalDate().toString());
    placedBetForm.setOption(bet.getBettingOption().toString());

    placedBetForm.setOdd(bet.getOdd().toString());
    Optional<Boolean> optionalWon = bet.isWon();
    placedBetForm.setBetWon(optionalWon.isPresent()? optionalWon.get().toString() : "pending");
    placedBetForm.setWagedValue(bet.getValue().toString());
    placedBetForm
        .setBetDateAndTime(bet.getDateTime() == null ? "N/A" : bet.getDateTime().toString());


    if (!optionalWon.isPresent() || optionalWon.isPresent() && optionalWon.get()){
      double amount = bet.getValue() * bet.getOdd();
      placedBetForm.setGainedValue(decimalFormat.format(amount));
    } else {
      placedBetForm.setGainedValue("0");
    }

    return placedBetForm;
  }


  public List<PlacedBetForm> map(List<Bet> betList) {
    return betList.stream().map(this::toPlacedBetForm).collect(Collectors.toList());

//    the same logic using java < 8
//    List<PlacedBetForm> result = new ArrayList<>();
//    for (Bet bet : betList) {
//      PlacedBetForm placedBetForm = toPlacedBetForm(bet);
//      result.add(placedBetForm);
//    }
//    return result;
  }
}
