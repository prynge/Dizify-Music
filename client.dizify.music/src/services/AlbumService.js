import axios from 'axios';
import { authHeader } from './auth-header';

const ALBUM_API_BASE_URL = "http://localhost:8080/api/album";
const REQUEST_HEADER = {
    headers: authHeader()
}

class AlbumService {

    getAlbum() {
        return axios.get(ALBUM_API_BASE_URL, REQUEST_HEADER);
    }

    createAlbum(nameAlbum, artistId, media) {
        var payload = { 'name': nameAlbum, 'artist': artistId, 'picture': media }
        return axios.post(ALBUM_API_BASE_URL, payload, REQUEST_HEADER)
    }

    getAlbumById(albumId) {
        return axios.get(ALBUM_API_BASE_URL + '/' + albumId, REQUEST_HEADER);
    }

    getTitleByAlbum(albumId) {
        return axios.get(ALBUM_API_BASE_URL + '/' + albumId + '/title', REQUEST_HEADER);
    }

    updateAlbum(album, albumId) {
        return axios.put(ALBUM_API_BASE_URL + '/' + albumId, album, REQUEST_HEADER);
    }

    deleteAlbum(albumId) {
        return axios.delete(ALBUM_API_BASE_URL + '/' + albumId, REQUEST_HEADER);
    }
}

export default new AlbumService();