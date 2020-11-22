import React, { Component } from "react";
import AuthService from '../services/AuthenticationService';

class AdminLoginComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
        }
       // this.addUser = this.addUser.bind(this);
    }

    saveUser = (e) => {
        e.preventDefault();
        let idConnect = { username: this.state.username, password: this.state.password };
        AuthService.loginAdmin(idConnect).then(() =>  {
            this.props.history.push('/home')
            window.location.reload();
        })
    }

    checkUsernameHandler = (event) => {
        this.setState({ username: event.target.value });
    }

    checkPasswordHandler = (event) => {
        this.setState({ password: event.target.value });
    }

    render() {
        return (
            <form className="form-login">
                <h3>Identification administrateur</h3>
                <div className="form-group">
                    <label>Email</label>
                    <input type="email" className="form-control" placeholder="Enter username" value={this.state.username} onChange={this.checkUsernameHandler} />
                </div>
                <div className="form-group">
                    <label>Mot de passe</label>
                    <input type="password" className="form-control" placeholder="Enter password" value={this.state.password} onChange={this.checkPasswordHandler} />
                </div>
                <div className="form-group">
                    <div className="custom-control custom-checkbox">
                        <input type="checkbox" className="custom-control-input" id="customCheck1" />
                    </div>
                </div>
                <button type="button" onClick={this.saveUser} className="btn btn-primary btn-lg btn-block">Se connecter</button>
            </form>
        );
    }
}

export default AdminLoginComponent;