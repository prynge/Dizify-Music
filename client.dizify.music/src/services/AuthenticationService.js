import axios from 'axios';

const AUTH_API_BASE_URL = "http://localhost:8080";


class AuthenticationService {

    getUser() {
        return axios.get(AUTH_API_BASE_URL);
    }

    createAdmin(user) {
        var payload = { ...user }
        console.log(payload);
        return axios.post(AUTH_API_BASE_URL + '/admin/signup', payload)
    }

    loginAdmin(user) {
        var payload = {'password': user.password, 'email': user.username }
        return axios
            .post(AUTH_API_BASE_URL + '/admin/signin', payload)
            .then(response => {
                console.log(response.data);
                if (response.data.accessToken) {
                localStorage.setItem("user", JSON.stringify(response.data));
                }

            return response.data;
      });
    }

    signup(user) {
        var payload = { ...user, role: 'user'}
        return axios.post(AUTH_API_BASE_URL + '/register', payload);
    }

    login(user) {
      var payload = {'password': user.password, 'email': user.mail }
      return axios
            .post(AUTH_API_BASE_URL + '/authenticate', payload)
            .then(response => {
                console.log(response.data);
                if (response.data.accessToken) {
                localStorage.setItem("user", JSON.stringify(response.data));
                }

            return response.data;
      });
    }

    logout() {
        localStorage.removeItem("user");
    }

    getCurrentUser() {
        const token =JSON.parse(localStorage.getItem('user'));
        if (token != null) {
            const user = {email: token.email, lname: token.lname, fname: token.fname, roles: token.roles}
            return user;
        }
        return null;
    }

}

export default new AuthenticationService();