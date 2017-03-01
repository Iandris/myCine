package com.mtyoung.com.omdbapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class SearchItem{

	@JsonProperty("Type")
	private String type;

	@JsonProperty("Year")
	private String year;

	@JsonProperty("imdbID")
	private String imdbID;

	@JsonProperty("Poster")
	private String poster;

	@JsonProperty("Title")
	private String title;

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setYear(String year){
		this.year = year;
	}

	public String getYear(){
		return year;
	}

	public void setImdbID(String imdbID){
		this.imdbID = imdbID;
	}

	public String getImdbID(){
		return imdbID;
	}

	public void setPoster(String poster){
		this.poster = poster;
	}

	public String getPoster(){
		return poster;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	@Override
 	public String toString(){
		return 
			"SearchItem{" + 
			"type = '" + type + '\'' + 
			",year = '" + year + '\'' + 
			",imdbID = '" + imdbID + '\'' + 
			",poster = '" + poster + '\'' + 
			",title = '" + title + '\'' + 
			"}";
		}
}