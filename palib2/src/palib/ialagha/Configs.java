package palib.ialagha;

import java.util.HashMap;
import java.util.Map;

public class Configs {
	public static final Map<String, Integer> researchCollectionMappings = new HashMap<>(); 
	
	static {
		researchCollectionMappings.put("General", 0);
		researchCollectionMappings.put("Journal Article", 1);
		researchCollectionMappings.put("Conference Paper", 2);
		researchCollectionMappings.put("Book Chapter", 3);
		researchCollectionMappings.put("MSc Thesis", 4);
		researchCollectionMappings.put("PhD Thesis", 5);

	}
	public class PortalAPI{
		public static final String baseUrl = "http://palib1.ucas.edu.ps/api/";		
		public static final String username = "dr.eyadalagha";
		public static final String password = "mryusuf2021";		
		public static final String loginUrl = baseUrl+"auth/login";
		public static final String storeUrl = baseUrl+"psearch/store";
		public static final String fetchCollectionsUrl = baseUrl+"psearch/fetchcollections";
		public static final int deliveredChuckSize = 5;
	}


	public class DSpace{
		public static final String url = "jdbc:postgresql://localhost/dspace";
		public static final String username = "dspace";
		public static  final String password = "romor2018";			
	}
}
