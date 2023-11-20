import React, { useState } from "react";
import { BrowserRouter as Router, Route, Switch } from "react-router-dom";
import Calendar from "react-calendar";
import moment from "moment";
import "moment/locale/ko";
import "react-calendar/dist/Calendar.css";
import "./App.css";

const CalendarPage = ({
  selectedDate,
  setSelectedDate,
  handleNextButtonClick,
}) => {
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

  const handleDayClick = (value, event) => {
    event.preventDefault();
    event.stopPropagation();
    setSelectedDate(value);
  };

  return (
    <div className="calendar-container">
      <Calendar
        onChange={handleDateChange}
        value={selectedDate}
        formatShortWeekday={(locale, date) => weekdays[date.getDay()]}
        view={["month"]}
        navigationLabel={customizeNavigationLabel}
        onClickDay={handleDayClick}
      />
      {selectedDate && (
        <>
          <p className="selected-date">
            선택한 날짜: {moment(selectedDate).format("YYYY.MM.DD")}
          </p>
          <button onClick={handleNextButtonClick}>다음으로</button>
        </>
      )}
    </div>
  );
};
