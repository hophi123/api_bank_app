/////////////////////////////////////////////////////////////////////////////
//
// © 2020 VNEXT TRAINING
//
/////////////////////////////////////////////////////////////////////////////

package com.vnext.phinh.api_bank_app.service.impl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vnext.phinh.api_bank_app.bean.AccountEntity;
import com.vnext.phinh.api_bank_app.bean.BankFeeEntity;
import com.vnext.phinh.api_bank_app.bean.ResultBean;
import com.vnext.phinh.api_bank_app.bean.TransactionEntity;
import com.vnext.phinh.api_bank_app.dao.TransactionDao;
import com.vnext.phinh.api_bank_app.model.TransFerResponse;
import com.vnext.phinh.api_bank_app.model.TransHistoryResponse;
import com.vnext.phinh.api_bank_app.service.AccountService;
import com.vnext.phinh.api_bank_app.service.AuthService;
import com.vnext.phinh.api_bank_app.service.BankFeeService;
import com.vnext.phinh.api_bank_app.service.BankService;
import com.vnext.phinh.api_bank_app.service.TransactionService;
import com.vnext.phinh.api_bank_app.service.UserService;
import com.vnext.phinh.api_bank_app.utils.ApiValidateException;
import com.vnext.phinh.api_bank_app.utils.RenameFile;

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
@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionDao transactiondao;
    @Autowired
    private AccountService accountService;
    @Autowired
    private BankFeeService transfeeservice;
    @Autowired
    private AuthService authService;
    @Autowired
    private BankService bankService;
    @Autowired
    private UserService userService;

    private static final Log log = LogFactory.getLog(UserServiceImpl.class);

    /**
     * getListTransDateToDate
     * @author (VNEXT) PhiNH
     * @param json
     * @return ResultBean(listTrans, "200", "Get list SUCCESS")
     */
    @Override
    public ResultBean getListTransDateToDate(String json) throws ParseException, ApiValidateException {
        log.debug("### getListTransDateToDate start ###");
        JSONObject jsonTrans = new JSONObject(json);
        if (jsonTrans.isEmpty()) {
            throw new ApiValidateException("400", "", "Please enter all field");
        } else {
            int id = authService.checkLogin(jsonTrans.getString("phone_number"), jsonTrans.getString("password"));
            String dateFrom = jsonTrans.getString("date_from");
            String dateTo = jsonTrans.getString("date_to");
            dateFrom += " 00:00:00";
            dateTo += " 23:59:59";
            List<TransactionEntity> listTrans = new ArrayList<TransactionEntity>();
            listTrans = transactiondao.getListTransaction(id, dateFrom, dateTo);
            if (listTrans == null) {
                throw new ApiValidateException("400", "List transaction is null");
            } else {
                log.debug("### getListTransDateToDate end ###");
                return new ResultBean(listTrans, "200", "Get list SUCCESS");
            }

        }

    }

    /**
     * addMoney
     * @author (VNEXT) PhiNH
     * @param json, id
     * @return TransactionEntity transactionEntity
     */
    @Override
    public TransactionEntity addMoney(Integer id, String json) throws ApiValidateException {
        log.debug("### addMoney start ###");
        AccountEntity accountEntity = null;
        SimpleDateFormat datefomat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        TransactionEntity transactionEntity = new TransactionEntity();
        JSONObject jsonTrans = new JSONObject(json);
        if (jsonTrans.isEmpty()) {
            throw new ApiValidateException("400", "Please enter all field");
        } else if (jsonTrans.getDouble("trans_amount") < 0) {
            throw new ApiValidateException("400", "trans-amount", "Transaction amount must be greater than 0.");
        } else if (authService.checkLogin(jsonTrans.getString("phone_number"), jsonTrans.getString("password")) != accountService.findAccount(id)
                .getId_user()) {
            throw new ApiValidateException("400", "id_user", "No access with this id!");
        } else {
            Double fee = 0D;
            Double trans_amount = jsonTrans.getDouble("trans_amount");
            accountEntity = accountService.findAccount(id);
            if (accountEntity == null) {
                throw new ApiValidateException("400", "AccountEntity is not exist");
            } else {
                Double balance = accountEntity.getBalance() + trans_amount;
                transactionEntity.setTrans_amount(trans_amount);
                transactionEntity.setFee(fee);
                transactionEntity.setAction(1);
                transactionEntity.setId_bank_from(accountEntity.getId_bank());
                transactionEntity.setId_bank_to(accountEntity.getId_bank());
                transactionEntity.setId_user_from(accountEntity.getId_user());
                transactionEntity.setId_user_to(accountEntity.getId_user());
                transactionEntity.setId_account_from(id);
                transactionEntity.setId_account_to(id);
                transactionEntity.setDate(datefomat.format(date));
                transactiondao.createTrans(transactionEntity);

                accountEntity.setBalance(balance);
                accountService.updateAccount(accountEntity);
                log.debug("### addMoney end ###");
                return transactionEntity;
            }
        }
    }

    /**
     * withdraw
     * @author (VNEXT) PhiNH
     * @param json
     * @return TransactionEntity transEntity
     */
    @Override
    public TransactionEntity withdraw(String json) throws ApiValidateException {
        log.debug("### withdraw start ###");
        AccountEntity accountEntity = null;
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        TransactionEntity transEntity = new TransactionEntity();
        Double fee = 0D;

        JSONObject jsonTrans = new JSONObject(json);
        if (jsonTrans.isEmpty()) {
            throw new ApiValidateException("400", "Please enter all field");
        } else if (jsonTrans.getDouble("trans_amount") < 0) {
            throw new ApiValidateException("400", "trans_amount", "Transaction amount must be greater than 0.");
        } else if (jsonTrans.getInt("id") <= 0) {
            throw new ApiValidateException("400", "id", "Id must be greater than 0.");
        } else if (authService.checkLogin(jsonTrans.getString("phone_number"), jsonTrans.getString("password")) != accountService
                .findAccount(jsonTrans.getInt("id")).getId_user()) {
            throw new ApiValidateException("400", "id_user", "No access with this id!");
        } else {
            Double trans_amount = jsonTrans.getDouble("trans_amount");
            Integer id = jsonTrans.getInt("id");
            accountEntity = accountService.findAccount(id);
            fee = getFee(accountEntity.getId_bank(), trans_amount);
            if (!checkBalance(accountEntity.getBalance(), trans_amount, fee)) {
                throw new ApiValidateException("400", "Available Balance is not enough!");
            } else {
                transEntity.setTrans_amount(trans_amount);
                transEntity.setFee(fee);
                transEntity.setId_bank_from(accountEntity.getId_bank());
                transEntity.setId_bank_to(accountEntity.getId_bank());
                transEntity.setAction(0);
                transEntity.setId_user_from(accountEntity.getId_user());
                transEntity.setId_user_to(accountEntity.getId_user());
                transEntity.setId_account_from(id);
                transEntity.setId_account_to(id);
                transEntity.setDate(dateformat.format(date));
                transactiondao.createTrans(transEntity);

                accountEntity.setBalance(accountEntity.getBalance() - trans_amount - fee);
                accountService.updateAccount(accountEntity);
                log.debug("### withdraw end ###");
                return transEntity;
            }
        }
    }

    /**
     * transfer
     * @author (VNEXT) PhiNH
     * @param json
     * @return TransactionEntity transEntity
     */
    @Override
    public TransactionEntity transfer(String json) throws ApiValidateException {
        log.debug("### transfer start ###");
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        TransactionEntity transEntity = new TransactionEntity();
        JSONObject jsonTrans = new JSONObject(json);

        if (jsonTrans.isEmpty()) {
            throw new ApiValidateException("400", "Please enter all field.");
        } else if (accountService.findAccount(jsonTrans.getInt("id_account_from")) == null) {
            throw new ApiValidateException("400", "id_account_from", "Account id does not exist!");
        } else if (accountService.findAccount(jsonTrans.getInt("id_account_to")) == null) {
            throw new ApiValidateException("400", "id_account_to", "Account id does not exist.");
        } else if (jsonTrans.getDouble("trans_amount") < 0) {
            throw new ApiValidateException("400", "trans_amount", "Transaction amount must be greater than 0.");
        } else if (authService.checkLogin(jsonTrans.getString("phone_number"), jsonTrans.getString("password")) != accountService
                .findAccount(jsonTrans.getInt("id_account_from")).getId_user()) {
            throw new ApiValidateException("400", "id_user", "No access with this id!");
        } else {

            Integer id_account_from = jsonTrans.getInt("id_account_from");
            Integer id_account_to = jsonTrans.getInt("id_account_to");
            Double trans_amount = jsonTrans.getDouble("trans_amount");
            AccountEntity accountfromEntity = accountService.findAccount(id_account_from);
            AccountEntity accounttoEntity = accountService.findAccount(id_account_to);
            Double accountfrom_balance = accountfromEntity.getBalance();
            Double accountto_balance = accounttoEntity.getBalance();
            Integer id_bank_from = accountfromEntity.getId_bank();
            Integer id_bank_to = accounttoEntity.getId_bank();
            Double fee = getFee(id_bank_from, id_bank_to, trans_amount);
            if (!checkBalance(accountfrom_balance, trans_amount, fee)) {
                throw new ApiValidateException("400", "Available Balance is not enough!");
            } else {
                transEntity.setTrans_amount(trans_amount);
                transEntity.setFee(fee);
                transEntity.setId_bank_from(id_bank_from);
                transEntity.setId_bank_to(id_bank_to);
                transEntity.setAction(2);
                transEntity.setId_account_from(id_account_from);
                transEntity.setId_account_to(id_account_to);
                transEntity.setId_user_from(accountfromEntity.getId_user());
                transEntity.setId_user_to(accounttoEntity.getId_user());
                transEntity.setDate(dateformat.format(date));
                transactiondao.createTrans(transEntity);

                accountfromEntity.setBalance(accountfrom_balance - trans_amount - fee);
                accountService.updateAccount(accountfromEntity);

                accounttoEntity.setBalance(accountto_balance + trans_amount);
                accountService.updateAccount(accounttoEntity);
                log.debug("### transfer end ###");
                return transEntity;
            }

        }

    }

    /**
     * getListTransHistory
     * @author (VNEXT) PhiNH
     * @param json
     * @return ResultBean(listTransHistoryResponse, "200", "Get list transaction history success!")
     */
    @Override
    public ResultBean getListTransHistory(String json) throws ApiValidateException {
        log.debug("### getListTransHistory start ###");
        JSONObject jsonTrans = new JSONObject(json);
        List<TransHistoryResponse> listTransHistoryResponse = new ArrayList<TransHistoryResponse>();
        List<TransactionEntity> listTrans = new ArrayList<TransactionEntity>();
        String bank_name = "";
        if (jsonTrans.isEmpty()) {
            throw new ApiValidateException("400", "Please enter all field!");
        } else {
            int id = authService.checkLogin(jsonTrans.getString("phone_number"), jsonTrans.getString("password"));
            listTrans = transactiondao.getListTransHistory(id);
            for (TransactionEntity transactionEntity : listTrans) {
                TransHistoryResponse transHistoryResponse = new TransHistoryResponse();
                transHistoryResponse.setAction(transactionEntity.getAction());
                bank_name = bankService.findBankById(transactionEntity.getId_bank_from()).getName();
                transHistoryResponse.setBank_name(bank_name);
                transHistoryResponse.setDate(transactionEntity.getDate());
                transHistoryResponse.setMoney(transactionEntity.getTrans_amount());
                listTransHistoryResponse.add(transHistoryResponse);
            }
        }
        log.debug("### getListTransHistory end ###");
        return new ResultBean(listTransHistoryResponse, "200", "Get list transaction history success!");
    }

    /**
     * getListTransFer
     * @author (VNEXT) PhiNH
     * @param json
     * @return ResultBean(listTransfer, "200", "Get list Transfer History success!")
     */
    @Override
    public ResultBean getListTransFer(String json) throws ApiValidateException {
        log.debug("### getListTransFer start ###");
        JSONObject jsonTrans = new JSONObject(json);
        List<TransactionEntity> listTrans = new ArrayList<TransactionEntity>();
        List<TransFerResponse> listTransfer = new ArrayList<TransFerResponse>();
        String bank_name_from = "";
        String bank_name_to = "";
        String user_name_from = "";
        String user_name_to = "";
        if (jsonTrans.isEmpty()) {
            throw new ApiValidateException("400", "Please enter all field!");
        } else {
            int id = authService.checkLogin(jsonTrans.getString("phone_number"), jsonTrans.getString("password"));
            listTrans = transactiondao.getListTransfer(id);
            for (TransactionEntity transactionEntity : listTrans) {
                TransFerResponse transFerResponse = new TransFerResponse();
                if (transactionEntity.getId_user_from() == id) {
                    transFerResponse.setAction("Chuyển tiền.");
                }
                if (transactionEntity.getId_user_to() == id) {
                    transFerResponse.setAction("Nhận tiền.");
                }
                bank_name_from = bankService.findBankById(transactionEntity.getId_bank_from()).getName();
                bank_name_to = bankService.findBankById(transactionEntity.getId_bank_to()).getName();
                user_name_from = userService.findById(transactionEntity.getId_user_from()).getName();
                user_name_to = userService.findById(transactionEntity.getId_user_to()).getName();
                transFerResponse.setBank_name_from(bank_name_from);
                transFerResponse.setBank_name_to(bank_name_to);
                transFerResponse.setName_user_from(user_name_from);
                transFerResponse.setName_user_to(user_name_to);
                transFerResponse.setDate(transactionEntity.getDate());
                transFerResponse.setMoney(transactionEntity.getTrans_amount());
                listTransfer.add(transFerResponse);
            }
        }
        log.debug("### getListTransFer end ###");
        return new ResultBean(listTransfer, "200", "Get list Transfer History success!");
    }

    /**
     * checkBalance
     * @author (VNEXT) PhiNH
     * @param balance, transs_amont, fee
     * @return Boolean check
     */
    @Override
    public Boolean checkBalance(Double balance, Double trans_amount, Double fee) {
        log.debug("### checkBalance start ###");
        Boolean check;
        Double avaiBalance = balance - trans_amount - fee;
        if (avaiBalance < 50000) {
            check = false;
        } else {
            check = true;
        }
        log.debug("### checkBalance end ###");
        return check;
    }

    /**
     * getFee
     * @author (VNEXT) PhiNH
     * @param id_bank1, id_bank2, trans_amount
     * @return Double fee
     */
    @Override
    public Double getFee(Integer id_bank1, Integer id_bank2, Double trans_amount) {
        log.debug("### getFee start ###");
        Double fee = 0D;
        if (id_bank1 == id_bank2) {
            fee = 0D;
        } else {
            fee = getFee(id_bank1, trans_amount);
        }
        log.debug("### getFee end ###");
        return fee;
    }

    /**
     * getFee
     * @author (VNEXT) PhiNH
     * @param id_bank, trans_amount
     * @return Double fee
     */
    @Override
    public Double getFee(Integer id_bank, Double trans_amount) {
        log.debug("### getFee start ###");
        Double fee = 0D;
        BankFeeEntity bankFeeEntity = null;
        bankFeeEntity = transfeeservice.getTransFeeByiId(id_bank);
        if (0 < trans_amount && trans_amount <= 5000000) {
            fee = bankFeeEntity.getFee_1();
        } else if (5000000 < trans_amount && trans_amount <= 1000000) {
            fee = bankFeeEntity.getFee_2();
        } else {
            fee = trans_amount * bankFeeEntity.getFee_3();
        }
        log.debug("### getFee end ###");
        return fee;
    }

    /**
     * outputTransactionToCSV
     * @author (VNEXT) PhiNH
     * @param json
     * @return String csvFile
     */
    @Override
    public String outputTransactionToCSV(String json) throws JSONException, ApiValidateException {
        log.debug("### outputTransactionToCSV start ###");
        JSONObject jsonTrans = new JSONObject(json);
        int id = authService.checkLogin(jsonTrans.getString("phone_number"), jsonTrans.getString("password"));
        List<TransHistoryResponse> listTrans = transactiondao.getListTrans(id);
        String fileName = RenameFile.renameFile();
        String csvFile = "C:/Users/Admin/Documents/output/" + fileName + ".csv";
        String bank_name = "";
        String action = "";
        String date = "";
        Double money = 0D;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader("Date", "MoneyTransaction", "Type", "Fullname", "BankName"));) {
            for (TransHistoryResponse transHistoryResponse : listTrans) {
                bank_name = transHistoryResponse.getBank_name();
                date = transHistoryResponse.getDate();
                money = transHistoryResponse.getMoney();
                if (transHistoryResponse.getAction() == 0) {
                    action = "Rút tiền";
                }
                if (transHistoryResponse.getAction() == 1) {
                    action = "Nạp tiền";
                }
                csvPrinter.printRecord(bank_name, action, date, money);
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.debug("### outputTransactionToCSV end ###");
        return csvFile;
    }

    @Override
    public ResultBean getListTransFer(String json, Integer id_bank) throws ApiValidateException {
        log.debug("### getListTransFer start ###");
        JSONObject jsonTrans = new JSONObject(json);
        List<TransactionEntity> listTrans = new ArrayList<TransactionEntity>();
        List<TransFerResponse> listTransfer = new ArrayList<TransFerResponse>();
        String bank_name_from = "";
        String bank_name_to = "";
        String user_name_from = "";
        String user_name_to = "";
        if (jsonTrans.isEmpty()) {
            throw new ApiValidateException("400", "Please enter all field!");
        } else {
            int id = authService.checkLogin(jsonTrans.getString("phone_number"), jsonTrans.getString("password"));
            listTrans = transactiondao.getListTransfer(id, id_bank);
            for (TransactionEntity transactionEntity : listTrans) {
                TransFerResponse transFerResponse = new TransFerResponse();
                if (transactionEntity.getId_user_from() == id) {
                    transFerResponse.setAction("Chuyển tiền.");
                }
                bank_name_from = bankService.findBankById(transactionEntity.getId_bank_from()).getName();
                bank_name_to = bankService.findBankById(transactionEntity.getId_bank_to()).getName();
                user_name_from = userService.findById(transactionEntity.getId_user_from()).getName();
                user_name_to = userService.findById(transactionEntity.getId_user_to()).getName();
                transFerResponse.setBank_name_from(bank_name_from);
                transFerResponse.setBank_name_to(bank_name_to);
                transFerResponse.setName_user_from(user_name_from);
                transFerResponse.setName_user_to(user_name_to);
                transFerResponse.setDate(transactionEntity.getDate());
                transFerResponse.setMoney(transactionEntity.getTrans_amount());
                listTransfer.add(transFerResponse);
            }
        }
        log.debug("### getListTransFer end ###");
        return new ResultBean(listTransfer, "200", "Get list Transfer History success!");
    }

}
