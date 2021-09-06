package com.intellibet.service;

import com.intellibet.dto.BetForm;
import com.intellibet.dto.PlacedBetForm;
import com.intellibet.mapper.BetMapper;
import com.intellibet.model.Bet;
import com.intellibet.model.Event;
import com.intellibet.model.User;
import com.intellibet.repository.BetRepository;
import com.intellibet.repository.EventRepository;
import com.intellibet.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class BetService {

  @Autowired
  private BetMapper betMapper;
  @Autowired
  private BetRepository betRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private EventRepository eventRepository;

  public void placeBet(BetForm betForm, String userEmail) {
    Bet bet = betMapper.map(betForm);

    linkUserWithBet(userEmail, bet);
    addEventToBet(betForm.getEventId(), bet);

    betRepository.save(bet);
  }
  private void addEventToBet(String eventId, Bet bet) {
    Optional<Event> optionalEvent = eventRepository.findById(Long.parseLong(eventId));
    if (!optionalEvent.isPresent()) {
      throw new IllegalArgumentException("Event id " + eventId + " is invalid!");
    }
    bet.setEvent(optionalEvent.get());
  }
  private void linkUserWithBet(String email, Bet bet) {
    User user = userRepository.findByEmail(email);
    bet.setUser(user);

    Double userBalance = user.getBalance();
    userBalance = userBalance - bet.getValue();
    user.setBalance(userBalance);
  }
  public BetForm getBetFormFor(Authentication authentication) {
    BetForm betForm = new BetForm("10");

    if(authentication == null){
      return betForm;
    }

    User user = userRepository.findByEmail(authentication.getName());
    betForm.setUserBalance(user.getBalance());
    return betForm;
  }
  public List<PlacedBetForm> getPendingBetsFor(String userEmail) {

    List<Bet> betList = betRepository.findPendingBetsByUserEmail(userEmail);
    return betMapper.map(betList);

  }
  public List<PlacedBetForm> getDecidedBetsFor(String userEmail) {

    List<Bet> betList = betRepository.findDecidedBetsByUserEmail(userEmail);
    return betMapper.map(betList);

  }
}
