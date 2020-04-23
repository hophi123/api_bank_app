/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [OVERVIEW] XXXXX.
 *
 * @author: (VNEXT) PhiNH
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/19      (VNEXT) PhiNH       Create new
*/
public class TransHistoryResponse {

    @JsonProperty("bank_name")
    private String bank_name;

    @JsonProperty("action")
    private Integer action;

    @JsonProperty("date")
    private String date;

    @JsonProperty("money")
    private Double money;

    public TransHistoryResponse() {
        super();
    }

    public TransHistoryResponse(String bank_name, Integer action, String date, Double money) {
        super();
        this.bank_name = bank_name;
        this.action = action;
        this.date = date;
        this.money = money;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

}
