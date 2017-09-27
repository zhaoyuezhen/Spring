package com.sts.spring.web.vo;

import java.util.List;

import com.sts.spring.dto.DataTableDto;

public class DataTableResVo {
	private int draw;
	private int recordsTotal;
	private int recordsFiltered;
	private List<DataTableDto> data;
	private String error;
	
	public int getDraw() {
		return draw;
	}
	public void setDraw(int draw) {
		this.draw = draw;
	}
	public int getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public int getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public List<DataTableDto> getData() {
		return data;
	}
	public void setData(List<DataTableDto> data) {
		this.data = data;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	@Override
	public String toString() {
		return "DataTableResVo [draw=" + draw + ", recordsTotal=" + recordsTotal + ", recordsFiltered="
				+ recordsFiltered + ", data=" + data + ", error=" + error + "]";
	}
	
	
}
