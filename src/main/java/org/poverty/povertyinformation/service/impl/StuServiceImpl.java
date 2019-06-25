package org.poverty.povertyinformation.service.impl;

import org.poverty.povertyinformation.mapper.StuMapper1;
import org.poverty.povertyinformation.pobj.RecipientStu;
import org.poverty.povertyinformation.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StuServiceImpl implements StuService{

    @Autowired
    private StuMapper1 sm;

    @Override
    public List<RecipientStu> fillAllStu(String name, String idCard) {
        List<RecipientStu> list=sm.fileAllStu(name, idCard);
        return list;
    }
}
