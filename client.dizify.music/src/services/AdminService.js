import axios from 'axios';

const ADMIN_API_BASE_URL = "http://localhost:8080/api/admins";

class AdminService {

    getAdmin() {
        return axios.get(ADMIN_API_BASE_URL);
    }

    createAdmin(admin) {
        return axios.post(ADMIN_API_BASE_URL, admin)
    }

    getAdmin(adminId) {
        return axios.get(ADMIN_API_BASE_URL + '/' + adminId);
    }

    updateAdmin(admin, adminId) {
        return axios.put(ADMIN_API_BASE_URL + '/' + adminId, admin);
    }

    deleteAdmin(adminId) {
        return axios.delete(ADMIN_API_BASE_URL + '/' + adminId);
    }
}

export default new AdminService();