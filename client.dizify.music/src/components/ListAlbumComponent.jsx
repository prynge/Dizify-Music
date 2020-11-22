import React, { Component } from 'react';
import AlbumService from '../services/AlbumService';
import FavoriService from '../services/FavoriService';
import AuthService from '../services/AuthenticationService';

class ListAlbumComponent extends Component {
    constructor(props) {
        super(props)
        const token = JSON.parse(localStorage.getItem('user'));
        this.state = {
            userId: { email: token.email },
            albums: [],
            disabled: [],
            artistFavoris: [],
            albumFavoris: [],
            titleFavoris: [],
            favoris: []
        }
        this.addFavori = this.addFavori.bind(this);
    }

    addFavori(elementAlbum) {
        var artist = null
        var album = elementAlbum
        var title = null
        FavoriService.createFavori(this.state.userId, artist, album, title).then(res =>
            window.location.reload()
        );
    }

    viewAlbum(id) {
        this.props.history.push(`/album/${id}`);
    }

    addAlbum() {
        this.props.history.push(`/admin/albums`);
    }

    editAlbum(id) {
        this.props.history.push(`/admin/album/${id}`);
    }

    deleteAlbum(id) {
        AlbumService.deleteAlbum(id);
        window.location.reload();
    }

    disableButton(id){
        let disabled= this.state.favoris.filter(fav => (fav.album != null && fav.user.email === this.state.currentUser.email && fav.album.id===id))
        return disabled[0]?.id != null
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
        AlbumService.getAlbum().then((res) => {
            this.setState({ albums: res.data });
        });

        FavoriService.getFavori().then((res) => {
            console.log(res.data);
            this.setState({ 
                favoris: res.data,
                albumFavoris: this.state.favoris.filter(fav => (fav.album != null && fav.user.email === this.state.currentUser.email)) });
        });

        this.setState({
            albumFavoris: this.state.favoris.filter(fav => (fav.album != null && fav.user.email === this.state.currentUser.email))
        })
    }

    render() {
        
        const { showUserBoard, showAdminBoard } = this.state;
        return (
            <div>
                <h2 className="text-center">Liste d'albums</h2>
                <div className="row">
                    {showAdminBoard && (
                    <button onClick={() => this.addAlbum()} className="btn btn-info">Ajouter un album</button>)}
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Nom de l'album</th>
                                <th>Artiste(s)</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.albums.map(
                                    album =>
                                        <tr key={album.id}>
                                            <td>{album.name}</td>
                                            <td>{album.artist.name}</td>
                                            <td>
                                                {showUserBoard && (
                                                <button disabled={this.disableButton(album.id)} onClick={() => this.addFavori(album)} className="btn btn-success">Ajouter aux favoris</button>)}
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.viewAlbum(album.id)} className="btn btn-info">Voir les titres</button>
                                                {showAdminBoard && (
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.editAlbum(album.id)} className="btn btn-info">Modifier</button>)}
                                                {showAdminBoard && (
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.deleteAlbum(album.id)} className="btn btn-danger">Supprimer</button>)}
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

export default ListAlbumComponent;