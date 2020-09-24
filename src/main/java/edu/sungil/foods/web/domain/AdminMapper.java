package edu.sungil.foods.web.domain;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import edu.sungil.foods.web.domain.dto.MenuInfo;
import edu.sungil.foods.web.domain.dto.OrderInfo;
import edu.sungil.foods.web.domain.dto.UserInfo;

@Mapper
public interface AdminMapper {

	void insertMenuInfo(MenuInfo menuInfo);

	List<MenuInfo> selectMenuList(MenuInfo menuInfo);

	void updateMenuInfo(MenuInfo menuInfo);

	List<OrderInfo> selectOrderList(OrderInfo orderInfo);

	void UpdateOrdStat(OrderInfo orderInfo);
	
}

