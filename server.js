const express = require('express');
const axios = require('axios');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
app.use(cors());
app.use(bodyParser.json());

const IRCTC_API_KEY = 'cb78bdba59msh15f0f8b4fbebb42p1c9975jsneda949af491a'; // Your API key

// In-memory storage for simplicity
let bookings = [];
let users = [{ id: 1, username: 'test', password: 'test', email: 'test@example.com' }]; // Mock users

// API Endpoint to search trains using IRCTC API
app.post('/api/search-trains', (req, res) => {
    const { from, to, date } = req.body;

    const options = {
        method: 'GET',
        url: 'https://irctc1.p.rapidapi.com/api/v1/searchTrain',
        params: { fromStationCode: from, toStationCode: to, date: date },
        headers: {
            'X-RapidAPI-Key': IRCTC_API_KEY,
            'X-RapidAPI-Host': 'irctc1.p.rapidapi.com'
        }
    };

    axios.request(options).then((response) => {
        res.json(response.data);
    }).catch((error) => {
        console.error('Error fetching train data from IRCTC API:', error);
        res.status(500).send('Error fetching train data from IRCTC API');
    });
});

// API Endpoint to book a train (stored in-memory)
app.post('/api/book-train', (req, res) => {
    const { userId, trainId, seatsBooked } = req.body;

    const newBooking = {
        id: bookings.length + 1,
        userId: userId,
        trainId: trainId,
        seatsBooked: seatsBooked,
        bookingDate: new Date()
    };

    bookings.push(newBooking);  // Store in-memory

    res.json({ success: true, bookingId: newBooking.id });
});

// API Endpoint to get bookings
app.get('/api/bookings', (req, res) => {
    res.json(bookings);  // Return all bookings
});

// Start the server
app.listen(5000, () => {
    console.log('Server running on http://localhost:5000');
});
