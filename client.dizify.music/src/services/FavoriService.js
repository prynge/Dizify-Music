import axios from 'axios';
import { authHeader } from './auth-header';

const FAVORI_API_BASE_URL = "http://localhost:8080/api/fav";
const REQUEST_HEADER = {
    headers: authHeader()
}

class FavoriService {

    getFavori() {
        return axios.get(FAVORI_API_BASE_URL, REQUEST_HEADER);
    }

    createFavori(userId, artistId, albumId, titleId) {
        var payload = { 'user': userId, 'artist': artistId, 'album': albumId, 'title': titleId }
        return axios.post(FAVORI_API_BASE_URL, payload, REQUEST_HEADER)
    }

    getFavoriById(favoriId) {
        return axios.get(FAVORI_API_BASE_URL + '/' + favoriId, REQUEST_HEADER);
    }

    getFavoriByUser(userId) {
        return axios.get(FAVORI_API_BASE_URL + '/' + userId, REQUEST_HEADER);
    }

    updateFavori(favori, favoriId) {
        return axios.put(FAVORI_API_BASE_URL + '/' + favoriId, favori, REQUEST_HEADER);
    }

    deleteFavori(favoriId) {
        return axios.delete(FAVORI_API_BASE_URL + '/' + favoriId, REQUEST_HEADER);
    }
}

export default new FavoriService();