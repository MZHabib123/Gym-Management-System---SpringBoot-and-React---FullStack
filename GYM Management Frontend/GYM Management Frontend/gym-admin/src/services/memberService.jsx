import axios from "axios";

const BASE_URL = "http://localhost:8081/members";

export const getMembers = async () => {
  return await axios.get(BASE_URL);
};

export const addMember = async (memberData) => {
  return await axios.post(BASE_URL, memberData);
};


const API_URL = "http://localhost:8081/members";

export const updateMember = (id, data) => {
  return axios.put(`${API_URL}/${id}`, data);
};

export const deleteMember = (id) => {
  return axios.delete(`${API_URL}/${id}`);
};