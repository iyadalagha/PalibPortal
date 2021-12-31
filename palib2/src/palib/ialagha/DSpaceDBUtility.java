package palib.ialagha;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class DSpaceDBUtility{

	Connection con;
	Statement st;
	

	String englishTitleQuery = "SELECT metadatavalue.text_value, handle.handle FROM metadatavalue, handle\r\n" + 
			"WHERE metadatavalue.metadata_field_id = (\r\n" + 
			"  SELECT metadata_field_id\r\n" + 
			"  FROM metadatafieldregistry mfr, metadataschemaregistry msr\r\n" + 
			"  WHERE mfr.metadata_schema_id = msr.metadata_schema_id\r\n" + 
			"  AND short_id = 'dc'\r\n" + 
			"  AND element = 'title'\r\n" + 
			"  AND qualifier IS NULL    \r\n" + 
			") AND metadatavalue.dspace_object_id IN (\r\n" + 
			"	SELECT uuid from item\r\n" + 
			") AND metadatavalue.dspace_object_id = handle.resource_id;\r\n" + 
			"";

	String arabicTitleQuery = "SELECT metadatavalue.text_value, handle.handle FROM metadatavalue, handle\r\n" + 
			"WHERE metadatavalue.metadata_field_id = (\r\n" + 
			"  SELECT metadata_field_id\r\n" + 
			"  FROM metadatafieldregistry mfr, metadataschemaregistry msr\r\n" + 
			"  WHERE mfr.metadata_schema_id = msr.metadata_schema_id\r\n" + 
			"  AND short_id = 'dc'\r\n" + 
			"  AND element = 'title'\r\n" + 
			"  AND qualifier = 'arabic'    \r\n" + 
			") AND metadatavalue.dspace_object_id IN (\r\n" + 
			"	SELECT uuid from item\r\n" + 
			") AND metadatavalue.dspace_object_id = handle.resource_id;\r\n" + 
			"\r\n" + 
			"";
	
	String authorQuery = "SELECT text_value FROM metadatavalue\r\n" + 
			"WHERE metadata_field_id = (\r\n" + 
			"  SELECT metadata_field_id\r\n" + 
			"  FROM metadatafieldregistry mfr, metadataschemaregistry msr\r\n" + 
			"  WHERE mfr.metadata_schema_id = msr.metadata_schema_id\r\n" + 
			"  AND short_id = 'dc'\r\n" + 
			"  AND element = 'contributor'\r\n" + 
			"  AND qualifier ='author'    \r\n" + 
			") AND dspace_object_id IN (\r\n" + 
			"	SELECT uuid from item\r\n" + 
			");";
	
	String supervisorQuery ="SELECT text_value FROM metadatavalue\r\n" + 
			"WHERE metadata_field_id = (\r\n" + 
			"  SELECT metadata_field_id\r\n" + 
			"  FROM metadatafieldregistry mfr, metadataschemaregistry msr\r\n" + 
			"  WHERE mfr.metadata_schema_id = msr.metadata_schema_id\r\n" + 
			"  AND short_id = 'dc'\r\n" + 
			"  AND element = 'contributor'\r\n" + 
			"  AND qualifier ='advisor'    \r\n" + 
			") AND dspace_object_id IN (\r\n" + 
			"	SELECT uuid from item\r\n" + 
			");";
	
	PreparedStatement pst;
	String nameQuery = "SELECT text_value FROM metadatavalue \n " + 
			"WHERE metadata_field_id = 8 and authority ilike ? limit 1 ;" ;
	
	
	public DSpaceDBUtility() {}
	
	
	public boolean connect(String url, String username, String password) throws SQLException {
		try 
		{
			Class.forName("org.postgresql.Driver");
		} 
		catch(java.lang.ClassNotFoundException e) 
		{
			System.err.print("ClassNotFoundException: ");
			System.err.println(e.getMessage());
		}

		con = DriverManager.getConnection(url,username, password);
		st = con.createStatement();
		pst = con.prepareStatement(nameQuery);
		return con != null;
	}
	
	public void disconnect() throws SQLException {
		if(st != null && !st.isClosed()) {
			st.close();
		}
		if(pst != null && !pst.isClosed()) {
			pst.close();
		}
		if(con != null && !con.isClosed()) {
			con.close();
		}
	}
	
	public List<Item> queryItems(String authorId, String collectionId, String fromYear, String toYear) throws SQLException {

		String query = "SELECT metadatavalue.text_value, metadatafieldregistry.element, metadatafieldregistry.qualifier, handle.handle FROM metadatavalue, metadatafieldregistry, handle\r\n" + 
				"WHERE handle.resource_id = metadatavalue.dspace_object_id AND handle.resource_type_id=2 AND metadatavalue.metadata_field_id = metadatafieldregistry.metadata_field_id "; 
		if(authorId != null && !authorId.trim().equals("")) {
			query+="AND metadatavalue.dspace_object_id IN (SELECT dspace_object_id from metadatavalue \r\n" + 
			"                         WHERE metadata_field_id=9 \r\n" + 
			"                         and authority='"+authorId+"') ";
		}
		if(collectionId != null && !collectionId.trim().equals("")) {
			query+="AND metadatavalue.dspace_object_id IN (SELECT item_id from collection2item where collection_id='"+collectionId+"') "; 
					
		}
		String yearClause1 = null, yearClause2= null;
		if(fromYear != null && !fromYear.trim().equals("")) {
			yearClause1=" text_value IS NOT null and text_value <> '' and CAST(substring(text_value,0,5) AS integer)>="+Integer.parseInt(fromYear)+" ";
		}
		if(toYear != null && !toYear.trim().equals("")) {
			yearClause2=" text_value IS NOT null and text_value <> '' and CAST(substring(text_value,0,5) AS integer) <="+Integer.parseInt(toYear)+" ";
		}	
		if(yearClause1 != null || yearClause2 != null) {
			query+=	" AND metadatavalue.dspace_object_id IN (SELECT dspace_object_id from metadatavalue WHERE metadata_field_id=21 "+(yearClause1==null?"":" and "+yearClause1)+" "+(yearClause2==null?"":" and "+yearClause2)+")";
		}
		MetadataHandler metadataHandler = new MetadataHandler();
		HashMap<String, Item> tmp = new HashMap<>();
		ResultSet rs = st.executeQuery(query);
		while(rs.next()){
			String element = rs.getString(2);
			String qualifier = rs.getString(3);
			String meta = element+(qualifier!=null?(":"+qualifier):"");
			String value = rs.getString(1);
			String handle = rs.getString(4);
			Item item = null;
			if(tmp.containsKey(handle)) {
				item = tmp.get(handle);
			}else {
				item = new Item();
				item.setIdentifier(handle);
				String libId = handle.substring(handle.indexOf("\\\\")+1);
				item.setFormat("text");
				libId = handle.substring(handle.indexOf("/")+1);

				item.setLib_id(libId);
				tmp.put(handle, item);
			}
			metadataHandler.handleMetadata(meta, value, item);
		}
		List<Item> orderedItems = new ArrayList<>(tmp.values());
		rs.close();
		return orderedItems;
	}
	
	public String queryItemsAsJson(String authorId, String collectionId, String fromYear, String toYear) throws SQLException {
		List<Item> items = queryItems(authorId, collectionId, fromYear, toYear);
		RepoItems repoItems = new RepoItems(items);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(repoItems);
		return json;		
	}
	
	public List<String> queryItemsAsJsonChuncks(int chunkSize, String collectionId, String fromYear, String toYear) throws SQLException {
		List<String> jsonChuncks = new ArrayList<>();
		List<Item> items = queryItems(null, collectionId, fromYear, toYear);
		List<List<Item>> itemsChuck = Lists.partition(items, chunkSize);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		for(List<Item> c: itemsChuck) {
			RepoItems repoItems = new RepoItems(c);
			String json = gson.toJson(repoItems);
			jsonChuncks.add(json);
		}

		return jsonChuncks;		
	}
}