import React, { Component } from 'react';
import ArtisteService from '../services/ArtistService';
import AlbumService from '../services/AlbumService';

class HomeComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
            artistes: [],
            randomArtist: [],
            albums: [],
            randomAlbum: [],
            item: null
        }
    }
    
    shuffle(array) {
        array.sort(() => Math.random() - 0.5);
      }

    componentDidMount() {
        ArtisteService.getArtist().then(async (res) => {
            await this.setState({ artistes: res.data });

            this.shuffle(this.state.artistes);
            this.setState({ randomArtist : this.state.artistes.slice(0,3)});
        });
        AlbumService.getAlbum().then(async (res) => {
            await this.setState({ albums: res.data });
            this.shuffle(this.state.albums);
            this.setState({ randomAlbum : this.state.albums.slice(0,3)});
        });
    }

    render() {
        return (
            <div>
                <h2 className="text-center">Bienvenue sur Dizify Music</h2>
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Nom de l'artiste</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.randomArtist.map(
                                    artiste =>
                                        <tr key={artiste.id}>
                                            <td>{artiste.name}</td>
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
                                <th>Nom de l'album</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.randomAlbum.map(
                                    album =>
                                        <tr key={album.id}>
                                            <td>{album.name}</td>
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

export default HomeComponent;