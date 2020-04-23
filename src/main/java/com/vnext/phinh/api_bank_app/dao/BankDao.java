/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.dao;

import java.util.List;

import com.vnext.phinh.api_bank_app.bean.BankEntity;

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
public interface BankDao {
    /**
     * getAll
     * @author: (VNEXT) PhiNH
     * @return List<BankEntity>
     */
    public List<BankEntity> getAll();

    /**
     * findBankById
     * @author: (VNEXT) PhiNH
     * @param id
     * @return BankEntity
     */
    public BankEntity findBankById(Integer id);
}
