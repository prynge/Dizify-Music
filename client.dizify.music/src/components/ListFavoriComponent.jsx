import React, { Component } from 'react';
import FavoriService from '../services/FavoriService';
import AuthService from '../services/AuthenticationService';

class ListFavoriComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            currentUser: undefined,
            artistFavoris: [],
            albumFavoris: [],
            titleFavoris: [],
        }
    }

    deleteFavori(id) {
        FavoriService.deleteFavori(id);
        window.location.reload();
    }

    viewArtist(id) {
        this.props.history.push(`/artiste/${id}`);
    }

    viewAlbum(id) {
        this.props.history.push(`/album/${id}`);
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
        FavoriService.getFavori().then((res) => {
            console.log(res.data);
            this.setState({ favoris: res.data });
        });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Liste des favoris</h2>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Artistes favoris</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.favoris?.filter(fav => (fav.artist != null && fav.user.email === this.state.currentUser.email)).map(
                                    favori =>
                                        <tr key={favori.id}>
                                            <td>{favori.artist.name}</td>
                                            <td>
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.deleteFavori(favori.id)} className="btn btn-danger">Supprimer</button>
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.viewArtist(favori.artist.id)} className="btn btn-info">Voir les albums</button>
                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Albums favoris</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.favoris?.filter(fav => (fav.album != null && fav.user.email === this.state.currentUser.email)).map(
                                    favori =>
                                        <tr key={favori.id}>
                                            <td>{favori.album.name}</td>
                                            <td>                                                
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.deleteFavori(favori.id)} className="btn btn-danger">Supprimer</button>
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.viewAlbum(favori.album.id)} className="btn btn-info">Voir les titres</button>
                                            </td>
                                        </tr>
                                )
                            }
                        </tbody>
                    </table>
                </div>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Titres favoris</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.favoris?.filter(fav => (fav.title != null && fav.user.email === this.state.currentUser.email)).map(
                                    favori =>
                                        <tr key={favori.id}>
                                            <td>{favori.title.name}</td>
                                            <td>
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.deleteFavori(favori.id)} className="btn btn-danger">Supprimer</button>
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