package org.gzhmc.report4gzhmc.service;


import org.gzhmc.common.base.BaseService;
import org.gzhmc.report4gzhmc.model.User;

/**
 * @author stshen
 *
 * 2017年11月8日
 */
public interface UserService extends BaseService<User>{
	
	public User getByUserName(String username);

	public int getMaxcId();
}
