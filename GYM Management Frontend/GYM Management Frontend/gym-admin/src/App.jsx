import { BrowserRouter as Router, Routes, Route , useLocation } from "react-router-dom";
import LandingPage from "./pages/LandingPage";
import Member from "./pages/Member";
import Trainer from "./pages/Trainer";
import Inventory from "./pages/Inventory";
import Attendance from "./pages/Attendance";
import ClassBooking from "./pages/ClassBooking";
import Navbar from "./components/Navbar";
import Login from "./pages/LoginPage";



function App() {
  return (
    <Router>

      {location.pathname !== "/" && <Navbar />}
     
      
      <Routes>
         <Route path="/" element={<Login />} />
        

        <Route path="/home" element={<LandingPage />} />
        <Route path="/member" element={<Member />} />
        <Route path="/trainer" element={<Trainer />} />
        <Route path="/inventory" element={<Inventory />} />
        <Route path="/attendance" element={<Attendance />} />
        <Route path="/class-booking" element={<ClassBooking />} />
      </Routes>
    </Router>
  );
}

export default App;
