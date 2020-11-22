import axios from 'axios';
import { authHeader } from './auth-header';

const TITLE_API_BASE_URL = "http://localhost:8080/api/title";
const REQUEST_HEADER = {
    headers: authHeader()
}

class TitleService {

    getTitle() {
        return axios.get(TITLE_API_BASE_URL, REQUEST_HEADER);
    }

    createTitle(nameTitle, length, albumId, artistId) {
        var payload = { 'name': nameTitle, 'length': length, 'album': albumId, 'artist': artistId }
        return axios.post(TITLE_API_BASE_URL, payload, REQUEST_HEADER)
    }

    getTitleById(titleId) {
        return axios.get(TITLE_API_BASE_URL + '/' + titleId, REQUEST_HEADER);
    }
    
    getTitleByAlbum(albumId) {
        return axios.get(TITLE_API_BASE_URL + '/' + albumId, REQUEST_HEADER);
    }

    updateTitle(title, titleId) {
        return axios.put(TITLE_API_BASE_URL + '/' + titleId, title, REQUEST_HEADER);
    }

    deleteTitle(titleId) {
        return axios.delete(TITLE_API_BASE_URL + '/' + titleId, REQUEST_HEADER);
    }
}

export default new TitleService();