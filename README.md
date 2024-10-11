# Movie Review App

Welcome to the Movie Review App repository! This full-stack application is built using Java Spring Boot for the backend API and ReactJS for the frontend user interface. The backend utilizes PostgresDB database to store and manage movie data and user reviews.

## Demo:

## Top Features

- **User Authentication**: Secure user authentication system to allow users to create accounts, log in, and manage their profiles.
- **Browse Movies**: Users can explore a vast collection of movies with detailed information.
- **Search Functionality**: A powerful search feature enables users to find movies based on titles, genres, actors, and more.
- **Write Reviews**: Registered users can write and submit reviews for their favorite movies.
- **Rating System**: Users can rate movies and view the average rating given by other users.

## Installation Steps

### Backend (Java Spring Boot)

1. **Clone the repository:**
 
git clone [https://github.com/JAMSHID12/fullStack-movie-review-application.git]
 

2. **Navigate to the backend directory:**
 ```
cd movie-review-app/backend

 ```

3. **Set up PostgresDB Atlas:**
- Create an account on [MongoDB Atlas](https://www.mongodb.com/cloud/atlas).
- Create a new cluster and obtain the connection string.

4. **Configure Postgres Connection:**
- Replace  query `application.properties` with your Postgres connection string.

5. **Run the Spring Boot Application:**
 ```
./mvnw spring-boot:run
 
```
6. The backend server should now be running at `http://localhost:8080`.

### Frontend (ReactJS)

1. **Navigate to the frontend directory:**
 ```
cd movie-review-app/frontend
 ```

2. **Install dependencies:**
 ```
npm install
 ```

3. **Set up environment variables:**
- Create a `.env` file in the frontend directory.
- Add the following line to the `.env` file and replace `<backend_api_url>` with the actual backend API URL (e.g., `http://localhost:8080`):
 
REACT_APP_API_URL=<backend_api_url>
 

4. **Run the React App:**

```
npm start
 ```

5. The React app should now be running at `http://localhost:3000`.

## Contributing


Happy coding! 🚀
@Jamshid ct
