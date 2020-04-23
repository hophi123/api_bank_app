/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * [OVERVIEW] XXXXX.
 *
 * @author: (VNEXT) PhiNH
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT) PhiNH       Create new
 */
public class BalanceResponse {
    /**
     * 
     */

    @JsonProperty("name")
    private String name;

    @JsonProperty("balance")
    private Double balance;

    @JsonProperty("bank_name")
    private String bank_name;

    public BalanceResponse() {
        super();
    }

    public BalanceResponse(String name, Double balance, String bank_name) {
        super();
        this.name = name;
        this.balance = balance;
        this.bank_name = bank_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

}
