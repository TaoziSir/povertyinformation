package org.poverty.povertyinformation.co;

import org.mybatis.spring.annotation.MapperScan;
import org.poverty.povertyinformation.pobj.Bookbuilding;
import org.poverty.povertyinformation.service.PoorlmtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class poorlmtController {

    @Autowired
    private PoorlmtService pr;

    @RequestMapping("/fileAll")
    public List<Bookbuilding> fileAll(String name, String idCard) {
        if (idCard=="") {
            idCard=null;
        }
        if (name=="") {
            name=null;
        }

        System.out.println(name+"=="+"=="+idCard);
        List<Bookbuilding> list=pr.fileAll(name, idCard);
        System.out.println(list.size());
        return list;
    }

//    @RequestMapping("/groupArea")
//    public List<String> ar() {
//        List<String> list=pr.groupArea();
//
//        return list;
//    }
}
