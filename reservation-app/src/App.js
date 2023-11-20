import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
import Calendar from "react-calendar";
import moment from "moment";
import "moment/locale/ko"; // 한국어 설정
import "react-calendar/dist/Calendar.css";

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

const MenuPage = ({
  handleMenuClick,
  handleNextButtonClick,
  showOrderSummary,
  order,
}) => (
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
        <h2>주문 요약</h2>
        <ul>
          {Object.entries(order).map(([menu, count]) => (
            <li key={menu}>{`${menu}: ${count}개`}</li>
          ))}
        </ul>

        <Link to="/order-summary">
          <button onClick={handleNextButtonClick}>다음으로</button>
        </Link>
      </div>
    )}
  </div>
);

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

const OrderSummaryPage = ({ order }) => (
  <div className="order-summary">
    <h2>주문 요약</h2>
    <ul>
      {Object.entries(order).map(([menu, count]) => (
        <li key={menu}>{`${menu}: ${count}개`}</li>
      ))}
    </ul>
  </div>
);

const App = () => {
  const [selectedDate, setSelectedDate] = useState(null);
  const [showReservation, setShowReservation] = useState(false);
  const [showMenu, setShowMenu] = useState(false);
  const [showOrderSummary, setShowOrderSummary] = useState(false);
  const [order, setOrder] = useState({}); // 주문한 음식과 갯수를 저장하는 객체

  const eventStartDate = new Date("2023-12-01");
  const eventEndDate = new Date("2023-12-31");

  const initialDate = new Date();
  initialDate.setMonth(11);

  const [currentDate, setCurrentDate] = useState(initialDate);

  const handleDateChange = (date) => {
    setSelectedDate(date);
    setShowReservation(true);
  };

  const weekdays = moment.weekdaysShort().map((day) => day.charAt(0));

  const customizeNavigationLabel = ({ date, view }) => {
    if (view === "month") {
      return <span>{moment(date).format("YYYY년 MM월")}</span>;
    }
    return null;
  };

  const handleDayClick = (value, event) => {
    event.preventDefault();
    event.stopPropagation();
    setSelectedDate(value);
    setShowReservation(true);
  };

  const handleNextButtonClick = () => {
    if (showReservation) {
      setShowReservation(false);
      setShowMenu(true);
      setShowOrderSummary(true);
    }
  };

  const handleMenuClick = (menuName) => {
    // 메뉴를 클릭하면 해당 메뉴의 주문 갯수를 늘리는 로직
    setOrder((prevOrder) => ({
      ...prevOrder,
      [menuName]: (prevOrder[menuName] || 0) + 1,
    }));
  };

  useEffect(() => {
    // useEffect를 사용하여 필요한 부가 동작 수행
  }, [selectedDate]);

  const ReservationInfo = ({ selectedDate, handleNextButtonClick }) => (
    <div className="reservation-info">
      <p>선택한 날짜: {moment(selectedDate).format("YYYY.MM.DD")}</p>
      <Link to="/menu">
        <button onClick={handleNextButtonClick}>다음으로</button>
      </Link>
    </div>
  );

  return (
    <Router>
      <div className="App">
        <h1>우아한 레스토랑 예약</h1>
        <Switch>
          <Route path="/menu">
            <MenuPage
              handleMenuClick={handleMenuClick}
              handleNextButtonClick={handleNextButtonClick}
              showOrderSummary={showOrderSummary}
              order={order}
            />
          </Route>
          <Route path="/order-summary">
            <OrderSummaryPage order={order} />
          </Route>
          <Route path="/">
            <div className="calendar-container">
              <Calendar
                onChange={handleDateChange}
                value={selectedDate}
                minDate={eventStartDate}
                maxDate={eventEndDate}
                calendarType="US"
                formatShortWeekday={(locale, date) => weekdays[date.getDay()]}
                view={["month"]}
                defaultActiveStartDate={currentDate}
                navigationLabel={customizeNavigationLabel}
                onClickDay={handleDayClick}
              />
              {showReservation && (
                <ReservationInfo
                  selectedDate={selectedDate}
                  handleNextButtonClick={handleNextButtonClick}
                />
              )}
            </div>
          </Route>
        </Switch>
      </div>
    </Router>
  );
};

export default App;
