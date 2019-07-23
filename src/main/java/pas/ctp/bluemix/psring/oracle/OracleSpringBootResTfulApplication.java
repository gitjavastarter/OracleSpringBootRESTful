package pas.ctp.bluemix.psring.oracle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.stereotype.Component;

import pas.ctp.bluemix.psring.oracle.domain.Dept;
import pas.ctp.bluemix.psring.oracle.service.DeptService;
import pas.ctp.bluemix.psring.oracle.service.POIService;

import static pas.ctp.bluemix.psring.oracle.amap.AmapGetPOIType.getPOIType;

@SpringBootApplication
@Component
public class OracleSpringBootResTfulApplication {
    @Autowired
	private DeptService service;


    @Autowired
    private POIService poiService;
    private static OracleSpringBootResTfulApplication oracleSpringBootResTfulApplication;
    @PostConstruct  
    public void init() {  
    	oracleSpringBootResTfulApplication = this;  
    	oracleSpringBootResTfulApplication.service = this.service;
        oracleSpringBootResTfulApplication.poiService = this.poiService;
  
    } 

    public static void main(String[] args) {
        SpringApplication.run(OracleSpringBootResTfulApplication.class, args);
        List<Dept> deptlist =oracleSpringBootResTfulApplication.poiService.getPOIInfo();
        System.out.println(deptlist.size());



        for (Dept dept : deptlist) {
            System.out.println(dept.getLng());
            System.out.println(dept.getRowid_num());
            dept.setCategory(getPOIType(dept.getLng() + "," + dept.getLat()));
            oracleSpringBootResTfulApplication.poiService.updatePOICatById(dept.getCategory(), dept.getRowid_num());
            System.out.println(dept.getCategory());
        }

//      	 List<Dept> deptlist = oracleSpringBootResTfulApplication.service.getDealerLocation();
//      	 CSVUtil.writeToCSV(deptlist);
//       JSONArray jsonArray = JSONArray.fromObject(deptlist);
//        List<Dept> deptlist =oracleSpringBootResTfulApplication.service.getPOI();
//        JSONUtil.writeToJSON(deptlist);

    }
}
