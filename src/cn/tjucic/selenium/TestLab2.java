package cn.tjucic.selenium;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.io.FileInputStream;
import java.io.InputStream;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


@RunWith (Parameterized.class)
public class TestLab2 {
	  private static WebDriver driver;
	  private static String baseUrl = "http://121.193.130.195:8800/login";
	  private static String filePath = "src/resources/data/软件测试名单.xlsx";
	  private String studentID = "";
	  private String studentName = "";
	  private String gitURL = "";
	  private static List<String[]> list = new ArrayList<String[]>();
	  
	  public TestLab2(String studentID, String studentName, String gitURL) {//构造函数用以调用参数
		  this.studentID = studentID;
		  this.studentName = studentName;
		  this.gitURL = gitURL;
	  }

	  @BeforeClass//在测试前只运行一次，保证打开一次浏览器并始终在一个网页进行测试
	  public static void setUp() throws Exception {
		  String driverPath = System.getProperty("user.dir") + "/src/resources/driver/geckodriver.exe";
		  System.setProperty("webdriver.gecko.driver", driverPath);//启动浏览器相关驱动
		  driver = new FirefoxDriver();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.get(baseUrl);
	  }
	  
	  @Parameters//参数列表构造
	  public static List<String[]> testValues(){
		  Row row = null;
		  String sID,sName, gURL;
		  List<String[]> ls = new ArrayList<String[]>();
		  try {
			  InputStream is = new FileInputStream(filePath);
			  Workbook wb = new XSSFWorkbook(is);
			  Sheet sheet = wb.getSheetAt(0);
			  int rowNum = sheet.getPhysicalNumberOfRows();
			  
			  for(int i = 2; i < rowNum; i++) {//前两行是标题，从第三行开始获取数据
			    	row = sheet.getRow(i);
			    	sID = getCellFormatValue(row.getCell(1)).toString();
			    	sName = getCellFormatValue(row.getCell(2)).toString();
			    	gURL = getCellFormatValue(row.getCell(3)).toString();
			    	ls.add(new String[] {sID, sName, gURL});
			  }
		  } catch (Exception e) {
			e.printStackTrace();
		  }
		  list = ls;
		return list;
	  }

	  @Test
	  public void testLab2() throws Exception {
		  driver.findElement(By.name("id")).clear();
	      driver.findElement(By.name("id")).sendKeys(studentID);
	      driver.findElement(By.name("password")).sendKeys(studentID.substring(4));
	      driver.findElement(By.id("btn_login")).click();
	      //测试内容包括studentID，studentName，gitURL，全部无误方显示正确
	      assertEquals(studentID, driver.findElement(By.id("student-id")).getText());
	      assertEquals(studentName, driver.findElement(By.id("student-name")).getText());
	      assertEquals(gitURL, driver.findElement(By.id("student-git")).getText());

	      //返回登录界面，进行下一组测试
	      driver.findElement(By.id("btn_logout")).click();
	      driver.findElement(By.id("btn_return")).click();
//	      driver.close();
		  
//	      driver.findElement(By.name("id")).clear();
//	      driver.findElement(By.name("id")).sendKeys("3016218093");
//	      driver.findElement(By.name("password")).sendKeys("218093");
//	      driver.findElement(By.id("btn_login")).click();
//	      assertEquals("https://github.com/SouperO", driver.findElement(By.id("student-git")).getText());
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	  }
	  
	  public static Object getCellFormatValue(Cell cell){//根据单元格数据类型获取内容
	        Object cellValue = null;
	        if(cell!=null){
	            //判断cell类型
	            switch(cell.getCellType()){
	                case NUMERIC:{
	                    cell.setCellType(CellType.STRING);  //将数值型cell设置为string型
	                    cellValue = cell.getStringCellValue();
	                    break;
	                }
	                case FORMULA:{
	                    //判断cell是否为日期格式
	                    if(DateUtil.isCellDateFormatted(cell)){
	                     //转换为日期格式YYYY-mm-dd
	                        cellValue = cell.getDateCellValue();
	                    }else{
	                            //数字
	                        cellValue = String.valueOf(cell.getNumericCellValue());
	                    }
	                    break;
	                }
	                case STRING:{
	                     cellValue = cell.getRichStringCellValue().getString();
	                     break;
	                }
	                default:
	                    cellValue = "";
	            }
	        }else{
	            cellValue = "";
	        }
	        return cellValue;
	    }
	    
}
