import { expect } from 'chai';
import { ProductsRepository } from '../../src/products/products.repository';
import { Product } from '../../src/products/products.entity';
import sinon from 'sinon';

describe('ProductsRepository', () => {
  let productsRepository: ProductsRepository;
  let mockProduct: Product;

  beforeEach(() => {
    productsRepository = new ProductsRepository();
    mockProduct = new Product();
    mockProduct.id = 1;
    mockProduct.code = 'P001';
    mockProduct.name = 'Product 1';
    mockProduct.price = 100;
    mockProduct.stock = 50;
  });

  afterEach(() => {
    sinon.restore();
  });

  it('should create a product', async () => {
    const saveStub = sinon.stub(productsRepository, 'save').returns(Promise.resolve(mockProduct));
    const result = await productsRepository.save(mockProduct);
    expect(result).to.deep.equal(mockProduct);
    expect(saveStub.calledOnce).to.be.true;
  });

  it('should find all products', async () => {
    const findStub = sinon.stub(productsRepository, 'find').returns(Promise.resolve([mockProduct]));
    const result = await productsRepository.find();
    expect(result).to.deep.equal([mockProduct]);
    expect(findStub.calledOnce).to.be.true;
  });

  it('should find a product by id', async () => {
    const findOneStub = sinon.stub(productsRepository, 'findOne').returns(Promise.resolve(mockProduct));
    const result = await productsRepository.findOne(1);
    expect(result).to.deep.equal(mockProduct);
    expect(findOneStub.calledOnce).to.be.true;
  });

  it('should update a product', async () => {
    const updateStub = sinon.stub(productsRepository, 'save').returns(Promise.resolve(mockProduct));
    const result = await productsRepository.save(mockProduct);
    expect(result).to.deep.equal(mockProduct);
    expect(updateStub.calledOnce).to.be.true;
  });

  it('should delete a product', async () => {
    const deleteStub = sinon.stub(productsRepository, 'delete').returns(Promise.resolve({ affected: 1 }));
    const result = await productsRepository.delete(1);
    expect(result).to.deep.equal({ affected: 1 });
    expect(deleteStub.calledOnce).to.be.true;
  });
});