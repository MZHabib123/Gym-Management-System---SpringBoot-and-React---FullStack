import { useState, useEffect } from "react";
import axios from "axios";
import Navbar from "../components/Navbar";
import "../styles/Inventory.css";

const Inventory = () => {

  const [activeTab, setActiveTab] = useState("create");
  const [equipment, setEquipment] = useState([]);
  const [maintenanceLogs, setMaintenanceLogs] = useState([]);

  const [newEquipment, setNewEquipment] = useState({
    name: "",
    category: "",
    quantity: 0,
    status: "Active",
    purchaseDate: ""
  });

  const [selectedEquipmentId, setSelectedEquipmentId] = useState("");
  const [statusUpdate, setStatusUpdate] = useState("Active");

  const [maintenanceData, setMaintenanceData] = useState({
    assetId: "",
    description: "",
    maintenanceDate: "",
    cost: 0
  });

  // ================= FETCH DATA =================

  useEffect(() => {
    fetchAssets();
  }, []);

  const fetchAssets = async () => {
    try {
      const response = await axios.get("http://localhost:8086/assets");
      setEquipment(response.data);
    } catch (error) {
      console.error("Error fetching assets:", error);
    }
  };

  const fetchMaintenanceLogs = async (assetId) => {
  try {
    const response = await axios.get(
      `http://localhost:8086/assets/${assetId}/maintenance`
    );
    setMaintenanceLogs(response.data);
  } catch (error) {
    console.error("Error fetching maintenance logs:", error);
  }
};


  // ================= CREATE ASSET =================

  const handleChange = (e) => {
    setNewEquipment({ ...newEquipment, [e.target.name]: e.target.value });
  };

  const createEquipment = async () => {
    try {
      await axios.post("http://localhost:8086/assets", newEquipment);
      fetchAssets();
      setNewEquipment({
        name: "",
        category: "",
        quantity: 0,
        status: "Active",
        purchaseDate: ""
      });
      alert("Asset Created Successfully");
    } catch (error) {
      console.error("Error creating asset:", error);
    }
  };

  // ================= UPDATE STATUS =================

  const updateStatus = async () => {
    try {
      await axios.put(
        `http://localhost:8086/assets/${selectedEquipmentId}/status?status=${statusUpdate}`
      );
      fetchAssets();
      setSelectedEquipmentId("");
      alert("Status Updated Successfully");
    } catch (error) {
      console.error("Error updating status:", error);
    }
  };

  // ================= LOG MAINTENANCE =================

  const logMaintenance = async () => {
    try {
      await axios.post(
        "http://localhost:8086/assets/maintenance",
        maintenanceData
      );
      fetchMaintenanceLogs(maintenanceData.assetId);

      setMaintenanceData({
        assetId: "",
        description: "",
        maintenanceDate: "",
        cost: 0
      });
      alert("Maintenance Logged Successfully");
    } catch (error) {
      console.error("Error logging maintenance:", error);
    }
  };

  return (
    <>
      <Navbar />

      <div className="inventory-wrapper">
        <h1 className="page-title">Inventory Management</h1>

        {/* Tabs */}
        <div className="tabs-container">
          <div
  className={`tab ${activeTab === "view" ? "active" : ""}`}
  onClick={() => {
    setActiveTab("view");
    fetchAssets();
  }}
>
  View Assets
</div>

          <div className={`tab ${activeTab === "create" ? "active" : ""}`}
            onClick={() => setActiveTab("create")}>
            Create Asset
          </div>

          <div className={`tab ${activeTab === "update" ? "active" : ""}`}
            onClick={() => setActiveTab("update")}>
            Update Status
          </div>

          <div className={`tab ${activeTab === "log" ? "active" : ""}`}
            onClick={() => setActiveTab("log")}>
            Log Maintenance
          </div>

          <div className={`tab ${activeTab === "history" ? "active" : ""}`}
            onClick={() => setActiveTab("history")}>
            Maintenance History
          </div>
        </div>

        <div className="content-section">


          {/* VIEW ALL ASSETS */}
{activeTab === "view" && (
  <div className="form-section">

    <button onClick={fetchAssets}>
      Refresh Assets
    </button>

    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Name</th>
          <th>Category</th>
          <th>Quantity</th>
          <th>Status</th>
          <th>Purchase Date</th>
        </tr>
      </thead>

      <tbody>
        {equipment.map((item) => (
          <tr key={item.id}>
            <td>{item.id}</td>
            <td>{item.name}</td>
            <td>{item.category}</td>
            <td>{item.quantity}</td>
            <td>{item.status}</td>
            <td>{item.purchaseDate}</td>
          </tr>
        ))}
      </tbody>
    </table>

  </div>
)}


          {/* CREATE ASSET */}
          {activeTab === "create" && (
            <div className="form-section">

              <input
                type="text"
                name="name"
                placeholder="Equipment Name"
                value={newEquipment.name}
                onChange={handleChange}
              />

              <input
                type="text"
                name="category"
                placeholder="Category"
                value={newEquipment.category}
                onChange={handleChange}
              />

              <input
                type="number"
                name="quantity"
                placeholder="Quantity"
                value={newEquipment.quantity}
                onChange={handleChange}
              />

              <select
                name="status"
                value={newEquipment.status}
                onChange={handleChange}
              >
                <option value="Active">Active</option>
                <option value="Under Maintenance">Under Maintenance</option>
                <option value="Inactive">Inactive</option>
              </select>

              <input
                type="date"
                name="purchaseDate"
                value={newEquipment.purchaseDate}
                onChange={handleChange}
              />

              <button onClick={createEquipment}>
                Create Equipment
              </button>
            </div>
          )}

          {/* UPDATE STATUS */}
          {activeTab === "update" && (
            <div className="form-section">

              <select
                value={selectedEquipmentId}
                onChange={(e) => setSelectedEquipmentId(e.target.value)}
              >
                <option value="">Select Equipment</option>
                {equipment.map(item => (
                  <option key={item.id} value={item.id}>
                    {item.name}
                  </option>
                ))}
              </select>

              <select
                value={statusUpdate}
                onChange={(e) => setStatusUpdate(e.target.value)}
              >
                <option value="Active">Active</option>
                <option value="Under Maintenance">Under Maintenance</option>
                <option value="Inactive">Inactive</option>
              </select>

              <button onClick={updateStatus}>
                Update Status
              </button>
            </div>
          )}

          {/* LOG MAINTENANCE */}
          {activeTab === "log" && (
            <div className="form-section">

              <select
                value={maintenanceData.assetId}
                onChange={(e) =>
                  setMaintenanceData({
                    ...maintenanceData,
                    assetId: e.target.value
                  })
                }
              >
                <option value="">Select Equipment</option>
                {equipment.map(item => (
                  <option key={item.id} value={item.id}>
                    {item.name}
                  </option>
                ))}
              </select>

              <textarea
                placeholder="Maintenance Description"
                value={maintenanceData.description}
                onChange={(e) =>
                  setMaintenanceData({
                    ...maintenanceData,
                    description: e.target.value
                  })
                }
              />

              <input
                type="date"
                value={maintenanceData.maintenanceDate}
                onChange={(e) =>
                  setMaintenanceData({
                    ...maintenanceData,
                    maintenanceDate: e.target.value
                  })
                }
              />

              <input
                type="number"
                placeholder="Cost"
                value={maintenanceData.cost}
                onChange={(e) =>
                  setMaintenanceData({
                    ...maintenanceData,
                    cost: e.target.value
                  })
                }
              />

              <button onClick={logMaintenance}>
                Log Maintenance
              </button>
            </div>
          )}

          {/* MAINTENANCE HISTORY */}
          {activeTab === "history" && (
  <div className="form-section">

    <select
      value={selectedEquipmentId}
      onChange={(e) => {
        setSelectedEquipmentId(e.target.value);
        fetchMaintenanceLogs(e.target.value);
      }}
    >
      <option value="">Select Equipment</option>
      {equipment.map(item => (
        <option key={item.id} value={item.id}>
          {item.name}
        </option>
      ))}
    </select>

    <table>
      <thead>
        <tr>
          <th>Asset ID</th>
          <th>Description</th>
          <th>Date</th>
          <th>Cost</th>
        </tr>
      </thead>
      <tbody>
        {maintenanceLogs.map((log, index) => (
          <tr key={index}>
            <td>{log.assetId}</td>
            <td>{log.description}</td>
            <td>{log.maintenanceDate}</td>
            <td>{log.cost}</td>
          </tr>
        ))}
      </tbody>
    </table>

  </div>
)}


        </div>
      </div>
    </>
  );
};

export default Inventory;
