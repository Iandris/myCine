package com.mtyoung.com.omdbapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class Title{

	@JsonProperty("Released")
	private String released;

	@JsonProperty("Metascore")
	private String metascore;

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

	@JsonProperty("imdbRating")
	private String imdbRating;

	@JsonProperty("imdbVotes")
	private String imdbVotes;

	@JsonProperty("Response")
	private String response;

	@JsonProperty("Runtime")
	private String runtime;

	@JsonProperty("Type")
	private String type;

	@JsonProperty("Awards")
	private String awards;

	@JsonProperty("Year")
	private String year;

	@JsonProperty("Language")
	private String language;

	@JsonProperty("Rated")
	private String rated;

	@JsonProperty("Poster")
	private String poster;

	@JsonProperty("Country")
	private String country;

	@JsonProperty("Genre")
	private String genre;

	@JsonProperty("Writer")
	private String writer;

	public void setReleased(String released){
		this.released = released;
	}

	public String getReleased(){
		return released;
	}

	public void setMetascore(String metascore){
		this.metascore = metascore;
	}

	public String getMetascore(){
		return metascore;
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

	public void setResponse(String response){
		this.response = response;
	}

	public String getResponse(){
		return response;
	}

	public void setRuntime(String runtime){
		this.runtime = runtime;
	}

	public String getRuntime(){
		return runtime;
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

	public void setYear(String year){
		this.year = year;
	}

	public String getYear(){
		return year;
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
			"released = '" + released + '\'' + 
			",metascore = '" + metascore + '\'' + 
			",imdbID = '" + imdbID + '\'' + 
			",plot = '" + plot + '\'' + 
			",director = '" + director + '\'' + 
			",title = '" + title + '\'' + 
			",actors = '" + actors + '\'' + 
			",imdbRating = '" + imdbRating + '\'' + 
			",imdbVotes = '" + imdbVotes + '\'' + 
			",response = '" + response + '\'' + 
			",runtime = '" + runtime + '\'' + 
			",type = '" + type + '\'' + 
			",awards = '" + awards + '\'' + 
			",year = '" + year + '\'' + 
			",language = '" + language + '\'' + 
			",rated = '" + rated + '\'' + 
			",poster = '" + poster + '\'' + 
			",country = '" + country + '\'' + 
			",genre = '" + genre + '\'' + 
			",writer = '" + writer + '\'' + 
			"}";
		}
}