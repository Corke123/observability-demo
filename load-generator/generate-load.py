import random
import requests
import time
import json

from concurrent.futures import ThreadPoolExecutor
from datetime import datetime, timedelta

DURATION_MINUTES = 60
USERS_PER_SECOND = 5

def generate_beer_id():
    random_value = random.random()
    if random_value > 0.5:
        return 1
    if random_value > 0.25:
        return 2
    if random_value > 0.15:
        return 3
    return 4

def generate_beer_rating():
    return random.randint(1,5)

def make_request():
    beer_rating = generate_beer_rating()
    
    url = "http://localhost:8082/ratings"
    payload = json.dumps({
        "beerId": generate_beer_id(),
        "rating": generate_beer_rating()
    })
    headers = {
        'Content-Type': 'application/json'
    }

    try:
        response = requests.post(url, headers=headers, data=payload)
        print(f"Sent POST request with body {payload}. Response: {response.status_code}")
    except requests.RequestException as e:
        print(f"Failed to send POST request: {e}")

if __name__ == "__main__":
    end_time = datetime.now() + timedelta(minutes=DURATION_MINUTES)
    interval = 1 / USERS_PER_SECOND

    print(f"Starting simulation for {DURATION_MINUTES} minutes with {USERS_PER_SECOND} requests per second.")

    with ThreadPoolExecutor(max_workers=USERS_PER_SECOND) as executor:
        while datetime.now() < end_time:
            for _ in range(USERS_PER_SECOND):
                executor.submit(make_request)
            time.sleep(interval)

    print("SImulation completed.")

