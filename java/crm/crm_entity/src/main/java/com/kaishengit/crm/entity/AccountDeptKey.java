package com.kaishengit.crm.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/7/17 0017.
 */
public class AccountDeptKey implements Serializable{
    private Integer deptId;

    private Integer accountId;

    private static final long serialVersionUID = 1L;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

}
