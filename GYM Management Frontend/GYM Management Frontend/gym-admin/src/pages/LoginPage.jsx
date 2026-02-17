import React, { useState } from "react";
import { useNavigate } from "react-router-dom";

import "../styles/Login.css";

import bgVideo from "../assets/video.mp4";

import { setToken, getUserRole } from "../services/authService";




function Login() {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch("http://localhost:8080/auth/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({ username, password }),
      });

      const data = await response.json();

      if (response.ok) {
        setToken(data.token);

        const role = getUserRole();

        if (role === "ADMIN") {
          navigate("/home");
        } else if (role === "TRAINER") {
          navigate("/home");
        } else {
          alert("Unauthorized role");
        }
      } else {
        alert(data.message || "Login failed");
      }
    } catch (error) {
      alert("Server error");
    }
  };

  return (
    <div className="login-container">
      
      {/* 🎥 Animated Gym Background */}
      <video autoPlay loop muted className="video-bg">
        <source
          src={bgVideo}
          type="video/mp4"
        />
      </video>

      {/* Dark Overlay */}
      <div className="overlay"></div>

      {/* Login Card */}
      <div className="login-card">
        <h1>Gym Portal Login</h1>

        <form onSubmit={handleSubmit}>
          <input
            type="text"
            placeholder="Username"
            required
            value={username}
            onChange={(e) => setUsername(e.target.value)}
          />

          <input
            type="password"
            placeholder="Password"
            required
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />

          <button type="submit" className="login-btn">
            Login
          </button>
        </form>
      </div>
    </div>
  );
}

export default Login;
