package edu.sungil.foods.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.sungil.foods.web.domain.AdminMapper;
import edu.sungil.foods.web.domain.DefaultMapper;
import edu.sungil.foods.web.domain.dto.MenuInfo;
import edu.sungil.foods.web.domain.dto.OrderInfo;
import edu.sungil.foods.web.domain.dto.UserInfo;

@Service
@Transactional
public class DefaultService {
	 @Autowired
	 DefaultMapper defaultMapper;
	 @Autowired
	 AdminMapper adminMapper;
	 
	 public String getName() {
	        return defaultMapper.selectName();
	        }

	public UserInfo getUserInfo(Long userNo) {
		return defaultMapper.selectUserInfo(userNo);
	}
	
	public void addUserInfo(UserInfo userInfo) {
		 defaultMapper.addUserInfo(userInfo);
	}

	public void order(OrderInfo orderInfo) throws Exception {
		
		MenuInfo menuInfo = new MenuInfo();
		menuInfo.setMenuNo(orderInfo.getMenuNo());
		List<MenuInfo> menuInfoList = adminMapper.selectMenuList(menuInfo);
		
		orderInfo.setMenuNm(menuInfoList.get(0).getMenuNm());
		Long ordQty = orderInfo.getOrdQty();
		Long menuPc = menuInfoList.get(0).getMenuPc();
		Long ordAmt = ordQty*menuPc;
		orderInfo.setOrdAmt(ordAmt);
		orderInfo.setOrdStat("결제완료");
		//주문가능재고 확인
		Long menuStock = menuInfoList.get(0).getMenuStock();
		if(ordQty > menuStock) {
			throw new Exception("재고가 부족합니다.");
		}
		
		//주문테이블에 인설트
		defaultMapper.insertOrd(orderInfo);
		//메뉴제고업데이트
		
		defaultMapper.updateMenuStock(orderInfo);
		
	}

}
