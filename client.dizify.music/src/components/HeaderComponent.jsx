import React, { Component } from 'react';
import { Nav, Navbar } from 'react-bootstrap';
import Logo from '../assets/logo.png';
import AuthService from '../services/AuthenticationService';
import UserService from '../services/UserService';

class HeaderComponent extends Component {
    constructor(props) {
        super(props);
        this.logOut = this.logOut.bind(this);

        this.state = {
            currentUser: undefined,
            showUserBoard: false,
            showAdminBoard: false,
            avatar: null
        }
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

        if (this.state.showAdminBoard) {
            UserService.getUserById(this.state.currentUser.mail).then(res=>{
                this.setState({avatar: res.data.avatar})
            })    
        }
        
    }

    logOut() {
        AuthService.logout();
    }

    render() {
        const { currentUser, showUserBoard, showAdminBoard } = this.state;
        return (
            <div>
                <header className="header">
                    <Navbar bg="dark" variant="dark">
                        <img src={Logo} width="30" height="30" className="d-inline-block align-top mr-3" alt="" />
                        <Navbar.Brand href="/">Dizify Music</Navbar.Brand>
                        {currentUser && showUserBoard &&(
                            <Nav className="mr-auto">
                                <Nav.Link href="/artistes">Artistes</Nav.Link>
                                <Nav.Link href="/albums">Albums</Nav.Link>
                                <Nav.Link href="/favoris">Favoris</Nav.Link>
                                <Nav.Link href="/playlists">Playlist</Nav.Link>
                            </Nav>)}
                        {currentUser && showAdminBoard && (
                            <Nav className="mr-auto">
                                <Nav.Link href="/artistes">Artistes</Nav.Link>
                                <Nav.Link href="/albums">Albums</Nav.Link>
                                <Nav.Link href="/titles">Titres</Nav.Link>
                                <Nav.Link href="/admin/add">Ajouter un nouvel administrateur</Nav.Link>
                            </Nav>)}
                        

                        {currentUser && (
                            <Nav className="ml-auto">
                                {showUserBoard && (
                                <img src={this.state.avatar} width="50" height="50" className="d-inline-block align-top mr-3" alt="" />)}{currentUser && (
                                <Nav.Link href="/profile">Profile</Nav.Link>)}
                                <Nav.Link href="/login" onClick={this.logOut}>LogOut</Nav.Link>
                            </Nav>)}
                        {!currentUser && (
                        <Nav className="ml-auto">
                            <Nav.Link href="/admin/login" className="nav-link">Admin</Nav.Link>
                            <Nav.Link href="/login" className="nav-link">User</Nav.Link>
                        </Nav>)}
                            
                    </Navbar>
                </header>
            </div>
        );
    }
}

export default HeaderComponent;