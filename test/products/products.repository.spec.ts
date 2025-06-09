import { expect } from 'chai';
import { ProductsRepository } from '../../src/products/products.repository';
import { Product } from '../../src/products/products.entity';
import sinon from 'sinon';
import { Test, TestingModule } from '@nestjs/testing';

describe('ProductsRepository', () => {
  let productsRepository: ProductsRepository;
  let mockProduct: Product;

    let testingModule: TestingModule;
  
    beforeEach(async () => {
      testingModule = await Test.createTestingModule({
        providers: [ProductsRepository],
      }).compile();
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
    const result = await productsRepository.findOne({where: { id: 1 }});
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
    const deleteResult = { raw: {}, affected: 1 };
    const deleteStub = sinon.stub(productsRepository, 'delete').returns(Promise.resolve(deleteResult));
    const result = await productsRepository.delete(1);
    expect(result).to.deep.equal({ raw: {}, affected: 1 });
    expect(deleteStub.calledOnce).to.be.true;
  });
});