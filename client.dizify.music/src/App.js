import './App.css';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import PrivateRoute from './PrivateRoute';
import LoginComponent from "./components/LoginComponent";
import SignupComponent from "./components/SignupComponent";
import HomeComponent from './components/HomeComponent';
import ListArtistComponent from './components/ListArtistComponent';
import ArtistComponent from './components/ArtistComponent';
import ListAlbumComponent from './components/ListAlbumComponent';
import AlbumComponent from './components/AlbumComponent';
import ListPlaylistComponent from './components/ListPlaylistComponent';
import CreatePlaylistComponent from './components/CreatePlaylistComponent';
import PlaylistComponent from './components/PlaylistComponent';
import ListFavoriComponent from './components/ListFavoriComponent';
import AddAdminComponent from './components/AddAdminComponent';
import AdminLoginComponent from './components/AdminLoginComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import AddArtistComponent from './components/AddArtistComponent';
import EditArtistComponent from './components/EditArtistComponent';
import AddAlbumComponent from './components/AddAlbumComponent';
import EditAlbumComponent from './components/EditAlbumComponent';
import AddTitleComponent from './components/AddTitleComponent';
import EditTitleComponent from './components/EditTitleComponent';
import ListTitleComponent from './components/ListTitleComponent';
import UserComponent from './components/UserComponent';

function App() {
  return (
    <div>
      <Router>
        <HeaderComponent />
        <div className="container pb-5">
          <Switch>
            <Route exact path="/" component={HomeComponent}></Route>
            <Route path="/login" component={LoginComponent}></Route>
            <Route path="/signup" component={SignupComponent}></Route>
            <Route path="/home" component={HomeComponent}></Route>
            <Route path="/admin/add" component={AddAdminComponent}></Route>
            <Route path="/admin/login" component={AdminLoginComponent}></Route>
            <PrivateRoute path="/artistes" component={ListArtistComponent}></PrivateRoute>
            <PrivateRoute path="/artiste/:id" component={ArtistComponent}></PrivateRoute>
            <PrivateRoute path="/albums" component={ListAlbumComponent}></PrivateRoute>
            <PrivateRoute path="/album/:id" component={AlbumComponent}></PrivateRoute>
            <PrivateRoute path="/favoris" component={ListFavoriComponent}></PrivateRoute>
            <PrivateRoute path="/playlists" component={ListPlaylistComponent}></PrivateRoute>
            <PrivateRoute path="/create-playlist" component={CreatePlaylistComponent}></PrivateRoute>
            <PrivateRoute path="/playlist/:id" component={PlaylistComponent}></PrivateRoute>
            <PrivateRoute path="/admin/albums" component={AddAlbumComponent}></PrivateRoute>
            <PrivateRoute path="/admin/album/:id" component={EditAlbumComponent}></PrivateRoute>
            <PrivateRoute path="/admin/artistes" component={AddArtistComponent}></PrivateRoute>
            <PrivateRoute path="/admin/artiste/:id" component={EditArtistComponent}></PrivateRoute>
            <PrivateRoute path="/admin/titles" component={AddTitleComponent}></PrivateRoute>
            <PrivateRoute path="/admin/title/:id" component={EditTitleComponent}></PrivateRoute>
            <PrivateRoute path="/titles" component={ListTitleComponent}></PrivateRoute>
            <PrivateRoute path="/profile" component={UserComponent}></PrivateRoute>
          </Switch>
        </div>
        <FooterComponent />
      </Router>
    </div>
  );
}

export default App;