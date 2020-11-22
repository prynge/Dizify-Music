import React, { Component } from 'react';
import PlaylistService from '../services/PlaylistService';
import FavoriService from '../services/FavoriService';

class ListFavoriComponent extends Component {
    constructor(props) {
        super(props)
        const token = JSON.parse(localStorage.getItem('user'));
        this.state = {
            userId: { email: token.email },
            playlistId: this.props.match.params.id,
            playlist: null,
        }
        this.addFavori = this.addFavori.bind(this);
    }

    addFavori(elementTitle) {
        var artistId = null
        var albumId = null
        var titleId = elementTitle
        FavoriService.createFavori(this.state.userId, artistId, albumId, titleId);
    }

    componentDidMount() {
        PlaylistService.getPlaylistById(this.state.playlistId).then((res) => {
            this.setState({ playlist: res.data });
        });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Playlist {this.state.playlist?.name}</h2>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Nom du titre</th>
                                <th>Durée</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.playlist?.title.map(
                                    element =>
                                        <tr key={element.id}>
                                            <td>{element.name}</td>
                                            <td>{element.length}</td>
                                            <td>
                                                <button onClick={() => this.addFavori(element)} className="btn btn-success">Ajouter aux favoris</button>
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

export default ListFavoriComponent;