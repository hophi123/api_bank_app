/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.utils;

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
public class RegexUtils {

    public static final String USER_NAME = "[^0-9][A-Za-z0-9]{4,10}";
    public static final String ID_PATTERN = "[0-9]+";
    public static final String PHONENUMBER_PATTERN = "^[0-9].{9,10}$";
    public static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=])(?=\\S+$).{3,8}$";
    public static final String DATE_PATTERN = "^\\d{4}/\\d{2}/\\d{2}$";
}
