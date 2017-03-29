package com.mtyoung.com.omdbapi;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Title{

	@JsonProperty("Metascore")
	private String metascore;

	@JsonProperty("BoxOffice")
	private String boxOffice;

	@JsonProperty("Website")
	private String website;

	@JsonProperty("imdbRating")
	private String imdbRating;

	@JsonProperty("imdbVotes")
	private String imdbVotes;

	@JsonProperty("Ratings")
	private List<RatingsItem> ratings;

	@JsonProperty("Runtime")
	private String runtime;

	@JsonProperty("Language")
	private String language;

	@JsonProperty("Rated")
	private String rated;

	@JsonProperty("Production")
	private String production;

	@JsonProperty("Released")
	private String released;

	@JsonProperty("imdbID")
	private String imdbID;

	@JsonProperty("Plot")
	private String plot;

	@JsonProperty("Director")
	private String director;

	@JsonProperty("Title")
	private String title;

	@JsonProperty("Actors")
	private String actors;

	@JsonProperty("Response")
	private String response;

	@JsonProperty("Type")
	private String type;

	@JsonProperty("Awards")
	private String awards;

	@JsonProperty("DVD")
	private String dVD;

	@JsonProperty("Year")
	private String year;

	@JsonProperty("Poster")
	private String poster;

	@JsonProperty("Country")
	private String country;

	@JsonProperty("Genre")
	private String genre;

	@JsonProperty("Writer")
	private String writer;

	public void setMetascore(String metascore){
		this.metascore = metascore;
	}

	public String getMetascore(){
		return metascore;
	}

	public void setBoxOffice(String boxOffice){
		this.boxOffice = boxOffice;
	}

	public String getBoxOffice(){
		return boxOffice;
	}

	public void setWebsite(String website){
		this.website = website;
	}

	public String getWebsite(){
		return website;
	}

	public void setImdbRating(String imdbRating){
		this.imdbRating = imdbRating;
	}

	public String getImdbRating(){
		return imdbRating;
	}

	public void setImdbVotes(String imdbVotes){
		this.imdbVotes = imdbVotes;
	}

	public String getImdbVotes(){
		return imdbVotes;
	}

	public void setRatings(List<RatingsItem> ratings){
		this.ratings = ratings;
	}

	public List<RatingsItem> getRatings(){
		return ratings;
	}

	public void setRuntime(String runtime){
		this.runtime = runtime;
	}

	public String getRuntime(){
		return runtime;
	}

	public void setLanguage(String language){
		this.language = language;
	}

	public String getLanguage(){
		return language;
	}

	public void setRated(String rated){
		this.rated = rated;
	}

	public String getRated(){
		return rated;
	}

	public void setProduction(String production){
		this.production = production;
	}

	public String getProduction(){
		return production;
	}

	public void setReleased(String released){
		this.released = released;
	}

	public String getReleased(){
		return released;
	}

	public void setImdbID(String imdbID){
		this.imdbID = imdbID;
	}

	public String getImdbID(){
		return imdbID;
	}

	public void setPlot(String plot){
		this.plot = plot;
	}

	public String getPlot(){
		return plot;
	}

	public void setDirector(String director){
		this.director = director;
	}

	public String getDirector(){
		return director;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setActors(String actors){
		this.actors = actors;
	}

	public String getActors(){
		return actors;
	}

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setAwards(String awards){
		this.awards = awards;
	}

	public String getAwards(){
		return awards;
	}

	public void setDVD(String dVD){
		this.dVD = dVD;
	}

	public String getDVD(){
		return dVD;
	}

	public void setYear(String year){
		this.year = year;
	}

	public String getYear(){
		return year;
	}

	public void setPoster(String poster){
		this.poster = poster;
	}

	public String getPoster(){
		return poster;
	}

	public void setCountry(String country){
		this.country = country;
	}

	public String getCountry(){
		return country;
	}

	public void setGenre(String genre){
		this.genre = genre;
	}

	public String getGenre(){
		return genre;
	}

	public void setWriter(String writer){
		this.writer = writer;
	}

	public String getWriter(){
		return writer;
	}

	@Override
 	public String toString(){
		return 
			"Title{" + 
			"metascore = '" + metascore + '\'' + 
			",boxOffice = '" + boxOffice + '\'' + 
			",website = '" + website + '\'' + 
			",imdbRating = '" + imdbRating + '\'' + 
			",imdbVotes = '" + imdbVotes + '\'' + 
			",ratings = '" + ratings + '\'' + 
			",runtime = '" + runtime + '\'' + 
			",language = '" + language + '\'' + 
			",rated = '" + rated + '\'' + 
			",production = '" + production + '\'' + 
			",released = '" + released + '\'' + 
			",imdbID = '" + imdbID + '\'' + 
			",plot = '" + plot + '\'' + 
			",director = '" + director + '\'' + 
			",title = '" + title + '\'' + 
			",actors = '" + actors + '\'' + 
			",response = '" + response + '\'' + 
			",type = '" + type + '\'' + 
			",awards = '" + awards + '\'' + 
			",dVD = '" + dVD + '\'' + 
			",year = '" + year + '\'' + 
			",poster = '" + poster + '\'' + 
			",country = '" + country + '\'' + 
			",genre = '" + genre + '\'' + 
			",writer = '" + writer + '\'' + 
			"}";
		}
}