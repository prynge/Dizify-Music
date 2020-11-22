import React, { Component } from "react";
import AlbumService from "../services/AlbumService";
import ArtistService from "../services/ArtistService";

class AddAlbumComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
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
        data.append('date', this.state.year)
        AlbumService.createAlbum(data).then(res => {
            this.props.history.push('/albums')
        })
    }

    saveYearHandler = (event) => {
        this.setState({ year: event.target.value });
    }

    saveNameHandler = (event) => {
        this.setState({ name: event.target.value });
    }

    handleArtistChange = (event) => {
        this.setState({ artist: event.target.value });
    }

    savePictureHandler = (event) => {
        this.setState({ picture: event.target.files[0] });
    }

    componentDidMount() {
        ArtistService.getArtist().then((res) => {
            this.setState({ artistes: res.data });
        });
        
    }

    render() {
        return (
            <form className="form-signup" >
                <h3>Ajouter un Album</h3>
                <div className="form-group">
                    <label>Nom</label>
                    <input type="text" name="name" className="form-control" placeholder="Name" value={this.state.name} onChange={this.saveNameHandler} />
                </div>
                <div className="form-group">
                    <label>Année</label>
                    <input type="number" name="year" className="form-control" placeholder="Année de publication" value={this.state.year} onChange={this.saveYearHandler} />
                </div>
                <div className="form-group">
                    <label>Artiste
                        <select className="form-control" value={this.state.artiste?.name} onChange={this.handleArtistChange}>
                        {
                            this.state.artistes?.map(
                                artiste =>
                                    <option value={artiste.id} >{artiste.name}</option>
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
        );
    }
}

export default AddAlbumComponent;