package org.zerock.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.Store;

@RestController
public class SampleListController {

	@GetMapping("/api/list")
	public List<Store> getList(){
		List<Store> storeList = new ArrayList<>();
		storeList.add(Store.builder()
				.name("강남포에버의원")
				.lat(37.502294)
				.lng(127.025905)
				.build());
		storeList.add(Store.builder()
				.name("고도일병원")
				.lat(37.506963)
				.lng(127.020496)
				.build());
		storeList.add(Store.builder()
				.name("미소병원")
				.lat(37.50823)
				.lng(127.03239)
				.build());
		storeList.add(Store.builder()
				.name("서세웅내과의원")
				.lat(37.516125)
				.lng(127.018861)
				.build());
		storeList.add(Store.builder()
				.name("강남 차병원")
				.lat(37.514796)
				.lng(127.034182)
				.build());
		return storeList;
	}
	
}
