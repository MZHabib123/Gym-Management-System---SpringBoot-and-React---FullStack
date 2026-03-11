import axios from "axios";

const API_URL = "http://localhost:8084/trainers";

export const getTrainers = () => {
  return axios.get(API_URL);
};

export const addTrainer = (data) => {
  return axios.post(API_URL, data);
};

export const deleteTrainerById = (id) => {
  return axios.delete(`${API_URL}/${id}`);
};

// Assign a member to a trainer (backend endpoint on a different port)
export const assignTrainer = (trainerId, memberId) => {
  const url = "http://localhost:8080/trainers/assign";
  return axios.post(url, { trainerId, memberId });
};
