package com.payroll.web.adapter.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AccountForm {

    @NotBlank
    @Size(min = 4, max = 4)
    @Pattern(regexp = "^[0-9]*$")
    private String financialInstitutionCode;

    @NotBlank
    @Size(min = 3, max = 3)
    @Pattern(regexp = "^[0-9]*$")
    private String typeNumber;

    @NotBlank
    @Size(min = 7, max = 7)
    @Pattern(regexp = "^[0-9]*$")
    private String accountNumber;

    public String getFinancialInstitutionCode() {
        return financialInstitutionCode;
    }

    public void setFinancialInstitutionCode(String financialInstitutionCode) {
        this.financialInstitutionCode = financialInstitutionCode;
    }

    public String getTypeNumber() {
        return typeNumber;
    }

    public void setTypeNumber(String typeNumber) {
        this.typeNumber = typeNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

}
