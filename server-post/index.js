const express = require('express');
const axios = require('axios');

const app = express();
const port = 3034;

app.use(express.json());

app.get('/health', (req, res) => {
    res.json({ status: 'Middle-tier server is running' });
  });
  
app.post('/location', async (req, res) => {
    return res.status(200).json({message: "Response from backend"});
});

app.listen(port, () => {
    console.log(`Middle-tier server is running on port ${port}`);
  });