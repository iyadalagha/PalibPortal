package palib.ialagha;

import java.util.HashMap;
import java.util.Map;

public class FacultyMapping {

	public enum Faculty{Medicine, IT, Engineering, Education, Science, Health_Sciences, Arts, Commerce, Osol, Sharia, Nursing}
	public static final Map<Faculty,String> facultyToCollectionMap = new HashMap<>();
	public static final Map<String,String> collectionIDToUUIDMap = new HashMap<>();

	static{
		facultyToCollectionMap.put(Faculty.Engineering,"5");
		facultyToCollectionMap.put(Faculty.Education,"13");
		facultyToCollectionMap.put(Faculty.IT,"20");
		facultyToCollectionMap.put(Faculty.Science,"30");
		facultyToCollectionMap.put(Faculty.Health_Sciences,"34");
		facultyToCollectionMap.put(Faculty.Arts,"9");
		facultyToCollectionMap.put(Faculty.Commerce,"38");
		facultyToCollectionMap.put(Faculty.Osol,"17");
		facultyToCollectionMap.put(Faculty.Sharia,"27");
		facultyToCollectionMap.put(Faculty.Nursing,"24");
		facultyToCollectionMap.put(Faculty.Medicine,"2");
		
	}
	
	static{
// publications
//		collectionIDToUUIDMap.put("5","798af700-d5fc-41d6-9996-effc4187ee7d");
//		collectionIDToUUIDMap.put("13","0d225617-63d0-4557-8a22-39f42f6375bd");
//		collectionIDToUUIDMap.put("20","44a5126c-6408-4a04-b06f-09af2c1b62f3");
//		collectionIDToUUIDMap.put("30","65dbf535-d4ed-4ff7-97d0-464e81a8c2b7");
//		collectionIDToUUIDMap.put("34","cd955650-8fe4-4377-89cb-7eb217e4abbf");
//		collectionIDToUUIDMap.put("9","cd555e78-e134-44ba-91ca-0c03820fb543");
//		collectionIDToUUIDMap.put("38","bb7c1ef6-b5af-4c11-980d-f872673d984a");
//		collectionIDToUUIDMap.put("17","a3ed8327-f743-4380-bb0f-659fa7d805da");
//		collectionIDToUUIDMap.put("27","de5064d3-c985-4b73-9302-80923f86bc9f");
//		collectionIDToUUIDMap.put("24","23e8b779-d15b-4fda-87a0-6b5281a0b7fd");
//		collectionIDToUUIDMap.put("2","d1dc8241-3828-45b9-9d19-99c19aa7251f");
		
// theses		
		
		//collectionIDToUUIDMap.put("6429","f8721c71-2528-4b51-8463-bca4ab783bff"); //commerce
		//collectionIDToUUIDMap.put("6431","e4d0d44e-9998-4607-a6fe-22b8ada76466"); // engineering
		//collectionIDToUUIDMap.put("6432","6c65ee87-8d05-414c-bb00-62c2452c1b1c"); // health sciences
		//collectionIDToUUIDMap.put("6433","15a2bff1-f217-4036-b1d1-3429855b5711"); // IT
		//collectionIDToUUIDMap.put("6434","13cda2c1-520d-4a8a-ab95-6da4b4c55ea3");  // medicine
		//collectionIDToUUIDMap.put("6435","01e6ce19-edca-4b8d-a494-0e3bd6e88574");  //osol
		//collectionIDToUUIDMap.put("6436","3bfc1904-8635-41d8-84ef-a3bffc421e1c");  //science
		//collectionIDToUUIDMap.put("14520","11695869-e290-4576-afb4-848f56d636e4"); //sharia
		//collectionIDToUUIDMap.put("6428","613f87fd-668b-46db-8ff2-6e75cf840e34"); // arts
		collectionIDToUUIDMap.put("6430","c39229cf-b35a-4edb-9486-4791034611eb"); // education

	}
	
	public static String getFacultyId(Faculty faculty) {
		return collectionIDToUUIDMap.get(facultyToCollectionMap.get(faculty));
	}

}
