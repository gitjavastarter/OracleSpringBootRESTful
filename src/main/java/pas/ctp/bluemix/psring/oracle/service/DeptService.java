package pas.ctp.bluemix.psring.oracle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import pas.ctp.bluemix.psring.oracle.domain.Dept;

import javax.annotation.Resource;
import java.util.List;

@Component
public class DeptService
{
//    @Resource(name="primaryJdbcTemplate")
//    protected JdbcTemplate primaryJdbcTemplate;

    @Autowired
    JdbcTemplate primaryJdbcTemplate;

    public List<Dept> getAllDeps()
    {
        List<Dept> deps = primaryJdbcTemplate.query("select longitude lng,latitude lat from htenterprise.vehicle_location where created_date between to_date('20171201','yyyymmdd')\n" +
        		"and to_date('20180101','yyyymmdd')", new BeanPropertyRowMapper<Dept>(Dept.class));

        return deps;
    }
    
    public List<Dept> getHeatMap()
    {
        List<Dept> deps = primaryJdbcTemplate.query("select longitude lng,latitude lat,count(*) count from htenterprise.vehicle_location where (created_date between to_date('20171201','yyyymmdd') and to_date('20180101','yyyymmdd'))\n" +
        		"and (longitude<>0 or latitude<>0) and (longitude<>268.4354550000 and latitude<>134.2177270000)\n" + 
        		"group by longitude,latitude", new BeanPropertyRowMapper<Dept>(Dept.class));

        return deps;
    }

    public List<Dept> getHeatMap_withoutdealer()
    {
        List<Dept> deps = primaryJdbcTemplate.query("select longitude lng,latitude lat,count(*) count from  htenterprise.vehicle_location where \n" +
                "(longitude<>0 or latitude<>0) and (longitude<>268.4354550000 and latitude<>134.2177270000) \n" +
                "and GetDistance(39.832544,116.37647,latitude,longitude)>2\n" +
                "and GetDistance(40.050654,116.250588,latitude,longitude)>2\n" +
                "and GetDistance(40.095766,116.415498,latitude,longitude)>2\n" +
                "and GetDistance(39.96791,116.492029,latitude,longitude)>2\n" +
                "group by longitude,latitude", new BeanPropertyRowMapper<Dept>(Dept.class));

        return deps;
    }

    public List<Dept> getHeatMap_beijing()
    {
        List<Dept> deps = primaryJdbcTemplate.query("select longitude lng,latitude lat,count(*) count from  htenterprise.vehicle_location where\n" +
                "                (longitude<>0 or latitude<>0) and (longitude<>268.4354550000 and latitude<>134.2177270000) \n" +
                "                and GetDistance(39.832544,116.37647,latitude,longitude)>2\n" +
                "                and GetDistance(40.050654,116.250588,latitude,longitude)>2\n" +
                "                and GetDistance(40.095766,116.415498,latitude,longitude)>2\n" +
                "                and GetDistance(39.96791,116.492029,latitude,longitude)>2\n" +
                "                and longitude>115.065764 and longitude<118.719224\n" +
                "                and latitude>39.063348 and latitude<40.637079\n" +
                "                and (created_date between to_date('20141201','yyyymmdd') and sysdate)\n" +
                "                group by longitude,latitude", new BeanPropertyRowMapper<Dept>(Dept.class));

        return deps;
    }
    
    public List<Dept> getMostActiveUser()
    {
        List<Dept> deps = primaryJdbcTemplate.query("select longitude lng,latitude lat,count(*) count from htenterprise.vehicle_status t, htenterprise.vehicle_location t1, htenterprise.account_information t2\n" +
        		"where t.vehicle_location_id=t1.vehicle_location_id and t.account_information_id=t2.account_information_id\n" + 
        		"and t2.vin='WVWLR57N7GV201313' group by longitude,latitude", new BeanPropertyRowMapper<Dept>(Dept.class));

        return deps;
    }

    public List<Dept> getDealerLocation()
    {
        List<Dept> deps = primaryJdbcTemplate.query("select longitude,latitude from\n" +
                "(select distinct t.vehicle_location_id,t.longitude,t.latitude from htenterprise.vehicle_location t,DEALER_COMPETITION t1\n" +
                "where getdistance(t.latitude,t.longitude,t1.latitude,t1.longitude)<2 and (t.created_date between to_date('20171201', 'yyyymmdd') and\n" +
                "       to_date('20180101', 'yyyymmdd'))\n" +
                "   and (t.longitude <> 0 or t.latitude <> 0)\n" +
                "   and (t.longitude <> 268.4354550000 and t.latitude <> 134.2177270000))", new BeanPropertyRowMapper<Dept>(Dept.class));

        return deps;
    }
   
}
