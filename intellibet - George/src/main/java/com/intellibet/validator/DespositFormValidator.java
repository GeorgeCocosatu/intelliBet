package com.intellibet.validator;

import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.apache.commons.lang3.math.NumberUtils.isDigits;
import static org.apache.commons.lang3.math.NumberUtils.isNumber;

import com.intellibet.dto.DepositForm;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class DespositFormValidator implements Validator {

  @Override
  public boolean supports(Class<?> aClass) {
    return aClass.equals(DepositForm.class);
  }

  @Override
  public void validate(Object o, Errors errors) {
    DepositForm depositForm = (DepositForm) o;
    if (isEmpty(depositForm.getAmount()) || !isNumber(depositForm.getAmount())) {
      errors.rejectValue("amount", "depositForm.amount.error");
    }
    double amount = Double.parseDouble(depositForm.getAmount());
    if (Double.compare(amount, 0) <= 0) {
      errors.rejectValue("amount", "depositForm.amount.error");
    }
  }
}
