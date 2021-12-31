package palib.ialagha;

import java.util.ArrayList;
import java.util.List;

import palib.ialagha.annotations.Abstract;
import palib.ialagha.annotations.Title;

public class Item {
	public String lib_id;
	public String title;
	public String description;
	public String rights;
	public String type;
	public String date;
	public String date_year;
	public String format;
	public String identifier;
	public String source;
	public String language;
	public String relation;
	public String isPartof;
	public String isPartofSeries;
	public String doi;
	
	public String getIsPartof() {
		return isPartof;
	}
	public void setIsPartof(String isPartof) {
		this.isPartof = isPartof;
	}
	public String getIsPartofSeries() {
		return isPartofSeries;
	}
	public void setIsPartofSeries(String isPartofSeries) {
		this.isPartofSeries = isPartofSeries;
	}
	public String getDoi() {
		return doi;
	}
	public void setDoi(String doi) {
		this.doi = doi;
	}
	public List<String> getKeywords() {
		return keywords;
	}
	public void setKeywords(List<String> keywords) {
		this.keywords = keywords;
	}
	List<String> publisher = new ArrayList<>(), subject = new ArrayList<>(), contributors = new ArrayList<>(), creator = new ArrayList<>(), supervisors = new ArrayList<>(), keywords = new ArrayList<>();
	
	
	public String getLib_id() {
		return lib_id;
	}
	public void setLib_id(String lib_id) {
		this.lib_id = lib_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getCreator() {
		return creator;
	}
	public void setCreator(List<String> creator) {
		this.creator = creator;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDate_year() {
		return date_year;
	}
	public void setDate_year(String date_year) {
		this.date_year = date_year;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getRelation() {
		return relation;
	}
	public void setRelation(String relation) {
		this.relation = relation;
	}
	public List<String> getPublisher() {
		return publisher;
	}
	public void setPublisher(List<String> publisher) {
		this.publisher = publisher;
	}
	public List<String> getSubject() {
		return subject;
	}
	public void setSubject(List<String> subject) {
		this.subject = subject;
	}
	public List<String> getSupervisors() {
		return supervisors;
	}
	public void setSupervisors(List<String> supervisors) {
		this.supervisors = supervisors;
	}
	public List<String> getContributors() {
		return contributors;
	}
	public void setContributors(List<String> contributors) {
		this.contributors = contributors;
	}
	
	
}
