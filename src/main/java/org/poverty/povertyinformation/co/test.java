package org.poverty.povertyinformation.co;

import io.lettuce.core.output.StatusOutput;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class test {

    @RequestMapping("/show")
    public String show() {
        System.out.println("aaassd");
        return "/show.html";
    }
}
