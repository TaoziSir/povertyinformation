package org.poverty.povertyinformation.co;

import io.lettuce.core.output.StatusOutput;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.poverty.povertyinformation.pobj.Bookbuilding;
import org.poverty.povertyinformation.service.PoorlmtService;
import org.poverty.povertyinformation.util.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class test {
    @Autowired
    PoorlmtService po;

    @RequestMapping("/show")
    public String show() {
        System.out.println("show");

        return "/show.html";
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
    @RequestMapping("/csv2")
    @ResponseBody
    public String csv2() {

        System.out.println("csv2");
        long t1 = System.currentTimeMillis();
        List<Bookbuilding> list=po.fileAll(null,null);
        LinkedHashMap map = new LinkedHashMap();
        map.put("1", "第一列");
        map.put("2", "第二列");
        map.put("3", "第三列");
        map.put("4", "第四列");
        map.put("5", "第四列");
        map.put("6", "第四列");
        map.put("7", "第四列");
        map.put("8", "第四列");
        map.put("9", "第四列");
        map.put("10", "第四列");
        map.put("11", "第四列");
        map.put("12", "第四列");
        map.put("13", "第四列");
        map.put("14", "第四列");
        String path = "G://";
        String fileName = "文件导出";
        String fileds[] = new String[]
                { "name", "area","village","administrativeVillage","naturalVillage",
                        "homeNumber","personNumber","identityCard",
                        "personSum","relation","cultureStandard","studentStatus","healthCondition"};// 设置列英文名（也就是实体类里面对应的列名）
        File file = CsvUtil.createCSVFile(list, fileds, map, path,
                fileName);
        String fileName2 = file.getName();
        System.out.println("文件名称：" + fileName2);
        long t2 = System.currentTimeMillis();

        System.out.println("CSV: " + (t2 - t1));
        return "完成";
    }

    @RequestMapping("3")
    @ResponseBody
    public String csv1()throws Exception{

        List<Bookbuilding> list=po.fileAll(null,null);
        int times = 10000 * 10;
        Object[] cells = {"满100减15元", "100011", 15};        //  导出为CSV文件
        long t1 = System.currentTimeMillis();
        FileWriter writer = new FileWriter("G:/test6.csv");
        CSVPrinter printer = CSVFormat.EXCEL.print(writer);
        for (int i = 0; i < list.size(); i++) {
            printer.printRecords(list);
        }
        printer.flush();
        printer.close();
        long t2 = System.currentTimeMillis();

        System.out.println("CSV: " + (t2 - t1));

        return "ok";
    }

    /**
     * 测试写100万数据需要花费多长时间     */
    @Test
    public void testMillion() throws Exception {
        List<Bookbuilding> list=po.fileAll("刘伟","430511198112094017");
        int times = 10000 * 10;
        Object[] cells = {"满100减15元", "100011", 15};        //  导出为CSV文件
        long t1 = System.currentTimeMillis();
        FileWriter writer = new FileWriter("G:/test3.csv");
        CSVPrinter printer = CSVFormat.EXCEL.print(writer);
        for (int i = 0; i < list.size(); i++) {
            printer.printRecord(list);
        }
        printer.flush();
        printer.close();
        long t2 = System.currentTimeMillis();

        System.out.println("CSV: " + (t2 - t1));
        //导出为Excel文件
        long t3 = System.currentTimeMillis();
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        for (int i = 0; i < list.size(); i++) {
            XSSFRow row = sheet.createRow(i);
            for (int j = 0; j < cells.length; j++) {
                XSSFCell cell = row.createCell(j);
                cell.setCellValue(String.valueOf(cells[j]));
            }
        }
        FileOutputStream fos = new FileOutputStream("G:/test2.xlsx");
        workbook.write(fos);
        fos.flush();
        fos.close();        long t4 = System.currentTimeMillis();
        System.out.println("Excel: " + (t4 - t3));
    }




}
