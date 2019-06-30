package org.poverty.povertyinformation.service;

import org.apache.poi.ss.formula.functions.T;
import org.poverty.povertyinformation.pobj.RecipientStu;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface StuService {
    public List<RecipientStu> fillAllStu(String name,String idCard);
    public List<RecipientStu> exportRecipientStu(String school,String hm);
}
