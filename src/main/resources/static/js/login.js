// login.js
document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector(".login-form");

  form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const username = document.querySelector('input[name="username"]').value;
    const password = document.querySelector('input[name="password"]').value;

    // Create a login object
    const loginData = {
      username: username,
      password: password,
    };

    const baseUrl = 'http://localhost:8080/api/v1/users/login-user';

    console.log("Logging in...");
    console.log(loginData);

    // Send login data to the backend API
    await fetch(`${baseUrl}/${username}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(loginData),
    })
      .then(response => {
        console.log(response); // Check the response object
        return response.json(); // Parse the response as JSON
      })
      .then(data => {
        console.log(data); // Display the response data
        // Extract the response message
        const responseMessage = data[0];
        alert(responseMessage);
        if (responseMessage === "User login successful") {
          // Redirect to the home page after the user clicks "OK"
          window.location.href = `/home/${username}`;
        }
      })
      .catch(error => {
        console.error('Error:', error);
        alert('Login failed due to an error. Please try again later.');
      });
  });
});




