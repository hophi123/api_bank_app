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
 * 001       1.0       2020/04/20      (VNEXT) PhiNH       Create new
*/
public class TransFerResponse {

    @JsonProperty("bank_name_from")
    private String bank_name_from;

    @JsonProperty("name_user_from")
    private String name_user_from;

    @JsonProperty("action")
    private String action;

    @JsonProperty("date")
    private String date;

    @JsonProperty("money")
    private Double money;

    @JsonProperty("bank_name_to")
    private String bank_name_to;

    @JsonProperty("name_user_to")
    private String name_user_to;

    public TransFerResponse() {
        super();
    }

    public TransFerResponse(String bank_name_from, String name_user_from, String action, String date, Double money, String bank_name_to, String name_user_to) {
        super();
        this.bank_name_from = bank_name_from;
        this.name_user_from = name_user_from;
        this.action = action;
        this.date = date;
        this.money = money;
        this.bank_name_to = bank_name_to;
        this.name_user_to = name_user_to;
    }

    public String getBank_name_from() {
        return bank_name_from;
    }

    public void setBank_name_from(String bank_name_from) {
        this.bank_name_from = bank_name_from;
    }

    public String getName_user_from() {
        return name_user_from;
    }

    public void setName_user_from(String name_user_from) {
        this.name_user_from = name_user_from;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
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

    public String getBank_name_to() {
        return bank_name_to;
    }

    public void setBank_name_to(String bank_name_to) {
        this.bank_name_to = bank_name_to;
    }

    public String getName_user_to() {
        return name_user_to;
    }

    public void setName_user_to(String name_user_to) {
        this.name_user_to = name_user_to;
    }

}
