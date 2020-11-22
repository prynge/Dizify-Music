import React, { Component } from 'react';
import ArtistService from '../services/ArtistService';
import FavoriService from '../services/FavoriService';
import AuthService from '../services/AuthenticationService';

class ListArtistComponent extends Component {
    constructor(props) {
        super(props)
        const token = JSON.parse(localStorage.getItem('user'));
        this.state = {
            userId: { email: token.email },
            artists: [],
            favoris: [],
        }
        this.addFavori = this.addFavori.bind(this);
    }

    addFavori(elementArtist) {
        var artist = elementArtist
        var album = null
        var title = null
        FavoriService.createFavori(this.state.userId, artist, album, title).then(res =>
            window.location.reload()
        );
    }

    viewArtist(id) {
        this.props.history.push(`/artiste/${id}`);
    }

    addArtist() {
        this.props.history.push(`/admin/artistes`);
    }

    editArtist(id) {
        this.props.history.push(`/admin/artiste/${id}`);
    }

    deleteArtist(id) {
        ArtistService.deleteArtist(id);
        window.location.reload();
    }

    disableButton(id){
        let disabled= this.state.favoris.filter(fav => (fav.artist != null && fav.user.email === this.state.currentUser.email && fav.artist.id===id))
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
        ArtistService.getArtist().then((res) => {
            this.setState({ artists: res.data });
        });
        FavoriService.getFavori().then((res) => {
            console.log(res.data);
            this.setState({ favoris: res.data });
        });
    }

    render() {
        const {  showUserBoard, showAdminBoard } = this.state;
        return (
            <div>
                <h2 className="text-center">Liste d'artistes</h2>
                <div className="row">
                    {showAdminBoard && (
                    <button onClick={() => this.addArtist()} className="btn btn-info">Ajouter un artiste</button>)}
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Nom de l'artiste</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.artists.map(
                                    artist =>
                                        <tr key={artist.id}>
                                            <td>{artist.name}</td>
                                            <td>
                                                {showUserBoard && (
                                                <button disabled={this.disableButton(artist.id)} onClick={() => this.addFavori(artist)} className="btn btn-success">Ajouter aux favoris</button>)}
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.viewArtist(artist.id)} className="btn btn-info">Voir détails</button>
                                                {showAdminBoard && (
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.editArtist(artist.id)} className="btn btn-info">Modifier</button>)}
                                                {showAdminBoard && (
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.deleteArtist(artist.id)} className="btn btn-danger">Supprimer</button>)}
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

export default ListArtistComponent;