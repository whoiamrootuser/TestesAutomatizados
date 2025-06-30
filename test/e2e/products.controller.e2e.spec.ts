import { expect } from 'chai';
import sinon, { SinonStub } from 'sinon';
import { ProductsController } from '../../src/products/products.controller';
import { ProductsService } from '../../src/products/products.service';
import { Product } from '../../src/products/products.entity';
import { Test, TestingModule } from '@nestjs/testing';
import request from 'supertest';
import { INestApplication } from '@nestjs/common';

describe('ProductsController', () => {
  let testingModule: TestingModule;
  let productsController: ProductsController;
  let productsService: sinon.SinonStubbedInstance<ProductsService>;

  let app: INestApplication;

  before(async () => {
    testingModule = await Test.createTestingModule({
      controllers: [ProductsController],
      providers: [
        {
          provide: ProductsService,
          useValue: sinon.createStubInstance(ProductsService),
        },
      ],
    }).compile();

    app = testingModule.createNestApplication();
    await app.init();

    productsController = testingModule.get<ProductsController>(ProductsController);
    productsService = testingModule.get(ProductsService);
  });

  after(async () => {
    await app.close();
  });

  describe('GET /products', () => {
    it('should return an array of products', async () => {
      const mockProducts: Product[] = [
        { id: 1, name: 'Product 1', price: 10, code: 'P001', stock: 100 },
        { id: 2, name: 'Product 2', price: 20, code: 'P002', stock: 200 },
      ];
      productsService.findAll.resolves(mockProducts);

      const response = await request(app.getHttpServer()).get('/products');
      expect(response.status).to.equal(200);
      expect(response.body).to.deep.equal(mockProducts);
    });
  });

  describe('GET /products/:id', () => {
    it('should return a product by id', async () => {
      const mockProduct: Product = { id: 1, name: 'Product 1', price: 10, code: 'P001', stock: 100 };
      productsService.findOne.resolves(mockProduct);

      const response = await request(app.getHttpServer()).get('/products/1');
      expect(response.status).to.equal(200);
      expect(response.body).to.deep.equal(mockProduct);
    });
  });

  describe('POST /products', () => {
    it('should create a product', async () => {
      const mockProduct: Product = { id: 1, name: 'Product 1', price: 10, code: 'P001', stock: 100 };
      productsService.create.resolves(mockProduct);

      const response = await request(app.getHttpServer())
        .post('/products')
        .send(mockProduct);
      expect(response.status).to.equal(201);
      expect(response.body).to.deep.equal(mockProduct);
    });
  });

  describe('PUT /products/:id', () => {
    it('should update a product', async () => {
      const mockProduct: Product = { id: 1, name: 'Updated Product', price: 15, code: 'P001', stock: 150 };
      productsService.update.resolves(mockProduct);

      const response = await request(app.getHttpServer())
        .put('/products/1')
        .send(mockProduct);
      expect(response.status).to.equal(200);
      expect(response.body).to.deep.equal(mockProduct);
    });
  });

  describe('DELETE /products/:id', () => {
    it('should delete a product', async () => {
      productsService.remove.resolves();

      const response = await request(app.getHttpServer()).delete('/products/1');
      expect(response.status).to.equal(204);
      expect(response.body).to.deep.equal({});
    });
  });
});