/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.dao;

import com.vnext.phinh.api_bank_app.bean.AccountEntity;

/**
 * [OVERVIEW] XXXXX.
 *
 * @author: (VNEXT) PhiNH
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/16      (VNEXT) PhiNH       Create new
*/
public interface AccountDao {
    /**
     * createAccount
     * @author: (VNEXT) PhiNH
     * @param accountEntity
     */
    public void createAccount(AccountEntity accountEntity);

    /**
     * findAccount
     * @author: (VNEXT) PhiNH
     * @param id_user
     * @param id_bank
     * @return AccountEntity
     */
    public AccountEntity findAccount(Integer id_user, Integer id_bank);

    /**
     * findAccount
     * @author: (VNEXT) PhiNH
     * @param id
     * @return AccountEntity
     */
    public AccountEntity findAccount(Integer id);

    /**
     * updateAccount
     * @author: (VNEXT) PhiNH
     * @param accountEntity
     */
    public void updateAccount(AccountEntity accountEntity);
}
