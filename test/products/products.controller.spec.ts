import { expect } from 'chai';
import { SinonStub, SinonSandbox, createSandbox } from 'sinon';
import { ProductsController } from '../../src/products/products.controller';
import { ProductsService } from '../../src/products/products.service';
import { Product } from '../../src/products/products.entity';

describe('ProductsController', () => {
  let productsController: ProductsController;
  let productsService: ProductsService;
  let sandbox: SinonSandbox;

  beforeEach(() => {
    sandbox = createSandbox();
    productsService = new ProductsService(null); // Mock the repository if needed
    productsController = new ProductsController(productsService);
  });

  afterEach(() => {
    sandbox.restore();
  });

  describe('createProduct', () => {
    it('should create a product', async () => {
      const productData: Product = { id: 1, code: 'P001', name: 'Product 1', price: 100, stock: 10 };
      const createStub: SinonStub = sandbox.stub(productsService, 'create').resolves(productData);

      const result = await productsController.createProduct(productData);

      expect(result).to.deep.equal(productData);
      expect(createStub.calledOnce).to.be.true;
    });
  });

  describe('getAllProducts', () => {
    it('should return an array of products', async () => {
      const products: Product[] = [
        { id: 1, code: 'P001', name: 'Product 1', price: 100, stock: 10 },
        { id: 2, code: 'P002', name: 'Product 2', price: 200, stock: 20 },
      ];
      const findAllStub: SinonStub = sandbox.stub(productsService, 'findAll').resolves(products);

      const result = await productsController.getAllProducts();

      expect(result).to.deep.equal(products);
      expect(findAllStub.calledOnce).to.be.true;
    });
  });

  describe('getProductById', () => {
    it('should return a product by id', async () => {
      const product: Product = { id: 1, code: 'P001', name: 'Product 1', price: 100, stock: 10 };
      const findOneStub: SinonStub = sandbox.stub(productsService, 'findOne').resolves(product);

      const result = await productsController.getProductById(1);

      expect(result).to.deep.equal(product);
      expect(findOneStub.calledOnceWith(1)).to.be.true;
    });
  });

  describe('updateProduct', () => {
    it('should update a product', async () => {
      const productData: Product = { id: 1, code: 'P001', name: 'Updated Product', price: 150, stock: 5 };
      const updateStub: SinonStub = sandbox.stub(productsService, 'update').resolves(productData);

      const result = await productsController.updateProduct(1, productData);

      expect(result).to.deep.equal(productData);
      expect(updateStub.calledOnceWith(1, productData)).to.be.true;
    });
  });

  describe('deleteProduct', () => {
    it('should delete a product', async () => {
      const deleteStub: SinonStub = sandbox.stub(productsService, 'remove').resolves(undefined);

      await productsController.deleteProduct(1);

      expect(deleteStub.calledOnceWith(1)).to.be.true;
    });
  });
});