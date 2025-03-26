import React, { useState } from "react";
import "./register.css";
import RegisterService from "../../service/register-service";
import { useNavigate } from "react-router-dom";
import User from "../../model/User";

export default function Register() {
  const [name, setName] = useState("");
  const [email, setEmail] = useState("");
  const [mobileNo, setMobileNo] = useState("");
  const [pwd, setPwd] = useState("");
  const role = "user";

  const navigate = useNavigate();

  const handleRegister = async (e) => {
    e.preventDefault();

    try {
      const response = await RegisterService.register({
        name,
        email,
        mobileNo,
        pwd,
        role,
      });

      let user = new User(...response.data);
      console.log(response.data);
      user.authStatus = "AUTH";

      localStorage.setItem("token", response.data.token);
      sessionStorage.setItem("userDetails", user);
      const csrfToken = getCookie("X-XSRF-TOKEN");
      console.log(csrfToken);
      navigate("/");
    } catch (error) {
      console.log("Registration failed", error);
    }
  };

  return (
    <div className="register">
      <form className="register-form" onSubmit={handleRegister}>
        <span className="register-form-title">Create an account</span>

        {/* Name */}
        <div className="form-group">
          <div className="wrap-input" data-validate="Please enter your name">
            <input
              className="input-field"
              type="text"
              name="name"
              id="name"
              placeholder="Name"
              value={name}
              onChange={(e) => setName(e.target.value)}
              required
            />
          </div>
          {/* TODO: NAME NOT NULL EXCEPTION */}
        </div>

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

        {/* Mobile No */}
        <div className="form-group">
          <div
            className="wrap-input"
            data-validate="Please enter your mobile no"
          >
            <input
              className="input-field"
              type="number"
              name="mobileNo"
              id="mobileNo"
              placeholder="Mobile No"
              value={mobileNo}
              onChange={(e) => setMobileNo(e.target.value)}
              required
            />
          </div>
          {/* TODO: MOBILE NO VALIDATION*/}
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
        <div className="container-register-btn">
          <button className="register-btn" type="submit">
            Sign up
          </button>
        </div>
      </form>
    </div>
  );
}

function getCookie(name) {
  const value = `; ${document.cookie}`;
  const parts = value.split(`; ${name}=`);
  if (parts.length === 2) return parts.pop().split(";").shift();
}
