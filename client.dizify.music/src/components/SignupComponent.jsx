import React, { Component } from "react";
import AuthService from '../services/AuthenticationService';

class SignupComponent extends Component {
    constructor(props) {
        super(props)

        this.state = {
        }
    }

    saveUser = (e) => {
        e.preventDefault();
        let user = { fname: this.state.firstName, lname: this.state.lastName, email: this.state.emailId, password: this.state.password };
        AuthService.signup(user).then(res => {
            this.props.history.push('/login');
        })
    }

    saveFirstNameHandler = (event) => {
        this.setState({ firstName: event.target.value });
    }

    saveLastNameHandler = (event) => {
        this.setState({ lastName: event.target.value });
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
                    <input type="text" name="firstName" className="form-control" placeholder="First name" value={this.state.firstName} onChange={this.saveFirstNameHandler} />
                </div>
                <div className="form-group">
                    <label>Nom</label>
                    <input type="text" name="lastName" className="form-control" placeholder="Last name" value={this.state.lastName} onChange={this.saveLastNameHandler} />
                </div>
                <div className="form-group">
                    <label>Email</label>
                    <input type="email" name="emailId" className="form-control" placeholder="Enter email" value={this.state.emailId} onChange={this.saveEmailHandler} />
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

export default SignupComponent;