// Registration page script

document.addEventListener("DOMContentLoaded", () => {
  const form = document.querySelector(".register-form");

  form.addEventListener("submit", async (event) => {
    event.preventDefault();

    const firstName = document.querySelector('input[name="firstName"]').value;
    const lastName = document.querySelector('input[name="lastName"]').value;
    const email = document.querySelector('input[name="email"]').value;
    const username = document.querySelector('input[name="username"]').value;
    const password = document.querySelector('input[name="password"]').value;

    // Create a user object
    const user = {
      firstName: firstName,
      lastName: lastName,
      email: email,
      username: username,
      password: password
    };
    console.log(user)
    const baseUrl = 'http://localhost:8080/api/v1/users/register-user'

    console.log("one");
    // Send user data to backend API
    await fetch(baseUrl, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(user)
    })
      .then(response => {
        console.log(response); // Check the response object
        return response.json(); // Parse the response as JSON
      })
      .then(data => {
        console.log("two");
        if (data.length > 0) {
          // Registration failed, display error messages
          const errorMessages = data.join('\n');
          alert('Registration failed:\n' + errorMessages);
        } else {
          // Registration successful, display success message
          alert('Registration successful');
          // Redirect to the login page after a delay of 20 seconds
          console.log("three");
          console.log("four");
        }
      })
      .catch(error => {
        console.error('Error:', error);
        alert('Registration failed due to an error. Please try again later.');
      });
  });
});
