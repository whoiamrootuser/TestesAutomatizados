import { Repository } from 'typeorm';
import { Product } from './products.entity';
import { Injectable } from '@nestjs/common';

@Injectable()
export class ProductsRepository extends Repository<Product> {
}