package palib.ialagha;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Equivalence.Wrapper;
import com.google.gson.Gson;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import palib.ialagha.apiresponse.login.AuthResponse;
import palib.ialagha.apiresponse.login.ResearchCollection;

public class PalibAPI {
	
	OkHttpClient client;
	MediaType mediaType;
	
	public PalibAPI() {
		OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
		builder.connectTimeout(60, TimeUnit.SECONDS); 
		builder.readTimeout(60, TimeUnit.SECONDS); 
		builder.writeTimeout(60, TimeUnit.SECONDS); 
		client = builder.build();
		mediaType = MediaType.parse("text/plain");

	}
	
	public String login(String username, String password) throws IOException {
				RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
				  .addFormDataPart("user_name",username)
				  .addFormDataPart("password",password)
				  .build();
				Request request = new Request.Builder()
				  .url(Configs.PortalAPI.loginUrl)
				  .method("POST", body)
				  .build();
				Response response = client.newCall(request).execute();
				Gson gson = new Gson();
				AuthResponse r = gson.fromJson(response.body().string(), AuthResponse.class);
				if(r.getSuccess()) {
					System.out.println("login succcessss");
					return r.getData().getAccess_token();
				}else {
					return null;
				}
	}
	
	public ResearchCollection[] getResearchCollections(String accessToken) throws IOException{
		mediaType = MediaType.parse("text/plain");
				Request request = new Request.Builder()
				  .url(Configs.PortalAPI.fetchCollectionsUrl)
				  .method("GET", null)
				  .addHeader("Accept", "application/json")
				  .addHeader("Authorization", "Bearer "+accessToken)
				  .build();
				Response response = client.newCall(request).execute();
				Gson gson = new Gson();
				ResearchCollection[] r = gson.fromJson(response.body().string(), ResearchCollection[].class);
				return r;				
	}
	
	public String sendMetadata(String jsonItems, String accessToken) throws IOException {
				MediaType mediaType = MediaType.parse("application/json");
				RequestBody body = RequestBody.create(mediaType, jsonItems);
				Request request = new Request.Builder()
				  .url("http://palib1.ucas.edu.ps/api/psearch/store")
				  .method("POST", body)
				  .addHeader("Accept", "application/json")
				  .addHeader("Authorization", "Bearer "+accessToken)
				  .addHeader("Content-Type", "application/json")
				  .build();
				Response response = client.newCall(request).execute();
				return response.body().string();
	}
	

}
