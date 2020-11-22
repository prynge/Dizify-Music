import React, { Component } from 'react';
import {DropdownButton, Dropdown}  from 'react-bootstrap'
import AlbumService from '../services/AlbumService';
import FavoriService from '../services/FavoriService';
import PlaylistService from '../services/PlaylistService';
import AuthService from '../services/AuthenticationService';

class AlbumComponent extends Component {
    constructor(props) {
        super(props)
        const token = JSON.parse(localStorage.getItem('user'));
        this.state = {
            userId: { email: token.email },
            albumId: this.props.match.params.id,
            album: {},
            artist: {},
            cover: {},
            titles: [],
            mylist: [],
            favoris: [],
            currentUser: undefined,
            showUserBoard: false,
            showAdminBoard: false,
            menu: false
        };
        this.toggleMenu = this.toggleMenu.bind(this);
        this.addFavori = this.addFavori.bind(this);
        this.addPlaylist = this.addPlaylist.bind(this);
    }

    addFavori(elementTitle) {
        var artistId = null
        var albumId = null
        var titleId = elementTitle
        FavoriService.createFavori(this.state.userId, artistId, albumId, titleId);
    }

    addToPlaylist(listId, elementTitle) {
        var titleId = elementTitle
        listId.title.push(titleId)
        PlaylistService.addToPlaylist(listId, this.state.userId, listId.title);
    }

    addPlaylist(elementTitle) {
        var titleId = elementTitle
        PlaylistService.createPlaylist(this.state.newPlaylist, this.state.userId, titleId);
    }

    SaveNewPlaylistHandler = (event) => {
        this.setState({ newPlaylist: event.target.value });
    }

    toggleMenu(){
        
        this.setState({ menu: !this.state.menu })
    }

    disableButton(id){
        let disabled= this.state.favoris.filter(fav => (fav.title != null && fav.user.email === this.state.currentUser.email && fav.title.id===id))
        return disabled[0]?.id != null
    }

    secondsToHms(d) {
        d = Number(d);
        var h = Math.floor(d / 3600);
        var m = Math.floor(d % 3600 / 60);
        var s = Math.floor(d % 3600 % 60);
    
        var hDisplay = h > 0 ? h + ":" : "";
        var mDisplay = m + ":";
        var sDisplay = s >= 10 ? s  : "0"+s;
        return hDisplay + mDisplay + sDisplay; 
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
        AlbumService.getAlbumById(this.state.albumId).then(async (res) => {
            await this.setState({ album: res.data });
        });

        AlbumService.getTitleByAlbum(this.state.albumId).then((res) => {
            this.setState({ titles: res.data });
        });

        PlaylistService.getPlaylist().then((res) => {
            const playlist = res.data.filter(list => (list.user.email === this.state.currentUser.email));
            this.setState({ mylist: playlist });
            console.log(this.state.mylist);
        });
        FavoriService.getFavori().then((res) => {
            console.log(res.data);
            this.setState({ favoris: res.data });
        });
    }

    render() {
        const { showUserBoard, showAdminBoard } = this.state;
        const show = (this.state.menu) ? "show" : "" ;
        return (
            <div>
                <h2 className="text-center">Morceaux pour {this.state.album.name} de {this.state.album.artist?.name}</h2>
                <h4 className="text-center">{this.state.album.date}</h4>
                <img src={this.state.album.picture} width="200" height="200" className="d-inline-block align-top mr-3" alt="" />
                <div className="row">
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Nom du titre</th>
                                <th>Durée</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.titles.map(
                                    title =>
                                        <tr key={title.id}>
                                            <td>{title.name}</td>
                                            <td>{this.secondsToHms(title.length)}</td>
                                            <td>
                                                
                                                {showUserBoard && (
                                                <div className="row">
                                                    <button disabled={this.disableButton(title.id)} onClick={() => this.addFavori(title)} className="btn btn-success col-6">Ajouter aux favoris</button>
                                                    <DropdownButton
                                                        as="Bouttons"
                                                        key={title.id}
                                                        id={title.id}
                                                        drop="right"
                                                        variant="secondary"
                                                        title={` Enregistrer dans une Playlist `}
                                                    >
                                                        <Dropdown.Header eventKey="1">
                                                            <div className="input-group mb-3">
                                                                <div className="input-group-prepend">
                                                                    <button style={{ marginLeft: "10px" }} onClick={() => this.addPlaylist(title)} className="btn btn-info">Enregistrer</button>
                                                                </div>
                                                                <input type="text" className="form-control" placeholder="Nouvelle playlist" aria-label="" aria-describedby="basic-addon1" name="playlist" value={this.state.newplaylist} onChange={this.SaveNewPlaylistHandler} />
                                                            </div>
                                                        </Dropdown.Header>
                                                        <Dropdown.Divider />
                                                        {this.state.mylist.map(list =>
                                                        <Dropdown.Item eventKey="0" onClick={() => {this.addToPlaylist(list, title); this.toggleMenu()}}>{list.name}</Dropdown.Item>)}
                                                    </DropdownButton>
                                                </div>)}
                                                
                                                {showAdminBoard && (
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.editTitle(title.id)} className="btn btn-info">Modifier</button>)}
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

export default AlbumComponent;