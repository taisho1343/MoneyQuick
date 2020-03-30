package com.payroll.web.adapter.controller.form;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ChargeRequestForm {

    @NotNull
    @DecimalMin("0")
    @DecimalMax("9999999999")
    private BigDecimal transferAmountOfMoney;

    @NotNull
    @Valid
    private AccountForRequestForm accountForRequest;

    public BigDecimal getTransferAmountOfMoney() {
        return transferAmountOfMoney;
    }

    public void setTransferAmountOfMoney(BigDecimal transferAmountOfMoney) {
        this.transferAmountOfMoney = transferAmountOfMoney;
    }

    public AccountForRequestForm getAccountForRequest() {
        return accountForRequest;
    }

    public void setAccountForRequest(AccountForRequestForm accountForRequest) {
        this.accountForRequest = accountForRequest;
    }

}
