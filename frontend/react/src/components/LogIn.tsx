import React, { useState } from "react";
import { Card, CardContent, Container, TextField } from "@mui/material";
import "./Login.css";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const Login = () => {
 
  const navigate = useNavigate();
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [loginAttempts, setLoginAttempts] = useState(0);
  const [loggedInUsername, setLoggedInUsername] = useState("");
  //const [redirectToPrivate, setRedirectToPrivate] = useState(false);
  


  const handleSubmit = (event: { preventDefault: () => void }) => {
    event.preventDefault();
  
    // Send login request to the server and handle the response accordingly
    fetch(`http://localhost:8080/api/login/${username}/${password}`)
      .then((response) => {
        return response.text();
      })
      .then(async (data) => {
        if (data === "true") {
          console.log("Login successful!");
          try{
            setLoggedInUsername(username); // set the logged-in user's username
            const isAdmin = await axios.get(`http://localhost:8080/api/isAdmin`);
            console.log(isAdmin.data);
            
            if(!isAdmin.data)
            {
              navigate('/privatedestinations', { state: { loggedInUsername: username } });
            }
            else{navigate('/destinations');}
            
          } catch(error){
            console.log(error);
          }
          // Do something to handle successful login
        } else {
          console.log("Login failed!");
          setLoginAttempts(loginAttempts + 1);
          console.log(loginAttempts);
          // Disable the login button and display an error message after three failed login attempts
          if (loginAttempts >= 2) {
            const loginButton = document.getElementById(
              "login-button"
            ) as HTMLButtonElement;
            if (loginButton) {
              loginButton.disabled = true;
              alert("Maximum login attempts exceeded. Please try again later.");
            }
          }
        }
      })
      .catch((error) => console.error(error));
  };


  
  return (
    <Container className="Container">
      <Card className="FormWrapper">
        <CardContent>
          <h1>Login</h1>
          <label className="TextField">
            Username:
            <input
              type="text"
              value={username}
              onChange={(event) => setUsername(event.target.value)}
            />
          </label>
          <label className="TextField">
            Password:
            <input
              type="password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
            />
          </label>
          <button type="submit" onClick={handleSubmit} disabled={loginAttempts >= 3}>
  Submit
</button>
        </CardContent>
      </Card>
    </Container>
  );}

export default Login;