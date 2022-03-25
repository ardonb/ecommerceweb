package com.ecommerce.setting;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.common.entity.Setting;
import com.ecommerce.common.entity.SettingCategory;

@Service
public class SettingService {
	@Autowired private SettingRepository repo;


	public List<Setting> getGeneralSettings() {
		return repo.findByTwoCategories(SettingCategory.GENERAL, SettingCategory.CURRENCY);
	}

}
