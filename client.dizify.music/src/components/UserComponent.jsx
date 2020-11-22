import React, { Component } from 'react';
import UserService from '../services/UserService';
import MediaService from '../services/MediaService';
import AuthService from '../services/AuthenticationService';

class UserComponent extends Component {
    constructor(props) {
        super(props)
        const token = JSON.parse(localStorage.getItem('user'));
        this.state = {
            userId: { email: token.email },
            user:{},
            picture: null,
            avatar: null,
        }
    }

    

    editAvatar(user) {
        const data = new FormData() 
        data.append('file', this.state.picture)
        data.append('fname', user.fname)
        data.append('lname', user.lname)
        UserService.updateUser(data,user.email).then(res => {
            window.location.reload();
        })
    }

    savePictureHandler = (event) => {
        this.setState({ picture: event.target.value });
    }

    componentDidMount() {
        const user = AuthService.getCurrentUser();
        

        if (user) {
            this.setState({
                user: user
            });
        }
        UserService.getUserById(this.state.user.email).then(res=>{
            this.setState({user: res.data})
        })

    }

    render() {
        return (
            <div className="row mt-100">
              <div className="col-4">
                <img src={this.state.avatar} width="200" height="200" className="d-inline-block align-top mr-3" alt="" />
                <div className="form-group">
                    <label>Picture</label>
                    <input type="file" name="picture" className="form-control" placeholder="Ajouter un avatar" onChange={this.savePictureHandler} />
                </div>
                <button onClick={() => this.editAvatar(this.state.user)} className="btn btn-info">Changer son avatar</button>
              </div>
              <div className="col-8">
                  <h2 className="text-center"> Votre profil</h2>
                  <h3 >Nom  : {this.state.user.lname}</h3>
                  <h3 >Prénom : {this.state.user.fname}</h3>
                  <h3 >Adresse email : {this.state.user.email}</h3>
              </div>
                
            </div>
        );
    }
}

export default UserComponent;