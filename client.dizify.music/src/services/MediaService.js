import axios from 'axios';

const AVATAR_API_BASE_URL = "https://i.pravatar.cc/200";
const COVER_API_BASE_URL = "https://picsum.photos/200";

class MediaService {

    getAvatar() {
        return axios.get(AVATAR_API_BASE_URL);
    }

    getCover() {
        return axios.get(COVER_API_BASE_URL);
    }
}

export default new MediaService();