package zx.ffts.web.action.xiongli;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;


import net.sf.json.JSONObject;


public class OrderAction extends BaseAction{
	
	public void getShopping(){
	   response.setContentType("text/html");
	   response.setCharacterEncoding("utf-8");
	   PrintWriter out;
	try {
		out = response.getWriter();
		JSONObject json=new JSONObject();
		List<Map<String, Object>> list=rdao.getShopping();
		json.put("list", list);
	    out.write(json.toString());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
	   
	}

}
