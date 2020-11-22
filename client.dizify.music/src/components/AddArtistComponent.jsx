import React, { Component } from "react";
import ArtistService from "../services/ArtistService";

class AddArtistComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            picture: null,
            name: null
        }
    }

    saveArtist = (e) => {
        e.preventDefault();
        const data = new FormData() 
        data.append('file', this.state.picture)
        data.append('artist', this.state.name)
        ArtistService.createArtist(data).then(res => {
            this.props.history.push('/artistes')
        })
    }

    saveNameHandler = (event) => {
        this.setState({ name: event.target.value });
    }

    savePictureHandler = (event) => {
        this.setState({ picture: event.target.files[0] });
    }

    render() {
        return (
            <form className="form-signup" >
                <h3>Ajouter un artiste</h3>
                <div className="form-group">
                    <label>Nom</label>
                    <input type="text" name="name" className="form-control" placeholder="Name" value={this.state.name} onChange={this.saveNameHandler} />
                </div>
                <div className="form-group">
                    <label>Picture</label>
                    <input type="file" name="picture" className="form-control" placeholder="Add a picture" onChange={this.savePictureHandler} />
                </div>
                <button type="submit" className="btn btn-primary btn-block" onClick={this.saveArtist}>Ajouter</button>
            </form>
        );
    }
}

export default AddArtistComponent;