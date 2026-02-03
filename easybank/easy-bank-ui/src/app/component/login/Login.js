import React, { useState } from "react";
import "./login.css";
import { useNavigate } from "react-router-dom";
import LoginService from "../../service/login-service";
export default function Login() {
  const [email, setEmail] = useState("");
  const [pwd, setPwd] = useState("");

  const navigate = useNavigate();

  const handleLogin = async (e) => {
    e.preventDefault();

    try {
      const response = await LoginService.login({
        email,
        pwd,
      });

      console.log(response.data);
    } catch (error) {
      console.log("Login failed", error);
    }
  };

  return (
    <div className="container">
      <div className="login">
        <form className="login-form" onSubmit={handleLogin}>
          <span className="login-form-title">Welcome back!</span>

          {/* Email */}
          <div className="form-group">
            <div className="wrap-input" data-validate="Please enter email">
              <input
                className="input-field"
                type="email"
                name="email"
                id="email"
                placeholder="Email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                required
              />
            </div>
            {/* TODO: IMPLEMENT EMAIL VALIDATION */}
          </div>

          {/* Password */}
          <div className="form-group">
            <div
              className="wrap-input"
              data-validate="Please enter your password"
            >
              <input
                className="input-field"
                type="password"
                name="pwd"
                id="pwd"
                placeholder="Password"
                value={pwd}
                onChange={(e) => setPwd(e.target.value)}
                required
              />
            </div>
            {/* TODO: PASSWORD VALIDATION*/}
          </div>

          {/* Submit button */}
          <div className="container-login-btn">
            <button className="login-btn" type="submit">
              Sign in
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
