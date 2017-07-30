package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Account;

import java.util.List;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
public interface AccountService {
    List<Account> findAllAccount();

    void saveAccount(Account account, Integer[] deptId);

    Long countAll();

    Long countByDeptId(Integer deptId);

    List<Account> findByDeptId(Integer deptId);

    void delAccountById(Integer id);

    Account login(String mobile, String password);

    void changePassword(String oldPassword, String password, Account account);
}
