/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.service;

import com.vnext.phinh.api_bank_app.bean.BankEntity;
import com.vnext.phinh.api_bank_app.bean.ResultBean;
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
public interface BankService {

    /**
     * getAll
     * @author: (VNEXT) PhiNH
     * @return ResultBean
     * @throws ApiValidateException
     */
    public ResultBean getAll() throws ApiValidateException;

    /**
     * findBankById
     * @author: (VNEXT) PhiNH
     * @param id
     * @return BankEntity
     */
    public BankEntity findBankById(Integer id);
}
