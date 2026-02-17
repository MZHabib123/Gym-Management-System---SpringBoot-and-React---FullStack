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
