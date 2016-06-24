package com.hayavadana.rating.webservice.rest.json;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.client.ClientProtocolException;
import org.codehaus.jettison.json.JSONObject;
import org.json.JSONException;
import org.json.JSONML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hayavadana.rating.bean.NfcBean;
import com.hayavadana.rating.service.BusinessService;
import com.hayavadana.rating.service.NfcService;

@Component
@Path("/nfcService")
public class NfcRest {
	@Autowired
	private NfcService nfcService;
	
	
	@POST
	@Path("/send")
	@Consumes(MediaType.APPLICATION_JSON)
	public JSONObject saveNike(NfcBean nfcBean) throws ClientProtocolException, IOException, JSONException, org.codehaus.jettison.json.JSONException{

		String number=null;
		NfcBean bean = nfcService.saveNfc(nfcBean);
		JSONObject guiId = new JSONObject();
		guiId.accumulate("guiId", bean.getGuiId());
		System.out.println("rest response:"+guiId);
		System.out.println("rest response:"+nfcBean.getProDesc());
		String tag = "";
		System.out.println("tag arr: "+nfcBean.getTagId());

		System.out.println("tag: "+tag);
		return   guiId;
	}
	
	@POST
	@Path("/getDetails")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public NfcBean getDetails(JSONObject nfcObj) throws ClientProtocolException, IOException, JSONException, org.codehaus.jettison.json.JSONException{
	 
		String guiId = nfcObj.optString("guiId");
		String tagId = nfcObj.optString("tagId");
		System.out.println("Input data from Client : guiId: "+guiId+"----- tagId"+tagId);
		NfcBean bean = nfcService.getNfc(guiId,tagId);
		
		return   bean;
	}
	
	@POST
	@Path("/writeStatus")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public JSONObject saveStatus(JSONObject nfcObj) throws ClientProtocolException, IOException, JSONException, org.codehaus.jettison.json.JSONException{
	 
		String guiId = nfcObj.optString("guiId");
		String status = nfcObj.optString("stastus");
		System.out.println("Input data from Client : guiId: "+guiId);
		String  message = nfcService.saveStstus(guiId,status);
		JSONObject result = new JSONObject();
		result.accumulate("result", message);
		
		return   result;
	}
}
