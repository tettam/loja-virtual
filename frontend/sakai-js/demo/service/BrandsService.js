import axios from "axios";
import { ServiceBase } from './ServiceBase';

export class BrandsService extends ServiceBase {
  
  constructor(){
    super('brands')
  }
}