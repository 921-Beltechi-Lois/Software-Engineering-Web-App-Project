import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
import './App.css'
import React from 'react'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from './components/LogIn'
import { AddDestination } from './components/AddDestination';
import { DestinationsShowAll } from './components/ViewPublicDestination';
import { DestinationsShowAllPrivate } from './components/ViewPrivateDestinations';
import { AddDestinationAdmin } from './components/AddDestinationAdmin';

function App() {
  const [count, setCount] = useState(0)

  return (
    <React.Fragment>
    <Router>

      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/addDestination" element={<AddDestination />} />
        <Route path="/addDestinationAdmin" element={<AddDestinationAdmin />} />

        <Route path = "/destinations" element = {<DestinationsShowAll/>}/>
        <Route path= "/privatedestinations" element = {<DestinationsShowAllPrivate/>}/>
  
      </Routes>
    </Router>
  </React.Fragment>
);
}
export default App
