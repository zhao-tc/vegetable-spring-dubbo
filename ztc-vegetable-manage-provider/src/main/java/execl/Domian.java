package execl;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;


public class Domian {
    public static void readExcel(String path) {
        int count = 102594;
        File file = new File(path);
        FileInputStream fis = null;
        Workbook workBook = null;
        if (file.exists()) {
            try {
                File f=new File("C:\\Users\\ztc\\Desktop\\ssss.txt");
                FileOutputStream fos1=new FileOutputStream(f,true);
                OutputStreamWriter dos1=new OutputStreamWriter(fos1);

                fis = new FileInputStream(file);
                workBook = WorkbookFactory.create(fis);
                int numberOfSheets = workBook.getNumberOfSheets();
                // sheet工作表
                for (int s = 0; s < numberOfSheets; s++) {
                    Sheet sheetAt = workBook.getSheetAt(s);
                    //获取工作表名称
                    String sheetName = sheetAt.getSheetName();
                    System.out.println("工作表名称：" + sheetName);
                    // 获取当前Sheet的总行数
                    int rowsOfSheet = sheetAt.getPhysicalNumberOfRows();
                    System.out.println("当前表格的总行数:" + rowsOfSheet);
                    for (int r = 4; r < 5; r++) {
                        Row row = sheetAt.getRow(r);
                        if (row == null) {
                            continue;
                        } else {
                            int rowNum = row.getRowNum() + 1;
                            System.out.println("当前行:" + rowNum);
                            // 总列(格)

                            String sql = "INSERT INTO esp_am_agent_salary " +
                                    "(SID,PLATFORM_TYPE,PLATFORM_SUB_TYPE,AGENT_CODE,AGENT_NAME,AGENT_GRAND,AGENT_GROUP,AGENT_GROUP_NAME,SALARY_MOUNTH,EARLY_COMMISSION,SOCIAL_SECURITY_ALLOWANCE,SERVICE_ALLOWANCE,RECOMMENDED_ALLOWANCE,MANAGER_MANAGEMENT_ALLOWANCE,MANAGER_GROWTH_ALLOWANCE,DIRECTOR_MANAGEMENT_ALLOWANCE,DIRECTOR_GROWTH_ALLOWANCE,CONTINUE_COMMISSION,RENEWAL_BONUS,MANAGER_RENEWAL_BONUS,DIRECTOR_RENEWAL_BONUS,ADDED_VALUE_TAX,INDIVIDUAL_INCOME_TAX,LAST_PERIOD_BALANCE,CURRENT_PERIOD_BALANCE,ADD_OR_DEDUCT_MONEY,OTHER_AD_MONEY,TO_PAY_AMOUNT,ACTUAL_PAY_AMOUNT," +
                                    "CREATED_USER,CREATED_DATE,MODIFIED_USER,MODIFIED_DATE,IS_DELETE) VALUES \n" +
                                    "("+count+",'HR','HRGX','"+row.getCell(1)+"','"+row.getCell(2)+"','"+row.getCell(3)+"','"+row.getCell(4)+"','"+row.getCell(5)+"','202001','"+row.getCell(6)+"','"+row.getCell(7)+"','"+row.getCell(8)+"','"+row.getCell(9)+"','"+row.getCell(10)+"','"+row.getCell(11)+"','"+row.getCell(12)+"','"+row.getCell(13)+"','"+row.getCell(14)+"','"+row.getCell(15)+"','"+row.getCell(16)+"','"+row.getCell(17)+"','"+row.getCell(18)+"','"+row.getCell(26)+"','"+row.getCell(27)+"','"+row.getCell(28)+"','"+row.getCell(29)+"','"+row.getCell(30)+"','"+row.getCell(31)+"','"+row.getCell(32)+"','system','2020-02-18 16:43:02.0','system','2020-02-18 16:43:10.0','1')\n" + ";";
                            count ++;
                            dos1.write(sql);
                            dos1.flush();
                        }
                    }
                }
                if (fis != null) {
                    fis.close();
                    fos1.close();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } else {
            System.out.println("文件不存在!");
        }
    }

    public static void main(String[] args) {
        readExcel("C:\\Users\\ztc\\Desktop\\AgentWageInfoX (1).xls");
    }


}

