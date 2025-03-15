import React, { Component } from "react";

export default class AddContacts extends Component {
    constructor(props) {
        super(props);
        this.firstNameRef = React.createRef();
        this.lastNameRef = React.createRef();
        this.emailRef = React.createRef();
    }

    submitContact(event) {
        event.preventDefault();

        let contact = {
            firstName: this.firstNameRef.current.value,
            lastName: this.lastNameRef.current.value,
            email: this.emailRef.current.value,
        };

        fetch("http://localhost:8080/api/contacts", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(contact),
        })
        .then(response => response.json())
        .then(() => window.location.reload());
    }

    render() {
        return (
            <div className="row">
                <form className="col s12" onSubmit={this.submitContact.bind(this)}>
                    <div className="row">
                        <div className="input-field col s6">
                            <input
                                ref={this.firstNameRef}
                                type="text"
                                class="validate"
                            />
                            <label htmlFor="first_name">First Name</label>
                        </div>
                        <div className="input-field col s6">
                            <input ref={this.lastNameRef} type="text" class="validate" />
                            <label htmlFor="last_name">Last Name</label>
                        </div>
                    </div>
                    <div className="row">
                        <div className="input-field col s12">
                            <input ref={this.emailRef} type="text" class="validate" />
                            <label htmlFor="email">Email</label>
                        </div>
                    </div>
                    <div className="row">
                        <button className="waves-effect waves-light btn" type="submit">Submit</button>
                    </div>
                </form>
            </div>
        );
    }
}
