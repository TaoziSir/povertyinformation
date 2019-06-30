package org.poverty.povertyinformation.co;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.mybatis.spring.annotation.MapperScan;
import org.poverty.povertyinformation.pobj.Bookbuilding;
import org.poverty.povertyinformation.pobj.RecipientStu;
import org.poverty.povertyinformation.service.PoorlmtService;
import org.poverty.povertyinformation.service.StuService;
import org.poverty.povertyinformation.util.ExcelConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class poorlmtController {

    @Autowired
    private PoorlmtService pr;
    @Autowired
    private StuService sr;

    @RequestMapping("/fileAll")
    public List<Bookbuilding> fileAll(String name, String idCard) {
        System.out.println(name + "==" + "==" + idCard);
        List<Bookbuilding> list = pr.fileAll(name, idCard);
        System.out.println(list.size());
        return list;
    }

    @RequestMapping("/fileAllStu")
    public List<RecipientStu> fileAllStu(String name, String idCard) {
        List<RecipientStu> list = sr.fillAllStu(name, idCard);

        return list;
    }


    @RequestMapping("/excel")
    public String exportSysSystemExcel(String area, String ping, HttpServletResponse response) throws Exception {
        long t1 = System.currentTimeMillis();
        System.out.println(area + "==" + ping);
        if (area == "") {
            area = null;
        }
        if (ping == "") {
            ping = null;
        }
        ServletOutputStream out = null;
        List<Bookbuilding> sysSystemVOList = null;
        ExcelWriter writer=null;
        try {
            out = response.getOutputStream();
            writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

            // 设置EXCEL名称
            String fileName = new String(("SystemExcel").getBytes(), "UTF-8");

            // 设置SHEET名称
            Sheet sheet = new Sheet(1, 0);
            sheet.setSheetName("Table");

            // 设置标题
            Table table = new Table(1);
            List<List<String>> titles = new ArrayList<List<String>>();
            titles.add(Arrays.asList("姓名"));
            titles.add(Arrays.asList("身份证"));
            titles.add(Arrays.asList("地区"));
            titles.add(Arrays.asList("县（乡）"));
            titles.add(Arrays.asList("村（社区）"));
            titles.add(Arrays.asList("脱贫情况"));
            table.setHead(titles);
            // 查询总数并 【封装相关变量 这块直接拷贝就行 不要改动】
//            Integer totalRowCount = pr.exportBookbuilding(area, ping).size();
//            Integer pageSize = ExcelConstant.PER_WRITE_ROW_COUNT;
//            Integer writeCount = totalRowCount % pageSize == 0 ? (totalRowCount / pageSize) : (totalRowCount / pageSize + 1);

            // 写数据 这个i的最大值直接拷贝就行了 不要改


//            for (int i = 0; i < writeCount; i++) {
            List<List<String>> dataList = new ArrayList<>();
//                // 此处查询并封装数据即可 currentPage, pageSize这个变量封装好的 不要改动
//                PageHelper.startPage(i + 1, pageSize);
            sysSystemVOList = pr.exportBookbuilding(area, ping);
            if (!CollectionUtils.isEmpty(sysSystemVOList)) {
                sysSystemVOList.forEach(eachSysSystemVO -> {
                    dataList.add(Arrays.asList(
                            eachSysSystemVO.getName(),
                            eachSysSystemVO.getIdCard(),
                            eachSysSystemVO.getArea(),
                            eachSysSystemVO.getVillage(),
                            eachSysSystemVO.getAdministrativeVillage(),
                            eachSysSystemVO.getOvercomePoverty()
                    ));
                });
            }
            writer.write0(dataList, sheet, table);
//            }
            long t2 = System.currentTimeMillis();

            System.out.println("CSV: " + (t2 - t1));

            // 下载EXCEL
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");

            out.flush();
            writer.finish();
            out.close();
        }
        finally {

        }
        String zi="";
        if (sysSystemVOList.size()>0) {
            zi="导出成功";
        }else {
            zi="此条件没有数据";
        }
        return zi;
    }
    @RequestMapping("/excelStu")
    public String exportRecipientStu(String school, String hm, HttpServletResponse response) throws Exception {
//        long t1 = System.currentTimeMillis();
//        System.out.println(school + "==" + hm);
        if (school == "") {
            school = null;
        }
        if (hm == "") {
            hm = null;
        }
        ServletOutputStream out = null;
        List<RecipientStu> sysSystemVOList = null;
        ExcelWriter writer=null;
        try {
            out = response.getOutputStream();
            writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

            // 设置EXCEL名称
            String fileName = new String(("student").getBytes(), "UTF-8");

            // 设置SHEET名称
            Sheet sheet = new Sheet(1, 0);
            sheet.setSheetName("Table");

            // 设置标题
            Table table = new Table(1);
            List<List<String>> titles = new ArrayList<List<String>>();
            titles.add(Arrays.asList("姓名"));
            titles.add(Arrays.asList("身份证"));
            titles.add(Arrays.asList("学校"));
            titles.add(Arrays.asList("居住地址"));
            titles.add(Arrays.asList("受助项目"));
            titles.add(Arrays.asList("受助金额"));
            titles.add(Arrays.asList("受助信息"));
            table.setHead(titles);

            List<List<String>> dataList = new ArrayList<>();

            sysSystemVOList = sr.exportRecipientStu(school, hm);
            if (!CollectionUtils.isEmpty(sysSystemVOList)) {
                sysSystemVOList.forEach(eachSysSystemVO -> {
                    dataList.add(Arrays.asList(
                            eachSysSystemVO.getName(),
                            eachSysSystemVO.getIdCard(),
                            eachSysSystemVO.getSchool(),
                            eachSysSystemVO.getAddress(),
                            eachSysSystemVO.getHelpedProject(),
                            eachSysSystemVO.getHelpedmoney(),
                            eachSysSystemVO.getHelpedMessage()
                    ));
                });
            }
            writer.write0(dataList, sheet, table);
//            long t2 = System.currentTimeMillis();
//            System.out.println("CSV: " + (t2 - t1));

            // 下载EXCEL
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
            response.setContentType("multipart/form-data");
            response.setCharacterEncoding("utf-8");

            out.flush();
            writer.finish();
            out.close();
        }
        finally {

        }
        String zi="";
        if (sysSystemVOList.size()>0) {
            zi="导出成功";
        }else {
            zi="此条件没有数据";
        }
        return zi;
    }

    }
