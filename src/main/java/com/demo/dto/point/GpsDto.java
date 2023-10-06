package com.demo.dto.point;

import lombok.Data;

@Data
public class GpsDto {
	
	private int id;
	private String car_num;
	private String date;
	private String time;
	private double lon;
	private double lat;
}
