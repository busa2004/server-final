package com.douzone.df.payload;

import java.util.List;

public class Hold {
	private List<Rate> rate;
	private List<Description> description;
	private List<GraphResponse> graph;
	public List<Rate> getRate() {
		return rate;
	}
	public void setRate(List<Rate> rate) {
		this.rate = rate;
	}
	public List<Description> getDescription() {
		return description;
	}
	public void setDescription(List<Description> description) {
		this.description = description;
	}
	public List<GraphResponse> getGraph() {
		return graph;
	}
	public void setGraph(List<GraphResponse> graph) {
		this.graph = graph;
	}
	public Hold(List<Rate> rate, List<Description> description, List<GraphResponse> graph) {
		super();
		this.rate = rate;
		this.description = description;
		this.graph = graph;
	}
	
	
	
}
