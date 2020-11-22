import React, { Component } from "react";
import AlbumService from "../services/AlbumService";
import ArtistService from "../services/ArtistService";

class EditAlbumComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            albumId: this.props.match.params.id,
            album: {},
            artistes: [],
            picture: null,
            name: null,
            date: null,
            artist: null
        }
    }

    saveAlbum = (e) => {
        e.preventDefault();
        const data = new FormData() 
        data.append('file', this.state.picture)
        data.append('album', this.state.name)
        data.append('artist', this.state.artist)
        data.append('date', this.state.date)
        AlbumService.updateAlbum(data, this.state.albumId).then(res => {
            this.props.history.push('/albums')
        })
    }

    saveNameHandler = (event) => {
        this.setState({ name: event.target.value });
    }

    saveYearHandler = (event) => {
        this.setState({ date: event.target.value });
    }

    saveArtistHandler = (event) => {
        this.setState({ artist: event.target.value });
    }

    savePictureHandler = (event) => {
        this.setState({ picture: event.target.files[0] });
    }

    componentDidMount() {
        AlbumService.getAlbumById(this.state.albumId).then((res) => {
            console.log(res.data);
            this.setState({ album: res.data });
        });
        ArtistService.getArtist().then((res) => {
            this.setState({ artistes: res.data });
        });
        
    }

    render() {
        return (
            <div>
                <h3 className="text-center" >Modifier un Album</h3>
                <div className="row">
                    <div className="col-4">
                    
                        <h3>Nom de l'album : {this.state.album.name}</h3>
                        <h3>Nom de l'artiste : {this.state.album.artist?.name}</h3>
                        <h4>Année de publication : {this.state.album.date}</h4>
                        <img src={this.state.album.picture} width="200" height="200" className="d-inline-block align-top mr-3" alt="" />
                    

                    </div>
                    <div className="col-8">
                        <form className="form-signup" >
                            <p>Veuillez remplir les champs que vous ne voulez pas modifier avec les valeurs actuelles.</p>
                            <div className="form-group">
                                <label>Nom</label>
                                <input type="text" name="name" className="form-control" placeholder="Name" value={this.state.name} onChange={this.saveNameHandler} />
                            </div>
                            <div className="form-group">
                                <label>Année</label>
                                <input type="number" name="year" className="form-control" placeholder="Année de publication de l'album" value={this.state.date} onChange={this.saveYearHandler} />
                            </div>
                            <div className="form-group">
                                <label>Artiste
                                    <select className="form-control" value={this.state.artist} onChange={this.handleArtistChange}>
                                    {
                                        this.state.artistes?.map(
                                            artiste =>
                                                <option key={artiste.id} value={artiste.id} >{artiste.name}</option>
                                        )
                                    }
                                    </select>
                                </label>
                            </div>
                            <div className="form-group">
                                <label>Picture</label>
                                <input type="file" name="picture" className="form-control" placeholder="Ajouter une image" value={this.state.picture} onChange={this.savePictureHandler} />
                            </div>
                            <button type="submit" className="btn btn-primary btn-block" onClick={this.saveAlbum}>Modifier</button>
                        </form>
                    </div>
            
                </div>
            </div>
        );
    }
}

export default EditAlbumComponent;