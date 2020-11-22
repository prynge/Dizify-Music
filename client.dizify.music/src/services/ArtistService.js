import axios from 'axios';
import { authHeader } from './auth-header';

const ARTIST_API_BASE_URL = "http://localhost:8080/api/artist";
const REQUEST_HEADER = {
    headers: authHeader()
}

class ArtistService {

    getArtist() {
        return axios.get(ARTIST_API_BASE_URL, REQUEST_HEADER);
    }

    createArtist(artist) {
        var payload = { ...artist }
        console.log(artist);
        console.log(payload);
        return axios.post(ARTIST_API_BASE_URL, artist, REQUEST_HEADER)
    }

    getArtistById(artistId) {
        return axios.get(ARTIST_API_BASE_URL + '/' + artistId, REQUEST_HEADER);
    }

    getAlbumByArtist(artistId) {
        return axios.get(ARTIST_API_BASE_URL + '/' + artistId + '/albums', REQUEST_HEADER);
    }

    getTitleByArtist(artistId) {
        return axios.get(ARTIST_API_BASE_URL + '/' + artistId + '/title', REQUEST_HEADER);
    }

    updateArtist(artist, artistId) {
        return axios.put(ARTIST_API_BASE_URL + '/' + artistId, artist, REQUEST_HEADER);
    }

    deleteArtist(artistId) {
        return axios.delete(ARTIST_API_BASE_URL + '/' + artistId, REQUEST_HEADER);
    }
}

export default new ArtistService();