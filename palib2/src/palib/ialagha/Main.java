package palib.ialagha;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import palib.ialagha.apiresponse.login.ResearchCollection;

public class Main {


	public static void main(String args[]) {
	 DSpaceDBUtility db = new DSpaceDBUtility();
		try {
			db.connect(Configs.DSpace.url, Configs.DSpace.username, Configs.DSpace.password);
			/*
			List<Item> items = db.queryItems(null, Settings.getFacultyId(Faculty.IT), null, null);
			for(Item item: items) {
				System.out.println("title: "+item.getTitle());
				System.out.println("Creator: " +item.getCreator());
				System.out.println("year: "+item.getDate_year());
				System.out.println("Abstract: "+item.getDescription());
				System.out.println("Format: "+item.getFormat());
				System.out.println("ID: "+item.getIdentifier());
				System.out.println("Language: "+item.getLanguage());
				System.out.println("License: "+item.getRights());
				System.out.println("Contributors: "+item.getContributors().toString());
				System.out.println("Type: "+item.getType());
				System.out.println("Subject: "+item.getSubject().toString());
				System.out.println("Publisher: "+item.getPublisher());
				System.out.println("---------------------------");
			}
			*/
			int count =0;
			PalibAPI api = new PalibAPI();
			String accessToken = api.login(Configs.PortalAPI.username, Configs.PortalAPI.password);
			if(accessToken != null) {
				for(String facultyCollectionId: FacultyMapping.collectionIDToUUIDMap.values()) {
					List<String> jsonChunks = db.queryItemsAsJsonChuncks(Configs.PortalAPI.deliveredChuckSize, facultyCollectionId, null, null);
					System.out.println(facultyCollectionId+" : "+ (jsonChunks.size()* Configs.PortalAPI.deliveredChuckSize));
					for(String json: jsonChunks) {
							String response = api.sendMetadata(json, accessToken);
							System.out.println(response);							
							if(response.contains("Too Many Attempts")) {
								do {
									Thread.sleep(10000);
									response = api.sendMetadata(json, accessToken);
								}while(response.contains("Too Many Attempts"));								
							}else {
								count++;
								Thread.sleep(5);
								System.out.println("sent count: "+count);
							}
							System.out.println("---------------------");
						}
					}
				}
			
		
			/*
			List<String> jsonChunks = db.queryItemsAsJsonChuncks(Configs.PortalAPI.deliveredChuckSize, FacultyMapping.getFacultyId(FacultyMapping.Faculty.Engineering), null, null);			
			PalibAPI api = new PalibAPI();
			String accessToken = api.login(Configs.PortalAPI.username, Configs.PortalAPI.password);
			if(accessToken != null) {
				for(String json: jsonChunks) {
					//System.out.println(json);
					String response = api.sendMetadata(json, accessToken);
					System.out.println(response);
					System.out.println("---------------------");
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			*/
			/*
			PalibAPI api = new PalibAPI();
			String accessToken = api.login(Configs.PortalAPI.username, Configs.PortalAPI.password);
			if(accessToken != null) {
				ResearchCollection[] r = api.getResearchCollections(accessToken);
				for(ResearchCollection rc: r) {
					System.out.println(rc.Name);
				}
			}
			*/

			//db.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	}
}
