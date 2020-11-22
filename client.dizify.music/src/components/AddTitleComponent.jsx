import React, { Component } from "react";
import ArtistService from "../services/ArtistService";
import AlbumService from "../services/AlbumService";
import TitleService from "../services/TitleService";

class AddTitleComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
        }
    }

    saveTitle = (e) => {
        e.preventDefault();
        
        let title = { name: this.state.name, length: this.state.length, artistes: this.state.artistes, albums: this.state.albums};
        TitleService.createTitle(title).then(res => {
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
        this.setState({ artistes: event.target.value });
    }

    saveAlbumHandler = (event) => {
        this.setState({ albums: event.target.value });
    }


    componentDidMount() {
        ArtistService.getArtist().then((res) => {
            this.setState({ artistes: res.data });
        });
        AlbumService.getAlbum().then((res) => {
            this.setState({ albums: res.data });
        });
        
    }

    render() {
        return (
            <form className="form-signup" >
                <h3>Ajouter un titre</h3>
                <div className="form-group">
                    <label>Nom</label>
                    <input type="text" name="name" className="form-control" placeholder="Name" value={this.state.name} onChange={this.saveNameHandler} />
                </div>
                <div className="form-group">
                    <label>Durée</label>
                    <input type="number" name="length" className="form-control" placeholder="La durée en secondes" value={this.state.lenth} onChange={this.saveLengthHandler} />
                </div>
                <div className="form-group">
                <label>Artiste
                        <select className="form-control" value={this.state.artiste?.name} onChange={this.handleArtistChange}>
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
                        <select className="form-control" value={this.state.albums} onChange={this.handleAlbumChange}>
                        {
                            this.state.albums?.map(
                                album =>
                                    <option value={album} >{album.name}</option>
                            )
                        }
                        </select>
                    </label>
                </div>
                <button type="submit" className="btn btn-primary btn-block" onClick={this.saveTitle}>Ajouter</button>
            </form>
        );
    }
}

export default AddTitleComponent;