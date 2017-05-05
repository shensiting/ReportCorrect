package org.gzhmc.report4gzhmc.mapper;

import org.gzhmc.common.base.BaseMapper;
import org.gzhmc.report4gzhmc.model.User;

public interface UserMapper extends BaseMapper<User>{

	public User getByUserName(String username);
	public int getMaxcId();
}
 