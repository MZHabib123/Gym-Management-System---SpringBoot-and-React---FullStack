import React, { useState, useEffect } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";
import "../styles/ClassBooking.css";

function ClassBooking() {
  const [activeTab, setActiveTab] = useState("view");
  const [sessionTab, setSessionTab] = useState("view");
  const [classes, setClasses] = useState([]);
  const [sessions, setSessions] = useState([]);
  const [isEditing, setIsEditing] = useState(false);
  const [selectedId, setSelectedId] = useState("");

  const [classData, setClassData] = useState({
    name: "",
    description: "",
    durationInMinutes: ""
  });

  const [sessionData, setSessionData] = useState({
    gymClassId: "",
    trainerId: "",
    sessionDate: "",
    startTime: "",
    capacity: ""
  });

  useEffect(() => {
    fetchClasses();
  }, []);

  const fetchClasses = async () => {
    const res = await axios.get("http://localhost:8085/classes");
    setClasses(res.data);
  };

  const fetchSessions = async () => {
    const res = await axios.get("http://localhost:8085/sessions");
    setSessions(res.data);
  };

  const saveClass = async () => {
    if (isEditing) {
      await axios.put(
        `http://localhost:8085/classes/${selectedId}`,
        classData
      );
    } else {
      await axios.post("http://localhost:8085/classes", classData);
    }

    fetchClasses();
    resetClassForm();
    setActiveTab("view");
  };

  const resetClassForm = () => {
    setIsEditing(false);
    setSelectedId("");
    setClassData({
      name: "",
      description: "",
      durationInMinutes: ""
    });
  };

  const deleteClass = async (id) => {
    await axios.delete(`http://localhost:8085/classes/${id}`);
    fetchClasses();
  };

  const createSession = async () => {
  try {
    await axios.post("http://localhost:8085/sessions", sessionData);

    alert("✅ Session created successfully!");

    // Clear form after success
    setSessionData({
      gymClassId: "",
      trainerId: "",
      sessionDate: "",
      startTime: "",
      capacity: ""
    });

    fetchSessions();
    setSessionTab("view"); // auto switch to view sessions

  } catch (error) {
    console.error("Error creating session:", error);
    alert("❌ Failed to create session. Please try again.");
  }
};


  const deleteSession = async (id) => {
    await axios.delete(`http://localhost:8085/sessions/${id}`);
    fetchSessions();
  };

  return (
    <>
      <Navbar />

      <div className="page-wrapper">

        <div className="page-change">

        

        

        <div className="top-buttons">
          <button onClick={() => setActiveTab("view")}>VIEW CLASS</button>
          <button onClick={() => {
            resetClassForm();
            setActiveTab("manage");
          }}>CREATE / MANAGE</button>
          <button onClick={() => {
            setActiveTab("sessions");
            fetchSessions();
          }}>SESSIONS</button>
        </div>

        {/* ================= VIEW CLASSES ================= */}
        {activeTab === "view" && (
          <div className="card-grid">
            {classes.map((cls) => (
              <div key={cls.id} className="card">
                <h3>{cls.name}</h3>
                <p className="desc">{cls.description}</p>
                <p><strong>Duration:</strong> {cls.durationInMinutes} mins</p>

                <div className="card-actions">
                  <button onClick={() => {
                    setIsEditing(true);
                    setSelectedId(cls.id);
                    setClassData(cls);
                    setActiveTab("manage");
                  }}>EDIT</button>

                  <button
                    className="danger"
                    onClick={() => deleteClass(cls.id)}
                  >
                    DELETE
                  </button>
                </div>
              </div>
            ))}
          </div>
        )}

        {/* ================= MANAGE CLASS ================= */}
        {activeTab === "manage" && (
          <div className="form-container fade-in">
            <h2>{isEditing ? "Update Class" : "Create Class"}</h2>

            <input
              type="text"
              placeholder="Class Name"
              value={classData.name}
              onChange={(e) =>
                setClassData({ ...classData, name: e.target.value })
              }
            />

            <textarea
              placeholder="Description"
              value={classData.description}
              onChange={(e) =>
                setClassData({ ...classData, description: e.target.value })
              }
            />

            <input
              type="number"
              placeholder="Duration (minutes)"
              value={classData.durationInMinutes}
              onChange={(e) =>
                setClassData({
                  ...classData,
                  durationInMinutes: e.target.value
                })
              }
            />

            <button className="primary-btn" onClick={saveClass}>
              {isEditing ? "UPDATE CLASS" : "CREATE CLASS"}
            </button>
          </div>
        )}

        {/* ================= SESSIONS ================= */}
        {activeTab === "sessions" && (
          <div className="sessions-container fade-in">

            <div className="session-nav">
              <button
                className={sessionTab === "view" ? "active" : ""}
                onClick={() => {
                  setSessionTab("view");
                  fetchSessions();
                }}
              >
                VIEW SESSIONS
              </button>

              <button
                className={sessionTab === "add" ? "active" : ""}
                onClick={() => setSessionTab("add")}
              >
                ADD SESSION
              </button>
            </div>

            {sessionTab === "view" && (
              <div className="card-grid">
                {sessions.map((session) => (
                  <div key={session.id} className="card">
                    <h3>Session #{session.id}</h3>
                    <p>Class ID: {session.gymClassId}</p>
                    <p>Date: {session.sessionDate}</p>
                    <p>Start: {session.startTime}</p>
                    <p>Capacity: {session.capacity}</p>

                    <button
                      className="danger"
                      onClick={() => deleteSession(session.id)}
                    >
                      DELETE
                    </button>
                  </div>
                ))}
              </div>
            )}

            {sessionTab === "add" && (
              <div className="form-container">
                <input type="number" placeholder="Gym Class ID"
                  onChange={(e) =>
                    setSessionData({ ...sessionData, gymClassId: e.target.value })
                  }
                />

                <input type="number" placeholder="Trainer ID"
                  onChange={(e) =>
                    setSessionData({ ...sessionData, trainerId: e.target.value })
                  }
                />

                <input type="date"
                  onChange={(e) =>
                    setSessionData({ ...sessionData, sessionDate: e.target.value })
                  }
                />

                <input type="time"
                  onChange={(e) =>
                    setSessionData({ ...sessionData, startTime: e.target.value })
                  }
                />

                <input type="number" placeholder="Capacity"
                  onChange={(e) =>
                    setSessionData({ ...sessionData, capacity: e.target.value })
                  }
                />

                <button className="primary-btn" onClick={createSession}>
                  CREATE SESSION
                </button>
              </div>
            )}
          </div>
        )}
</div>
      </div>
    </>
  );
}

export default ClassBooking;
