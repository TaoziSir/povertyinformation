package org.poverty.povertyinformation.service;


import org.poverty.povertyinformation.pobj.Bookbuilding;

import java.util.List;

public interface PoorlmtService {

    public List<String> groupArea();

    public List<Bookbuilding> fileAll(String name,String area,String idCard);
}
