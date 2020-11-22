import React, { Component } from 'react'
import PlaylistService from '../services/PlaylistService';

class CreatePlaylistComponent extends Component {
    constructor(props) {
        super(props)
        const token = JSON.parse(localStorage.getItem('user'));
        this.state = {
            userId: { email: token.email }
        }
    }

    savePlaylist = (e) => {
        e.preventDefault();
        let playlist = { name: this.state.namePlaylist, user: this.state.userId, title: null };
        PlaylistService.createPlaylist(playlist).then(res => {
            this.props.history.push("/playlists");
        })
    }

    SavePlaylistHandler = (event) => {
        this.setState({ namePlaylist: event.target.value });
    }

    cancel() {
        this.props.history.push("/playlists");
    }

    render() {
        return (
            <form className="form-create-playlist" >
                <h3>Créer une Playlist</h3>
                <div className="form-group">
                    <label>Nom de la playlist</label>
                    <input placeholder="Nom de la playlist" name="playlist" className="form-control" value={this.state.playlist} onChange={this.SavePlaylistHandler} />
                </div>
                <button type="submit" className="btn btn-success btn-block" onClick={this.savePlaylist}>Créer</button>
                <button type="submit" className="btn btn-danger btn-block" onClick={this.cancel}>Annuler</button>
            </form>
        )
    }
}

export default CreatePlaylistComponent;