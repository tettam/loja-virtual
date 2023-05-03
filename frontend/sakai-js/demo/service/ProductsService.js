import axios from "axios";
import { ServiceBase } from './ServiceBase';

export class ProductsService extends ServiceBase {
  
  constructor(){
    super('products')
  }
}