/*

window.addEventListener('DOMContentLoaded', () => {
    // Fetch the logged in user information and update the navbar
    const loggedInUserElement = document.getElementById('logged-in-user');
    const loggedInUser = getCurrentUser(); // Replace with your logic to get the logged in user
    loggedInUserElement.textContent = `Logged in as ${loggedInUser}`;

    // Fetch and display the book list
    const bookListElement = document.getElementById('book-list');
    fetchBookList()
        .then(books => {
            books.forEach(book => {
                const bookElement = document.createElement('div');
                bookElement.textContent = book.title;
                bookListElement.appendChild(bookElement);
            });
        })
        .catch(error => {
            console.error('Error fetching book list:', error);
        });

    // Fetch and display the book reviews
    const bookReviewsElement = document.getElementById('book-reviews');
    fetchBookReviews()
        .then(reviews => {
            reviews.forEach(review => {
                const reviewElement = document.createElement('div');
                reviewElement.classList.add('book-card');
                reviewElement.innerHTML = `
                    <img src="${review.book.coverImageUrl}" alt="Book Cover">
                    <p>${review.review}</p>
                    <div class="rating">${getStarRating(review.rating)}</div>
                `;
                bookReviewsElement.appendChild(reviewElement);
            });
        })
        .catch(error => {
            console.error('Error fetching book reviews:', error);
        });
});

// Replace with your logic to fetch the logged in user
function getCurrentUser() {
    return 'John Doe';
}

// Replace with your logic to fetch the book list
function fetchBookList() {
    return fetch('/api/books')
        .then(response => response.json())
        .then(data => data.books);
}

// Replace with your logic to fetch the book reviews
function fetchBookReviews() {
    return fetch('/api/reviews')
        .then(response => response.json())
        .then(data => data.reviews);
}

// Helper function to generate star rating HTML
function getStarRating(rating) {
    const starCount = 5;
    const filledStar = '<i class="fas fa-star"></i>';
    const emptyStar = '<i class="far fa-star"></i>';

    const filledStars = filledStar.repeat(rating);
    const emptyStars = emptyStar.repeat(starCount - rating);

    return filledStars + emptyStars;
}


























*/