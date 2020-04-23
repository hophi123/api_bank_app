/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.bean;

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
public class ErrorBean {

    private static final long serialVersionUID = 1L;

    private String code;
    private String field;
    private String message;

    public ErrorBean() {
    }

    public ErrorBean(String code, String field, String message) {
        this.code = code;
        this.field = field;
        this.message = message;
    }

    public ErrorBean(String code, String field) {
        this.code = code;
        this.field = field;
    }

    public ErrorBean(String code, String field, String[] param) {
        this.code = code;
        this.field = field;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

}
