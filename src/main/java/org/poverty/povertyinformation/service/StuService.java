package org.poverty.povertyinformation.service;

import org.poverty.povertyinformation.pobj.RecipientStu;

import java.util.List;

public interface StuService {
    public List<RecipientStu> fillAllStu(String name,String idCard);
}
