import axios from 'axios';
import {TOKEN} from "@/utils/constants.js";

const apiClient = axios.create({
    baseURL: import.meta.env.VITE_API_BASE_URL, 
    headers: {
        'Content-Type': 'application/json',
    },
});

apiClient.interceptors.request.use(
    (config) => {
        const token =  sessionStorage.getItem(TOKEN); 
        
        if (token) {
            config.headers.Authorization = `Bearer ${token}`;
        }

        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

apiClient.interceptors.response.use(
    (response) => response,
    (error) => {
        if (error.response && error.response.status === 401) { 
            console.error('Unauthorized! Redirecting to login...');
            window.location.href = '/auth/login'
        }
        return Promise.reject(error);
    }
);

export default apiClient;
