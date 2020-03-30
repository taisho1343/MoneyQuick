package com.payroll.web.adapter.controller.form;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public class FundTransferUnitToAddForm {

    @NotNull
    private LocalDate chargeDate;

    @NotNull
    @Valid
    private List<ChargeRequestForm> chargeRequests;

    public List<ChargeRequestForm> getChargeRequests() {
        return chargeRequests;
    }

    public void setChargeRequests(List<ChargeRequestForm> chargeRequests) {
        this.chargeRequests = chargeRequests;
    }

    public LocalDate getChargeDate() {
        return chargeDate;
    }

    public void setChargeDate(LocalDate chargeDate) {
        this.chargeDate = chargeDate;
    }

}
