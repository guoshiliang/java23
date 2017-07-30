package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;

import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**客户管理业务层
 * Created by Administrator on 2017/7/20 0020.
 */
public interface CustomerService {


PageInfo<Customer> findMyCustomer(Map<String, Object> queryParam);

    List<String> findAllTrade();

    List<String> findAllSource();

    void saveNewCustomer(Customer customer, Account account);

    Customer findById(Integer id);

    void editCustomer(Customer customer);

    void delCustomer(Customer customer);

    void shareCustomerToPublic(Customer customer, Account account);

    void transferCustomerToAccount(Customer customer, Integer accountId, Account account);

    void exportAccountCustomerToExcel(Account account, OutputStream outputStream);

    List<Customer> findByAccountId(Integer accountId);

    List<Map<String,Object>> findCustomerLevelCount();

}
