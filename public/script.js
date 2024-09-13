document.getElementById('search-form').addEventListener('submit', function(event) {
    event.preventDefault();

    const from = document.getElementById('from').value;
    const to = document.getElementById('to').value;
    const date = document.getElementById('date').value;

    // Send search query to server
    fetch('http://localhost:5000/api/search-trains', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ from, to, date })
    })
    .then(response => response.json())
    .then(data => {
        console.log('API Response:', data);  // Log the data to see the structure
        
        const trainList = document.getElementById('train-list');
        trainList.innerHTML = ''; // Clear previous results

        // Check if data is an array and then proceed
        if (Array.isArray(data)) {
            data.forEach(train => {
                const listItem = document.createElement('li');
                listItem.innerHTML = `
                    Train: ${train.train_name} (${train.train_number}) - Route: ${train.route}
                    <button onclick="bookTrain(${train.id})">Book</button>
                `;
                trainList.appendChild(listItem);
            });
        } else {
            trainList.innerHTML = '<li>No trains found</li>';
        }
    })
    .catch(error => {
        console.error('Error:', error);
        const trainList = document.getElementById('train-list');
        trainList.innerHTML = '<li class="error-message">Error fetching trains. Please try again later.</li>';
    });
});

function bookTrain(trainId) {
    const userId = 1; // Assuming logged-in user (mock data)
    const seatsBooked = 1; // Assume booking 1 seat

    fetch('http://localhost:5000/api/book-train', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ userId, trainId, seatsBooked })
    })
    .then(response => response.json())
    .then(data => {
        if (data.success) {
            alert(`Booking successful! Booking ID: ${data.bookingId}`);
        } else {
            alert('Booking failed');
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
}
