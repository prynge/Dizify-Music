import React, { Component } from "react";
import ArtistService from "../services/ArtistService";

class EditArtistComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            artistId: this.props.match.params.id,
            artist: {},
            name: null,
            picture: null
        }
    }

    saveArtist = (e) => {
        e.preventDefault();
        const data = new FormData() 
        data.append('file', this.state.picture)
        data.append('artist', this.state.name)
        ArtistService.updateArtist(data, this.state.artistId).then(res => {
            this.props.history.push('/artistes')
        })
    }

    saveNameHandler = (event) => {
        this.setState({ name: event.target.value });
    }

    savePictureHandler = (event) => {
        this.setState({ picture: event.target.value });
    }

    componentDidMount() {
        ArtistService.getArtistById(this.state.artistId).then((res) => {
            this.setState({ artist: res.data });
        });
        
    }

    render() {
        return (
            <div>
                <h3>Modifier les informations de l'artiste</h3>
                <div className="row">
                    <div className="col-4">
                    
                        <h3 className="text-center">Nom de l'artiste : {this.state.artist.name}</h3>
                        <img src={this.state.artist.picture} width="200" height="200" className="d-inline-block align-top mr-3" alt="" />
                    

                    </div>
                    <div className="col-8">
                        <form className="form-signup" >
                            <p>Veuillez remplir les champs que vous ne voulez pas modifier avec les valeurs actuelles.</p>
                            <div className="form-group">
                                <label>Nom</label>
                                <input type="text" name="name" className="form-control" placeholder="Name" value={this.state.name} onChange={this.saveNameHandler} />
                            </div>
                            <div className="form-group">
                                <label>Picture</label>
                                <input type="file" name="picture" className="form-control" placeholder="Ajouter un image" value={this.state.picture} onChange={this.savePictureHandler} />
                            </div>
                            <button type="submit" className="btn btn-primary btn-block" onClick={this.saveArtist}>Ajouter</button>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default EditArtistComponent;