import React from "react";
import { NavLink } from "react-router-dom";
import "../styles/Navbar.css";

const Navbar = () => {
  return (
    <nav className="navbar">
      <div className="navbar-container">

        <NavLink to="/" className="logo">
          GYM ADMIN
        </NavLink>

        <ul className="nav-links">
          <li>
            <NavLink to="/home" className="nav-item">Home</NavLink>
          </li>
          <li>
            <NavLink to="/member" className="nav-item">Member</NavLink>
          </li>
          <li>
            <NavLink to="/trainer" className="nav-item">Trainer</NavLink>
          </li>
          <li>
            <NavLink to="/inventory" className="nav-item">Inventory</NavLink>
          </li>
          <li>
            <NavLink to="/attendance" className="nav-item">Attendance</NavLink>
          </li>
          <li>
            <NavLink to="/class-booking" className="nav-item">Class Booking</NavLink>
          </li>
          <li>
            <NavLink to="/" className="nav-item">Log out</NavLink>
          </li>
        </ul>

      </div>
    </nav>
  );
};

export default Navbar;
