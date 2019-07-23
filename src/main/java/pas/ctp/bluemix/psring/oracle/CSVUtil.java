package pas.ctp.bluemix.psring.oracle;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import pas.ctp.bluemix.psring.oracle.domain.Dept;
import pas.ctp.bluemix.psring.oracle.service.DeptService;

public class CSVUtil {
	

	/**
     * 导出
     * 
     * @param file csv文件(路径+文件名)，csv文件不存在会自动创建
     * @param dataList 数据
     * @return
     */
	public static boolean exportCsv(File file, List<String> dataList){
        boolean isSucess=false;
        
        FileOutputStream out=null;
        OutputStreamWriter osw=null;
        BufferedWriter bw=null;
        try {
            out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);
            bw =new BufferedWriter(osw);
            if(dataList!=null && !dataList.isEmpty()){
                for(String data : dataList){
                    bw.append(data).append("\r");
                }
            }
            isSucess=true;
        } catch (Exception e) {
            isSucess=false;
        }finally{
            if(bw!=null){
                try {
                    bw.close();
                    bw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(osw!=null){
                try {
                    osw.close();
                    osw=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
            if(out!=null){
                try {
                    out.close();
                    out=null;
                } catch (IOException e) {
                    e.printStackTrace();
                } 
            }
        }
        
        return isSucess;
	}
	
	public static final String CSV_SEPARATOR = ",";
	public static void writeToCSV(List<Dept> depts)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\products.csv"), "UTF-8"));
            for (Dept dept1 : depts)
            {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(dept1.getLng() <=0 ? 0 : dept1.getLng());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(dept1.getLat());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }
	
	public static void main(String[] args) {
		 List<String> dataList=new ArrayList<String>();
	        dataList.add("1,张三,男");
	        dataList.add("2,李四,男");
	        dataList.add("3,小红,女");
	        boolean isSuccess=CSVUtil.exportCsv(new File("D:/test.csv"), dataList);
	        System.out.println(isSuccess);         
	}
}
