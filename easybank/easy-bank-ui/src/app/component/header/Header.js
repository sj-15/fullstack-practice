import React from "react";
import { Link } from "react-router-dom";
import "./header.css";
import logo from "../../../assest/logo.png";

export default function Header() {
  return (
    <div data-navigation="container" class="i-top-header">
      <div className="wrapper top-section">
        <div className="hleft">
          <Link className="logo self-logo" to="/">
            <img alt="logo" src={logo}></img>
          </Link>
        </div>
        <nav className="hright dnav">
          <ul>
            <li className="nav-item">
              <Link to="/">Home</Link>
            </li>
            <li className="nav-item">
              <Link to="/login">Login</Link>
            </li>
            <li className="nav-item">
              <Link to="/register">Register</Link>
            </li>
            <li className="nav-item">
              <Link to="/account">Account</Link>
            </li>
            <li className="nav-item">
              <Link to="/logout">Logout</Link>
            </li>
          </ul>
        </nav>
      </div>
    </div>
  );
}
