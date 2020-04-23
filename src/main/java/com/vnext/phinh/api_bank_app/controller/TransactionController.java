/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.controller;

import java.text.ParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vnext.phinh.api_bank_app.bean.ResultBean;
import com.vnext.phinh.api_bank_app.bean.TransactionEntity;
import com.vnext.phinh.api_bank_app.service.TransactionService;
import com.vnext.phinh.api_bank_app.service.impl.UserServiceImpl;
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
@RestController
@RequestMapping("/api")
public class TransactionController {

    @Autowired
    private TransactionService transService;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * getListTransDateToDate
     * @author: (VNEXT) PhiNH
     * @param json
     * @return ResponseEntity<ResultBean>
     * @throws ParseException
     */
    @RequestMapping(value = "/trans", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResultBean> getListTransDateToDate(@RequestBody String json) throws ParseException {
        log.debug("### getListTransDateToDate start ###");
        ResultBean resultBean = null;
        try {
            resultBean = transService.getListTransDateToDate(json);
        } catch (ApiValidateException e) {
            e.printStackTrace();
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        }
        log.debug("### getListTransDateToDate end ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * addMoney
     * @author: (VNEXT) PhiNH
     * @param json
     * @param id
     * @return ResponseEntity<ResultBean>
     * @throws ParseException
     */
    @RequestMapping(value = "/trans/add/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResultBean> addMoney(@RequestBody String json, @PathVariable Integer id) throws ParseException {
        log.debug("### addMoney start ###");
        ResultBean resultBean = null;
        TransactionEntity transactionEntity = null;
        try {
            transactionEntity = transService.addMoney(id, json);
        } catch (ApiValidateException e) {
            e.printStackTrace();
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        }
        resultBean = new ResultBean(transactionEntity, "200", "Add money SUCCESS!");
        log.debug("### addMoney end ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**withdraw
     * 
     * @author: (VNEXT) PhiNH
     * @param json
     * @return ResponseEntity<ResultBean>
     * @throws ParseException
     */
    @RequestMapping(value = "/trans/withdraw", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResultBean> withdraw(@RequestBody String json) throws ParseException {
        log.debug("### withdraw start ###");
        ResultBean resultBean = null;
        TransactionEntity transactionEntity = null;
        try {
            transactionEntity = transService.withdraw(json);
        } catch (ApiValidateException e) {
            e.printStackTrace();
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        }
        resultBean = new ResultBean(transactionEntity, "200", "Withdraw SUCCESS!");
        log.debug("### withdraw end ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * transfer
     * @author: (VNEXT) PhiNH
     * @param json
     * @return ResponseEntity<ResultBean>
     * @throws ParseException
     */
    @RequestMapping(value = "/trans/transfer", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<ResultBean> transfer(@RequestBody String json) throws ParseException {
        log.debug("### transfer start ###");
        ResultBean resultBean = null;
        TransactionEntity transactionEntity = null;
        try {
            transactionEntity = transService.transfer(json);
        } catch (ApiValidateException e) {
            e.printStackTrace();
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        }
        resultBean = new ResultBean(transactionEntity, "200", "Transfer SUCCESS");
        log.debug("### transfer end ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * getListTransHistory
     * @author: (VNEXT) PhiNH
     * @param json
     * @return ResponseEntity<ResultBean>
     * @throws ParseException
     */
    @RequestMapping(value = "/trans/history", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResultBean> getListTransHistory(@RequestBody String json) throws ParseException {
        log.debug("### getListTransHistory start ###");
        ResultBean resultBean = null;
        try {
            resultBean = transService.getListTransHistory(json);
        } catch (ApiValidateException e) {
            e.printStackTrace();
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        }
        log.debug("### getListTransHistory end ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * getListTransFer
     * @author: (VNEXT) PhiNH
     * @param json
     * @return ResponseEntity<ResultBean>
     * @throws ParseException
     */
    @RequestMapping(value = "/trans/transfer", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResultBean> getListTransFer(@RequestBody String json) throws ParseException {
        log.debug("### getListTransFer start ###");
        ResultBean resultBean = null;
        try {
            resultBean = transService.getListTransFer(json);
        } catch (ApiValidateException e) {
            e.printStackTrace();
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        }
        log.debug("### getListTransFer end ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * getListTransFerByIdBank
     * @author: (VNEXT) PhiNH
     * @param json
     * @param id
     * @return ResponseEntity<ResultBean>
     * @throws ParseException
     */
    @RequestMapping(value = "/trans/transfer/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResultBean> getListTransFerByIdBank(@RequestBody String json, @PathVariable Integer id) throws ParseException {
        log.debug("### getListTransFer start ###");
        ResultBean resultBean = null;
        try {
            resultBean = transService.getListTransFer(json, id);
        } catch (ApiValidateException e) {
            e.printStackTrace();
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        }
        log.debug("### getListTransFer end ###");
        return new ResponseEntity<ResultBean>(resultBean, HttpStatus.OK);
    }

    /**
     * outputToCSV
     * @author: (VNEXT) PhiNH
     * @param json
     * @return ResponseEntity<ResultBean>
     * @throws ParseException
     */
    @RequestMapping(value = "/trans/export", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ResultBean> outputToCSV(@RequestBody String json) throws ParseException {
        log.debug("### outputToCSV start ###");
        ResultBean resultBean = null;
        String file = "";
        try {
            file = transService.outputTransactionToCSV(json);
        } catch (ApiValidateException e) {
            e.printStackTrace();
            resultBean = new ResultBean(e.getCode(), e.getField(), e.getMessage());
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<ResultBean>(resultBean, HttpStatus.BAD_GATEWAY);
        }
        log.debug("### outputToCSV end ###");
        return new ResponseEntity<ResultBean>(new ResultBean(file, "200", "output success"), HttpStatus.CREATED);
    }
}
