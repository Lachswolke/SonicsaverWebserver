import React, { useState, useEffect } from 'react';
import { useLocation } from 'react-router-dom';
import './siteStyle.css';

const handleLogout = () => {
    localStorage.removeItem('authToken');
    window.location.href = '/';
};

const Welcome = () => {
    const [accidents, setAccidents] = useState([]);
    const location = useLocation();
    const username = location.state?.username || 'User';

    // Fetch accidents data from the API
    useEffect(() => {
        const fetchAccidents = async () => {
            try {
                const response = await fetch('/api/accidents');
                const data = await response.json();
                setAccidents(data);
            } catch (error) {
                console.error('Error fetching accidents:', error);
            }
        };

        fetchAccidents();
    }, []);

    return (
        <div className="welcome-container">
            <h1>Hello {username}!</h1>
            <p>Welcome to the protected page.</p>
            <button onClick={handleLogout}>Logout</button>

            <h2>Accidents List</h2>
            <ul>
                {accidents.map(accident => (
                    <li key={accident.id}>
                        <strong>Accident :</strong> {accident.accidentAt}
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default Welcome;
