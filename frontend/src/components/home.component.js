import React, {Component} from "react";

export default class Home extends Component {

    render() {
        return (
            <div className="container">
                <header className="jumbotron">
                    <h3>Username/Password</h3>
                    <p>
                        {/*admin=111111<br/>*/}
                        user=111111<br/>
                        editor=111111
                    </p>
                    <br/>
                    <h3>REST API Docs</h3>
                    <a href="http://localhost:8080/swagger-ui/" target="_blank">http://localhost:8080/swagger-ui</a>
                </header>
            </div>
        );
    }
}
