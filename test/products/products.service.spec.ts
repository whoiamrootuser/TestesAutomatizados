import { expect } from 'chai';
import { ProductsService } from '../../src/products/products.service';
import { ProductsRepository } from '../../src/products/products.repository';
import { Product } from '../../src/products/products.entity';
import sinon from 'sinon';

describe('ProductsService', () => {
  let productsService: ProductsService;
  let productsRepository: sinon.SinonStubbedInstance<ProductsRepository>;

  beforeEach(() => {
    productsRepository = sinon.createStubInstance(ProductsRepository);
    productsService = new ProductsService(productsRepository as any);
  });

  afterEach(() => {
    sinon.restore();
  });

  describe('create', () => {
    it('should create a product', async () => {
      const productData = { code: 'P001', name: 'Product 1', price: 100, stock: 10 };
      const product = new Product();
      Object.assign(product, productData);
      productsRepository.save.resolves(product);

      const result = await productsService.create(productData);
      expect(result).to.deep.equal(product);
      expect(productsRepository.save.calledOnce).to.be.true;
    });
  });

  describe('findAll', () => {
    it('should return an array of products', async () => {
      const productArray = [new Product(), new Product()];
      productsRepository.find.resolves(productArray);

      const result = await productsService.findAll();
      expect(result).to.deep.equal(productArray);
      expect(productsRepository.find.calledOnce).to.be.true;
    });
  });

  describe('findOne', () => {
    it('should return a product by id', async () => {
      const product = new Product();
      product.id = 1;
      productsRepository.findOne.resolves(product);

      const result = await productsService.findOne(1);
      expect(result).to.deep.equal(product);
      expect(productsRepository.findOne.calledOnceWith(1)).to.be.true;
    });
  });

  describe('update', () => {
    it('should update a product', async () => {
      const productData = { id: 1, code: 'P001', name: 'Updated Product', price: 150, stock: 5 };
      const product = new Product();
      Object.assign(product, productData);
      productsRepository.findOne.resolves(product);
      productsRepository.save.resolves(product);

      const result = await productsService.update(1, productData);
      expect(result).to.deep.equal(product);
      expect(productsRepository.findOne.calledOnceWith(1)).to.be.true;
      expect(productsRepository.save.calledOnce).to.be.true;
    });
  });

  describe('remove', () => {
    it('should remove a product', async () => {
      const product = new Product();
      product.id = 1;
      productsRepository.findOne.resolves(product);
      productsRepository.remove.resolves(product);

      const result = await productsService.remove(1);
      expect(result).to.deep.equal(product);
      expect(productsRepository.findOne.calledOnceWith(1)).to.be.true;
      expect(productsRepository.remove.calledOnce).to.be.true;
    });
  });
});