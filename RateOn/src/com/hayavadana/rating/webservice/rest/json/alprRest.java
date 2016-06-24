package com.hayavadana.rating.webservice.rest.json;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.stereotype.Component;

import Decoder.BASE64Decoder;

@Component
@Path("/alprService")
public class alprRest {
	
	@POST
	@Path("/send")
	@Consumes(MediaType.TEXT_PLAIN)		//APPLICATION_JSON
	public Response saveUserRating(String img) throws ClientProtocolException, IOException, JSONException{
		System.out.println("Input data from Client : country: "+img);		//alpr.getCountry()
		String number=null;
		number=getNumber(img);
		
		return Response.status(200).entity(number).build(); 
	}
	
    public String getNumber(String img) throws ClientProtocolException, IOException, JSONException {
   /* 	
    	 String temp="{\"plate\":{\"data_type\":\"alpr_results\",\"epoch_time\":1460696092073,\"img_height\":720,\"img_width\":1280,\"results\":[{\"plate\":\"R001\",\"confidence\":88.453407,\"region_confidence\":45,\"region\":\"ny\",\"plate_index\":0,\"processing_time_ms\":14.519923,\"candidates\":[{\"matches_template\":0,\"plate\":\"R001\",\"confidence\":88.453407},{\"matches_template\":0,\"plate\":\"R0O1\",\"confidence\":75.558968},{\"matches_template\":0,\"plate\":\"RO01\",\"confidence\":74.817245},{\"matches_template\":0,\"plate\":\"R0Q1\",\"confidence\":74.521721},{\"matches_template\":0,\"plate\":\"RQ01\",\"confidence\":74.170258},{\"matches_template\":0,\"plate\":\"RD01\",\"confidence\":72.953903},{\"matches_template\":0,\"plate\":\"RG01\",\"confidence\":68.679329},{\"matches_template\":0,\"plate\":\"RB01\",\"confidence\":67.338577},{\"matches_template\":0,\"plate\":\"R0D1\",\"confidence\":65.568489},{\"matches_template\":0,\"plate\":\"R0U1\",\"confidence\":65.121002}],\"coordinates\":[{\"y\":384,\"x\":169},{\"y\":381,\"x\":241},{\"y\":417,\"x\":242},{\"y\":420,\"x\":171}],\"matches_template\":0,\"requested_topn\":10},{\"plate\":\"RH001\",\"confidence\":91.364983,\"region_confidence\":87,\"region\":\"co\",\"plate_index\":1,\"processing_time_ms\":15.268581,\"candidates\":[{\"matches_template\":0,\"plate\":\"RH001\",\"confidence\":91.364983},{\"matches_template\":0,\"plate\":\"RH00\",\"confidence\":84.424034},{\"matches_template\":0,\"plate\":\"RH0O1\",\"confidence\":80.729492},{\"matches_template\":0,\"plate\":\"RHO01\",\"confidence\":80.451004},{\"matches_template\":0,\"plate\":\"RHQ01\",\"confidence\":79.792625},{\"matches_template\":0,\"plate\":\"RH0Q1\",\"confidence\":79.752029},{\"matches_template\":0,\"plate\":\"RHD01\",\"confidence\":78.849075},{\"matches_template\":0,\"plate\":\"RH0D1\",\"confidence\":78.451851},{\"matches_template\":0,\"plate\":\"RHU01\",\"confidence\":75.107452},{\"matches_template\":0,\"plate\":\"RH0G1\",\"confidence\":74.138214}],\"coordinates\":[{\"y\":396,\"x\":579},{\"y\":395,\"x\":681},{\"y\":438,\"x\":681},{\"y\":441,\"x\":579}],\"matches_template\":0,\"requested_topn\":10}],\"version\":2,\"processing_time_ms\":229.135971,\"regions_of_interest\":[]},\"img_width\":1280,\"credits_monthly_used\":38,\"img_height\":720,\"total_processing_time\":236.69899999998734,\"credits_monthly_total\":1500,\"credit_cost\":1}";
    	 
    	 System.out.println("temp String sent is:---\n"+temp.toString());
    	    return temp;
    */	
    	System.out.println("\n\n============= "+new Date().toGMTString()+" ==============");
    	  
        
    	System.out.println("country name:"+img+"--");
 //   	String country=alpr.getCountry(); 
    	
    	//--------------------------------
    	BufferedImage image = null;
    	byte[] imageByte;

    	BASE64Decoder decoder = new BASE64Decoder();
    	imageByte = decoder.decodeBuffer(img);
    	ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
    	image = ImageIO.read(bis);
    	bis.close();

    	// write the image to a file
    	File file = new File("image.png");
    	ImageIO.write(image, "jpeg", file);
    	
	    DefaultHttpClient httpclient = new DefaultHttpClient();
	    httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);

	    HttpPost httppost = new HttpPost("https://api.openalpr.com/v1/recognize?secret_key=sk_acbbc75ff4f048c4edac8340&tasks=plate,make,color&country="+"us");
	  
	    //File file = new File("C:\\Users\\USER\\Desktop\\aaa.jpg");
	    
	    Object path;
	    
	    MultipartEntity mpEntity = new MultipartEntity();
	    ContentBody cbFile = new FileBody(file, "image/jpeg");
	    mpEntity.addPart("image", cbFile);

	    httppost.setEntity(mpEntity);
	    System.out.println("executing request " + httppost.getRequestLine());
	    
	    
	    System.out.println(httpPostToString(httppost).toString());
	    HttpResponse response = httpclient.execute(httppost);
	    HttpEntity resEntity = response.getEntity();
	    
	    String json = null;

	    System.out.println("sreeni:" +response.getStatusLine());
	    //-----------------------------------------------------------
//	    if (resEntity != null) {
//	    	System.out.println(EntityUtils.toString(resEntity));
//	    	json = EntityUtils.toString(resEntity);
//	    }
	    //------------------------------------------------------------
	    if (resEntity != null) {
	    	 BufferedReader r = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
	    	 	
			    StringBuilder total = new StringBuilder();

			    String line = null;

			    while ((line = r.readLine()) != null) {
			    
			       total.append(line);
			    }
			    r.close();
			    String respString  = total.toString();
			    System.out.println("respString is:"+respString);
			    json=respString;
			    System.out.println("QQQQQ "+respString);
	      JSONObject respJSON = new JSONObject(respString);
           System.out.println("HAHAHAHA "+(String)respJSON.optString("plate").toString());
           System.out.println();
           String plateString = (String)respJSON.optString("plate").toString();
		    JSONObject respJSONplate = new JSONObject(plateString);
           System.out.println("OOOOOOO "+(String)respJSONplate.optString("results").toString());
           String resultsStr = (String)respJSONplate.optString("results").toString();


           
           
           JSONArray jsonArrForResults = new JSONArray(resultsStr);
           
           for (int i=0;i<jsonArrForResults.length();i++){
	        System.out.println("\n\n**********************************");

           JSONObject jsonChildNode = jsonArrForResults.getJSONObject(i);
           String plateNumber = jsonChildNode.optString("plate").toString();
           System.out.println("Plate Number: "+plateNumber);
           
           String confidence = jsonChildNode.optString("confidence").toString();
           System.out.println("Confidence: "+confidence);
      //     json= plateNumber;
           String candidatesStr = (String)jsonChildNode.optString("candidates").toString();
           JSONArray jsonArrForCand = new JSONArray(candidatesStr);
           for (int j=0;j<jsonArrForCand.length();j++){
   	        System.out.println("*************CANDIDATE " + j + " *********************");

               JSONObject jsonChildNodeCand = jsonArrForCand.getJSONObject(j);
               String candPlateNumber = jsonChildNodeCand.optString("plate").toString();
	            System.out.println("Candidate Plate Number: "+candPlateNumber);
	            
	            String canConfidence = jsonChildNodeCand.optString("confidence").toString();
	            System.out.println("Candidate Confidence: "+canConfidence);
	            

           }

           }
           //========================================color==========================================//
           
           System.out.println("====color====== \n cccccc "+(String)respJSON.optString("color").toString());
           String colorString = (String)respJSON.optString("color").toString();
		  //  JSONObject respJSONcolor = new JSONObject(colorString);
		    JSONArray jsonArrForColor = new JSONArray(colorString);
		    for (int j=0;j<jsonArrForColor.length();j++){
		    	System.out.println("*************CANDIDATE " + j + " *********************");
		    	JSONObject jsonChildNodeCand = jsonArrForColor.getJSONObject(j);
	               String candColor = jsonChildNodeCand.optString("value").toString();
		            System.out.println("Candidate color: "+candColor);
		            
		            String canConfidence = jsonChildNodeCand.optString("confidence").toString();
		            System.out.println("Candidate Confidence: "+canConfidence);
		    }
		    
		    //========================================make============================================//
		    
		    System.out.println("======make====== \n cccccc "+(String)respJSON.optString("make").toString());
	           String makeString = (String)respJSON.optString("make").toString();
			  //  JSONObject respJSONcolor = new JSONObject(colorString);
			    JSONArray jsonArrForMake = new JSONArray(makeString);
			    for (int j=0;j<jsonArrForMake.length();j++){
			    	System.out.println("*************CANDIDATE " + j + " *********************");
			    	JSONObject jsonChildNodeCand = jsonArrForMake.getJSONObject(j);
		               String candMake = jsonChildNodeCand.optString("value").toString();
			            System.out.println("Candidate Make: "+candMake);
			            
			            String canConfidence = jsonChildNodeCand.optString("confidence").toString();
			            System.out.println("Candidate Confidence: "+canConfidence);
			    }
	    }
	    if (resEntity != null) {
	      resEntity.consumeContent();
	    }

	    httpclient.getConnectionManager().shutdown();
	  
   //     return new ModelAndView("hellopage", "message", json);  
	    return json;
}  
    
    
public  String httpPostToString(HttpPost httppost) {
	
   
		
	    StringBuilder sb = new StringBuilder();
	    String num=new String();
	    Double conf=0.00;
	    sb.append("\nRequestLine:");
	    sb.append(httppost.getRequestLine().toString());

	    int i = 0;
	    for(Header header : httppost.getAllHeaders()){
	      if(i == 0){
	          sb.append("\nHeader:");
	      }
	        i++;
	        for(HeaderElement element : header.getElements()){
	            for(NameValuePair nvp :element.getParameters()){
	                sb.append(nvp.getName());
	                sb.append("=");
	                sb.append(nvp.getValue());
	                sb.append(";");
	               /* if(Double.parseDouble(nvp.getValue())>conf){
	                	num=nvp.getName()
	                }*/
	                
	            }
	        }
	    }
	    HttpEntity entity = httppost.getEntity();

	    String content = "";
	    if(entity != null){
	        try {

	            //content = IOUtils.toString(entity.getContent());
	        	long contentlen = entity.getContentLength();
	        	System.out.println("sreeni 111:"+contentlen);
	        	
	        } catch (Exception e) {
	            e.printStackTrace();
	        } 
	    }
	    sb.append("\nContent:");
	    sb.append(content);

	  
	    return sb.toString();
	    
	   
	
	}
}
