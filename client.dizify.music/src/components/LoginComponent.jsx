import React, { Component } from "react";
import AuthService from '../services/AuthenticationService';

class LoginComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
        }
    }

    logUser = (e) => {
        e.preventDefault();
        let idConnect = { mail: this.state.emailId, password: this.state.password };
        AuthService.login(idConnect).then(() => {
            this.props.history.push('/home');
            window.location.reload();
        })
    }

    checkEmailHandler = (event) => {
        this.setState({ emailId: event.target.value });
    }

    checkPasswordHandler = (event) => {
        this.setState({ password: event.target.value });
    }

    render() {
        return (
            <form className="form-login">
                <h3>Identification</h3>
                <div className="form-group">
                    <label>Email</label>
                    <input type="email" className="form-control" placeholder="Enter email" value={this.state.emailId} onChange={this.checkEmailHandler} />
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
                <button type="button" onClick={this.logUser} className="btn btn-primary btn-lg btn-block">Se connecter</button>
                <p className="forgot-password text-right">
                    Pas encore <a href="signup">inscrit ?</a>
                </p>
            </form>
        );
    }
}

export default LoginComponent;