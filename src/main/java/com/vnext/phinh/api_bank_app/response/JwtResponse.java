/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.response;

import java.io.Serializable;

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
public class JwtResponse implements Serializable{
    private static final long serialVersionUID = -8091879091924046844L;
    private final String token;
    private final UserEntity user;

    public JwtResponse(String token, UserEntity user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public UserEntity getUser() {
        return user;
    }
}
