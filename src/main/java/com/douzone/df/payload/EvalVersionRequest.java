package com.douzone.df.payload;

import java.util.ArrayList;

import com.douzone.df.model.EvalItem;



public class EvalVersionRequest {
	private String version;
	private ArrayList<EvalItem> dataSource;
	
	public EvalVersionRequest() {
		
	}
		
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	public ArrayList<EvalItem> getDataSource() {
		return dataSource;
	}

	public void setDataSource(ArrayList<EvalItem> dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public String toString() {
		return "EvalVersionRequest [version=" + version + ", dataSource=" + dataSource + "]";
	}

}
