package org.poverty.povertyinformation.service.impl;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.formula.functions.T;
import org.poverty.povertyinformation.mapper.StuMapper1;
import org.poverty.povertyinformation.pobj.Bookbuilding;
import org.poverty.povertyinformation.pobj.RecipientStu;
import org.poverty.povertyinformation.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
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

    @Override
    public List<RecipientStu> exportRecipientStu(String school, String hm) {
        return sm.exportRecipientStu(school, hm);
    }


}
