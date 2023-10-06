package com.demo.dao.point;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.demo.dto.point.CoordDto;
import com.demo.dto.point.GpsDto;
import com.demo.dto.point.NoiseDto;
import com.demo.dto.point.RpmDto;

@Mapper
public interface ICoordDao {

	// 데이터베이스에 데이터를 넣는 메서드
	void insertCoord(@Param("coord") CoordDto coordDto);

	// 데이터베이스에서 데이터를 읽어오는 메서드
	List<GpsDto> find114carByGpsTable();

	List<NoiseDto> find114carByNoiseTable();

	List<RpmDto> find114carByRpmTable();

	List<GpsDto> find103carByGpsTable();

	List<NoiseDto> find103carByNoiseTable();

	List<RpmDto> find103carByRpmTable();
	
}
