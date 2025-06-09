import { Test, TestingModule } from '@nestjs/testing';
import { INestApplication } from '@nestjs/common';
import * as request from 'supertest';
import { AppModule } from '../src/app.module';
import { expect } from "chai";

describe('App E2E Tests', () => {
  let app: INestApplication;

  before(async () => {
    const moduleFixture: TestingModule = await Test.createTestingModule({
      imports: [AppModule],
    }).compile();

    app = moduleFixture.createNestApplication();
    await app.init();
  });

  it('/products (GET)', () => {
    return request(app.getHttpServer())
      .get('/products')
      .expect(200)
      .expect((res) => {
        expect(res.body).to.be.an('array');
      });
  });

  it('/products (POST)', () => {
    return request(app.getHttpServer())
      .post('/products')
      .send({ code: 'P001', name: 'Product 1', price: 100, stock: 50 })
      .expect(201)
      .expect((res) => {
        expect(res.body).to.have.property('id');
        expect(res.body.code).to.equal('P001');
      });
  });

  it('/products/:id (GET)', () => {
    return request(app.getHttpServer())
      .get('/products/1')
      .expect(200)
      .expect((res) => {
        expect(res.body).to.have.property('id', 1);
      });
  });

  it('/products/:id (PUT)', () => {
    return request(app.getHttpServer())
      .put('/products/1')
      .send({ name: 'Updated Product 1', price: 150 })
      .expect(200)
      .expect((res) => {
        expect(res.body).to.have.property('name', 'Updated Product 1');
      });
  });

  it('/products/:id (DELETE)', () => {
    return request(app.getHttpServer())
      .delete('/products/1')
      .expect(204);
  });

  after(async () => {
    await app.close();
  });
});