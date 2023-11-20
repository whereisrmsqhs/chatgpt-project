import React, { useState, useEffect } from "react";
import moment from "moment";
import "./App.css";

const menuList = {
  appetizer: [
    { name: "양송이수프", price: 6000 },
    { name: "타파스", price: 5500 },
    { name: "시저샐러드", price: 8000 },
  ],
  main: [
    { name: "티본스테이크", price: 55000 },
    { name: "바비큐립", price: 54000 },
    { name: "해산물파스타", price: 35000 },
    { name: "크리스마스파스타", price: 25000 },
  ],
  dessert: [
    { name: "초코케이크", price: 15000 },
    { name: "아이스크림", price: 5000 },
  ],
  drink: [
    { name: "제로콜라", price: 3000 },
    { name: "레드와인", price: 60000 },
    { name: "샴페인", price: 25000 },
  ],
};

const renderMenuList = (menuItems, onClickHandler) => (
  <div className="menu-list">
    {menuItems.map((item) => (
      <div
        key={item.name}
        className="menu-item"
        onClick={() => onClickHandler(item.name)}
      >
        {`${item.name} (${item.price.toLocaleString()}원)`}
      </div>
    ))}
  </div>
);

const MenuPage = ({
  handleMenuClick,
  handleNextButtonClick,
  showOrderSummary,
  order,
  selectedDate,
  setOrder,
}) => {
  const calculateTotalAmount = () => {
    let totalAmount = 0;

    // 각 주문 메뉴의 가격을 곱해서 총 합 계산
    Object.entries(order).forEach(([menu, count]) => {
      const menuItem = getMenuByName(menu);
      // 메뉴를 찾지 못한 경우에 대한 예외 처리 추가
      if (menuItem && menuItem.price) {
        totalAmount += menuItem.price * count;
      } else {
        console.warn(`Could not calculate price for "${menu}".`);
      }
    });

    return totalAmount;
  };

  const getMenuByName = (name) => {
    // 메뉴 리스트에서 주어진 이름에 해당하는 메뉴 찾기
    for (const category in menuList) {
      const menu = menuList[category].find((item) => item.name === name);
      if (menu) {
        return menu;
      }
    }
    // 메뉴를 찾지 못한 경우에 대한 예외 처리 추가
    console.warn(`Menu "${name}" not found in the menuList.`);
    return { price: 0 }; // 기본값 또는 0으로 처리
  };

  const convertOrderInfoToJson = async () => {
    try {
      const orderInfo = [];

      // 각 주문 메뉴의 주문 형식 변환
      for (const [menu, count] of Object.entries(order)) {
        const menuOrder = `${menu}-${count}`;
        orderInfo.push(menuOrder);
      }

      // 예정 방문 날짜의 월을 뺀 형태로 변환
      const formattedDate = moment(selectedDate).format("DD");

      // 주문 정보를 JSON으로 변환
      const jsonOrderInfo = {
        order: orderInfo.join(","),
        date: formattedDate,
      };

      console.log("JSON 주문 정보:", jsonOrderInfo);

      // 백엔드로 POST 요청 보내기
      const response = await fetch("/menu", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          // 다른 필요한 헤더가 있다면 추가하세요
        },
        body: JSON.stringify(jsonOrderInfo),
      });

      // 요청이 성공했는지 확인 (상태 코드가 2xx인지)
      if (response.ok) {
        console.log("주문 정보가 성공적으로 백엔드로 전송되었습니다!");
      } else {
        console.error("주문 정보를 백엔드로 전송하는 데 실패했습니다.");
      }
    } catch (error) {
      console.error("주문 정보를 전송하는 동안 오류가 발생했습니다:", error);
    }
  };

  const handleCancelClick = (menuName) => {
    setOrder((prevOrder) => {
      const updatedOrder = { ...prevOrder };
      if (updatedOrder[menuName] > 0) {
        // 주문 수량이 0보다 큰 경우에만 감소
        updatedOrder[menuName] -= 1;
      }
      // 주문 수량이 0이 되었을 때 해당 메뉴를 삭제
      if (updatedOrder[menuName] === 0) {
        delete updatedOrder[menuName];
      }
      return updatedOrder;
    });
  };

  return (
    <div className="menu-container">
      <div className="menu-wrapper">
        <div className="menu-list">
          <h2>애피타이저</h2>
          {renderMenuList(menuList.appetizer, handleMenuClick)}

          <h2>메인</h2>
          {renderMenuList(menuList.main, handleMenuClick)}

          <h2>디저트</h2>
          {renderMenuList(menuList.dessert, handleMenuClick)}

          <h2>음료</h2>
          {renderMenuList(menuList.drink, handleMenuClick)}
        </div>
      </div>

      {showOrderSummary && (
        <div className="order-summary">
          <h2>예정 방문 날짜: {moment(selectedDate).format("MM월 DD일")}</h2>
          <h2>주문 요약</h2>
          <ul>
            {Object.entries(order).map(([menu, count]) => (
              <li key={menu}>
                {`${menu}: ${count}개`}
                <button onClick={() => handleCancelClick(menu)}>취소</button>
              </li>
            ))}
          </ul>
          <p className="total-amount">
            총 주문 금액: {calculateTotalAmount().toLocaleString()}원
          </p>
          {/* 주문 정보를 JSON으로 변환하여 출력 */}
          <button onClick={() => convertOrderInfoToJson()}>다음으로</button>
        </div>
      )}
    </div>
  );
};

export default MenuPage;
