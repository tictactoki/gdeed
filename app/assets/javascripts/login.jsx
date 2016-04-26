/**
 * Created by stephane on 26/04/2016.
 */

var Login = React.createClass({

    getInitialState: function () {
        return {
            email: "",
            password: ""
        };
    },

    render: function () {
        return (
            <form className="signIn" onSubmit={this.handleSubmit}>
                <fieldset>
                    <legend>Sign In</legend>
                    <input id="email" refs="email" name="email" type="email" placeholder="Email" onChange={this.setValue.bind(this,'email')}/>
                    <input id="password" refs="password" name="password" type="password" placeholder="Password" onChange={this.setValue.bind(this,'password')}/>
                    <input type="submit" value="OK"/>
                </fieldset>
            </form>
        );
    },

    setValue: function(field, event) {
        var object = {};
        object[field] = event.target.value;
        this.setState(object);
    },

    componentDidMount: function () {

    },

    handleSubmit: function (event) {
        event.preventDefault();
        var email = this.state.email.trim();
        var password = this.state.password.trim();
        alert(email);
        var signIn = {
            email: email,
            password: password
        };
        console.log(signIn);
        $.ajax({
            url: "http://localhost:9000/signIn/",
            type: 'POST',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify(signIn),
            success: function (data) {
                console.log(event);
            }.bind(this),
            errors: function (xhr, status, err) {
                console.error(status, err);
            }.bind(this)


        });

    }

});

$(document).ready(function () {

    ReactDOM.render(<Login/>, document.getElementById("content"));

});