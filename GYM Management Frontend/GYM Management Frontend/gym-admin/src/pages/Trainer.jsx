import { useState, useEffect } from "react";
import Navbar from "../components/Navbar";
import "../styles/Trainer.css";

import {
  getTrainers,
  addTrainer,
  deleteTrainerById,
  assignTrainer
} from "../services/trainerService";

const Trainer = () => {

  const [activeTab, setActiveTab] = useState("create");
  const [trainers, setTrainers] = useState([]);
  const [loading, setLoading] = useState(false);

  const [newTrainer, setNewTrainer] = useState({
  name: "",
  specialization: "",
  experienceYears: "",
  email: "",
  phone: "",
  status: "ACTIVE"
});


  const [assignData, setAssignData] = useState({
    trainerId: "",
    memberId: ""
  });

  // ================= FETCH TRAINERS =================
  const fetchTrainers = async () => {
    try {
      setLoading(true);
      const response = await getTrainers();
      setTrainers(response.data);
    } catch (error) {
      console.error("Error fetching trainers:", error);
      alert("Failed to fetch trainers. Check backend.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchTrainers();
  }, []);

  // ================= CREATE TRAINER =================
  const handleTrainerChange = (e) => {
    setNewTrainer({
      ...newTrainer,
      [e.target.name]: e.target.value
    });
  };

  const createTrainer = async () => {
  try {
    if (
      !newTrainer.name ||
      !newTrainer.specialization ||
      !newTrainer.experienceYears ||
      !newTrainer.email ||
      !newTrainer.phone
    ) {
      alert("Please fill all required fields.");
      return;
    }

    const trainerPayload = {
      ...newTrainer,
      experienceYears: Number(newTrainer.experienceYears)
    };

    await addTrainer(trainerPayload);

    alert("Trainer created successfully!");

    setNewTrainer({
      name: "",
      specialization: "",
      experienceYears: "",
      email: "",
      phone: "",
      status: "ACTIVE"
    });

    fetchTrainers();
    setActiveTab("view");

  } catch (error) {
    console.error("Error creating trainer:", error);
    alert("Failed to create trainer. Check backend.");
  }
};


  // ================= DELETE TRAINER =================
  const handleDeleteTrainer = async (id) => {
    try {
      const confirmDelete = window.confirm("Are you sure you want to delete this trainer?");
      if (!confirmDelete) return;

      await deleteTrainerById(id);

      alert("Trainer deleted successfully!");

      fetchTrainers();

    } catch (error) {
      console.error("Error deleting trainer:", error);
      alert("Failed to delete trainer");
    }
  };

  // ================= ASSIGN TRAINER (FRONTEND ONLY FOR NOW) =================
  const handleAssignChange = (e) => {
    setAssignData({
      ...assignData,
      [e.target.name]: e.target.value
    });
  };

  const submitAssignTrainer = async () => {
    try {
      if (!assignData.trainerId || !assignData.memberId) {
        alert("Please select both trainer and member.");
        return;
      }

      await assignTrainer(Number(assignData.trainerId), Number(assignData.memberId));

      alert("Trainer Assigned Successfully!");
      setAssignData({ trainerId: "", memberId: "" });
    } catch (error) {
      console.error("Error assigning trainer:", error);
      alert("Failed to assign trainer. Check backend.");
    }
  };

  return (
    <>
      <Navbar />

      <div className="trainer-wrapper">

        

        {/* Tabs */}
        <div className="tabs-container">
          <div
            className={`tab ${activeTab === "create" ? "active" : ""}`}
            onClick={() => setActiveTab("create")}
          >
            Create Trainer
          </div>

          <div
            className={`tab ${activeTab === "assign" ? "active" : ""}`}
            onClick={() => setActiveTab("assign")}
          >
            Assign Trainer
          </div>

          <div
            className={`tab ${activeTab === "view" ? "active" : ""}`}
            onClick={() => {
              setActiveTab("view");
              fetchTrainers();
            }}
          >
            View Trainers
          </div>

          <div
            className={`tab ${activeTab === "delete" ? "active" : ""}`}
            onClick={() => {
              setActiveTab("delete");
              fetchTrainers();
            }}
          >
            Deactivate Trainer
          </div>
        </div>

        <div className="content-section">

          {activeTab === "create" && (
  <div className="form-section">

    <input
      type="text"
      name="name"
      placeholder="Trainer Name"
      value={newTrainer.name}
      onChange={handleTrainerChange}
    />

    <input
      type="text"
      name="specialization"
      placeholder="Specialization"
      value={newTrainer.specialization}
      onChange={handleTrainerChange}
    />

    <input
      type="number"
      name="experienceYears"
      placeholder="Experience (Years)"
      value={newTrainer.experienceYears}
      onChange={handleTrainerChange}
    />

    <input
      type="email"
      name="email"
      placeholder="Email"
      value={newTrainer.email}
      onChange={handleTrainerChange}
    />

    <input
      type="text"
      name="phone"
      placeholder="Phone"
      value={newTrainer.phone}
      onChange={handleTrainerChange}
    />

    <select
      name="status"
      value={newTrainer.status}
      onChange={handleTrainerChange}
    >
      <option value="ACTIVE">ACTIVE</option>
      <option value="INACTIVE">INACTIVE</option>
    </select>

    <button onClick={createTrainer}>
      Create Trainer
    </button>

  </div>
)}


          {/* ================= ASSIGN TRAINER ================= */}
          {activeTab === "assign" && (
            <div className="form-section">
              <input
                type="number"
                name="trainerId"
                placeholder="Trainer ID"
                value={assignData.trainerId}
                onChange={handleAssignChange}
              />

              <input
                type="number"
                name="memberId"
                placeholder="Member ID"
                value={assignData.memberId}
                onChange={handleAssignChange}
              />

              <button onClick={submitAssignTrainer}>
                Assign
              </button>
            </div>
          )}

          {/* ================= VIEW TRAINERS ================= */}
          {activeTab === "view" && (
            <>
              {loading ? (
                <p>Loading trainers...</p>
              ) : (
                <table>
                  <thead>
  <tr>
    <th>ID</th>
    <th>Name</th>
    <th>Specialization</th>
    <th>Experience</th>
    <th>Email</th>
    <th>Phone</th>
    <th>Status</th>
  </tr>
</thead>

                  <tbody>
                    {trainers.length === 0 ? (
                      <tr>
                        <td colSpan="3">No trainers found</td>
                      </tr>
                    ) : (
                      trainers.map(trainer => (
                        <tr key={trainer.id}>
  <td>{trainer.id}</td>
  <td>{trainer.name}</td>
  <td>{trainer.specialization}</td>
  <td>{trainer.experienceYears}</td>
  <td>{trainer.email}</td>
  <td>{trainer.phone}</td>
  <td>{trainer.status}</td>
</tr>
                      ))
                    )}
                  </tbody>
                </table>
              )}
            </>
          )}

          {/* ================= DELETE TRAINER ================= */}
          {activeTab === "delete" && (
            <>
              {loading ? (
                <p>Loading trainers...</p>
              ) : (
                <table>
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Name</th>
                      <th>Action</th>
                    </tr>
                  </thead>
                  <tbody>
                    {trainers.map(trainer => (
                      <tr key={trainer.id}>
                        <td>{trainer.id}</td>
                        <td>{trainer.name}</td>
                        <td>
                          <button
                            onClick={() => handleDeleteTrainer(trainer.id)}
                          >
                            Deactivate
                          </button>
                        </td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              )}
            </>
          )}

        </div>
      </div>
    </>
  );
};

export default Trainer;
