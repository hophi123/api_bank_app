/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.response;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * [OVERVIEW] XXXXX.
 *
 * @author: (VNEXT) PhiNH
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/15      (VNEXT) PhiNH       Create new
*/
public class TransFer {
    /**
     * 
     */
    @JsonProperty("id_user")
    private Integer id_user;

    @JsonProperty("action")
    private Integer action;

    @JsonProperty("trans_amount")
    private Double trans_amount;

    @JsonProperty("id_user_receive")
    private Integer id_user_receive;

    public TransFer() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TransFer(Integer id_user, Integer action, Double trans_amount, Integer id_user_receive) {
        super();
        this.id_user = id_user;
        this.action = action;
        this.trans_amount = trans_amount;
        this.id_user_receive = id_user_receive;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public Double getTrans_amount() {
        return trans_amount;
    }

    public void setTrans_amount(Double trans_amount) {
        this.trans_amount = trans_amount;
    }

    public Integer getId_user_receive() {
        return id_user_receive;
    }

    public void setId_user_receive(Integer id_user_receive) {
        this.id_user_receive = id_user_receive;
    }

}
