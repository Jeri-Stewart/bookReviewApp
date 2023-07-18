document.addEventListener("DOMContentLoaded", () => {
  const loggedInUsernameElement = document.getElementById("loggedInUsername");
  const bookListElement = document.getElementById("book-list");
  const reviewContainerElement = document.getElementById("review-container");

  const loggedInUsername = loggedInUsernameElement.innerText;

  loggedInUsernameElement.innerText = loggedInUsername;

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

  // Fetch reviews from the server
  fetch("/api/v1/reviews")
    .then((response) => response.json())
    .then((reviews) => {
      reviews.forEach((review) => {
        const reviewCard = document.createElement("div");
        reviewCard.classList.add("review-card");

        const rating = document.createElement("p");
        rating.innerText = `Rating: ${review.rating} out of 5`;
        reviewCard.appendChild(rating);

        const reviewContent = document.createElement("p");
        reviewContent.innerText = review.review;
        reviewCard.appendChild(reviewContent);

        const deleteButton = document.createElement("button");
        deleteButton.innerText = "Delete";
        deleteButton.id = "deleteBtn"
        deleteButton.addEventListener("click", () => {
          deleteReview(review.id);
          reviewCard.remove();
        });
        reviewCard.appendChild(deleteButton);

        reviewContainerElement.appendChild(reviewCard);
      });
    })
    .catch((error) => {
      console.error("Error fetching reviews:", error);
    });

  function deleteReview(reviewId) {
    fetch(`/api/v1/reviews/${reviewId}`, {
      method: "DELETE",
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("Review deleted:", data);
      })
      .catch((error) => {
        console.error("Error deleting review:", error);
      });
  }
});




/*
document.addEventListener("DOMContentLoaded", () => {
  const loggedInUsernameElement = document.getElementById("loggedInUsername");
  const bookListElement = document.getElementById("book-list");
  const reviewContainerElement = document.getElementById("review-container");

  const loggedInUsername = loggedInUsernameElement.innerText;

  loggedInUsernameElement.innerText = loggedInUsername;

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

  // Fetch reviews from the server
  fetch("/api/v1/reviews")
    .then((response) => response.json())
    .then((reviews) => {
      console.log(reviews); // Log the reviews response to the console
      reviews.forEach((review) => {
        const reviewCard = document.createElement("div");
        reviewCard.classList.add("review-card");

        const bookImage = document.createElement("img");
        bookImage.src = review.book.image;
        bookImage.alt = review.book.title;
        reviewCard.appendChild(bookImage);

        const bookTitle = document.createElement("h3");
        bookTitle.innerText = review.book.title;
        reviewCard.appendChild(bookTitle);

        const authorName = document.createElement("p");
        authorName.innerText = review.book.author.name;
        reviewCard.appendChild(authorName);

        const rating = document.createElement("p");
        rating.innerText = `Rating: ${review.rating} out of 5`;
        reviewCard.appendChild(rating);

        const reviewContent = document.createElement("p");
        reviewContent.innerText = review.review;
        reviewCard.appendChild(reviewContent);

        reviewContainerElement.appendChild(reviewCard);
      });
    })
    .catch((error) => {
      console.error("Error fetching reviews:", error);
    });
});





*/












