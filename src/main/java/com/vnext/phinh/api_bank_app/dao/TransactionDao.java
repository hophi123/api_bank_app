/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.dao;

import java.text.ParseException;
import java.util.List;

import com.vnext.phinh.api_bank_app.bean.TransactionEntity;
import com.vnext.phinh.api_bank_app.response.TransHistoryResponse;

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
public interface TransactionDao {

    /**
     * getListTransaction
     * @author: (VNEXT) PhiNH
     * @param id
     * @param dateFrom
     * @param dateTo
     * @return List<TransactionEntity>
     * @throws ParseException
     */
    public List<TransactionEntity> getListTransaction(Integer id, String dateFrom, String dateTo) throws ParseException;

    /**
     * createTrans
     * @author: (VNEXT) PhiNH
     * @param transactionEntity
     */
    public void createTrans(TransactionEntity transactionEntity);

    /**
     * getListTransHistory
     * @author: (VNEXT) PhiNH
     * @param id
     * @return List<TransactionEntity>
     */
    public List<TransactionEntity> getListTransHistory(Integer id);

    /**
     * getListTrans
     * @author: (VNEXT) PhiNH
     * @param id
     * @return List<TransHistoryResponse>
     */
    public List<TransHistoryResponse> getListTrans(Integer id);

    /**
     * getListTransfer
     * @author: (VNEXT) PhiNH
     * @param id
     * @return List<TransactionEntity>
     */
    public List<TransactionEntity> getListTransfer(Integer id);

    /**
     * getListTransfer
     * @author: (VNEXT) PhiNH
     * @param id_user
     * @param id_bank
     * @return List<TransactionEntity>
     */
    public List<TransactionEntity> getListTransfer(Integer id_user, Integer id_bank);
}
