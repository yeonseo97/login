package com.demo.service.point;

import java.util.List;

import org.springframework.stereotype.Service;

import com.demo.dao.point.ICoordDao;
import com.demo.dto.point.CoordDto;
import com.demo.dto.point.GpsDto;
import com.demo.dto.point.NoiseDto;
import com.demo.dto.point.RpmDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RequiredArgsConstructor
@Service
public class CoordService {
	
	private final ICoordDao coordDao;
	
	public void insertCoord() {
		List<GpsDto> car114FromGpsList = coordDao.find114carByGpsTable();
		List<NoiseDto> car114FromNoiseList = coordDao.find114carByNoiseTable();
		List<RpmDto> car114FromRpmList = coordDao.find114carByRpmTable();
		List<GpsDto> car103FromGpsList = coordDao.find103carByGpsTable();
		List<NoiseDto> car103FromNoiseList = coordDao.find103carByNoiseTable();
		List<RpmDto> car103FromRpmList = coordDao.find103carByRpmTable();
		// 114 차량		
		for(int i = 0; i < car114FromGpsList.size(); i++) {
			CoordDto coordDto = new CoordDto();

			coordDto.setDate(car114FromGpsList.get(i).getDate());
			coordDto.setTime(car114FromGpsList.get(i).getTime());
			coordDto.setCar_num(car114FromGpsList.get(i).getCar_num());
			coordDto.setLon(car114FromGpsList.get(i).getLon());
			coordDto.setLat(car114FromGpsList.get(i).getLat());
			coordDto.setNoise(car114FromNoiseList.get(i).getNoise());
			coordDto.setRpm(car114FromRpmList.get(i).getRpm());

			boolean is_done = false;
			
			if(car114FromNoiseList.get(i).getNoise() >= 80 && 
			   car114FromRpmList.get(i).getRpm() >= 1500) {
				is_done = true;
			}
			coordDto.set_done(is_done);
			log.info("coordDto : {}",coordDto.toString());
			// CoordDto를 coord 테이블에 저장
            coordDao.insertCoord(coordDto);
		}
		// 103 차량
		for(int i = 0; i < car103FromGpsList.size(); i++) {
			CoordDto coordDto = new CoordDto();

			coordDto.setDate(car103FromGpsList.get(i).getDate());
			coordDto.setTime(car103FromGpsList.get(i).getTime());
			coordDto.setCar_num(car103FromGpsList.get(i).getCar_num());
			coordDto.setLon(car103FromGpsList.get(i).getLon());
			coordDto.setLat(car103FromGpsList.get(i).getLat());
			coordDto.setNoise(car103FromNoiseList.get(i).getNoise());
			coordDto.setRpm(car103FromRpmList.get(i).getRpm());
			boolean is_done = false;
			
			if(car103FromNoiseList.get(i).getNoise() >= 80 && 
			   car103FromRpmList.get(i).getRpm() >= 1500) {
				is_done = true;
			}
			coordDto.set_done(is_done);
			log.info("coordDto : {}",coordDto.toString());
			// CoordDto를 coord 테이블에 저장
            coordDao.insertCoord(coordDto);
		}
    }
    
}
