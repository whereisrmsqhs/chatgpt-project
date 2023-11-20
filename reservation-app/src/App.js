import React, { useState, useEffect } from "react";
import { BrowserRouter as Router, Route, Link, Switch } from "react-router-dom";
import Calendar from "react-calendar";
import moment from "moment";
import "moment/locale/ko"; // 한국어 설정
import "react-calendar/dist/Calendar.css";

import "./App.css";

const MenuPage = ({
  handleMenuClick,
  handleNextButtonClick,
  showOrderSummary,
  order,
}) => (
  <div className="menu-container">
    <h2>주문 메뉴</h2>
    <div className="menu-list">
      <div className="menu-item" onClick={() => handleMenuClick("음식1")}>
        음식1
      </div>
      <div className="menu-item" onClick={() => handleMenuClick("음식2")}>
        음식2
      </div>
      {/* 추가적인 메뉴 항목들을 여기에 추가 */}
    </div>
    {showOrderSummary && (
      <div className="order-summary">
        <h2>주문 요약</h2>
        <ul>
          {Object.entries(order).map(([menu, count]) => (
            <li key={menu}>{`${menu}: ${count}개`}</li>
          ))}
        </ul>
      </div>
    )}
    <Link to="/order-summary">
      <button onClick={handleNextButtonClick}>다음으로</button>
    </Link>
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
