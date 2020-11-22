import React, { Component } from 'react';
import PlaylistService from '../services/PlaylistService.js';
import AuthService from '../services/AuthenticationService';

class ListPlaylistComponent extends Component {
    constructor(props) {
        super(props)
        
        this.state = {
            playlists: []
        }
    }

    addPlaylist() {
        this.props.history.push("/create-playlist");
    }

    deletePlaylist(id) {
        PlaylistService.deletePlaylist(id);
        window.location.reload()
    }

    viewPlaylist(id) {
        this.props.history.push(`/playlist/${id}`);
    }

    componentDidMount() {
        const user = AuthService.getCurrentUser();
    
        if (user) {
          this.setState({
            currentUser: user,
            showUserBoard: user.roles.includes("ROLE_USER"),
            showAdminBoard: user.roles.includes("ROLE_ADMIN"),
          });
        }
        PlaylistService.getPlaylist().then((res) => {
            this.setState({ playlists: res.data });
        });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Liste des playlists</h2>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Nom de la playlist</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.playlists?.filter(list => (list.user.email === this.state.currentUser.email)).map(
                                    playlist =>
                                        <tr key={playlist.id}>
                                            <td>{playlist.name}</td>
                                            <td>
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.deletePlaylist(playlist.id)} className="btn btn-danger">Supprimer</button>
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.viewPlaylist(playlist.id)} className="btn btn-info">Voir les titres</button>
                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
            </div>
        );
    }
}

export default ListPlaylistComponent;