/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vnext.phinh.api_bank_app.bean.UserEntity;

/**
 * [OVERVIEW] XXXXX.
 *
 * @author: (VNEXT) PhiNH
 * @version: 1.0
 * @History
 * [NUMBER]  [VER]     [DATE]          [USER]             [CONTENT]
 * --------------------------------------------------------------------------
 * 001       1.0       2020/04/23      (VNEXT) PhiNH       Create new
*/
public interface UserRepositoty extends JpaRepository<UserEntity, Integer> {
    
    /**
     * findByName
     * @author: (VNEXT) PhiNH
     * @param name
     * @return Optional<UserEntity>
     */
    Optional<UserEntity> findByName(String name);
    
    
    

}
