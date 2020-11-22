import React, { Component } from "react";
import AuthService from '../services/AuthenticationService';

class AddAdminComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
        }
    }

    saveUser = (e) => {
        e.preventDefault();
        
        let user = { email: this.state.username, password: this.state.password, };
        AuthService.createAdmin(user).then(res => {
            //this.props.history.push('/')
        })
    }

    saveUsernameHandler = (event) => {
        this.setState({ username: event.target.value });
    }

    saveEmailHandler = (event) => {
        this.setState({ emailId: event.target.value });
    }

    savePasswordHandler = (event) => {
        this.setState({ password: event.target.value });
    }

    render() {
        return (
            <form className="form-signup" >
                <h3>Inscription</h3>
                <div className="form-group">
                    <label>Prénom</label>
                    <input type="text" name="fusernameName" className="form-control" placeholder="First name" value={this.state.username} onChange={this.saveUsernameHandler} />
                </div>
                <div className="form-group">
                    <label>Mot de passe</label>
                    <input type="password" name="password" className="form-control" placeholder="Enter password" value={this.state.password} onChange={this.savePasswordHandler} />
                </div>
                <button type="submit" className="btn btn-primary btn-block" onClick={this.saveUser}>S'inscrire</button>
                <p className="forgot-password text-right">
                    Déja <a href="login">inscrit ?</a>
                </p>
            </form>
        );
    }
}

export default AddAdminComponent;