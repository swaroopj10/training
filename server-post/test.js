const ClassBRestController = require('./ClassBRestController');
const axios = require('axios');
const axiosMock = require('axios-mock-adapter');

describe('ClassBRestController', () => {
  let api;
  let axiosMockInstance;

  beforeAll(() => {
    api = new ClassBRestController();
    axiosMockInstance = new axiosMock(api.axios);
  });

  afterAll(() => {
    axiosMockInstance.restore();
  });

  describe('getAllBooks', () => {
    it('should return books data for a valid location', async () => {
      axiosMockInstance.onGet('http://localhost:3034/classB/books/all/US-NC').reply(200, { data: 'books data' });
      const req = { params: { location: 'US-NC' } };
      const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };

      await api.getAllBooks(req, res);

      expect(res.status).toHaveBeenCalledWith(200);
      expect(res.status().json).toHaveBeenCalledWith({ data: 'books data' });
    });

    it('should return a 500 status for an error during API request', async () => {
      axiosMockInstance.onGet('http://localhost:3034/classB/books/all/US-NC').reply(500);
      const req = { params: { location: 'US-NC' } };
      const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };

      await api.getAllBooks(req, res);

      expect(res.status).toHaveBeenCalledWith(500);
      expect(res.status().json).toHaveBeenCalledWith({ error: 'An error occurred while fetching data from the API.' });
    });

    it('should return a 404 status for an invalid location', async () => {
      const req = { params: { location: 'Invalid' } };
      const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };

      await api.getAllBooks(req, res);

      expect(res.status).toHaveBeenCalledWith(404);
      expect(res.status().json).toHaveBeenCalledWith({ error: 'An error occurred while fetching data from the API - Invalid Location' });
    });
  });

  describe('getAllDvds', () => {
    it('should return DVDs data for a valid location', async () => {
      axiosMockInstance.onGet('http://localhost:3035/classB/dvd/all/US-NC').reply(200, { data: 'DVDs data' });
      const req = { params: { location: 'US-NC' } };
      const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };

      await api.getAllDvds(req, res);

      expect(res.status).toHaveBeenCalledWith(200);
      expect(res.status().json).toHaveBeenCalledWith({ data: 'DVDs data' });
    });

    it('should return a 500 status for an error during API request', async () => {
      axiosMockInstance.onGet('http://localhost:3035/classB/dvd/all/US-NC').reply(500);
      const req = { params: { location: 'US-NC' } };
      const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };

      await api.getAllDvds(req, res);

      expect(res.status).toHaveBeenCalledWith(500);
      expect(res.status().json).toHaveBeenCalledWith({ error: 'An error occurred while fetching data from the API.' });
    });

    it('should return a 404 status for an invalid location', async () => {
      const req = { params: { location: 'Invalid' } };
      const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };

      await api.getAllDvds(req, res);

      expect(res.status).toHaveBeenCalledWith(404);
      expect(res.status().json).toHaveBeenCalledWith({ error: 'An error occurred while fetching data from the API - Invalid Location' });
    });
  });

  describe('getAllLaptops', () => {
    it('should return books data for a valid location', async () => {
        axiosMockInstance.onGet('http://localhost:3036/classB/laptops/all/US-NC').reply(200, { data: 'books data' });
        const req = { params: { location: 'US-NC' } };
        const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };
  
        await api.getAllBooks(req, res);
  
        expect(res.status).toHaveBeenCalledWith(200);
        expect(res.status().json).toHaveBeenCalledWith({ data: 'books data' });
      });
  
      it('should return a 500 status for an error during API request', async () => {
        axiosMockInstance.onGet('http://localhost:3036/classB/laptops/all/US-NC').reply(500);
        const req = { params: { location: 'US-NC' } };
        const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };
  
        await api.getAllBooks(req, res);
  
        expect(res.status).toHaveBeenCalledWith(500);
        expect(res.status().json).toHaveBeenCalledWith({ error: 'An error occurred while fetching data from the API.' });
      });
  
      it('should return a 404 status for an invalid location', async () => {
        const req = { params: { location: 'Invalid' } };
        const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };
  
        await api.getAllBooks(req, res);
  
        expect(res.status).toHaveBeenCalledWith(404);
        expect(res.status().json).toHaveBeenCalledWith({ error: 'An error occurred while fetching data from the API - Invalid Location' });
      });
  });

  describe('getBooksTeam', () => {
    it('should return books data for a valid location', async () => {
        axiosMockInstance.onGet('http://localhost:3034/classB/books/team').reply(200, { data: 'books data' });
        const req = { params: { location: 'US-NC' } };
        const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };
  
        await api.getAllBooks(req, res);
  
        expect(res.status).toHaveBeenCalledWith(200);
        expect(res.status().json).toHaveBeenCalledWith({ data: 'books data' });
      });
  
      it('should return a 500 status for an error during API request', async () => {
        axiosMockInstance.onGet('http://localhost:3034/classB/books/team').reply(500);
        const req = { params: { location: 'US-NC' } };
        const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };
  
        await api.getAllBooks(req, res);
  
        expect(res.status).toHaveBeenCalledWith(500);
        expect(res.status().json).toHaveBeenCalledWith({ error: 'An error occurred while fetching data from the API.' });
      });
  
      it('should return a 404 status for an invalid location', async () => {
        const req = { params: { location: 'Invalid' } };
        const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };
  
        await api.getAllBooks(req, res);
  
        expect(res.status).toHaveBeenCalledWith(404);
        expect(res.status().json).toHaveBeenCalledWith({ error: 'An error occurred while fetching data from the API - Invalid Location' });
      });
  });

  describe('getDvdsTeam', () => {
    it('should return books data for a valid location', async () => {
        axiosMockInstance.onGet('http://localhost:3035/classB/dvds/team').reply(200, { data: 'books data' });
        const req = { params: { location: 'US-NC' } };
        const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };
  
        await api.getAllBooks(req, res);
  
        expect(res.status).toHaveBeenCalledWith(200);
        expect(res.status().json).toHaveBeenCalledWith({ data: 'books data' });
      });
  
      it('should return a 500 status for an error during API request', async () => {
        axiosMockInstance.onGet('http://localhost:3035/classB/dvds/team').reply(500);
        const req = { params: { location: 'US-NC' } };
        const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };
  
        await api.getAllBooks(req, res);
  
        expect(res.status).toHaveBeenCalledWith(500);
        expect(res.status().json).toHaveBeenCalledWith({ error: 'An error occurred while fetching data from the API.' });
      });
  
      it('should return a 404 status for an invalid location', async () => {
        const req = { params: { location: 'Invalid' } };
        const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };
  
        await api.getAllBooks(req, res);
  
        expect(res.status).toHaveBeenCalledWith(404);
        expect(res.status().json).toHaveBeenCalledWith({ error: 'An error occurred while fetching data from the API - Invalid Location' });
      });
  });

  describe('getLaptopsTeam', () => {
    it('should return books data for a valid location', async () => {
        axiosMockInstance.onGet('http://localhost:3036/classB/laptops/team').reply(200, { data: 'books data' });
        const req = { params: { location: 'US-NC' } };
        const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };
  
        await api.getAllBooks(req, res);
  
        expect(res.status).toHaveBeenCalledWith(200);
        expect(res.status().json).toHaveBeenCalledWith({ data: 'books data' });
      });
  
      it('should return a 500 status for an error during API request', async () => {
        axiosMockInstance.onGet('http://localhost:3036/classB/laptops/team').reply(500);
        const req = { params: { location: 'US-NC' } };
        const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };
  
        await api.getAllBooks(req, res);
  
        expect(res.status).toHaveBeenCalledWith(500);
        expect(res.status().json).toHaveBeenCalledWith({ error: 'An error occurred while fetching data from the API.' });
      });
  
      it('should return a 404 status for an invalid location', async () => {
        const req = { params: { location: 'Invalid' } };
        const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };
  
        await api.getAllBooks(req, res);
  
        expect(res.status).toHaveBeenCalledWith(404);
        expect(res.status().json).toHaveBeenCalledWith({ error: 'An error occurred while fetching data from the API - Invalid Location' });
      });
  });

  describe('getClassBTeam', () => {
    it('should return team data from a file', async () => {
      const teamData = [{ name: 'Team Class B' }];
      spyOn(api, 'fs').and.returnValue({
        readFileSync: jasmine.createSpy().and.returnValue(JSON.stringify(teamData)),
      });
      const req = {};
      const res = { setHeader: jasmine.createSpy(), end: jasmine.createSpy() };

      api.getClassBTeam(req, res);

      expect(res.setHeader).toHaveBeenCalledWith('content-type', 'application/json');
      expect(res.end).toHaveBeenCalledWith(JSON.stringify(teamData));
    });

    it('should return a 500 status for an error while reading team data', async () => {
      spyOn(api, 'fs').and.returnValue({
        readFileSync: jasmine.createSpy().and.throwError('File read error'),
      });
      const req = {};
      const res = { status: jasmine.createSpy().and.returnValue({ json: jasmine.createSpy() }) };

      api.getClassBTeam(req, res);

      expect(res.status).toHaveBeenCalledWith(500);
      expect(res.status().json).toHaveBeenCalledWith({ error: 'An error occurred while fetching data from the API.' });
    });
  });
});
