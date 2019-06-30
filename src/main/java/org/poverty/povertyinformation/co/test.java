package org.poverty.povertyinformation.co;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.metadata.Table;
import com.alibaba.excel.support.ExcelTypeEnum;


import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.poverty.povertyinformation.pobj.Bookbuilding;
import org.poverty.povertyinformation.service.PoorlmtService;
import org.poverty.povertyinformation.util.ExcelConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class test {
    @Autowired
    PoorlmtService po;


    @RequestMapping("/export")
    public String show() {
        System.out.println("export");

        return "/export.html";
    }
    @RequestMapping("/show2")
    public String show2() {
        System.out.println("show2");

        return "/show2.html";
    }
    @RequestMapping("/show3")
    public String show3() {
        System.out.println("show3");

        return "/show3.html";
    }








    @Test
    public void e() throws IOException {
        // 生成EXCEL并指定输出路径
        OutputStream out = new FileOutputStream("E:\\temp\\withoutHead2.xlsx");
        ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

        // 设置SHEET
        Sheet sheet = new Sheet(1, 0);
        sheet.setSheetName("sheet1");

        // 设置标题
        Table table = new Table(1);
        List<List<String>> titles = new ArrayList<List<String>>();
        titles.add(Arrays.asList("用户ID"));
        titles.add(Arrays.asList("名称"));
        titles.add(Arrays.asList("年龄"));
        titles.add(Arrays.asList("生日"));
        table.setHead(titles);

        // 模拟分批查询：总记录数50条，每次查询20条，  分三次查询 最后一次查询记录数是10
        Integer totalRowCount = 50;
        Integer pageSize = 20;
        Integer writeCount = totalRowCount % pageSize == 0 ? (totalRowCount / pageSize) : (totalRowCount / pageSize + 1);

        // 注： 此处仅仅为了模拟数据，实用环境不需要将最后一次分开，合成一个即可， 参数为： currentPage = i+1;  pageSize = pageSize
        for (int i = 0; i < writeCount; i++) {

            // 前两次查询 每次查20条数据
            if (i < writeCount - 1) {

                List<List<String>> userList = new ArrayList<>();
                for (int j = 0; j < pageSize; j++) {
                    userList.add(Arrays.asList("ID_" + Math.random(), "小明", String.valueOf(Math.random())));
                }
                writer.write0(userList, sheet, table);

            } else if (i == writeCount - 1) {

                // 最后一次查询 查多余的10条记录
                List<List<String>> userList = new ArrayList<>();
                Integer lastWriteRowCount = totalRowCount - (writeCount - 1) * pageSize;
                for (int j = 0; j < lastWriteRowCount; j++) {
                    userList.add(Arrays.asList("ID_" + Math.random(), "小明", String.valueOf(Math.random()) ));
                }
                writer.write0(userList, sheet, table);
            }
        }

        writer.finish();

    }

    @RequestMapping("/ex")
    public void exportSysSystemExcel(String area,String ping, HttpServletResponse response) throws Exception {
        long t1 = System.currentTimeMillis();
//        List<Bookbuilding> list=po.fileAll(null,null);
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            ExcelWriter writer = new ExcelWriter(out, ExcelTypeEnum.XLSX);

            // 设置EXCEL名称
            String fileName = new String(("SystemExcel").getBytes(), "UTF-8");

            // 设置SHEET名称
            Sheet sheet = new Sheet(1, 0);
            sheet.setSheetName("Table");

            Integer totalRowCount = po.fileAll(null,null).size();
            Integer pageSize = ExcelConstant.PER_WRITE_ROW_COUNT;
            Integer writeCount = totalRowCount % pageSize == 0 ? (totalRowCount / pageSize) : (totalRowCount / pageSize + 1);

            // 设置标题
            Table table = new Table(1);
            List<List<String>> titles = new ArrayList<List<String>>();
            titles.add(Arrays.asList("系统名称"));
            titles.add(Arrays.asList("系统标识"));
            titles.add(Arrays.asList("描述"));
            titles.add(Arrays.asList("状态"));
            titles.add(Arrays.asList("创建人"));
            titles.add(Arrays.asList("创建时间"));
            table.setHead(titles);

//            for (int i = 0; i < writeCount; i++) {
                List<List<String>> dataList = new ArrayList<>();
//
//                // 此处查询并封装数据即可 currentPage, pageSize这个变量封装好的 不要改动
//                PageHelper.startPage(i + 1, pageSize);

                List<Bookbuilding> sysSystemVOList = po.fileAll("王豪", null);
                if (!CollectionUtils.isEmpty(sysSystemVOList)) {
                    sysSystemVOList.forEach(eachSysSystemVO -> {
                        dataList.add(Arrays.asList(
                                eachSysSystemVO.getName(),
                                eachSysSystemVO.getAdministrativeVillage(),
                                eachSysSystemVO.getIdCard(),
                                eachSysSystemVO.getOvercomePoverty(),
                                eachSysSystemVO.getVillage()
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
            writer.finish();
            out.flush();

        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }



    }





}
