const request = require('supertest');
const express = require('express');
const ClassBRestController = require('./your-rest-controller-file'); // Replace with the correct path

describe('ClassB API', () => {
    let app;

    beforeAll(() => {
        app = express();
        const api = new ClassBRestController();
        api.start();
    });

    afterAll(() => {
        api.stop();
    });

    it('should return a list of books for a valid location with a 200 status code', (done) => {
        request(app)
            .get('/classB/books/all/US-NC') // Replace with a valid location
            .expect(200)
            .end((err, res) => {
                if (err) return done(err);
                expect(res.body).toBeDefined(); // Check if the response body exists
                done();
            });
    });

    it('should return a list of DVDs for a valid location with a 200 status code', (done) => {
        request(app)
            .get('/classB/dvds/all/IN') 
            .expect(200)
            .end((err, res) => {
                if (err) return done(err);
                expect(res.body).toBeDefined(); 
                done();
            });
    });

    it('should return a list of laptops for a valid location with a 200 status code', (done) => {
        request(app)
            .get('/classB/laptops/all/IRE') // Replace with a valid location
            .expect(200)
            .end((err, res) => {
                if (err) return done(err);
                expect(res.body).toBeDefined(); // Check if the response body exists
                done();
            });
    });

    it('should return a list of team books with a 200 status code', (done) => {
        request(app)
            .get('/classB/books/team')
            .expect(200)
            .end((err, res) => {
                if (err) return done(err);
                expect(res.body).toBeDefined(); // Check if the response body exists
                done();
            });
    });

    it('should return a list of team DVDs with a 200 status code', (done) => {
        request(app)
            .get('/classB/dvds/team')
            .expect(200)
            .end((err, res) => {
                if (err) return done(err);
                expect(res.body).toBeDefined(); // Check if the response body exists
                done();
            });
    });

    it('should return a list of team laptops with a 200 status code', (done) => {
        request(app)
            .get('/classB/laptops/team')
            .expect(200)
            .end((err, res) => {
                if (err) return done(err);
                expect(res.body).toBeDefined(); // Check if the response body exists
                done();
            });
    });

    it('should return a list of Class B team with a 200 status code', (done) => {
        request(app)
            .get('/classB/team')
            .expect(200)
            .end((err, res) => {
                if (err) return done(err);
                expect(res.body).toBeDefined(); // Check if the response body exists
                done();
            });
    });

    // Add more positive test cases for other methods
});
