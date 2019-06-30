package org.poverty.povertyinformation.service.impl;

import org.poverty.povertyinformation.mapper.PoorImtMapper;
import org.poverty.povertyinformation.pobj.Bookbuilding;
import org.poverty.povertyinformation.service.PoorlmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoorlmtServiceImpl implements PoorlmtService {

    @Autowired
    private PoorImtMapper poorlmt;




    @Override
    public List<Bookbuilding> fileAll(String name,  String idCard) {
        return poorlmt.fileAll(name,idCard);
    }

    @Override
    public List<Bookbuilding> exportBookbuilding(String area, String over) {
        return poorlmt.exportBookbuilding(area, over);
    }


}
