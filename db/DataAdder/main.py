import requests
from datetime import datetime

# Function to add accident data
def add_accident(accident_at):
    url = 'http://localhost:8080/api/accidents'
    headers = {'Content-Type': 'application/json'}
    data = {
        'accidentAt': accident_at
    }

    response = requests.post(url, json=data, headers=headers)

    # Check if the response code is 200 or 201 (both indicate success)
    if response.status_code in [200, 201]:
        print("Accident data added successfully!")
        print(f"Response: {response.json()}")
    else:
        print(f"Failed to add accident. Status code: {response.status_code}")
        print(f"Error message: {response.text}")

# Get the current time in the required format
accident_time = datetime.now().strftime('%Y-%m-%dT%H:%M:%S')

# Add the accident
add_accident(accident_time)
