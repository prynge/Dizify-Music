import React, { Component } from "react";
import TitleService from "../services/TitleService";
import ArtistService from "../services/ArtistService";
import AlbumService from "../services/AlbumService";
import AuthService from '../services/AuthenticationService';

class AddTitleComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            titleId: this.props.match.params.id,
            title: {},
            album: {},
            artist: {},
        }
    }

    saveTitle = (e) => {
        e.preventDefault();
        
        let title = { name: this.state.name, length: this.state.length, artistes: this.state.artistes, albums: this.state.albums};
        TitleService.updateTitle(title, this.state.titleId).then(res => {
            this.props.history.push('/titles')
        })
    }

    saveNameHandler = (event) => {
        this.setState({ name: event.target.value });
    }

    saveLengthHandler = (event) => {
        this.setState({ length: event.target.value });
    }

    saveArtistHandler = (event) => {
        this.setState({ artiste: event.target.value });
    }

    saveAlbumHandler = (event) => {
        this.setState({ album: event.target.value });
    }


    componentDidMount() {
        const user = AuthService.getCurrentUser();
    
        if (user) {
          this.setState({
            showAdminBoard: user.roles.includes("ROLE_ADMIN"),
          });
        }
        TitleService.getTitleById(this.state.titleId).then((res) => {
            this.setState({ title: res.data });
        });
        ArtistService.getArtist().then((res) => {
            this.setState({ artistes: res.data });
        });
        AlbumService.getAlbum().then((res) => {
            this.setState({ albums: res.data });
        });
    }

    render() {
        return (
            <div>
                <h3 className="text-center" >Modifier un titre</h3>
                <div className="row">
                    <div className="col-4">
                        <h3 className="text-center">Titre : {this.state.title.name}</h3>
                        <h3 className="text-center">Nom de l'album : {this.state.title.album?.name}</h3>
                        <h3 className="text-center">Nom de l'artiste : {this.state.title.artist?.name}</h3>
                    </div>
                    <div className="col-8">
                        <form className="form-signup" >
                            <p>Veuillez remplir les champs que vous ne voulez pas modifier avec les valeurs actuelles.</p>
                            <div className="form-group">
                                <label>Nom</label>
                                <input type="text" name="name" className="form-control" placeholder="Name" value={this.state.name} onChange={this.saveNameHandler} />
                            </div>
                            <div className="form-group">
                                <label>Durée</label>
                                <input type="number" name="length" className="form-control" placeholder="Ajouter une image" value={this.state.length} onChange={this.saveLengthHandler} />
                            </div>
                            <div className="form-group">
                            <label>Artiste
                                    <select className="form-control" value={this.state.artiste} onChange={this.handleArtistChange}>
                                    {
                                        this.state.artistes?.map(
                                            artiste =>
                                                <option value={artiste} >{artiste.name}</option>
                                        )
                                    }
                                    </select>
                                </label>
                            </div>
                            <div className="form-group">
                            <label>Album
                                    <select className="form-control" value={this.state.album} onChange={this.handleAlbumChange}>
                                    {
                                        this.state.albums?.map(
                                            album =>
                                                <option value={album} >{album.name}</option>
                                        )
                                    }
                                    </select>
                                </label>
                            </div>
                            <button type="submit" className="btn btn-primary btn-block" onClick={this.saveTitle}>Modifier</button>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default AddTitleComponent;