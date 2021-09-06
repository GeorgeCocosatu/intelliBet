package com.intellibet.validator;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.math.NumberUtils.isNumber;

import com.intellibet.dto.BetForm;
import com.intellibet.dto.DepositForm;
import com.intellibet.model.User;
import com.intellibet.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class BetFormValidator implements Validator {

  @Autowired
  private UserRepository userRepository;

  @Override
  public boolean supports(Class<?> aClass) {
    return aClass.equals(BetForm.class);
  }

  public void fullValidation(BetForm betForm, Errors errors, String userEmail) {
    validate(betForm, errors);
    if (errors.hasErrors()) {
      return;
    }
    validateBetAmountAgainstUserBalance(betForm.getValue(), userEmail, errors);
  }


  @Override
  public void validate(Object o, Errors errors) {
    BetForm betForm = (BetForm) o;

    if (isEmpty(betForm.getValue()) || !isNumber(betForm.getValue())) {
      errors.rejectValue("value", "betForm.value.error");
    }
    double amount = Double.parseDouble(betForm.getValue());
    if (Double.compare(amount, 0) <= 0) {
      errors.rejectValue("value", "betForm.value.error");
    }
    /* TODO: validate that the eventId exists and that the option is one of the existing ones */
  }


  private void validateBetAmountAgainstUserBalance(String betValue, String userEmail, Errors errors) {
    User user = userRepository.findByEmail(userEmail);
    Double betValueAsDouble = Double.parseDouble(betValue);
    if (Double.compare(user.getBalance(), betValueAsDouble) < 0) {
      errors.rejectValue("value", "betForm.value.overbalance");
    }
  }
}
