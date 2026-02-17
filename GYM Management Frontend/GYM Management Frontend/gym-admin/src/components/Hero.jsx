import React from "react";
import { useNavigate } from "react-router-dom";
import "../styles/Hero.css";
import gymLanding from "../assets/gym_landing.png";


const Hero = () => {
  const navigate = useNavigate();

  const handleStart = () => {
    navigate("/member"); // you can change this later
  };

  return (
    <section className="hero">
  <div className="hero-overlay">
    <div className="hero-content">

      {/* LEFT SIDE - TEXT */}
      <div className="hero-text">
        <h1>
          Empower Your <span>Gym</span>,<br />
          Elevate Your Business
        </h1>

        <p className="hero-subtitle">
          All-in-One Gym Management System to Simplify Your Success.
        </p>

        <div className="hero-buttons">
          <button className="primary-btn" onClick={handleStart}>
            Get Started
          </button>
        </div>
      </div>

      {/* RIGHT SIDE - IMAGE */}
      <div className="hero-image">
        <img src={gymLanding} alt="Gym Dashboard Preview" />
      </div>

    </div>
  </div>
</section>

  );
};

export default Hero;
