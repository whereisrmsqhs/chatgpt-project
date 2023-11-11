import React, { useState, useEffect } from "react";
import Calendar from "react-calendar";
import moment from "moment";
import "moment/locale/ko"; // 한국어 설정
import "react-calendar/dist/Calendar.css";

import "./App.css";

const App = () => {
  const [selectedDate, setSelectedDate] = useState(null);
  const eventStartDate = new Date("2023-12-01");
  const eventEndDate = new Date("2023-12-31");

  const initialDate = new Date();
  initialDate.setMonth(11);

  const [currentDate, setCurrentDate] = useState(initialDate);

  const handleDateChange = (date) => {
    setSelectedDate(date);
  };

  const weekdays = moment.weekdaysShort().map((day) => day.charAt(0));

  const customizeNavigationLabel = ({ date, view }) => {
    if (view === "month") {
      return <span>{moment(date).format("YYYY년 MM월")}</span>;
    }
    return null;
  };

  // onClickDay prop을 사용하여 날짜 클릭 시 연도와 월 변경 기능 막기
  const handleDayClick = (value, event) => {
    event.preventDefault();
    event.stopPropagation();
    setSelectedDate(value);
  };

  return (
    <div className="App">
      <h1>우아한 레스토랑 예약</h1>
      <div className="calendar-container">
        <p className="reservation-message">
          {selectedDate
            ? `선택한 날짜: ${selectedDate.toLocaleDateString()}`
            : "예약 날짜를 정해주세요."}
        </p>
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
        />
      </div>
    </div>
  );
};

export default App;
