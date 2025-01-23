import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './siteStyle.css';


const Login = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const navigate = useNavigate();

  const handleUsernameChange = (e) => setUsername(e.target.value);
  const handlePasswordChange = (e) => setPassword(e.target.value);

  const handleLogin = async () => {
    try {
      const response = await axios.post('/api/auth/login', { username, password });
      localStorage.setItem('authToken', response.data.token);
      navigate('/welcome', { state: { username } });
    } catch (error) {
      setMessage('Invalid credentials');
    }
  };

  const handleRegister = async () => {
    try {
      const response = await axios.post('/api/auth/register', { username, password });
      setMessage('Registration successful! Please log in.');
    } catch (error) {
      setMessage('Registration failed');
    }
  };

  return (
    <div className="login-container">
      <h2>Login</h2>
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={handleUsernameChange}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={handlePasswordChange}
      />
      <div>
        <button onClick={handleLogin}>Login</button>
        <button onClick={handleRegister}>Register</button>
      </div>
      {message && <p>{message}</p>}
    </div>
  );
};

export default Login;
