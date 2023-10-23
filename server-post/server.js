const express = require('express');
const axios = require('axios');

const app = express();
const port = 3022; 

app.use(express.json());

app.get('/health', (req, res) => {
  res.json({ status: 'Middle-tier server is running' });
});

app.post('/classB/:servicename/all/location', async (req, res) => {
  try {
    const serviceName = req.params.servicename;
    if (!req.body) {
      return res.status(400).json({ error: 'Bad Request: Missing data in the request.' });
    }

    const response = await axios.post(`http://localhost:${getServicePort(serviceName)}/location`, req.body);

    if (!response || !response.data) {
      return res.status(500).json({ error: 'Invalid response from the backend server.' });
    }

    res.json(response.data);
  } catch (error) {
    console.error(error);

    if (error.code === 'ECONNREFUSED') {
      res.status(500).json({ error: 'Backend server is unreachable.' });
    } else if (error.code === 'ETIMEDOUT') {
      res.status(500).json({ error: 'Request to the backend server timed out.' });
    } else {
      res.status(500).json({ error: 'Internal Server Error' });
    }
  }
});

function getServicePort(servicename) {
  switch (servicename) {
    case 'Books':
      return 3034;
    case 'Food':
      return 3035;
    case 'Toys':
      return 3036;
    default:
      return 0;
  }
}

app.listen(port, () => {
  console.log(`Middle-tier server is running on port ${port}`);
});
