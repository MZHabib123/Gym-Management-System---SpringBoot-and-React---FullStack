import { useState, useEffect } from "react";
import Navbar from "../components/Navbar";
import "../styles/Member.css";
import memberImage from "../assets/member_page2.png";

import {
  getMembers,
  addMember,
  updateMember,
  deleteMember
} from "../services/memberService";

const Member = () => {
  const [activeTab, setActiveTab] = useState("get");
  const [members, setMembers] = useState([]);
  const [loading, setLoading] = useState(false);

  const [updateId, setUpdateId] = useState("");

  const [updateData, setUpdateData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    planId: "",
    membershipStatus: "ACTIVE"
  });

  const [newMember, setNewMember] = useState({
    firstName: "",
    lastName: "",
    email: "",
    phone: "",
    planId: "",
    membershipStatus: "ACTIVE"
  });

  // 🔥 AUTO LOAD MEMBERS
  useEffect(() => {
    fetchMembers();
  }, []);

  // ================= FETCH MEMBERS =================
  const fetchMembers = async () => {
    try {
      setLoading(true);
      const response = await getMembers();
      setMembers(response.data);
    } catch (error) {
      console.error("Error fetching members:", error);
      alert("Failed to fetch members.");
    } finally {
      setLoading(false);
    }
  };

  // ================= HANDLE INPUT =================
  const handleChange = (e) => {
    setNewMember({
      ...newMember,
      [e.target.name]: e.target.value
    });
  };

  const handleUpdateChange = (e) => {
    setUpdateData({
      ...updateData,
      [e.target.name]: e.target.value
    });
  };

  // ================= ADD MEMBER =================
  const handleAddMember = async () => {
    try {
      if (!newMember.firstName || !newMember.lastName || !newMember.email) {
        alert("Please fill required fields.");
        return;
      }

      await addMember(newMember);

      alert("Member added successfully!");
      fetchMembers();

      setNewMember({
        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        planId: "",
        membershipStatus: "ACTIVE"
      });

      setActiveTab("get");
    } catch (error) {
      console.error("Error adding member:", error);
      alert("Failed to add member.");
    }
  };

  // ================= UPDATE MEMBER =================
  const handleUpdateMember = async () => {
    try {
      if (!updateId) {
        alert("Please enter Member ID");
        return;
      }

      await updateMember(updateId, updateData);

      alert("Member updated successfully!");

      fetchMembers();
      setActiveTab("get");

      setUpdateId("");
      setUpdateData({
        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        planId: "",
        membershipStatus: "ACTIVE"
      });
    } catch (error) {
      console.error("Error updating member:", error);
      alert("Failed to update member.");
    }
  };

  // ================= DELETE MEMBER =================
  const handleDeleteMember = async (id) => {
    try {
      const confirmDelete = window.confirm(
        "Are you sure you want to delete this member?"
      );
      if (!confirmDelete) return;

      await deleteMember(id);

      alert("Member deleted successfully!");
      fetchMembers();
    } catch (error) {
      console.error("Error deleting member:", error);
      alert("Failed to delete member.");
    }
  };

  return (
    <>
      <Navbar />

      <div className="member-wrapper">
        <div className="member-layout">

          {/* LEFT SIDE */}
          <div className="member-left">

           

            {/* Tabs */}
            <div className="tabs-container">

              <div
                className={`tab ${activeTab === "get" ? "active" : ""}`}
                onClick={() => {
                  setActiveTab("get");
                  fetchMembers();
                }}
              >
                Get Members
              </div>

              <div
                className={`tab ${activeTab === "add" ? "active" : ""}`}
                onClick={() => setActiveTab("add")}
              >
                Add Member
              </div>

              <div
                className={`tab ${activeTab === "update" ? "active" : ""}`}
                onClick={() => setActiveTab("update")}
              >
                Update Member
              </div>

            </div>

            {/* Content */}
            <div className="content-section">

              {/* ================= GET MEMBERS ================= */}
              {activeTab === "get" && (
                <>
                  {loading ? (
                    <p>Loading members...</p>
                  ) : (
                    <table>
                      <thead>
                        <tr>
                          <th>ID</th>
                          <th>Name</th>
                          <th>Email</th>
                          <th>Phone</th>
                          <th>Plan</th>
                          <th>Status</th>
                          <th>Actions</th>
                        </tr>
                      </thead>
                      <tbody>
                        {members.length === 0 ? (
                          <tr>
                            <td colSpan="7">No members found</td>
                          </tr>
                        ) : (
                          members.map((member) => (
                            <tr key={member.id}>
                              <td>{member.id}</td>
                              <td>{member.firstName} {member.lastName}</td>
                              <td>{member.email}</td>
                              <td>{member.phone}</td>
                              <td>{member.planName}</td>
                              <td>{member.membershipStatus}</td>
                              <td>
                                <button
                                  className="delete-btn"
                                  onClick={() => handleDeleteMember(member.id)}
                                >
                                  Delete
                                </button>
                              </td>
                            </tr>
                          ))
                        )}
                      </tbody>
                    </table>
                  )}
                </>
              )}

              {/* ================= ADD MEMBER ================= */}
              {activeTab === "add" && (
                <div className="form-section">

                  <input
                    type="text"
                    name="firstName"
                    placeholder="First Name"
                    value={newMember.firstName}
                    onChange={handleChange}
                  />

                  <input
                    type="text"
                    name="lastName"
                    placeholder="Last Name"
                    value={newMember.lastName}
                    onChange={handleChange}
                  />

                  <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={newMember.email}
                    onChange={handleChange}
                  />

                  <input
                    type="text"
                    name="phone"
                    placeholder="Phone"
                    value={newMember.phone}
                    onChange={handleChange}
                  />

                  <input
                    type="number"
                    name="planId"
                    placeholder="Plan ID"
                    value={newMember.planId}
                    onChange={handleChange}
                  />

                  <button className="add-btn" onClick={handleAddMember}>
                    Add Member
                  </button>

                </div>
              )}

              {/* ================= UPDATE MEMBER ================= */}
              {activeTab === "update" && (
                <div className="form-section">

                  <input
                    type="number"
                    placeholder="Member ID"
                    value={updateId}
                    onChange={(e) => setUpdateId(e.target.value)}
                  />

                  <input
                    type="text"
                    name="firstName"
                    placeholder="First Name"
                    value={updateData.firstName}
                    onChange={handleUpdateChange}
                  />

                  <input
                    type="text"
                    name="lastName"
                    placeholder="Last Name"
                    value={updateData.lastName}
                    onChange={handleUpdateChange}
                  />

                  <input
                    type="email"
                    name="email"
                    placeholder="Email"
                    value={updateData.email}
                    onChange={handleUpdateChange}
                  />

                  <input
                    type="text"
                    name="phone"
                    placeholder="Phone"
                    value={updateData.phone}
                    onChange={handleUpdateChange}
                  />

                  <input
                    type="number"
                    name="planId"
                    placeholder="Plan ID"
                    value={updateData.planId}
                    onChange={handleUpdateChange}
                  />

                  <button
                    className="update-btn"
                    onClick={handleUpdateMember}
                  >
                    Update Member
                  </button>

                </div>
              )}

            </div>
          </div>

          {/* RIGHT SIDE IMAGE */}
          <div className="member-right">
            <img src={memberImage} alt="Member Preview" />
          </div>

        </div>
      </div>
    </>
  );
};

export default Member;
