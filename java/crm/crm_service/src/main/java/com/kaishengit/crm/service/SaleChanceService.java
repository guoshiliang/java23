package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.SaleChance;
import com.kaishengit.crm.entity.SaleChanceRecord;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/24 0024.
 */
public interface SaleChanceService {

    List<SaleChance> findSaleChanceByCustId(Integer id);

    SaleChance findSaleChanceById(Integer id);

    List<SaleChanceRecord> findChanceRecordByChanceId(Integer id);


    List<String> findAllProgressList();

    void saveNewChance(SaleChance saleChance);

    void delSaleChanceById(Integer id);

    void saveNewChanceRecord(SaleChanceRecord record);

    PageInfo<SaleChance> findByParam(Map<String, Object> queryParam);

    void updateSaleChanceProgress(Integer id, String progress);

}
