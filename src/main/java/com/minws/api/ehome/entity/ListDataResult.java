/**
 * 
 */
/**
 * @author Administrator
 *
 */
package com.minws.api.ehome.entity;

import java.util.List;

import com.minws.api.framework.exception.RestResult;

public class ListDataResult extends RestResult {

	private List<Data> data;

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	public ListDataResult() {

	}

	public ListDataResult(List<Data> data) {
		this.data = data;
	}

}