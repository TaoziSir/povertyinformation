package org.poverty.povertyinformation.co;

import org.mybatis.spring.annotation.MapperScan;
import org.poverty.povertyinformation.pobj.Bookbuilding;
import org.poverty.povertyinformation.pobj.RecipientStu;
import org.poverty.povertyinformation.service.PoorlmtService;
import org.poverty.povertyinformation.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

public class poorlmtController {

    @Autowired
    private PoorlmtService pr;
    @Autowired
    private StuService sr;

    @RequestMapping("/fileAll")
    public List<Bookbuilding> fileAll(String name, String idCard) {
        System.out.println(name+"=="+"=="+idCard);
        List<Bookbuilding> list=pr.fileAll(name, idCard);
        System.out.println(list.size());
        return list;
    }

    @RequestMapping("/fileAllStu")
    public List<RecipientStu> fileAllStu (String name,String idCard) {
        List<RecipientStu> list=sr.fillAllStu(name, idCard);

        return list;
    }
}
