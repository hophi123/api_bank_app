/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////
package com.vnext.phinh.api_bank_app.dao;

import java.util.List;

import com.vnext.phinh.api_bank_app.bean.UserEntity;
import com.vnext.phinh.api_bank_app.response.BalanceResponse;

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
public interface UserDao {
    /**
     * createUser
     * @author: (VNEXT) PhiNH
     * @param userEntity
     */
    public void createUser(UserEntity userEntity);

    /**
     * updateUser
     * @author: (VNEXT) PhiNH
     * @param userEntity
     */
    public void updateUser(UserEntity userEntity);

    /**
     * findById
     * @author: (VNEXT) PhiNH
     * @param id
     * @return UserEntity
     */
    public UserEntity findById(int id);

    /**
     * findByPhoneNumber
     * @author: (VNEXT) PhiNH
     * @param phone_number
     * @return UserEntity
     */
    public UserEntity findByEmail(String email);

    /**
     * findOtherPhoneNumber
     * @author: (VNEXT) PhiNH
     * @param phone_number
     * @param id
     * @return UserEntity
     */
    public UserEntity findOtherPhoneNumber(String phone_number, Integer id);

    /**
     * getBalanceById
     * @author: (VNEXT) PhiNH
     * @param id
     * @return List<BalanceResponse>
     */
    public List<BalanceResponse> getBalanceById(int id);
    
    public UserEntity findUserByName(String name);
}
