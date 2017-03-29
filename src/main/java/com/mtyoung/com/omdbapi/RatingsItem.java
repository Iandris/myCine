package com.mtyoung.com.omdbapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.annotation.Generated;

@Generated("com.robohorse.robopojogenerator")
public class RatingsItem{

	@JsonProperty("Value")
	private String value;

	@JsonProperty("Source")
	private String source;

	public void setValue(String value){
		this.value = value;
	}

	public String getValue(){
		return value;
	}

	public void setSource(String source){
		this.source = source;
	}

	public String getSource(){
		return source;
	}

	@Override
 	public String toString(){
		return 
			"RatingsItem{" + 
			"value = '" + value + '\'' + 
			",source = '" + source + '\'' + 
			"}";
		}
}