package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Disk;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by Administrator on 2017/7/26 0026.
 */

public interface DiskService {

    List<Disk> findDiskByPid(Integer pId);

    Disk findById(Integer id);

    void saveNewFile(Disk disk, InputStream inputStream);

    void saveNewFolder(Disk disk);

    void downloadFile(OutputStream outputStream, Disk disk);

}
