import axios from 'axios';
import { authHeader } from './auth-header';

const PLAYLIST_API_BASE_URL = "http://localhost:8080/api/playlist";
const REQUEST_HEADER = {
    headers: authHeader()
}

class PlaylistService {

    getPlaylist() {
        return axios.get(PLAYLIST_API_BASE_URL, REQUEST_HEADER);
    }

    createPlaylist(namePlaylist, userId, titleId) {
        var payload = { 'name': namePlaylist, 'user': userId, 'title': [titleId] }
        return axios.post(PLAYLIST_API_BASE_URL, payload, REQUEST_HEADER)
    }

    addToPlaylist(playlist, userId, titleId) {
        var payload = { ...playlist, 'user': userId, 'title': titleId }
        return axios.put(PLAYLIST_API_BASE_URL + '/' + playlist.id, payload, REQUEST_HEADER)
    }

    getPlaylistById(playlistId) {
        return axios.get(PLAYLIST_API_BASE_URL + '/' + playlistId, REQUEST_HEADER);
    }

    updatePlaylist(playlist, playlistId) {
        return axios.put(PLAYLIST_API_BASE_URL + '/' + playlistId, playlist, REQUEST_HEADER);
    }

    deletePlaylist(playlistId) {
        return axios.delete(PLAYLIST_API_BASE_URL + '/' + playlistId, REQUEST_HEADER);
    }
}

export default new PlaylistService();