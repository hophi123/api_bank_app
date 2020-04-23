/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.bean;

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
@Table(name = "bank_fee")
public class BankFeeEntity {

    private static final long serialVersionUTD = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_bank")
    private Integer id_bank;

    @Column(name = "fee_1")
    private Double fee_1;

    @Column(name = "fee_2")
    private Double fee_2;

    @Column(name = "fee_3")
    private Double fee_3;

    public BankFeeEntity() {
        super();
    }

    public BankFeeEntity(Integer id, Integer id_bank, Double fee_1, Double fee_2, Double fee_3) {
        super();
        this.id = id;
        this.id_bank = id_bank;
        this.fee_1 = fee_1;
        this.fee_2 = fee_2;
        this.fee_3 = fee_3;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_bank() {
        return id_bank;
    }

    public void setId_bank(Integer id_bank) {
        this.id_bank = id_bank;
    }

    public Double getFee_1() {
        return fee_1;
    }

    public void setFee_1(Double fee_1) {
        this.fee_1 = fee_1;
    }

    public Double getFee_2() {
        return fee_2;
    }

    public void setFee_2(Double fee_2) {
        this.fee_2 = fee_2;
    }

    public Double getFee_3() {
        return fee_3;
    }

    public void setFee_3(Double fee_3) {
        this.fee_3 = fee_3;
    }

    public static long getSerialversionutd() {
        return serialVersionUTD;
    }

}
