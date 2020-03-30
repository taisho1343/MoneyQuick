package com.payroll.web.adapter.controller.form;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AccountForRequestForm {

    @NotBlank
    @Size(min = 1, max = 30)
    @Pattern(regexp = "^[ 0-9A-Zｦ-ﾟﾞﾟ\\\\｢｣()/.-]$|^[0-9A-Zｦ-ﾟﾞﾟ\\\\｢｣()/.-][ 0-9A-Zｦ-ﾟﾞﾟ\\\\｢｣()/.-]*[0-9A-Zｦ-ﾟﾞﾟ\\\\｢｣()/.-]$")
    private String accountHolder;

    @NotNull
    @Valid
    private AccountForm account;

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public AccountForm getAccount() {
        return account;
    }

    public void setAccount(AccountForm account) {
        this.account = account;
    }
}
