// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
import "./App.css";
import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./components/LogIn";
import { AppHome } from "./components/AppHome";
import { AddDestination } from "./components/AddDestination";

function App() {
  return (
    <React.Fragment>
      <Router>
        <Routes>
          <Route path="/home" element={<AppHome />} />
          <Route path="/addDestination" element={<AddDestination />} />
          <Route path="/" element={<Login />} />
        </Routes>
      </Router>
    </React.Fragment>
  );
}
export default App
