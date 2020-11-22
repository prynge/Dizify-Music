import React, { Component } from 'react';
import TitleService from '../services/TitleService';
import AuthService from '../services/AuthenticationService';

class ListTitleComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
           
            titles:[]
        }
    }

    addTitle() {
        this.props.history.push(`/admin/titles`);
    }

    editTitle(id) {
        this.props.history.push(`/admin/title/${id}`);
    }

    deleteTitle(id) {
        TitleService.deleteTitle(id);
        window.location.reload();
    }

    componentDidMount() {
        const user = AuthService.getCurrentUser();
    
        if (user) {
          this.setState({
            showAdminBoard: user.roles.includes("ROLE_ADMIN"),
          });
        }
        TitleService.getTitle().then((res) => {
            this.setState({ titles: res.data });
        });
    }

    render() {
        
        const {  showAdminBoard } = this.state;
        return (
            <div>
                <h2 className="text-center">Liste des titres</h2>
                <div className="row">
                    {showAdminBoard && (
                    <button onClick={() => this.addTitle()} className="btn btn-info">Ajouter un titre</button>)}
                    <table className="table table-striped table-bordered">
                        <thead>
                            <tr>
                                <th>Nom du titre</th>
                                <th>Artiste</th>
                                <th>Album</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            {
                                this.state.titles.map(
                                    title =>
                                        <tr key={title.id}>
                                            <td>{title.name}</td>
                                            <td>{title.artist.name}</td>
                                            <td>{title.album.name}</td>
                                            <td>
                                                {showAdminBoard && (
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.editTitle(title.id)} className="btn btn-info">Modifier</button>)}
                                                {showAdminBoard && (
                                                <button style={{ marginLeft: "10px" }} onClick={() => this.deleteTitle(title.id)} className="btn btn-danger">Supprimer</button>)}
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

export default ListTitleComponent;
