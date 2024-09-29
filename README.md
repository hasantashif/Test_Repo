# FanCode SDET Assignment

## Overview
This project automates the verification of whether users from the city "FanCode" have completed more than 50% of their todo tasks. It fetches user data from the JSONPlaceholder API, calculates completion rates, and provides options for filtering and viewing detailed user information.

### Key Features:
- Fetches user data and todos from the JSONPlaceholder API.
- Calculates the completion percentage of todos for users from FanCode city.
- Displays detailed user information, including posts and albums.
- Command-line filtering options to search for specific users by name.
- JavaFX GUI for a more interactive user experience (optional).

### Criteria:
- Users are considered from FanCode city if their latitude is between -40 and 5 and their longitude is between 5 and 100.
- The application calculates the completion percentage of todos for these users.

## Prerequisites
- **Java 11+**
- **Gradle**
- **Internet connection** (for fetching data from the API)

## Setup and Running the Project

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/your-username/Test_Repo.git
   cd Test_Repo
