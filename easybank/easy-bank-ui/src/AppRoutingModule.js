import { Routes, Route } from "react-router-dom";
import Home from "./app/component/home/Home";
import Login from "./app/component/login/Login";
import Account from "./app/component/account/Account";
import Register from "./app/component/register/Register";

export default function AppRoutingModule() {
  return (
    <Routes>
      <Route path="/" element={<Home />} />
      <Route path="/login" element={<Login />} />
      <Route path="/account" element={<Account />} />
      <Route path="/register" element={<Register />} />
    </Routes>
  );
}
