# Intruder Detection System - Android Client

This is an Android application that connects to a Raspberry Pi 3 server with an intruder detection system. The app receives notifications when an intruder is detected, shows the intruder's image, and plays a 10-second clip of the intrusion. Additionally, the app supports live streaming from the detection system.

## Features

- **Real-time Notifications**: Receive alerts when the system detects an intruder.
- **Intruder Image & Video Clip**: View an image of the intruder and a 10-second video clip capturing the event.
- **Live Streaming**: Stream live footage directly from the intruder detection system.
- **Android Client**: User-friendly interface to monitor and review intrusions.

## Architecture Overview

The app communicates with a Raspberry Pi 3 server, which processes data from the detection system and sends notifications and media to the Android client.

- **Server-side Python Code**: The intruder detection system and server-side communication is implemented in Python. You can view the Python code [here](https://github.com/MrAmmia/FYP_Python).

## Tech Stack

- **Android Development**: Kotlin
- **Server Communication**: RESTful API
- **Media Handling**: ExoPlayer for video playback
- **Notification**: Firebase Cloud Messaging
- **Backend Server**: Django backend hosted onRaspberry Pi 3
- **Intruder Detection**: Implemented on the server-side using Python

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/MrAmmia/FYP_Android.git
   cd FYP_Android

2. Set up the Intruder detection circuitry using the [circuit schematic](https://drive.google.com/file/d/1vbsxO61WuUxKJJC82_BYAV8Hb9vRPMYc/view?usp=sharing) & [diagram](https://drive.google.com/file/d/1QYjqIIEHI4IHIKjGHAdLVCsW5OR7Zf3R/view?usp=sharing) and [Python code](https://github.com/MrAmmia/FYP_Python/blob/master/motion.py)

3. Set up the server by following the instructions in the [Python server repository](https://github.com/MrAmmia/FYP_Python).
    - **Clone the Django Server Repository**
      ```bash
      git clone https://github.com/MrAmmia/FYP_Python.git
      cd FYP_Python
   - **Create a Virtual Environment**
     ```bash
     python -m venv env
     source env/bin/activate  # On Windows use `env\Scripts\activate`
   - **Install Dependencies**:Navigate to the project directory and install the required dependencies from requirements.txt
     ```bash
     pip install -r requirements.txt

   - **Configure the Server Settings**: In the [settings.py](https://github.com/MrAmmia/FYP_Python/blob/master/fyp/settings.py) file, configure your database and other necessary settings, such as the IP address of the Raspberry Pi.
      Ensure that the app can send notifications to the Android client using Firebase Cloud Messaging (FCM) or another push notification service
   -  **Run Migrations**: Apply database migrations to set up the necessary tables:
      ```bash
      python manage.py migrate

   - **Start the Server**: Run the Django development server
     ```bash
     python manage.py runserver 0.0.0.0:8000

 
  
4. Build the Android app in Android Studio or using Gradle:
   ```bash
   ./gradlew build
5. Run the app on an Android device or emulator.
6. Configure the app to connect to your Raspberry Pi server by updating the server URL in the configuration file.

## How It Works

  - **Intruder Detection**: The Raspberry Pi server runs an intruder detection system that processes data from connected sensors and cameras.
  - **Notification**:  When an intruder is detected, a notification is sent to the Android app with a snapshot and a short video clip.
  - **Live Streaming**: The app connects to the Raspberry Pi's camera feed and allows live monitoring of the premises.

## Future Enhancements

   - Two-way audio communication to allow interaction with the intruder.
   - Integration with cloud storage for storing intrusion footage.
   - Enhanced AI for more accurate intrusion detection.

## Project Report
   You can find the detailed project report [here](https://drive.google.com/file/d/1IwOLd_-q2-xBeFQMKTZ0ImCQV4o5IB-F/view?usp=sharing)

## Contributing
  Contributions are welcome! Please feel free to fork the repository, open issues, and submit pull requests.
