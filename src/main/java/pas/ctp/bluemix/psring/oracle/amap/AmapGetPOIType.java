package pas.ctp.bluemix.psring.oracle.amap;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.json.JSONArray;
import org.json.JSONObject;


import static org.springframework.util.StringUtils.replace;


public class AmapGetPOIType{

    public static String parseJSONWithJSONObject(String jsonData){
        JSONObject jObject = null;
        jObject  = new JSONObject(jsonData);
        JSONObject geoObject = jObject.getJSONObject("regeocode");
        JSONArray  poi = geoObject.getJSONArray("pois");
        if(poi.length()==0){
            return "Unspecified";
        }
        else {
            JSONObject poi0 = poi.getJSONObject(0);
            String geoType = poi0.getString("type");
            String geoName = poi0.getString("name");
            String typeDetails[] = geoType.split(";");
            String typeDetail = typeDetails[0];

            return typeDetail;
        }
    }


    public static String getPOIType(String lnglat){
        String POIType="";
        try{
        String map_codeurl = "http://restapi.amap.com/v3/geocode/regeo?output=json&location=lnglat&key=f16c2ccb5f9474aa5f9f3ab28e63ef9f&radius=0&extensions=all";
        map_codeurl=replace(map_codeurl,"lnglat",lnglat);
        StringBuffer sbf = new StringBuffer();
        String result = null;
        HttpURLConnection conn = (HttpURLConnection) new URL(map_codeurl).openConnection();
        conn.setRequestProperty("Accept", "application/json");
        conn.setConnectTimeout(5000);
        conn.setRequestMethod("GET");
        int code = conn.getResponseCode();
        System.out.println(code);
        if(code==200){
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
            POIType=parseJSONWithJSONObject(result);
        }
    }
        catch(IOException ex) {
            ex.printStackTrace();
        }
        return POIType;

    }


    public static void main(String[] args){

        Logger logger = Logger.getLogger(getPOIType("118.94531,32.176796"));
        logger.info(getPOIType("118.94531,32.176796"));
    }
}