

const buttons = document.querySelectorAll('.filter-button');

document.addEventListener('DOMContentLoaded', () => {
    const filterButtons = document.querySelectorAll('.filter-button');
    const reviews = document.querySelectorAll('.review');

    console.log ("filterbuttons", filterButtons);
    console.log("reviews",reviews)
    filterButtons.forEach(button => {
        button.addEventListener('click', () => {
            const rating = button.getAttribute('data-rating');

            console.log("Giá trị data-rating được chọn là:", rating); // In giá trị ra console


            // Xóa lớp active khỏi tất cả các nút
            filterButtons.forEach(btn => btn.classList.remove('active'));
            button.classList.add('active');

            // Lọc reviews
            reviews.forEach(review => {
                console.log(review.getAttribute('data-rating'))
                if (rating === 'all' || review.getAttribute('data-rating') === rating) {
                    review.style.display = 'block'; // Hiển thị
                } else {
                    review.style.display = 'none'; // Ẩn
                }
            });
        });
    });
});

window.onload = function() {
    var quantity = parseInt(document.getElementById('quantity').innerText);  // Lấy số lượng từ phần tử HTML
    var addToCartButton = document.getElementById('add-to-cart');
    var buyNowButton = document.getElementById('buy-now');

    if (quantity === 0) {
        // Vô hiệu hóa nút nếu số lượng bằng 0
        addToCartButton.style.pointerEvents = 'none';  // Ngừng sự kiện click
        addToCartButton.style.opacity = '0.5';  // Làm mờ nút
        buyNowButton.style.pointerEvents = 'none';  // Ngừng sự kiện click
        buyNowButton.style.opacity = '0.5';  // Làm mờ nút
        buyNowButton.style.backgroundColor = '#ccc';
        addToCartButton.style.backgroundColor = '#ccc';

    }
};

document.addEventListener('DOMContentLoaded', () => {
    const stars = document.querySelectorAll('.star-rating .star');
    const hiddenRating = document.getElementById('hiddenRating');
    const reviewForm = document.getElementById('reviewForm');
    const ratingError = document.createElement('p'); // Tạo thông báo lỗi nếu chưa có
    ratingError.id = 'ratingError';
    ratingError.style.color = 'red';
    ratingError.style.display = 'none';
    ratingError.textContent = 'Vui lòng chấm điểm cho game!!!';
    reviewForm.insertBefore(ratingError, reviewForm.querySelector('.form-group:last-child')); // Chèn thông báo lỗi vào form

    stars.forEach((star, index) => {
        // Hover effect
        star.addEventListener('mouseover', () => {
            stars.forEach((s, i) => {
                s.style.color = i <= index ? '#f39c12' : '#ccc';
            });
        });

        // Reset hover effect
        star.addEventListener('mouseout', () => {
            stars.forEach(s => {
                s.style.color = s.classList.contains('selected') ? '#f39c12' : '#ccc';
            });
        });

        // Click to select
        star.addEventListener('click', () => {
            hiddenRating.value = index + 1;
            stars.forEach((s, i) => {
                s.classList.toggle('selected', i <= index);
            });
            ratingError.style.display = 'none'; // Ẩn thông báo lỗi nếu có
        });
    });

    // Kiểm tra trước khi gửi form
    reviewForm.addEventListener('submit', (e) => {
        if (!hiddenRating.value) { // Nếu chưa chọn sao
            e.preventDefault(); // Chặn gửi form
            ratingError.style.display = 'block'; // Hiển thị thông báo lỗi
        }
    });
});
