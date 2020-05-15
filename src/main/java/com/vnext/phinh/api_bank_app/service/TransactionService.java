/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.service;

import java.text.ParseException;

import org.json.JSONException;

import com.vnext.phinh.api_bank_app.bean.ResultBean;
import com.vnext.phinh.api_bank_app.bean.TransactionEntity;
import com.vnext.phinh.api_bank_app.utils.ApiValidateException;

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
public interface TransactionService {

    /**
     * getListTransDateToDate
     * @author: (VNEXT) PhiNH
     * @param json
     * @return ResultBean
     * @throws ParseException
     * @throws ApiValidateException
     */
    public ResultBean getListTransDateToDate(String json) throws ParseException, ApiValidateException;

    /**
     * addMoney
     * @author: (VNEXT) PhiNH
     * @param id
     * @param json
     * @return TransactionEntity
     * @throws ApiValidateException
     */
    public TransactionEntity addMoney(Integer id, String json) throws ApiValidateException;

    /**
     * withdraw
     * @author: (VNEXT) PhiNH
     * @param json
     * @return TransactionEntity
     * @throws ApiValidateException
     */
    public TransactionEntity withdraw(Integer id, String json) throws ApiValidateException;

    /**
     * getFee
     * @author: (VNEXT) PhiNH
     * @param id_bank
     * @param trans_amount
     * @return Double
     */
    public Double getFee(Integer id_bank, Double trans_amount);

    /**
     * getFee
     * @author: (VNEXT) PhiNH
     * @param id_bank1
     * @param id_bank2
     * @param trans_amount
     * @return Double
     */
    public Double getFee(Integer id_bank1, Integer id_bank2, Double trans_amount);

    /**
     * checkBalance
     * @author: (VNEXT) PhiNH
     * @param balance
     * @param trans_amount
     * @param fee
     * @return Boolean
     */
    public Boolean checkBalance(Double balance, Double trans_amount, Double fee);

    /**
     * transfer
     * @author: (VNEXT) PhiNH
     * @param json
     * @return TransactionEntity
     * @throws ApiValidateException
     */
    public TransactionEntity transfer(Integer id, String json) throws ApiValidateException;

    /**
     * getListTransHistory
     * @author: (VNEXT) PhiNH
     * @param json
     * @return ResultBean
     * @throws ApiValidateException
     */
    public ResultBean getListTransHistory() throws ApiValidateException;

    /**
     * getListTransFer
     * @author: (VNEXT) PhiNH
     * @param json
     * @return ResultBean
     * @throws ApiValidateException
     */
    public ResultBean getListTransFer() throws ApiValidateException;
    
    /**
     * getListTransFer
     * @author: (VNEXT) PhiNH
     * @param json
     * @param id_bank
     * @return ResultBean
     * @throws ApiValidateException
     */
    public ResultBean getListTransFerByIdBank(Integer id_bank) throws ApiValidateException;
    
    /**
     * outputTransactionToCSV
     * @author: (VNEXT) PhiNH
     * @param json
     * @return String
     * @throws JSONException
     * @throws ApiValidateException
     */
    public String outputTransactionToCSV() throws JSONException, ApiValidateException;
    
}
