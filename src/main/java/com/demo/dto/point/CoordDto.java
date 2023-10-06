package com.demo.dto.point;

import lombok.Data;

@Data
public class CoordDto {

	private int id;
	private String date;
	private String time;
	private String car_num;
	private double lon;
	private double lat;
	private int rpm;
	private int noise;
	private String geom;
	private boolean is_done;
}
