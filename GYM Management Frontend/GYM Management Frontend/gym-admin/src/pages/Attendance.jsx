import { useState } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";
import "../styles/Attendance.css";

const Attendance = () => {

  const [activeTab, setActiveTab] = useState("track");

  const [memberId, setMemberId] = useState("");
  const [memberHistory, setMemberHistory] = useState([]);

  // ================= CHECK IN =================

  const handleCheckIn = async () => {
    if (!memberId) {
      alert("Please enter Member ID");
      return;
    }

    try {
      await axios.post("http://localhost:8083/attendance/check-in", {
        memberId: memberId
      });

      alert("Check-In Successful ✅");
    } catch (error) {
      console.error("Check-In Error:", error);
      alert("Check-In Failed ❌");
    }
  };

  // ================= CHECK OUT =================

  const handleCheckOut = async () => {
    if (!memberId) {
      alert("Please enter Member ID");
      return;
    }

    try {
      await axios.post("http://localhost:8083/attendance/check-out", {
        memberId: memberId
      });

      alert("Check-Out Successful ✅");
    } catch (error) {
      console.error("Check-Out Error:", error);
      alert("Check-Out Failed ❌");
    }
  };

  // ================= FETCH MEMBER HISTORY =================

  const fetchMemberHistory = async () => {
    if (!memberId) {
      alert("Enter Member ID");
      return;
    }

    try {
      const response = await axios.get(
        `http://localhost:8083/attendance/member/${memberId}`
      );

      setMemberHistory(response.data);
      setActiveTab("history");

    } catch (error) {
      console.error("Fetch History Error:", error);
      alert("Could not fetch history ❌");
    }
  };

  return (
    

      <div className="attendance-wrapper">
        <div className="attendance-container">


        {/* Tabs */}
        <div className="tabs-container">

          <div
            className={`tab ${activeTab === "track" ? "active" : ""}`}
            onClick={() => setActiveTab("track")}
          >
            Check-In / Check-Out
          </div>

          <div
            className={`tab ${activeTab === "history" ? "active" : ""}`}
            onClick={() => setActiveTab("history")}
          >
            Member History
          </div>

        </div>

        <div className="content-section">

          {/* ================= TRACK SECTION ================= */}
          {activeTab === "track" && (
            <div className="form-section">

              <input
                type="number"
                placeholder="Enter Member ID"
                value={memberId}
                onChange={(e) => setMemberId(e.target.value)}
              />

              <button onClick={handleCheckIn}>
                Check In
              </button>

              <button onClick={handleCheckOut}>
                Check Out
              </button>

              <button onClick={fetchMemberHistory}>
                View Member History
              </button>

            </div>
          )}

          {/* ================= HISTORY SECTION ================= */}
          {activeTab === "history" && (
            <table>
              <thead>
                <tr>
                  <th>ID</th>
                  <th>Member ID</th>
                  <th>Check-In</th>
                  <th>Check-Out</th>
                </tr>
              </thead>
              <tbody>
                {memberHistory.length > 0 ? (
                  memberHistory.map((log) => (
                    <tr key={log.id}>
                      <td>{log.id}</td>
                      <td>{log.memberId}</td>
                      <td>{log.checkInTime}</td>
                      <td>{log.checkOutTime || "Active"}</td>
                    </tr>
                  ))
                ) : (
                  <tr>
                    <td colSpan="4">No Records Found</td>
                  </tr>
                )}
              </tbody>
            </table>
          )}

        </div>
      </div>
      </div>
    
  );
};

export default Attendance;
