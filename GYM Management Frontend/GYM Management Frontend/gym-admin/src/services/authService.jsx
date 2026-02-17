import { jwtDecode } from "jwt-decode";

export const setToken = (token) => {
  localStorage.setItem("token", token);
};

export const getToken = () => {
  return localStorage.getItem("token");
};

export const removeToken = () => {
  localStorage.removeItem("token");
};

export const getDecodedToken = () => {
  const token = getToken();
  if (!token) return null;

  try {
    return jwtDecode(token);
  } catch {
    return null;
  }
};

export const getUserRole = () => {
  return getDecodedToken()?.role || null;
};

export const isAuthenticated = () => {
  const decoded = getDecodedToken();
  if (!decoded) return false;

  if (decoded.exp * 1000 < Date.now()) {
    removeToken();
    return false;
  }

  return true;
};
