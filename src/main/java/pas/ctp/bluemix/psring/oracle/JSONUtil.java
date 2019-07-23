package pas.ctp.bluemix.psring.oracle;


import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import pas.ctp.bluemix.psring.oracle.domain.Dept;

public class JSONUtil {
	
	public static void writeToJSON(List<Dept> depts) {
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		
		
		String json = "var heatmapData =" + gson.toJson(depts);
			
//		System.out.println(json);
		
		try {
            FileWriter fileWriter = new FileWriter("D:\\HeatMap_withoutdealer.json");

            fileWriter.write(json);
            fileWriter.close();
		}
		
		catch(Exception e){
			e.printStackTrace();
		}

	}

	
}
