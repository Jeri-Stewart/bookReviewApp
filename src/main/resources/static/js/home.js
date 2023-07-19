console.log("home.js loaded");

document.addEventListener("DOMContentLoaded", () => {
  const loggedInUsernameElement = document.getElementById("loggedInUsername");
  const bookListElement = document.getElementById("book-list");
  const reviewContainerElement = document.getElementById("review-container");
  const reviewForm = document.getElementById("review-form");
  const updateForm = document.getElementById("update-form");
  const selectReviewElement = document.getElementById("book-title")
  const selectUpdateElement = document.getElementById("update-book-title")

  const loggedInUsername = loggedInUsernameElement.innerText;
  loggedInUsernameElement.innerText = loggedInUsername;

  console.log("Logged-in username:", loggedInUsername);


  let books = []; // Initialize the books variable as an empty array
  let reviews = []; // Initialize the reviews variable as an empty array

  // Fetch books from the server
  fetch("/api/v1/books")
    .then((response) => response.json())
    .then((fetchedBooks) => {
      books = fetchedBooks;

      books.forEach((book) => {
        const bookCard = document.createElement("div");
        bookCard.classList.add("book-card");

        const bookImage = document.createElement("img");
        bookImage.src = book.image;
        bookImage.alt = book.title;

        bookCard.appendChild(bookImage);

        bookListElement.appendChild(bookCard);

        const bookTitleOption = document.createElement("option");
        bookTitleOption.value = book.id;
        bookTitleOption.innerText = book.title;
        reviewForm.bookTitle.appendChild(bookTitleOption);
      });

      // Fetch user's reviews
      fetch(`/api/v1/reviews/user/${loggedInUserId}`)
        .then((response) => response.json())
        .then((fetchedReviews) => {
          reviews = fetchedReviews;


          reviews.forEach((review) => {
            const reviewCard = document.createElement("div");
            reviewCard.classList.add("review-card");
            reviewCard.id = `review-card-${review.reviewId}`;
                const reviewCardImage = document.createElement("div");
                reviewCardImage.classList.add("review-card-image");

                const reviewImage = document.createElement("img");
                reviewImage.src = review.book.image;
                reviewImage.alt = review.book.title;

                reviewCardImage.appendChild(reviewImage);
                reviewCard.appendChild(reviewCardImage);


            const reviewCardContent = document.createElement("div");
            reviewCardContent.classList.add("review-card-content");

            const reviewTitle = document.createElement("h3");
            reviewTitle.classList.add("review-card-title");
            reviewTitle.innerText = review.book.title;


            const reviewContent = document.createElement("p");
            reviewContent.classList.add("review-card-review");
            reviewContent.innerText = review.review;

            const deleteButton = document.createElement("button");
            deleteButton.classList.add("delete-button");
            deleteButton.innerText = "Delete";
            deleteButton.id = `delete-review-${review.reviewId}`;
            deleteButton.addEventListener("click", () => {
              deleteReview(review.reviewId);
              reviewCard.remove();
            });

            reviewCardContent.appendChild(reviewTitle);
            reviewCardContent.appendChild(reviewContent);
            reviewCardContent.appendChild(deleteButton);
            reviewCard.appendChild(reviewCardContent);

            reviewContainerElement.appendChild(reviewCard);
          });
        })
        .catch((error) => {
          console.error("Error fetching reviews:", error);
        });
    })
    .catch((error) => {
      console.error("Error fetching books:", error);
    });

    // POST AND PUT REQUEST FOR BOOK REVIEWS

  reviewForm.addEventListener("submit", (event) => {
    event.preventDefault();

    const selectedOption = selectReviewElement.options[selectReviewElement.selectedIndex];
    const selectedOptionBookName = selectedOption.innerText;
    const targetBook = books.find((book) => book.title === selectedOptionBookName);
    const targetBookId = targetBook ? targetBook.bookId : null;
    const targetBookAuthor = targetBook ? targetBook.authorId : null;
    const targetBookImg = targetBook ? targetBook.image : null;

    const reviewInput = document.getElementById("review");
    const reviewData = {
      review: reviewInput.value,
      bookId: targetBookId,
      authorId: targetBookAuthor,
      image: targetBookImg
    };

    const submitBtn = event.target.querySelector("button[type='submit']");
    const buttonId = submitBtn.id;

    if (buttonId === "create") {
        // Check if the user has already reviewed the book
        const alreadyReviewed = reviews.find((review) => review.bookId === targetBookId);
        console.log(alreadyReviewed);
        if (alreadyReviewed) {
          alert("Book has already been reviewed. Delete or update the review.");
          location.reload();
          return;
        }

        // Create review
        fetch(`/api/v1/reviews/user/${loggedInUserId}/book/${targetBookId}`, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(reviewData),
        })
          .then((response) => response.json())
          .then((data) => {
            console.log("Review added:", data);
            alert(data);
            location.reload();
          })
          .catch((error) => {
            console.error("Error adding review:", error);
          });
      } else if (buttonId === "updateBtn") {
        const reviewId = parseInt(submitBtn.dataset.reviewId);
        fetch(`/api/v1/reviews/${reviewId}`, {
          method: "PUT",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(reviewData),
        })
          .then((response) => response.json())
          .then((data) => {
            console.log("Review updated:", data);
            const reviewCard = document.getElementById(`review-card-${reviewId}`);
            reviewCard.querySelector(".review-card-review").innerText = reviewData.review;
          })
          .catch((error) => {
            console.error("Error updating review:", error);
          });
      }

      reviewForm.reset();
    });

// DELETE REQUEST FOR BOOK REVIEWS

  function deleteReview(reviewId) {
    fetch(`/api/v1/reviews/${reviewId}`, {
      method: "DELETE",
    })
      .then((response) => response.json())
      .then((data) => {
        console.log("Review deleted:", data);
        alert(data);
        location.reload();
      })
      .catch((error) => {
        console.error("Error deleting review:", error);
      });
  }
});



