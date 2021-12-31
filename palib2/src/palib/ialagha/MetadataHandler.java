package palib.ialagha;

public class MetadataHandler {
	public void handleMetadata(String meta, String value, Item item) {
		if(meta.equals("title") && value != null && !value.isEmpty()) {
			item.setTitle(value);
		}else if(meta.equals("description:abstract")) {
			item.setDescription(value);
		}else if(meta.equals("date:issued")) {
			if(value != null  && value.length()>=4) {
				try {
					value = value.substring(0, 4);
					int year = Integer.parseInt(value);
					item.setDate_year(String.valueOf(year));
				}catch(NumberFormatException e) {
					item.setDate_year("2000");
				}
			}
		}else if(meta.equals("contributor:author")) {
			item.getCreator().add(value);
		}else if(meta.equals("contributor:advisor")) {
			item.getSupervisors().add(value);
		}else if(meta.equals("type")) {
			item.setType(String.valueOf(0));
			if(value != null && !value.isEmpty()) {
				for(String key: Configs.researchCollectionMappings.keySet()) {
					if(key.equalsIgnoreCase(value.trim()) || key.toLowerCase().contains(value.toLowerCase().trim())) {
						int typeNo = Configs.researchCollectionMappings.get(key);
						item.setType(String.valueOf(typeNo));
						break;
					}
				}
			}
		}else if(meta.equals("relation:ispartof")) {
			item.setIsPartof(value);
		}else if(meta.equals("relation:ispartofseries")) {
			item.setIsPartofSeries(value);
		}else if(meta.equals("publisher")) {
			item.getPublisher().add(value);
		}else if(meta.equals("rights:license")) {
			item.setRights(value);
		}else if(meta.equals("subject")) {
			item.getSubject().add(value);
			item.getKeywords().add(value);
		}else if((meta.equals("language") || meta.equals("language:iso")) && value != null && !value.isEmpty()) {
			item.setLanguage(value);
		}else if(meta.equals("dc.identifier.doi")) {
			item.setDoi(value);
		}else if(meta.equals("dc.identifier.uri")) {
			item.setSource(value);
		}

		if(item.getDescription() == null || item.getDescription().isEmpty()) {
			item.setDescription("No abstract available");
		}
		if(item.getLanguage() == null || item.getLanguage().isEmpty()) {
			item.setLanguage("en");
		}
		if(item.getSubject().isEmpty()) {
			item.getSubject().add("Not available");
		}
		if(item.getPublisher().isEmpty()) {
			item.getPublisher().add("Not available");
		}
	}
}
