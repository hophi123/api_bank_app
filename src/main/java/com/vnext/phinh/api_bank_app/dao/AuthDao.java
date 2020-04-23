/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.dao;

import com.vnext.phinh.api_bank_app.bean.UserEntity;

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
public interface AuthDao {
    /**
     * checkLogin
     * @author: (VNEXT) PhiNH
     * @param phone_number
     * @param password
     * @return UserEntity
     */
    public UserEntity checkLogin(String phone_number, String password);
}
