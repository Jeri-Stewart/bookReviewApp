document.addEventListener("DOMContentLoaded", () => {
  const loggedInUsernameElement = document.getElementById("loggedInUsername");
  const homeLink = document.getElementById("home-link");
  const profileLink = document.getElementById("profile-link");
  const reviewLink = document.getElementById("review-link");

  const loggedInUsername = loggedInUsernameElement.innerText;

  loggedInUsernameElement.innerText = loggedInUsername;

  homeLink.href = `/home/${loggedInUsername}`;
  profileLink.href = `/profile/${loggedInUsername}`;
  reviewLink.href = `/reviews/${loggedInUsername}`;

  console.log("Logged-in username:", loggedInUsername);

  // Fetch book titles from the server
  fetch("/api/v1/books/titles")
    .then((response) => response.json())
    .then((titles) => {
      titles.forEach((title) => {
        const option = document.createElement("option");
        option.value = title;
        option.innerText = title;
        bookTitleSelect.appendChild(option);
      });
    })
    .catch((error) => {
      console.error("Error fetching book titles:", error);
    });

  reviewForm.addEventListener("submit", (e) => {
    e.preventDefault();

    // Get form values
    const bookTitle = bookTitleSelect.value;
    const rating = document.getElementById("rating").value;
    const review = document.getElementById("review").value;

    // Create review object
    const newReview = {
      bookTitle,
      rating,
      review
    };

    // Send the review data to the server (you can replace the endpoint with your actual API endpoint)
    fetch("/api/v1/reviews", {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify(newReview)
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("Review submitted successfully:", data);
        // Reset the form
        reviewForm.reset();
        // Show a success message or redirect to a confirmation page
        alert("Review submitted successfully!");
      })
      .catch((error) => {
        console.error("Error submitting review:", error);
        // Show an error message or handle the error appropriately
        alert("An error occurred while submitting the review.");
      });
  });

  const bookListElement = document.getElementById("book-list");
  const reviewContainerElement = document.getElementById("review-container");

  console.log("Logged-in username:", loggedInUsername);

  // Fetch books from the server
  fetch("/api/v1/books")
    .then((response) => response.json())
    .then((books) => {
      books.forEach((book) => {
        const bookCard = document.createElement("div");
        bookCard.classList.add("book-card");

        const bookImage = document.createElement("img");
        bookImage.src = book.image;
        bookImage.alt = book.title;

        bookCard.appendChild(bookImage);

        bookListElement.appendChild(bookCard);
      });
    })
    .catch((error) => {
      console.error("Error fetching books:", error);
    });
