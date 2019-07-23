package pas.ctp.bluemix.psring.oracle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pas.ctp.bluemix.psring.oracle.domain.Dept;

import java.util.List;

@Component
public class POIService {
//    @Autowired
//    @Qualifier("secondaryJdbcTemplate")
//    protected JdbcTemplate secondaryJdbcTemplate;
    @Autowired
    JdbcTemplate secondaryJdbcTemplate;


    //Query POI Coordinate
    public List<Dept> getPOIInfo(){

        List<Dept> deps = secondaryJdbcTemplate.query("select rowid_num,longit lng,lat from hti_poi_test", new BeanPropertyRowMapper<Dept>(Dept.class));

        return deps;
    }



    //Update POI Catalog
    public boolean updatePOICatById(String poi_cat, long rowid_num) {
        String sql = "update hti_poi_test set poi_cat = ? where rowid_num = ?";
       return this.secondaryJdbcTemplate.update(sql, new Object[] {poi_cat, rowid_num}) > 0 ? true: false;

    }



    public List<Dept> getWholePOI()
    {
        List<Dept> deps = secondaryJdbcTemplate.query("select longit lng,lat,count(*) count from hti_poi_info where poi_cat='住宿服务'\n" +
                "and (longit<>0 or lat<>0) and (longit<>268.4354550000 and lat<>134.2177270000) and tcu_id not like 'CE%'\n" +
                "group by longit,lat", new BeanPropertyRowMapper<Dept>(Dept.class));

        return deps;
    }

}
