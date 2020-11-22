import axios from 'axios';
import { authHeader } from './auth-header';

const USER_API_BASE_URL = "http://localhost:8080/api/user";
const REQUEST_HEADER = {
    headers: authHeader()
}

class UserService {

    getUser() {
        return axios.get(USER_API_BASE_URL, REQUEST_HEADER);
    }

    createUser(firstName, lastName, password, mail) {
        var payload = { 'fname': firstName, 'lname': lastName, 'password': password, 'email': mail }
        return axios.post(USER_API_BASE_URL, payload, REQUEST_HEADER)
    }

    getUserById(userId) {
        return axios.get(USER_API_BASE_URL + '/' + userId, REQUEST_HEADER);
    }

    getFavoriByUser(userId) {
        return axios.get(USER_API_BASE_URL + '/' + userId + '/favoris', REQUEST_HEADER);
    }

    getPlaylistByUser(userId) {
        return axios.get(USER_API_BASE_URL + '/' + userId + '/playlist', REQUEST_HEADER);
    }

    updateUser(user, userId) {
        return axios.put(USER_API_BASE_URL + '/' + userId, user, REQUEST_HEADER);
    }

    deleteUser(userId) {
        return axios.delete(USER_API_BASE_URL + '/' + userId, REQUEST_HEADER);
    }
}

export default new UserService();