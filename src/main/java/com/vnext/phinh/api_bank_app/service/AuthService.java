/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.service;

import com.vnext.phinh.api_bank_app.bean.UserEntity;
import com.vnext.phinh.api_bank_app.utils.ApiValidateException;

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
public interface AuthService {

    /**
     * checkLogin
     * @author: (VNEXT) PhiNH
     * @param json
     * @return UserEntity
     * @throws ApiValidateException
     */
    public UserEntity checkLogin(String json) throws ApiValidateException;

    /**
     * checkLogin
     * @author: (VNEXT) PhiNH
     * @param phone_number
     * @param password
     * @return Integer
     * @throws ApiValidateException
     */
    public Integer checkLogin(String phone_number, String password) throws ApiValidateException;
}
