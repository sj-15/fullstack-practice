import React from "react";
import "./App.css";
import Header from "./app/component/header/Header";
import AppRoutingModule from "./AppRoutingModule";
import { BrowserRouter as Router } from "react-router-dom";

function App() {
  return (
    <Router>
      <div className="App">
        <Header />
        <AppRoutingModule />
      </div>
    </Router>
  );
}

export default App;
