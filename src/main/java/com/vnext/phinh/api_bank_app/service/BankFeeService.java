/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.service;

import com.vnext.phinh.api_bank_app.bean.BankFeeEntity;

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
public interface BankFeeService {

    /**
     * getTransFeeByiId
     * @author: (VNEXT) PhiNH
     * @param id
     * @return BankFeeEntity
     */
    public BankFeeEntity getTransFeeByiId(int id);

}
