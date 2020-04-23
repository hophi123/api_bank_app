/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Entity
@Table(name = "transaction")
public class TransactionEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "trans_amount")
    private Double trans_amount;

    @Column(name = "fee")
    private Double fee;

    @Column(name = "date")
    private String date;

    @Column(name = "id_bank_from")
    private Integer id_bank_from;

    @Column(name = "id_bank_to")
    private Integer id_bank_to;

    @Column(name = "action")
    private Integer action;

    @Column(name = "id_user_from")
    private int id_user_from;

    @Column(name = "id_user_to")
    private int id_user_to;

    @Column(name = "id_account_from")
    private int id_account_from;

    @Column(name = "id_account_to")
    private int id_account_to;

    public TransactionEntity() {
        super();
    }

    public TransactionEntity(Integer id, Double trans_amount, Double fee, String date, Integer id_bank_from, Integer id_bank_to, Integer action,
            int id_user_from, int id_user_to, int id_account_from, int id_account_to) {
        super();
        this.id = id;
        this.trans_amount = trans_amount;
        this.fee = fee;
        this.date = date;
        this.id_bank_from = id_bank_from;
        this.id_bank_to = id_bank_to;
        this.action = action;
        this.id_user_from = id_user_from;
        this.id_user_to = id_user_to;
        this.id_account_from = id_account_from;
        this.id_account_to = id_account_to;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getTrans_amount() {
        return trans_amount;
    }

    public void setTrans_amount(Double trans_amount) {
        this.trans_amount = trans_amount;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId_bank_from() {
        return id_bank_from;
    }

    public void setId_bank_from(Integer id_bank_from) {
        this.id_bank_from = id_bank_from;
    }

    public Integer getId_bank_to() {
        return id_bank_to;
    }

    public void setId_bank_to(Integer id_bank_to) {
        this.id_bank_to = id_bank_to;
    }

    public Integer getAction() {
        return action;
    }

    public void setAction(Integer action) {
        this.action = action;
    }

    public int getId_user_from() {
        return id_user_from;
    }

    public void setId_user_from(int id_user_from) {
        this.id_user_from = id_user_from;
    }

    public int getId_user_to() {
        return id_user_to;
    }

    public void setId_user_to(int id_user_to) {
        this.id_user_to = id_user_to;
    }

    public int getId_account_from() {
        return id_account_from;
    }

    public void setId_account_from(int id_account_from) {
        this.id_account_from = id_account_from;
    }

    public int getId_account_to() {
        return id_account_to;
    }

    public void setId_account_to(int id_account_to) {
        this.id_account_to = id_account_to;
    }

}
