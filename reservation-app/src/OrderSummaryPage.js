import React, { useState, useEffect } from "react";

import "./OrderSummaryPage.css";

const OrderSummaryPage = ({ order, orderSummaryData }) => {
  return (
    <div className="order-summary">
      <h2>주문 요약</h2>
      {orderSummaryData ? (
        // orderSummaryData가 존재할 때 필요한 정보를 출력
        <div>
          <p>안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.</p>
          <p>
            12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)
          </p>
          <p>{orderSummaryData.requirement.visitDate}</p>
          <p>
            주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g.
            해산물파스타-2,레드와인-1,초코케이크-1)
          </p>
          <p>
            {Object.entries(order).map(([menu, count], index, array) => (
              <span key={menu}>
                {`${menu}-${count}${index < array.length - 1 ? "," : ""}`}
              </span>
            ))}
          </p>
          <br></br>
          <p>&lt;주문메뉴&gt;</p>
          <p>
            {Object.entries(order).map(([menu, count]) => (
              <li key={menu}>{`${menu}: ${count}개`}</li>
            ))}
          </p>
          <br></br>
          <p>&lt;할인 전 총주문 금액&gt;</p>
          <p>{orderSummaryData.totalAmountBeforeDiscount.price + "원"}</p>
          <br></br>
          <p>&lt;증정메뉴&gt;</p>
          <p>
            {orderSummaryData.eachDiscountAmount.champaigneAmount.price === 0
              ? "없음"
              : `샴페인 1개`}
          </p>
          <br></br>
          <p>&lt;혜택내역&gt;</p>
          {orderSummaryData.eachDiscountAmount.christmasEventDiscountAmount
            .price !== 0 && (
            <p>{`크리스마스 디데이 할인: - ${orderSummaryData.eachDiscountAmount.christmasEventDiscountAmount.price}원`}</p>
          )}
          {orderSummaryData.eachDiscountAmount.weekDayDiscountAmount.price !==
            0 && (
            <p>{`평일 할인: - ${orderSummaryData.eachDiscountAmount.weekDayDiscountAmount.price}원`}</p>
          )}
          {orderSummaryData.eachDiscountAmount.weekEndDiscountAmount.price !==
            0 && (
            <p>{`주말 할인: - ${orderSummaryData.eachDiscountAmount.weekEndDiscountAmount.price}원`}</p>
          )}
          {orderSummaryData.eachDiscountAmount.specialDiscountAmount.price !==
            0 && (
            <p>{`특별 할인: - ${orderSummaryData.eachDiscountAmount.specialDiscountAmount.price}원`}</p>
          )}
          {orderSummaryData.eachDiscountAmount.champaigneAmount.price !== 0 && (
            <p>{`증정 이벤트: - ${orderSummaryData.eachDiscountAmount.champaigneAmount.price}원`}</p>
          )}
          <br></br>
          <p>&lt;총혜택 금액&gt;</p>
          <p>{`-${orderSummaryData.totalDiscountAmount}원`}</p>
          <br></br>
          <p>&lt;할인 후 예상 결제 금액&gt;</p>
          <p>{orderSummaryData.expectedPayment + "원"}</p>
          <br></br>
          <p>&lt;12월 이벤트 배지&gt;</p>
          <p>{orderSummaryData.eventBadge}</p>
        </div>
      ) : (
        // orderSummaryData가 없을 때의 처리
        <p>로딩 중...</p>
      )}
    </div>
  );
};

export default OrderSummaryPage;
